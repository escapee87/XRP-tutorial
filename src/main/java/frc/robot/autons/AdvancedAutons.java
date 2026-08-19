package frc.robot.autons;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.XRPDrivetrain;
import frc.robot.subsystems.XRPReflectance;
import frc.robot.subsystems.XRPUltrasonic;

public class AdvancedAutons {

    public static Command goUntilLine(XRPDrivetrain drivetrain, XRPReflectance reflectance) {
        BooleanSupplier darkSupplier = () -> reflectance.getLeftColor() <= 3000 && reflectance.getRightColor() <= 3000;

        return Commands.deadline(
            Commands.waitUntil(darkSupplier),
            Commands.run(() -> drivetrain.tankDrive(0.5, 0.5)),
            Commands.run(() -> System.out.println(reflectance.getLeftColor() + " " + reflectance.getRightColor()))
        );
    }

    public static Command spinDetect(XRPDrivetrain drivetrain, XRPUltrasonic ultrasonic) {
        BooleanSupplier closeSupplier = () -> ultrasonic.getDistance() < 300;

        return Commands.deadline(
            Commands.waitUntil(closeSupplier),
            Commands.run(() -> drivetrain.tankDrive(-0.5, 0.5))
        );
    }

    public static Command goUntilBlock(XRPDrivetrain drivetrain, XRPUltrasonic ultrasonic) {
        BooleanSupplier blockSupplier = () -> ultrasonic.getDistance() < 100;

        return Commands.deadline(
            Commands.waitUntil(blockSupplier),
            Commands.run(() -> drivetrain.tankDrive(0.7, 0.7))
        );
    }

    public static Command getBlock(XRPDrivetrain drivetrain, XRPUltrasonic ultrasonic) {
        return Commands.sequence(
            spinDetect(drivetrain, ultrasonic),
            goUntilBlock(drivetrain, ultrasonic),
            Commands.deadline(
                Commands.waitSeconds(0.5),
                Commands.run(() -> drivetrain.tankDrive(0.7, 0.7))
            ),
            Commands.deadline(
                Commands.waitSeconds(2),
                Commands.run(() -> drivetrain.tankDrive(0., 0.7))
            )
        );
    }
}
