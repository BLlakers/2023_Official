
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






public class Manipulator extends SubsystemBase{
  
// product-specific voltage->pressure conversion, see product manual
// in this case, 250(V/5)-25
// the scale parameter in the AnalogPotentiometer constructor is scaled from 1 instead of 5,
// so if r is the raw AnalogPotentiometer output, the pressure is 250r-25
double scale = 250;
double offset = -25;
AnalogPotentiometer pressureTransducer = new AnalogPotentiometer(/* the AnalogIn port*/ 2, scale, offset);
//Mtprivate final DoubleSolenoid m_DoubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, 0, 7);

DoubleSolenoid exampleDoublePH = new DoubleSolenoid(30, PneumaticsModuleType.REVPH, 0, 7);


//exampleDoublePH.set(kOff);
//exampleDoublePH.set(kForward);

//exampleDoublePCM.set(manipController.manipButtonA.kForward);
//exampleDoublePCM.
//DoublePCM.set(manipController.manipButtonB.kReverse);

//private static final int kSolenoidButton = 1;
//private static final int kDoubleSolenoidForward = 2;
//private static final int kDoubleSolenoidReverse = 3;


// scaled values in psi units
double psi = pressureTransducer.get();  
Compressor phCompressor = new Compressor(Constants.PHChannel, PneumaticsModuleType.REVPH);

  double current = phCompressor.getPressure();

  //phCompressor.enableAnalog();
  //phCompressor.disable();
  
  
  //exampleSolenoidPH.set(true);
  //exampleSolenoidPH.set(false);
  

  double hellotest = phCompressor.getPressure();

  //boolean enabled = phCompressor.enabled();
  boolean pressureSwitch = phCompressor.getPressureSwitchValue();
  @Override
  public void periodic() {
  phCompressor.enableAnalog(50,60);
  //System.out.println("hello");
  //System.out.println(hellotest);
  
 }
  
//public void toggle(){
  //exampleDoublePH.set(Value.kForward);
  // exampleDoublePH.set(Value.kReverse);
//}

public CommandBase toggleGripper() {
  // Inline construction of command goes here.
  // Subsystem::RunOnce implicitly requires `this` subsystem.
  
  return runOnce(
      () -> {
        /* one-time action goes here */
        //WP  - Add code here to toggle the gripper solenoid
        System.out.println("Toggled gripper solenoid");

        System.out.println(exampleDoublePH.get());
        //exampleDoublePH.toggle(exampleDoublePH.set(Value.kForward.kReverse));
        
        if (exampleDoublePH.get() == Constants.kForward) {
          exampleDoublePH.set(Value.kReverse);
        } 
        else {
          exampleDoublePH.set(Value.kForward);

        }
      
        

      
        //exampleDoublePH.toggle();
        //exampleDoublePH.toggle();
       }
      );
  }

}

