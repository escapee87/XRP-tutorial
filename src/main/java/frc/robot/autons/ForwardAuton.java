package frc.robot.autons;

import javax.sound.sampled.SourceDataLine;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotContainer;
import frc.robot.subsystems.XRPDrivetrain;

public class ForwardAuton {
    public static Command goForward(XRPDrivetrain drivetrain, Subsystem subsystem) {
        return Commands.sequence(
            Commands.run(() -> drivetrain.tankDrive(0.7, 0.7), subsystem)
        );
    }
}
