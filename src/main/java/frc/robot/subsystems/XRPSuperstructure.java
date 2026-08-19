package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

public class XRPSuperstructure {
    private final XRPDrivetrain m_xrpDrivetrain;

    private final XRPArm m_xrpArm;

    private final XRPUltrasonic m_xrpUltrasonic;

    private final XRPReflectance m_xrpReflectance;

    public XRPSuperstructure(XRPDrivetrain xrpDrivetrain, XRPArm xrpArm, XRPUltrasonic xrpUltrasonic, XRPReflectance xrpReflectance) {
        m_xrpDrivetrain = xrpDrivetrain;
        m_xrpArm = xrpArm;
        m_xrpUltrasonic = xrpUltrasonic;
        m_xrpReflectance = xrpReflectance;
    }

    public Command drive(DoubleSupplier supp_leftVal, DoubleSupplier supp_rightVal) {
        return m_xrpDrivetrain.tankDriveCmd(supp_leftVal, supp_rightVal);
    }

    // public Command keepDistance() {
    //     if (m_xrpUltrasonic.getDistance() <= 150) {
    //         System.out.println("Too Close!");
    //         return m_xrpDrivetrain.tankDriveCmd(() -> -1, () -> -1);
    //     }
    //     return Commands.none();
    // }
}
