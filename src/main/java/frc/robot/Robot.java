// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.autons.GameAutons;
import frc.robot.subsystems.XRPArm;
import frc.robot.subsystems.XRPDrivetrain;
import frc.robot.subsystems.XRPReflectance;
import frc.robot.subsystems.XRPSuperstructure;
import frc.robot.subsystems.XRPUltrasonic;


public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private final XRPDrivetrain m_xrpDrivetrain = new XRPDrivetrain();

  private final XRPArm m_xrpArm = new XRPArm();

  private final XRPUltrasonic m_xrpUltrasonic = new XRPUltrasonic();

  private final XRPReflectance m_xrpReflectance = new XRPReflectance();

  private final XRPSuperstructure m_xrpSuperstructure = new XRPSuperstructure(
    m_xrpDrivetrain,
    m_xrpArm,
    m_xrpUltrasonic,
    m_xrpReflectance
  );

  SendableChooser<Command> m_chooser = new SendableChooser<Command>();

  private Joystick m_controller = new Joystick(0);

  private DoubleSupplier m_leftY = () -> m_controller.getRawAxis(0);
  private DoubleSupplier m_rightY = () -> m_controller.getRawAxis(1);

  private JoystickButton m_button1 = new JoystickButton(m_controller, 1);
  private JoystickButton m_button2 = new JoystickButton(m_controller, 2);
  private JoystickButton m_button3 = new JoystickButton(m_controller, 3);
  private JoystickButton m_button4 = new JoystickButton(m_controller, 4);

  
  public Robot() {
    configureButtonBindings();
    createAutonChooser();
    m_xrpArm.dropArm();
  }

  private void configureButtonBindings() {
    m_button1.whileTrue(m_xrpArm.liftArm());
    m_button2.whileTrue(m_xrpArm.dropArm());
    // m_button3.toggleOnTrue(m_xrpSuperstructure.keepDistance());
    m_button4.toggleOnTrue(m_xrpReflectance.getColor());
  }

  private void createAutonChooser() {
    // m_chooser.setDefaultOption("Nothing", SimpleAutons.nothing(m_xrpDrivetrain, m_xrpDrivetrain));
    // m_chooser.addOption("Forward", SimpleAutons.forward(m_xrpDrivetrain, m_xrpDrivetrain));
    // m_chooser.addOption("Front and Back", SimpleAutons.frontBack(m_xrpDrivetrain, m_xrpDrivetrain));
    // m_chooser.addOption("Go Until Line", AdvancedAutons.goUntilLine(m_xrpDrivetrain, m_xrpReflectance));
    // m_chooser.addOption("Get Block", AdvancedAutons.getBlock(m_xrpDrivetrain, m_xrpUltrasonic));

    m_chooser.setDefaultOption("Nothing", GameAutons.nothing());
    m_chooser.addOption("Get Blocks From Right", GameAutons.getOwnBlocksRight(m_xrpDrivetrain));
    m_chooser.addOption("Get Blocks From Left", GameAutons.getOwnBlocksLeft(m_xrpDrivetrain));
    m_chooser.addOption("Steal From Railex", GameAutons.stealFromRailex(m_xrpDrivetrain));
    m_chooser.addOption("Disrupt Tony", GameAutons.disruptTony(m_xrpDrivetrain));
    m_chooser.addOption("Total Sabotage Right", GameAutons.totalSabotageRight(m_xrpDrivetrain));
    m_chooser.addOption("Total Sabotage Left", GameAutons.totalSabotageLeft(m_xrpDrivetrain));
    SmartDashboard.putData(m_chooser);
  }

  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    CommandScheduler.getInstance().schedule(m_xrpSuperstructure.drive(m_leftY, m_rightY));
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}
}
