
package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

import java.util.EventListener;
import java.awt.*;
import java.awt.event.*;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveTrainPID;
import frc.robot.commands.AutoCommand;
//import frc.robot.commands.JoyFun;
import frc.robot.commands.ManualRotateArmCommand;
import frc.robot.commands.AutoRotateArmCommand;
import frc.robot.commands.SwerveDriveCommand;
import frc.robot.commands.AlignCommand;
import frc.robot.commands.AutoClawCommand;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Tags;
import frc.robot.subsystems.Stuff;
import frc.robot.commands.AprilAlignCommand;

public class RobotContainer {
  DriveTrainPID m_DriveTrainPID = new DriveTrainPID();
  Arm m_Arm = new Arm();
  Claw m_Claw = new Claw();
  Stuff m_Stuff = new Stuff();
  Tags m_Tags = new Tags();


  XboxController driverController = new XboxController(Constants.DriverControllerChannel);
  XboxController manipController = new XboxController(Constants.ManipControllerChannel);
  //XboxController vJoystick = new XboxController(Constants.vJoy);
  JoystickButton driverButtonB = new JoystickButton(driverController, Constants.buttonB);
  JoystickButton manipButtonA = new JoystickButton(manipController, Constants.buttonA);
  JoystickButton driverButtonRight = new JoystickButton(driverController, Constants.buttonRight);
  JoystickButton driverButtonLeft = new JoystickButton(driverController, Constants.buttonLeft);
  JoystickButton manipButtonX = new JoystickButton(manipController, Constants.buttonX);
  JoystickButton driverButtonX = new JoystickButton(driverController, Constants.buttonX);
  JoystickButton driverButtonRS = new JoystickButton(driverController, Constants.buttonRS);
  JoystickButton manipButtonB = new JoystickButton(manipController, Constants.buttonB);
  JoystickButton manipButtonY = new JoystickButton(manipController, Constants.buttonY);
  JoystickButton manipButtonRight = new JoystickButton(manipController, Constants.buttonRight);
  JoystickButton manipButtonLeft = new JoystickButton(manipController, Constants.buttonLeft);
  JoystickButton manipButtonOptions = new JoystickButton(manipController, Constants.buttonOptions);

  /*JoystickButton vJoystickButton1 = new JoystickButton(vJoystick, 1);
  JoystickButton vJoystickButton2 = new JoystickButton(vJoystick, 2);
  JoystickButton vJoystickButton3 = new JoystickButton(vJoystick, 3);
  JoystickButton vJoystickButton4 = new JoystickButton(vJoystick, 4);
  JoystickButton vJoystickButton5 = new JoystickButton(vJoystick, 5);
  JoystickButton vJoystickButton6 = new JoystickButton(vJoystick, 6);
  JoystickButton vJoystickButton7 = new JoystickButton(vJoystick, 7);
  JoystickButton vJoystickButton8 = new JoystickButton(vJoystick, 8); */

  // A chooser for autonomous commands
  SendableChooser<Integer> m_chooser = new SendableChooser<>();


  public RobotContainer() {  
    configureShuffleboard();
    configureBindings();
  }

    /**
   * Use this method to define your trigger->comand mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    ///new Trigger(m_exampleSubsystem::exampleCondition)
    ///    .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    ///m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());

    m_DriveTrainPID.setDefaultCommand(new SwerveDriveCommand (() -> driverController.getLeftY(),
    () -> driverController.getLeftX(), () -> driverController.getRightX(), m_DriveTrainPID));
    //limelight allign works on both controllers
    //manipButtonX.whileTrue(new AlignCommand(m_DriveTrainPID, () -> frc.robot.subsystems.Stuff.angle));
    //driverButtonX.whileTrue(new AlignCommand(m_DriveTrainPID, () -> frc.robot.subsystems.Stuff.angle));
    
    //calling it
    manipButtonX.whileTrue(new AprilAlignCommand(m_DriveTrainPID, () -> frc.robot.subsystems.Stuff.tx, () -> frc.robot.subsystems.Tags.s8));
    
    //vJoystickButton1.whileTrue(frc.robot.commands.JoyFun.vJoyFunc(1));
    //vJoystickButton2.whileTrue(frc.robot.commands.JoyFun.vJoyFunc(2));
    //vJoystickButton3.whileTrue(frc.robot.commands.JoyFun.vJoyFunc(3));
    //vJoystickButton4.whileTrue(frc.robot.commands.JoyFun.vJoyFunc(4));
    //vJoystickButton5.whileTrue(frc.robot.commands.JoyFun.vJoyFunc(5));
    //vJoystickButton6.whileTrue(frc.robot.commands.JoyFun.vJoyFunc(6));
    //vJoystickButton7.whileTrue(frc.robot.commands.JoyFun.vJoyFunc(7));
    //vJoystickButton8.whileTrue(frc.robot.commands.JoyFun.vJoyFunc(8));



    //driverButtonB.whileTrue(new FieldAlignedCommand(m_DriveTrain));
    driverButtonRS.onTrue(m_DriveTrainPID.WheelzLock());

    
    //WP - DO NOT UNCOMMENT WITHOUT TALKING TO WARD
    //manipButtonOptions.whileTrue(new ManualRotateArmCommand(() -> manipController.getLeftY(), m_Arm));
    
    //saftey start
    m_Arm.setDefaultCommand(new AutoRotateArmCommand (m_Arm));
    //manipButtonLeft.onTrue(m_Arm.LowerArm()); // starts at 1 (5 deegrees) goes down
    //manipButtonRight.onTrue(m_Arm.RaiseArm());  //  starts at 1, when pressed goes up to 2 (82 Deegrees), when pressed again goes up to 3 (85 deegrees)
    //manipButtonA.toggleOnTrue(m_Arm.toggleArm());
    //manipButtonB.toggleOnTrue(m_Claw.toggleGripper());
    //manipButtonY.whileTrue(new AutoClawCommand(m_Claw));
    //sfatey end
  }

  private void configureShuffleboard(){
        // Add commands to the autonomous command chooser
        m_chooser.setDefaultOption("Auto 1", 1);
        m_chooser.addOption("Auto 2", 2);
        m_chooser.addOption("Auto 3", 3);
    
        SmartDashboard.putData(m_chooser);
     

  }

  public Command getAutonomousCommand() {
    Command autoSeq = Commands.sequence(
    Commands.waitSeconds(1.0),
    new AutoCommand(m_DriveTrainPID, m_chooser.getSelected()));
    return autoSeq;
    //return new AutoCommand(m_DriveTrain);
  }
}
