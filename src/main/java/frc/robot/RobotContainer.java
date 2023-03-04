
package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

import java.sql.Driver;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AlignCommand;
import frc.robot.commands.AutoCommand;
import frc.robot.commands.ManualRotateArmCommand;
import frc.robot.commands.AutoRotateArmCommand;
import frc.robot.commands.SwerveDriveCommand;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Stuff;
import frc.robot.subsystems.Tags;
//add in later
//import frc.robot.commands.AprilAlignCommand;

public class RobotContainer {
  DriveTrain m_DriveTrain = new DriveTrain();
  Arm m_Arm = new Arm();
  Claw m_Claw = new Claw();
  Stuff m_Stuff = new Stuff();
  Tags m_Tags = new Tags();


  XboxController driverController = new XboxController(Constants.DriverControllerChannel);
  XboxController manipController = new XboxController(Constants.ManipControllerChannel);
  JoystickButton driverButtonA = new JoystickButton(driverController, Constants.buttonA);
  JoystickButton manipButtonA = new JoystickButton(manipController, Constants.buttonA);
  //JoystickButton manipButtonB = new JoystickButton(manipController, Constants.buttonB);

  JoystickButton driverButtonRight = new JoystickButton(driverController, Constants.buttonRight);
  JoystickButton driverButtonLeft = new JoystickButton(driverController, Constants.buttonLeft);

  JoystickButton manipButtonX = new JoystickButton(manipController, Constants.buttonX);


  //2022 Code
  //JoystickButton manipButtonA = new JoystickButton(manipController, Constants.buttonA);
  JoystickButton manipButtonB = new JoystickButton(manipController, Constants.buttonB);
  //JoystickButton manipButtonX = new JoystickButton(manipController, Constants.buttonX);
  JoystickButton manipButtonY = new JoystickButton(manipController, Constants.buttonY);
  JoystickButton manipButtonRight = new JoystickButton(manipController, Constants.buttonRight);
  JoystickButton manipButtonLeft = new JoystickButton(manipController, Constants.buttonLeft);
  JoystickButton manipButtonOptions = new JoystickButton(manipController, 7);
  //JoystickButton manipButtonStart = new JoystickButton(manipController, 8);
  
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

    m_DriveTrain.setDefaultCommand(new SwerveDriveCommand (() -> driverController.getLeftY(),
    () -> driverController.getLeftX(), () -> driverController.getRightX(), m_DriveTrain));

    m_Arm.setDefaultCommand(new AutoRotateArmCommand (m_Arm));
    manipButtonOptions.whileTrue(new ManualRotateArmCommand(() -> manipController.getLeftY(), m_Arm));
    manipButtonLeft.onTrue(m_Arm.LowerArm()); // starts at 1 (5 deegrees) goes down
    manipButtonRight.onTrue(m_Arm.RaiseArm());  //  starts at 1, when pressed goes up to 2 (82 Deegrees), when pressed again goes up to 3 (85 deegrees)
    manipButtonA.toggleOnTrue(m_Arm.toggleArm());
    manipButtonB.toggleOnTrue(m_Claw.toggleGripper());

    manipButtonX.whileTrue(new AlignCommand(m_DriveTrain, () -> frc.robot.subsystems.Stuff.angle));
    //manipButtonB.whileTrue(new AprilAlignCommand(m_DriveTrain, () -> frc.robot.subsystems.Tags.tx2));

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
    new AutoCommand(m_DriveTrain, m_chooser.getSelected()));

    return autoSeq;
    //return new AutoCommand(m_DriveTrain);
  }
}