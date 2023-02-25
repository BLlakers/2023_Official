package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.revrobotics.CANSparkMax;
import java.util.function.DoubleSupplier;

import javax.lang.model.util.ElementScanner14;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Arm;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RotateArmCommand extends CommandBase {
  DoubleSupplier m_leftY;
  Arm m_Arm;
  DigitalInput ArmLimitSwitch = new DigitalInput(9);
 
  public RotateArmCommand(DoubleSupplier _leftY, Arm _Arm) {
    m_leftY = _leftY;
    m_Arm = _Arm;
    addRequirements(m_Arm);

  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    double controllerValue = m_leftY.getAsDouble();
    
    SmartDashboard.putNumber("armRotationMtr", m_Arm.armRotationMtr.getSelectedSensorPosition());

    //Limit switch is inverted logic
    if (!ArmLimitSwitch.get()) {
      
      if (controllerValue >= 0) {
        m_Arm.armRotationMtr.set(ControlMode.PercentOutput, controllerValue);
      } else {
        m_Arm.armRotationMtr.set(ControlMode.PercentOutput, 0 * controllerValue);
      }

    } else {
      // m_Arm.armRotationMtr.set(ControlMode.PercentOutput, -1 * controllerValue);

      // lines 30 --- 34 are setting the Deadzone value of the controller. It tells
      // the controller not to move at .1> (a small amount on the controller) and to
      // move everywhere else.
      System.out.println(m_leftY.getAsDouble());

      if (Math.abs(controllerValue) < Constants.deadzone) {

        controllerValue = 0;
      } else {
        controllerValue = controllerValue;
      }
      m_Arm.armRotationMtr.set(ControlMode.PercentOutput, controllerValue);
    }
  }

  @Override
  public void end(boolean interrupted) {
  }

  public boolean isFinished() {
    return false;
  }

}
