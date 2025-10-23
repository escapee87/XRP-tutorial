package frc.robot.autons;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.XRPDrivetrain;

public class GameAutons {

    public static Command getOwnBlocksRight(XRPDrivetrain drivetrain) {
        return Commands.sequence(
            Commands.race(
                Commands.run(() -> drivetrain.tankDrive(0.7, 0)),
                Commands.waitSeconds(0.8)
            ),
            Commands.race(
                Commands.run(() -> drivetrain.tankDrive(0.7, 0.7)),
                Commands.waitSeconds(0.5)
            ),
            Commands.race(
                Commands.run(() -> drivetrain.tankDrive(0.8, 0.9)),
                Commands.waitSeconds(2.3)
            ),
            Commands.race(
                Commands.run(() -> drivetrain.tankDrive(-0.7, 0.8)),
                Commands.waitSeconds(1.2)
            ),
            Commands.race(
                Commands.run(() -> drivetrain.tankDrive(0.9, 0.9)),
                Commands.waitSeconds(2)
            )
        );
    }

    public static Command getOwnBlocksLeft(XRPDrivetrain drivetrain) {
        return Commands.sequence(
            Commands.race(
                Commands.run(() -> drivetrain.tankDrive(0, 0.7)),
                Commands.waitSeconds(0.7)
            ),
            Commands.race(
                Commands.run(() -> drivetrain.tankDrive(0.75, 0.7)),
                Commands.waitSeconds(0.5)
            ),
            Commands.race(
                Commands.run(() -> drivetrain.tankDrive(0.95, 0.8)),
                Commands.waitSeconds(2.3)
            ),
            Commands.race(
                Commands.run(() -> drivetrain.tankDrive(0.8, -0.7)),
                Commands.waitSeconds(1)
            ),
            Commands.race(
                Commands.run(() -> drivetrain.tankDrive(0.9, 0.9)),
                Commands.waitSeconds(2)
            )
        );
    }
}
