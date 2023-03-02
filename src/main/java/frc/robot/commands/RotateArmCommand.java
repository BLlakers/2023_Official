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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;;
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
    double m_sensorPosition = -m_Arm.armRotationMtr.getSelectedSensorPosition(); // Variable to hold the sensor position
    double m_sensorDegrees = (m_sensorPosition * 360) / (90 * 2048);

    

    /*
    double controllerValue = m_leftY.getAsDouble();
    double m_sensorPosition = -m_Arm.armRotationMtr.getSelectedSensorPosition(); // Variable to hold the sensor position
    SmartDashboard.putNumber("armRotationMtr", m_sensorPosition);

    // Limit switch is inverted logic
    if (!ArmLimitSwitch.get()) {
      m_Arm.armRotationMtr.setSelectedSensorPosition(0);
      if (controllerValue >= 0) {
        m_Arm.armRotationMtr.set(ControlMode.PercentOutput, 0 * controllerValue);
      } else {
        m_Arm.armRotationMtr.set(ControlMode.PercentOutput, controllerValue);
      }

    } else {
      // setting the Deadzone value of the controller. It tells
      // the controller not to move at .1> (a small amount on the controller) and to
      // move everywhere else.
      if (Math.abs(controllerValue) < Constants.deadzone) {
        controllerValue = 0;
      } else {
        controllerValue = controllerValue;
      }

      // m_Arm.armRotationMtr.set(ControlMode.PercentOutput, .5 * controllerValue);
      /*
       * The following code reads the motor encoder sensor and sets the percentoutput
       * to 0
       * This proof of concept needs further logic added to continue moving below the
       * threshold, in the opposite direction
       

      //If we are at the limit
      if (m_sensorPosition >= 25000) {
        //Then don't go further
        if (controllerValue <= 0) {
          m_Arm.armRotationMtr.set(ControlMode.PercentOutput, 0 * controllerValue);
        } else {
          m_Arm.armRotationMtr.set(ControlMode.PercentOutput, 1 * controllerValue);
        }
      }
      else{
        m_Arm.armRotationMtr.set(ControlMode.PercentOutput, 1 * controllerValue);  
      } */
    }
  }

  @Override
  public void end(boolean interrupted) {
  }

  public boolean isFinished() {
    return false;
  }

}
