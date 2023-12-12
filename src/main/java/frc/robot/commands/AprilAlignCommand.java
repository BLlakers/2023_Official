package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainPID;

public class AprilAlignCommand extends CommandBase {
  DriveTrainPID m_DriveTrain; // Creates an object DriveTrain
  //DoubleSupplier m_angle; unused so comment out
  DoubleSupplier m_tx2; //x amount you need to move by
  DoubleSupplier m_s8; //distance from tag
  public AprilAlignCommand(DriveTrainPID _DriveTrain, DoubleSupplier _tx2, DoubleSupplier _s8) { // Creates a contrusctor for auto command (How
                                                                       // things get set up)
    m_DriveTrain = _DriveTrain;
    m_tx2 = _tx2;
    m_s8 = _s8;
    //s8 is the distance to move to get closer, tx2 is the x amount you need to move by
    addRequirements(m_DriveTrain);
  }

@Override
  public void initialize() { // Runs once at the beginning of the command

  }

  @Override
  public void execute() { // Runs multiple times
    double move = 0.0;
    double forwards = 0.0;
    
    //this is old stuff, but i am keeping it just as a referance and just incase it is needed again
    // turn2 must somehow become a doubble supplier and the drivetrain is off too
    // finding the turning amount needed
    Double turn1 = 0.035; // how much the motor must turn to turn one degree, this number IS WRONG: it
                          // should be either 0.03555 or, i bet it is actully 0.02261
    Double turn2 = turn1 * m_tx2.getAsDouble();
    // Double turn = (turn2.getAsDoubleSupplier());
    SmartDashboard.putNumber("command angle april tags", m_tx2.getAsDouble());
    //end of old stuff

    
    //figuring out which way to drive
    if (m_tx2.getAsDouble() >= 3){
      //too far to right so it slowly moves to the left
      move = 0.2;
    }

    else if (m_tx2.getAsDouble() <= -3){
      //too far to the left so it slowly moves to the right
      move = -0.2;
    }

    //fianlly drivinG
    Double move1 = move;
    //added to fix error in the last statemnt because it said move was "not final"
    
    System.out.println(move1);
    
    if (m_tx2.getAsDouble() == 0){
      m_DriveTrain.drive(0,forwards,0, false, false);
      //now we are centered so move forwards
      //this is currently not going to work (made it so it shouldent run) as it has not been tested yet
      //and could go very poorly if it goes wrong
      if (m_s8.getAsDouble() <= 1.989){
        forwards = 0.1;
        //saying that if we are more than 12 inches, move forward. s8 increaces as we get closer btw
        //assumes that 0.023 = 1 inch. dont have enough testing to deinitively say this is 100% right

      }
      else if (m_s8.getAsDouble() >= 1.989){
        forwards = 0.0; 
        // saying if we closer than 12 inches, dont go anywhere. s8 increaces as we get closer btw
        //assumes that 0.023 = 1 inch. dont have enough testing to deinitively say this is 100% right

      }


    }
    else{
      //rotate in the correct direction, or not at all if alligned
      m_DriveTrain.drive(0, move1, 0, false, false);
    }
  }

  @Override
  public void end(boolean interrupted) {

  }
}
