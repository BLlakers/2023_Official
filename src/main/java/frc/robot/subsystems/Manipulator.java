
package frc.robot.subsystems;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;



public class Manipulator extends SubsystemBase{
// product-specific voltage->pressure conversion, see product manual
// in this case, 250(V/5)-25
// the scale parameter in the AnalogPotentiometer constructor is scaled from 1 instead of 5,
// so if r is the raw AnalogPotentiometer output, the pressure is 250r-25
double scale = 250;
double offset = -25;
AnalogPotentiometer pressureTransducer = new AnalogPotentiometer(/* the AnalogIn port*/ 2, scale, offset);

// scaled values in psi units
double psi = pressureTransducer.get();  
Compressor phCompressor = new Compressor(30, PneumaticsModuleType.REVPH);

  double current = phCompressor.getPressure();
  //phCompressor.enableAnalog();
  //phCompressor.disable();
  
  Solenoid exampleSolenoidPH = new Solenoid(PneumaticsModuleType.REVPH, 1);
  
  //exampleSolenoidPH.set(true);
  //exampleSolenoidPH.set(false);
  

  double hellotest = phCompressor.getPressure();

  boolean enabled = phCompressor.enabled();
  boolean pressureSwitch = phCompressor.getPressureSwitchValue();
  
  @Override
  public void periodic() {
  phCompressor.enableAnalog(100,110);
  System.out.println("hello");
  System.out.println(hellotest);
    }
  
}

