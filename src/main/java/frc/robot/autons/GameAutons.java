package frc.robot.autons;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.XRPDrivetrain;

public class GameAutons {

    public static Command nothing() {
        return Commands.none();
    }

    public static Command getOwnBlocksRight(XRPDrivetrain drivetrain) {
        return Commands.sequence(
            drive(drivetrain, 0.7, 0, 0.8),
            drive(drivetrain, 0.7, 0.7, 0.5),
            drive(drivetrain, 0.77, 0.9, 2.4),
            drive(drivetrain, -0.7, 0.8, 1),
            drive(drivetrain, 0.9, 0.9, 2)
        );
    }

    public static Command getOwnBlocksLeft(XRPDrivetrain drivetrain) {
        return Commands.sequence(
            drive(drivetrain, 0, 0.7, 0.7),
            drive(drivetrain, 0.75, 0.7, 0.5),
            drive(drivetrain, 0.95, 0.8, 2.3),
            drive(drivetrain, 0.8, -0.7, 1),
            drive(drivetrain, 0.9, 0.9, 2)
        );
    }

    public static Command stealFromRailex(XRPDrivetrain drivetrain) {
        return Commands.sequence(
            drive(drivetrain, 0.7, 0, 1),
            drive(drivetrain, 0.85, 0.9, 2.8),
            drive(drivetrain, 0.2, 1, 1),
            drive(drivetrain, 1, 1, 0.7),
            drive(drivetrain, 0.2, 0.8, 0.75),
            drive(drivetrain, 1, 1, 1.8)
        );
    }

    public static Command disruptTony(XRPDrivetrain drivetrain) {
        return Commands.sequence(
            drive(drivetrain, 0, 0.7, 1),
            drive(drivetrain, 1, 0.89, 4.3)
        );
    }

    public static Command totalSabotageRight(XRPDrivetrain drivetrain) {
        return Commands.sequence(
            drive(drivetrain, 0.7, 0, 2),
            drive(drivetrain, 0.95, 1, 7)
        );
    }

    public static Command totalSabotageLeft(XRPDrivetrain drivetrain) {
        return Commands.sequence(
            drive(drivetrain, 0, 0.7, 2),
            drive(drivetrain, 1, 0.95, 7)
        );
    }
    
    private static Command drive(XRPDrivetrain drivetrain, double leftWheelSpeed, double rightWheelSpeed, double seconds) {
        return Commands.race(
                Commands.run(() -> drivetrain.tankDrive(leftWheelSpeed, rightWheelSpeed)),
                Commands.waitSeconds(seconds)
        );
    }
}
