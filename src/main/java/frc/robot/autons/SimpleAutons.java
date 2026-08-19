package frc.robot.autons;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.XRPArm;
import frc.robot.subsystems.XRPDrivetrain;


public class SimpleAutons {
    public static Command nothing(XRPDrivetrain drivetrain, Subsystem subsystem) {
        return Commands.sequence(
            Commands.none()
        );
    }

    public static Command forward(XRPDrivetrain drivetrain, Subsystem subsystem) {
        return Commands.sequence(
            Commands.run(() -> drivetrain.tankDrive(0.7, 0.7), subsystem)
        );
    }
    
    public static Command frontBack(XRPDrivetrain drivetrain, Subsystem subsystem) {
        return Commands.sequence(
            Commands.race(
                Commands.run(() -> drivetrain.tankDrive(0.7, 0.7), subsystem),
                Commands.waitSeconds(3)
            ),
            Commands.race(
                Commands.run(() -> drivetrain.tankDrive(-0.7, -0.7), subsystem),
                Commands.waitSeconds(3)
            )
        );
    }

    public static Command liftArm(XRPArm arm, Subsystem subsystem) {
        return Commands.sequence(
            Commands.run(() -> arm.liftArm(), subsystem)
        );
    }
}
