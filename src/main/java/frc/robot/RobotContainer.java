// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.autons.ForwardAuton;
import frc.robot.autons.FrontBackAuton;
import frc.robot.commands.ForwardCommand;
import frc.robot.subsystems.XRPDrivetrain;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final XRPDrivetrain m_xrpDrivetrain = new XRPDrivetrain();

  private final ForwardCommand m_forwardCommand = new ForwardCommand(m_xrpDrivetrain);

  private XboxController m_Controller = new XboxController(0);

  private DoubleSupplier m_leftY = () -> m_Controller.getLeftY();
  private DoubleSupplier m_rightY = () -> m_Controller.getRightY();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    
  }

  public void drive() {
    m_xrpDrivetrain.tankDrive(m_leftY.getAsDouble(), m_rightY.getAsDouble());
    System.out.println(m_leftY.getAsDouble() + ", " + m_rightY.getAsDouble());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return FrontBackAuton.frontBack(m_xrpDrivetrain, m_xrpDrivetrain);
  }
}
