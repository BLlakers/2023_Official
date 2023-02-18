package frc.robot.commands;
import com.revrobotics.CANSparkMax;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
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
     m_Arm.armRotationMtr.set(m_leftY.getAsDouble());
     System.out.println(m_leftY.getAsDouble());
    }  
    @Override
    public void end(boolean interrupted) {
    }
    public boolean isFinished() {
      return false;
    }

}
