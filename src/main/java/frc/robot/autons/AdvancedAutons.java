package frc.robot.autons;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.XRPDrivetrain;
import frc.robot.subsystems.XRPReflectance;

public class AdvancedAutons {

    public static Command goUntilLine(XRPDrivetrain drivetrain, XRPReflectance reflectance) {
        BooleanSupplier darkSupplier = () -> reflectance.getLeftColor() <= 3000 && reflectance.getRightColor() <= 3000;

        return Commands.deadline(
            Commands.waitUntil(darkSupplier),
            Commands.run(() -> drivetrain.tankDrive(0.5, 0.5)),
            Commands.run(() -> System.out.println(reflectance.getLeftColor() + " " + reflectance.getRightColor()))
        );
    }
}
