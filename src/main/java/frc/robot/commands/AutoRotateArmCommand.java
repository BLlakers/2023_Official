package frc.robot.commands;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Arm;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class AutoRotateArmCommand extends CommandBase {
  Arm m_Arm;
  Double targetDegrees;

  public AutoRotateArmCommand(Arm _Arm) {
    m_Arm = _Arm;
    addRequirements(m_Arm);

  }

  @Override
  public void initialize() {
    
  }

  @Override
  public void execute() {
    double m_sensorPosition = m_Arm.armRotationMtr.getSelectedSensorPosition(); // Variable to hold the sensor position
    m_Arm.ArmDegrees = (m_sensorPosition * 360 * 0.72) / (120 * 2048); // 120:1 gearbox
    double drivevalue = 0;

    // Determine the target position
    if (m_Arm.ArmPosition == 1) { // The target position for 0 = Lower, 1 = pickup, 2 = Drop
      targetDegrees = Constants.Positions[0];
    } else if (m_Arm.ArmPosition == 2) {
      targetDegrees = Constants.Positions[1];
    } else if (m_Arm.ArmPosition == 3) {
      targetDegrees = Constants.Positions[2];
    } else if (m_Arm.ArmPosition == 4) {
      targetDegrees = Constants.Positions[3];
    }
    SmartDashboard.putNumber("Target", targetDegrees);
    SmartDashboard.putNumber("Arm Degrees", m_Arm.ArmDegrees);
    // Use bang bang control to reach the target
    if (m_Arm.ArmDegrees <= (targetDegrees - Constants.ArmTolerance)) {
      drivevalue = drivevalue + 0.25;
    } else if (m_Arm.ArmDegrees >= (targetDegrees + Constants.ArmTolerance)) {
      drivevalue = drivevalue - 0.25;
    } else {

    }

    // Apply a feedforward constant to hold the arm in position
    drivevalue = drivevalue + 0.14 * Math.sin(m_Arm.ArmDegrees * Math.PI / 180);
    SmartDashboard.putNumber("Drive Value", drivevalue);

    if (!m_Arm.ArmLimitSwitch.get()) {
      // Logic if the limit switch is pressed
      // Set the encoder to the lower position, update the position we are at
      // accordingly
      m_Arm.armRotationMtr.setSelectedSensorPosition(Constants.Positions[0] * 120 * 2048 / (360 * 0.72));

      if (drivevalue <= 0) {
        m_Arm.armRotationMtr.set(ControlMode.PercentOutput, 0 * drivevalue);
      } else {
        m_Arm.armRotationMtr.set(ControlMode.PercentOutput, drivevalue);
      }

    } else {
      // Logic if the limit switch is NOT pressed
      if (m_Arm.ArmDegrees >= 90) {
        // Then don't go further
        if (drivevalue >= 0) {
          // then if the motor drivevalue is greater than 0. DONT LET MOTOR OVERDRIVE 90 deegrees. 
          m_Arm.armRotationMtr.set(ControlMode.PercentOutput, 0 * drivevalue); // dont drive
        } else {
          m_Arm.armRotationMtr.set(ControlMode.PercentOutput, 1 * drivevalue); // drive like normal
        }
      } else {
        m_Arm.armRotationMtr.set(ControlMode.PercentOutput, 1 * drivevalue); // drive like normal
      }
    }
  }

  @Override
  public void end(boolean interrupted) {
    m_Arm.armRotationMtr.set(ControlMode.PercentOutput, 0);
  }

  public boolean isFinished() {
    return false;
  }

}
