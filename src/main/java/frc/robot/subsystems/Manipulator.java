
package frc.robot.subsystems;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid;



public class Manipulator extends SubsystemBase{
  private final Manipulator manipController = new Manipulator();
// product-specific voltage->pressure conversion, see product manual
// in this case, 250(V/5)-25
// the scale parameter in the AnalogPotentiometer constructor is scaled from 1 instead of 5,
// so if r is the raw AnalogPotentiometer output, the pressure is 250r-25
double scale = 250;
double offset = -25;
AnalogPotentiometer pressureTransducer = new AnalogPotentiometer(/* the AnalogIn port*/ 2, scale, offset);
private final DoubleSolenoid m_DoubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, 0, 7);
  private static final int kSolenoidButton = 1;
  private static final int kDoubleSolenoidForward = 2;
  private static final int kDoubleSolenoidReverse = 3;
// scaled values in psi units
double psi = pressureTransducer.get();  
Compressor phCompressor = new Compressor(30, PneumaticsModuleType.REVPH);

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
  phCompressor.enableAnalog(100,110);
  System.out.println("hello");
  System.out.println(hellotest);
    }
  
}

