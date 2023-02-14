
package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.revrobotics.CANSparkMax;

import frc.robot.subsystems.Arm;
//import frc.robot.subsystems.DriveTrain;
//import frc.robot.RobotContainer; //Replace this with the Arm subsystem

public class RotateArmCommand extends CommandBase {
  DoubleSupplier m_leftY;
  Arm m_Arm;  //Replace this with an Arm type and name it m_Arm

  public RotateArmCommand(DoubleSupplier _leftY, Arm _dArm) { //Replace drivetrain with arm
    m_leftY = _leftY;
    m_Arm = _dArm; //Replace this with arm
    addRequirements(m_Arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_Arm.armRotationMtr.setIdleMode(CANSparkMax.IdleMode.kBrake); //Change this to the arm motor and setIdleMode to "kBrake"
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Insert the code here to set the arm motor according to m_leftY joystick value
    //To get the value from the joystick you'll need to call 
    System.out.println(m_leftY.getAsDouble());
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
