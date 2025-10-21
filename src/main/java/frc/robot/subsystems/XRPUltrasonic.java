package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class XRPUltrasonic extends SubsystemBase{
    private static final AnalogInput m_analogInput = new AnalogInput(2);

    public XRPUltrasonic() {}

    public void alert(XRPDrivetrain drivetrain) {
        if (m_analogInput.getValue() <= 150) {
            System.out.println("close");
            drivetrain.tankDrive(-1, -1);
        }
    }

    public double getDistance() {
        return m_analogInput.getValue();
    }

    public Command alertClose(XRPDrivetrain drivetrain) {
        return Commands.run(() -> alert(drivetrain));
    }
}
