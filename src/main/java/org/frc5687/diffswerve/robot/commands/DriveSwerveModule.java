package org.frc5687.diffswerve.robot.commands;

import org.frc5687.diffswerve.robot.subsystems.DiffSwerveModule;
import org.frc5687.diffswerve.robot.subsystems.DriveTrain;

import static org.frc5687.diffswerve.robot.subsystems.DiffSwerveModule.ModuleID.FrontRight;

public class DriveSwerveModule extends OutliersCommand {

    private DriveTrain _driveTrain;


    public DriveSwerveModule(DriveTrain driveTrain){
        _driveTrain = driveTrain;
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        super.execute();
        _driveTrain.setFrontRightSpeeds(0.1,0.1);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
