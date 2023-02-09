package frc.robot.subsystems;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.XboxController.Button;

public class Manipulator extends SubsystemBase{
  
// product-specific voltage->pressure conversion, see product manual
// in this case, 250(V/5)-25
// the scale parameter in the AnalogPotentiometer constructor is scaled from 1 instead of 5,
// so if r is the raw AnalogPotentiometer output, the pressure is 250r-25
double scale = 250;
double offset = -25;
AnalogPotentiometer pressureTransducer = new AnalogPotentiometer(/* the AnalogIn port*/ 2, scale, offset);

DoubleSolenoid exampleDoublePH = new DoubleSolenoid(30, PneumaticsModuleType.REVPH, 0, 7);

//exampleDoublePH.set(kOff);
//exampleDoublePH.set(kForward);

// scaled values in psi units
double psi = pressureTransducer.get();  
Compressor phCompressor = new Compressor(Constants.PHChannel, PneumaticsModuleType.REVPH);
  boolean pressureSwitch = phCompressor.getPressureSwitchValue();
  @Override
  public void periodic() {
  phCompressor.enableAnalog(50,60);
  //System.out.println("hello");
  //System.out.println(hellotest);
  
 }

public CommandBase toggleGripper() {
  // Inline construction of command goes here.
  // Subsystem::RunOnce implicitly requires `this` subsystem.
  
  return runOnce(
      () -> {
        /* one-time action goes here */
        //WP  - Add code here to toggle the gripper solenoid
        System.out.println("Toggled gripper solenoid");
        
        System.out.println(exampleDoublePH.get());
        exampleDoublePH.set(Value.kReverse);
        exampleDoublePH.toggle();
       }
      );
  }

}
