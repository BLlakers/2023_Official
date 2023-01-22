
package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AutoCommand;
import frc.robot.commands.SwerveDriveCommand;
import frc.robot.subsystems.DriveTrain;

public class RobotContainer {
  DriveTrain m_DriveTrain = new DriveTrain();
  XboxController driverController = new XboxController(Constants.DriverControllerChannel);
  XboxController manipController = new XboxController(Constants.ManipControllerChannel);
  JoystickButton manipButtonA = new JoystickButton(manipController, Constants.buttonA);
  JoystickButton manipButtonB = new JoystickButton(manipController, Constants.buttonB);
  JoystickButton manipButtonX = new JoystickButton(manipController, Constants.buttonX);
  JoystickButton manipButtonY = new JoystickButton(manipController, Constants.buttonY);
  JoystickButton manipButtonRight = new JoystickButton(manipController, Constants.buttonRight);
  JoystickButton manipButtonLeft = new JoystickButton(manipController, Constants.buttonLeft);
  JoystickButton manipButtonOptions = new JoystickButton(manipController, 7);
  JoystickButton manipButtonStart = new JoystickButton(manipController, 8);

  public RobotContainer() {  
    m_DriveTrain.setDefaultCommand(new SwerveDriveCommand (() -> driverController.getLeftY(),
     () -> driverController.getLeftX(), () -> driverController.getRightX(), m_DriveTrain));
  }

  public Command getAutonomousCommand() {
    Command autoSeq = Commands.sequence(
    Commands.waitSeconds(3.0),
    new AutoCommand(m_DriveTrain)
    );
    
    return autoSeq;
    //return new AutoCommand(m_DriveTrain);
  }
}