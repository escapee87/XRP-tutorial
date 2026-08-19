package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class XRPReflectance extends SubsystemBase{
    private static final AnalogInput m_analogInputLeft = new AnalogInput(0);
    private static final AnalogInput m_analogInputRight = new AnalogInput(1);
    
    public XRPReflectance() {}

    public int getLeftColor() {
        return m_analogInputLeft.getValue();
    }

    public int getRightColor() {
        return m_analogInputRight.getValue();
    }

    public Command getColor() {
        return Commands.run(() -> System.out.println(m_analogInputLeft.getValue() + ", " + m_analogInputRight.getValue()));
    }
}
