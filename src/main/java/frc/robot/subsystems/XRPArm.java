package frc.robot.subsystems;
import edu.wpi.first.wpilibj.xrp.XRPServo;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class XRPArm extends SubsystemBase {
    private static final XRPServo m_armServo = new XRPServo(4);
    
    public XRPArm() {}
 
    public Command dropArm() {
        return Commands.run(() -> {
            m_armServo.setAngle(30);
        });
    }

    public Command liftArm() {
        return Commands.run(() -> {
            m_armServo.setAngle(180);
            System.out.println(m_armServo.getPosition());
        });
    }
}
