
package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.commands.AutoCommand;
import frc.robot.commands.RotateArmCommand;
import frc.robot.commands.SwerveDriveCommand;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Arm;


public class RobotContainer {
  DriveTrain m_DriveTrain = new DriveTrain();
  Arm m_Arm = new Arm();
  Claw m_Claw = new Claw();
  XboxController driverController = new XboxController(Constants.DriverControllerChannel);
  XboxController manipController = new XboxController(Constants.ManipControllerChannel);
  //JoystickButton driverButtonA = new JoystickButton(driverController, Constants.buttonA);
  JoystickButton manipButtonA = new JoystickButton(manipController, Constants.buttonA);
  //JoystickButton manipButtonB = new JoystickButton(manipController, Constants.buttonB);
   
  //2022 Code
  //JoystickButton manipButtonA = new JoystickButton(manipController, Constants.buttonA);
  //JoystickButton manipButtonB = new JoystickButton(manipController, Constants.buttonB);
  //JoystickButton manipButtonX = new JoystickButton(manipController, Constants.buttonX);
  JoystickButton manipButtonY = new JoystickButton(manipController, Constants.buttonY);
  JoystickButton manipButtonRight = new JoystickButton(manipController, Constants.buttonRight);
  //JoystickButton manipButtonLeft = new JoystickButton(manipController, Constants.buttonLeft);
  //JoystickButton manipButtonOptions = new JoystickButton(manipController, 7);
  //JoystickButton manipButtonStart = new JoystickButton(manipController, 8);
  
  // A chooser for autonomous commands
  SendableChooser<Integer> m_chooser = new SendableChooser<>();


  public RobotContainer() {  
    configureShuffleboard();
    configureBindings();
  }

    /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
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

    m_Arm.setDefaultCommand(new RotateArmCommand (() -> manipController.getLeftY(), m_Arm));

    manipButtonA.toggleOnTrue(m_Arm.toggleGripper());
    manipButtonRight.toggleOnTrue(m_Claw.toggleGripper());
    
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