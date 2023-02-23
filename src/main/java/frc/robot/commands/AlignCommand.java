package frc.robot.commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class AlignCommand extends CommandBase {
  DriveTrain m_DriveTrain; // Creates an object DriveTrain 
  DoubleSupplier m_angle;

  public AlignCommand(DriveTrain _DriveTrain, DoubleSupplier _angle) { // Creates a contrusctor for auto command (How things get set up)
    m_DriveTrain = _DriveTrain;
    m_angle = _angle;
    addRequirements(m_DriveTrain);
  }

  @Override
  public void initialize() { // Runs once at the beginning of the command 
    
  }

  @Override
  public void execute() { // Runs multiple times
        
    
        //turn2 must somehow become a doubble supplier and the drivetrain is off too
        //finding the turning amount needed
        Double turn1 = 0.035; //how much the motor must turn to turn one degree
        Double turn2 = turn1 * m_angle.getAsDouble();
        //Double turn = (turn2.getAsDoubleSupplier());

        SmartDashboard.putNumber("command angle", m_angle.getAsDouble());
        //movement for the left(left joystick thing) y on the "joystick"
        DoubleSupplier lefty;
        lefty = () -> 0.0;
        
        //movement fo the left(left joystick thing) x on the "joystick"
        DoubleSupplier leftx;
        leftx = () -> 0.0;
        
        //finaly driving
        m_DriveTrain.drive(() -> 0, () -> 0, () -> 0);
        m_DriveTrain.drive(lefty, leftx, () -> turn2);
        //make this so it ONLY runs once


    }

  @Override
  public void end(boolean interrupted) {

  }
} 
