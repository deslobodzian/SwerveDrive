/* (C)2020-2021 */
package org.frc5687.diffswerve.robot.commands;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import org.frc5687.diffswerve.robot.util.ILoggingSource;
import org.frc5687.diffswerve.robot.util.MetricTracker;
import org.frc5687.diffswerve.robot.util.RioLogger;

public abstract class OutliersCommand extends CommandBase implements ILoggingSource {
    private MetricTracker _metricTracker;
    private Notifier _controlLoop;

    public OutliersCommand() {}

    public OutliersCommand(double timeout) {
        super.withTimeout(timeout);
    }

    @Override
    public void error(String message) {
        RioLogger.error(this, message);
    }

    @Override
    public void warn(String message) {
        RioLogger.warn(this, message);
    }

    @Override
    public void info(String message) {
        RioLogger.info(this, message);
    }

    @Override
    public void debug(String message) {
        RioLogger.debug(this, message);
    }

    public void metric(String name, String value) {
        SmartDashboard.putString(getClass().getSimpleName() + "/" + name, value);
        if (_metricTracker != null) {
            _metricTracker.put(name, value);
        }
    }

    public void metric(String name, double value) {
        SmartDashboard.putNumber(getClass().getSimpleName() + "/" + name, value);
        if (_metricTracker != null) {
            _metricTracker.put(name, value);
        }
    }

    public void metric(String name, boolean value) {
        SmartDashboard.putBoolean(getClass().getSimpleName() + "/" + name, value);
        if (_metricTracker != null) {
            _metricTracker.put(name, value);
        }
    }

    protected void logMetrics(String... metrics) {
        _metricTracker = MetricTracker.createMetricTracker(getClass().getSimpleName(), metrics);
        _metricTracker.pause();
    }

    protected void startPeriodic(double kDt) {
        try {
            _controlLoop = new Notifier(this::execute);
        } catch (Exception e) {
            e.printStackTrace();
        }
        _controlLoop.startPeriodic(kDt);
    }

    @Override
    public void initialize() {
        super.initialize();
        if (_metricTracker != null) {
            _metricTracker.resume();
        }
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        if (_controlLoop != null) {
            _controlLoop.stop();
        }
        if (_metricTracker != null) {
            _metricTracker.pause();
        }
    }

    @Override
    public void execute() {
        if (_metricTracker != null && _metricTracker.isPaused()) {
            _metricTracker.resume();
        }
    }

    protected void enableMetrics() {
        if (_metricTracker != null) {
            _metricTracker.enable();
        }
    }

    protected void disableMetrics() {
        if (_metricTracker != null) {
            _metricTracker.disable();
        }
    }

    protected void innerExecute() {}
}
