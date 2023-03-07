package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.AnalogInput;

public class Claw extends SubsystemBase{

DoubleSolenoid exampleDoublePH = new DoubleSolenoid(30, PneumaticsModuleType.REVPH, 1, 8); //NO ROBOT CODE THIS IS SOLENOID PORT
AnalogInput ultrasonicSensor = new AnalogInput(3);
public boolean SensingPiece;


@Override
public void periodic() {
if (ultrasonicSensor.getVoltage() >= .7){
SensingPiece = true;
} else {
SensingPiece = false;
}
  SmartDashboard.putNumber("Ultrasonic getVoltage", ultrasonicSensor.getVoltage());
  SmartDashboard.putBoolean("Sensing somethign in the claw?", SensingPiece);

}

  

  public CommandBase toggleGripper() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.

    return runOnce(
        () -> {
          // one-time action goes here
          // WP - Add code here to toggle the gripper solenoid
          System.out.println(exampleDoublePH.get());
          exampleDoublePH.set(Value.kReverse);
          exampleDoublePH.toggle();
        });
  }

}
