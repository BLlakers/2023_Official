package frc.robot.commands;
import com.revrobotics.CANSparkMax;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Arm;

public class RotateArmCommand extends CommandBase {
  DoubleSupplier m_leftY;
  Arm m_Arm; 
  public RotateArmCommand(DoubleSupplier _leftY, Arm _Arm) {
    m_leftY = _leftY;
    m_Arm = _Arm;
    addRequirements(m_Arm);
    
  } 
  @Override

  public void initialize() {
      m_Arm.armRotationMtr.setIdleMode(CANSparkMax.IdleMode.kBrake);
      }
      @Override
    
    public void execute () {
    
      double controllerValue = m_leftY.getAsDouble();

    // lines 30 --- 34 are setting the Deadzone value of the controller. It tells the controller not to move at .1> (a small amount on the controller) and to move everywhere else. 
     System.out.println(m_leftY.getAsDouble()); 
     if(Math.abs(controllerValue) < Constants.deadzone) {
      controllerValue = 0;
     } else {
      controllerValue = controllerValue;
     }
     m_Arm.armRotationMtr.set(-1 * controllerValue);
    }  
    @Override
    public void end(boolean interrupted) {
    }
    public boolean isFinished() {
      return false;
    }

}
