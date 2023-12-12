package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Stuff extends SubsystemBase {
    // these are global
    public static Double angle = 0.0;
    //public Double aligncamera; unsued so commented it out
    public boolean isAligned;
    
    @Override
    public void periodic() {
        // camera stuff, from the documentation
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        //works with both limelight and aprilitags for some reason
        //gets its network key
        NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ty = table.getEntry("ty");
   
        //camera x and y from network values becoming varables
        double camerax = tx.getDouble(0.0);
        double cameray = ty.getDouble(0.0);
        
        //puts on smartdashboard
        SmartDashboard.putNumber("Limelight X", camerax);
        SmartDashboard.putNumber("Limelight Y", cameray);

        // finding if it is within the perfect angles. perfect angles are from -3 to
        // 3, with 0 being perfectly centered
        if (3 >= camerax && -3 <= camerax) {
            isAligned = true;
            angle = 0.0;
        }
        //if no tag is identified, this will run since both will = 0, in practice this
        //is theroreticly possible but extremely unlikely
        else if(camerax == 0 && cameray == 0){
            isAligned = false;
            angle = 0.0;


        }
        else {
            isAligned = false;
            //it not alligned so make the global varible = the angle needed to travel. the -1 is so 
            //it knows if you are at 50 degreese, you need to move -50 to get to the right spot
            angle = -camerax;
            //puts on smartdashboard
            SmartDashboard.putNumber("subsystemangle", angle);
        }
        //puts on smartdashboard
        SmartDashboard.putNumber("subsystemangle", angle);
        SmartDashboard.putBoolean("Aligned?", isAligned);
    }
}
