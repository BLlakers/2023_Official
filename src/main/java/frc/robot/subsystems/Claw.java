package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Claw extends SubsystemBase {

  DoubleSolenoid exampleDoublePH = new DoubleSolenoid(30, PneumaticsModuleType.REVPH, 1, 8); // NO ROBOT CODE THIS IS
                                                                                             // SOLENOID PORT

  @Override
  public void periodic() {

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
