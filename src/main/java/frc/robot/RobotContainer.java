
package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
//import frc.robot.commands.AutoCommand;
//import frc.robot.commands.IntakeCommand;
//import frc.robot.commands.RelayCommand;
//import frc.robot.commands.ShootCommand;
import frc.robot.commands.SwerveDriveCommand;
//import frc.robot.commands.WinchCommand;
//import frc.robot.commands.WinchReverseCommand;
import frc.robot.subsystems.DriveTrain;
//import frc.robot.subsystems.Endgame;
//import frc.robot.subsystems.IntakeRelay;
//import frc.robot.subsystems.Shooter;

public class RobotContainer {
  DriveTrain m_DriveTrain = new DriveTrain();
  //Shooter m_Shooter = new Shooter();
  //IntakeRelay m_IntakeRelay = new IntakeRelay();
  //Endgame m_Endgame = new Endgame();
  XboxController driverController = new XboxController(Constants.DriverControllerChannel);
  XboxController manipController = new XboxController(Constants.ManipControllerChannel);
  // XboxController ManipController = new XboxController(Constants.upperIntakeMotorChannel);
  //did by rafa try chaning
   // JoystickButton drivButtonA = new JoystickButton(driverController, Constants.buttonA);
  //end of change
  JoystickButton manipButtonA = new JoystickButton(manipController, Constants.buttonA);
  JoystickButton manipButtonB = new JoystickButton(manipController, Constants.buttonB);
  JoystickButton manipButtonX = new JoystickButton(manipController, Constants.buttonX);
  JoystickButton manipButtonY = new JoystickButton(manipController, Constants.buttonY);
  JoystickButton manipButtonRight = new JoystickButton(manipController, Constants.buttonRight);
  JoystickButton manipButtonLeft = new JoystickButton(manipController, Constants.buttonLeft);
  JoystickButton manipButtonOptions = new JoystickButton(manipController, 7);
  JoystickButton manipButtonStart = new JoystickButton(manipController, 8);

  public RobotContainer() {  
    configureButtonBindings();
    m_DriveTrain.setDefaultCommand(new SwerveDriveCommand (() -> driverController.getLeftY(),
     () -> driverController.getLeftX(), () -> driverController.getRightX(), m_DriveTrain));
  }

  private void configureButtonBindings() { 
    //rafercode
    // drivButtonA.whileHeld(new Shooter(ballup)))
    //manipButtonA.whileHeld(new ShootCommand(m_Shooter));
    //manipButtonB.whileHeld(new RelayCommand(m_IntakeRelay));
    //manipButtonY.whileHeld(new IntakeCommand(m_IntakeRelay));
    //manipButtonRight.whileHeld(new WinchCommand(m_Endgame));
    //manipButtonLeft.whileHeld(new WinchReverseCommand(m_Endgame));
  }

  //public Command getAutonomousCommand() {
    //return new AutoCommand(m_DriveTrain, m_Shooter);
  //}    
 }
