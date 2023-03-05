package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class AlignCommand extends CommandBase {
  DriveTrain m_DriveTrain; // Creates an object DriveTrain
  DoubleSupplier m_angle;

  public AlignCommand(DriveTrain _DriveTrain, DoubleSupplier _angle) { // Creates a contrusctor for auto command (How
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
    Double move = 0.0;
    // turn2 must somehow become a doubble supplier and the drivetrain is off too
    // finding the turning amount needed
    Double turn1 = 0.035; // how much the motor must turn to turn one degree, this number IS WRONG: it
                          // should be either 0.03555 or, i bet it is actully 0.02261
    Double turn2 = turn1 * m_angle.getAsDouble();
    // Double turn = (turn2.getAsDoubleSupplier());
    SmartDashboard.putNumber("command angle", m_angle.getAsDouble());

    // movement for the left(left joystick thing) y on the "joystick"
    DoubleSupplier lefty;
    lefty = () -> 0.0;
    // movement fo the left(left joystick thing) x on the "joystick"
    DoubleSupplier leftx;
    leftx = () -> 0.0;

    // finaly driving

    if (m_angle.getAsDouble() >= 13){
      //too far to right so it moves to the left
      move = -0.2;
    }
    else if (m_angle.getAsDouble() <= 5){
      //too far to the left so it moves to the right
      move = 0.2;
    }

    //actully driving
    Double move1 = move;
    //added to fix error in the lat statemnt

    if (m_angle.getAsDouble() == 9.6){
      m_DriveTrain.drive(() -> 0, () -> 0, () -> 0);
    }
    else{
      m_DriveTrain.drive(lefty, leftx, () -> move1);
    }
    
    // make this so it ONLY runs once

  }

  @Override
  public void end(boolean interrupted) {

  }
}
