
package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.revrobotics.CANSparkMax;
import frc.robot.subsystems.DriveTrain; //Replace this with the Arm subsystem

public class RotateArmCommand extends CommandBase {
  DoubleSupplier m_leftY;
  DriveTrain m_DriveTrain;  //Replace this with an Arm type and name it m_Arm

  public RotateArmCommand(DoubleSupplier _leftY, DriveTrain _dTrain) { //Replace drivetrain with arm
    m_leftY = _leftY;
    m_DriveTrain = _dTrain; //Replace this with arm
    addRequirements(m_DriveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_DriveTrain.brDrive.setIdleMode(CANSparkMax.IdleMode.kCoast); //Change this to the arm motor and setIdleMode to "kBrake"
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Insert the code here to set the arm motor according to m_leftY joystick value
    //To get the value from the joystick you'll need to call m_leftY.getAsDouble();
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
