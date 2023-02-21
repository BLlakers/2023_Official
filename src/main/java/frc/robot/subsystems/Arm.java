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
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.RobotContainer;

public class Arm extends SubsystemBase{
  
// product-specific voltage->pressure conversion, see product manual
// in this case, 250(V/5)-25
// the scale parameter in the AnalogPotentiometer constructor is scaled from 1 instead of 5,
// so if r is the raw AnalogPotentiometer output, the pressure is 250r-25
//double scale = 250;
//double offset = -25;
//AnalogPotentiometer pressureTransducer = new AnalogPotentiometer(/* the AnalogIn port*/ 2, scale, offset);

DoubleSolenoid exampleDoublePH = new DoubleSolenoid(30, PneumaticsModuleType.REVPH, 0, 7);
public CANSparkMax armRotationMtr = new CANSparkMax(Constants.armMotorChannel, MotorType.kBrushless);


//exampleDoublePH.set(kOff);
//exampleDoublePH.set(kForward);
 
// scaled values in psi units
//double psi = pressureTransducer.get();  
Compressor phCompressor = new Compressor(Constants.PHChannel, PneumaticsModuleType.REVPH);
  //boolean pressureSwitch = phCompressor.getPressureSwitchValue();
  
  @Override
  public void periodic() {
  phCompressor.enableAnalog(50,60);
 }

//This is is an inline command construction.  Commands which are so quick and easy as to only run a line one
// (they have no start, periodic, end behavior) can be defined this way.  Inline commands are defined in the subsystem
// instead of in a separate .java file in the commands folder.
public CommandBase toggleGripper() {
  // Subsystem::RunOnce implicitly requires `this` subsystem.
  
  return runOnce(
      () -> {
        /* one-time action goes here */
        System.out.println("Toggled arm solenoid");
        
        System.out.println(exampleDoublePH.get());
        exampleDoublePH.set(Value.kReverse);
        exampleDoublePH.toggle();
        
       }
      );
  }



}
