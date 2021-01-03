/* (C)2020-2021 */
package org.frc5687.diffswerve.robot;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import org.frc5687.diffswerve.robot.commands.*;
import org.frc5687.diffswerve.robot.commands.OutliersCommand;
import org.frc5687.diffswerve.robot.subsystems.*;
import org.frc5687.diffswerve.robot.util.*;

public class RobotContainer extends OutliersContainer {

    private OI _oi;

    private DriveTrain _driveTrain;

    public RobotContainer(Robot robot, IdentityMode identityMode) {
        super(identityMode);
    }

    public void init() {
        _oi = new OI();
        _driveTrain = new DriveTrain(this);

        _oi.initializeButtons(_driveTrain);
        setDefaultCommand(_driveTrain, new DriveSwerveModule(_driveTrain, _oi));
    }

    public void periodic() {}

    public void disabledPeriodic() {}

    @Override
    public void disabledInit() {
        _driveTrain.setBottomLeftModuleVector(new Vector2d(0, 0));
    }

    @Override
    public void teleopInit() {}

    @Override
    public void autonomousInit() {}

    private void setDefaultCommand(OutliersSubsystem subSystem, OutliersCommand command) {
        if (subSystem == null || command == null) {
            return;
        }
        CommandScheduler s = CommandScheduler.getInstance();
        s.setDefaultCommand(subSystem, command);
    }

    @Override
    public void updateDashboard() {

        _driveTrain.updateDashboard();
    }
}
