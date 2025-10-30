// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.autons.AdvancedAutons;
import frc.robot.autons.SimpleAutons;
import frc.robot.autons.GameAutons;
import frc.robot.subsystems.XRPArm;
import frc.robot.subsystems.XRPDrivetrain;
import frc.robot.subsystems.XRPReflectance;
import frc.robot.subsystems.XRPUltrasonic;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final XRPDrivetrain m_xrpDrivetrain = new XRPDrivetrain();

  private final XRPArm m_xrpArm = new XRPArm();

  private final XRPUltrasonic m_xrpUltrasonic = new XRPUltrasonic();

  private final XRPReflectance m_xrpReflectance = new XRPReflectance();

  SendableChooser<Command> m_chooser = new SendableChooser<Command>();

  private Joystick m_controller = new Joystick(0);

  private DoubleSupplier m_leftY = () -> m_controller.getRawAxis(0);
  private DoubleSupplier m_rightY = () -> m_controller.getRawAxis(1);

  private JoystickButton m_button1 = new JoystickButton(m_controller, 1);
  private JoystickButton m_button2 = new JoystickButton(m_controller, 2);
  private JoystickButton m_button3 = new JoystickButton(m_controller, 3);
  private JoystickButton m_button4 = new JoystickButton(m_controller, 4);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    createAutonChooser();
    m_xrpArm.dropArm();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    m_button1.whileTrue(m_xrpArm.liftArm());
    m_button2.whileTrue(m_xrpArm.dropArm());
    m_button3.toggleOnTrue(m_xrpUltrasonic.alertClose(m_xrpDrivetrain));
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

  public void drive() {
    m_xrpDrivetrain.tankDrive(m_leftY.getAsDouble(), m_rightY.getAsDouble());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }
}
