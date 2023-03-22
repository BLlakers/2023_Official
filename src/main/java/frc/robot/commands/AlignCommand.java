package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainPID;

public class AlignCommand extends CommandBase {
  DriveTrainPID m_DriveTrain; // Creates an object DriveTrain
  DoubleSupplier m_angle;

  public AlignCommand(DriveTrainPID _DriveTrain, DoubleSupplier _angle) { // Creates a contrusctor for auto command (How
                                                                       // things get set up)
    m_DriveTrain = _DriveTrain;
    m_angle = _angle;
    addRequirements(m_DriveTrain);
  }

  @Override
  public void initialize() { // Runs once at the beginning of the command

  }

  @Override
  public void execute() { // Runs multiple times
    double move = 0.0;
    
    SmartDashboard.putNumber("command angle", m_angle.getAsDouble());

    
    //figuring out which way to drive
    if (m_angle.getAsDouble() >= 10){
      //too far to right so it slowly moves to the left
      move = -0.125;
    }

    else if (m_angle.getAsDouble() <= 8){
      //too far to the left so it slowly moves to the right
      move = 0.125;
    }

    //fianlly drivinG
    Double move1 = move;
    //added to fix error in the last statemnt because it said move was "not final"

    if (m_angle.getAsDouble() == 9.6){
      m_DriveTrain.drive(0,0, 0, false, false);
    }
    else{
      m_DriveTrain.drive(0, 0, move1, false, false);
    }
  }

  @Override
  public void end(boolean interrupted) {

  }
}
