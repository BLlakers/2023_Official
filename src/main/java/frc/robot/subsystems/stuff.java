package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

import java.util.function.DoubleSupplier;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class stuff {
    //these are global-ish
    Double angle;
    Double aligncamera;

    public void cameraTest() {
        //camera stuff, from the documentation
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ty = table.getEntry("ty");
        double camerax = tx.getDouble(0.0);
        double cameray = ty.getDouble(0.0);
        SmartDashboard.putNumber("Limelight X", camerax);
        SmartDashboard.putNumber("Limelight Y", cameray);
    
        SmartDashboard.putNumber("Limelight X", camerax);
        SmartDashboard.putNumber("Limelight Y", cameray);
        
        //finding if it is within he perfect angles. perfect angles are from 13.5 to 5.7, with 9.6 being perfectly centered
        if (13.5 >= camerax && 5.7 <= camerax) {
        System.out.println("alligned");
        try {
          Thread.sleep(500);
        }  catch( InterruptedException ex) {
          Thread.currentThread().interrupt();
        }
    
        } 
        else { 
         System.out.println("NOT alligned");
         //finds where the robot should move to in terms of degreese, which i will eventuly translate into motr rpm (example: 1 degree = 0.2 motor rpm) and i will tell the motrs
         //to move at the rpm need to make it be as close to 9.6 as possible
         angle = aligncamera - 9.6 * -1;
         System.out.println(angle);
         try {
          Thread.sleep(500);
        }  catch( InterruptedException ex) {
          Thread.currentThread().interrupt();
        }
        } 
        }
    public void center(){
        //turn2 must somehow become a doubble supplier and the drivetrain is off too
        //finding the turning amount needed
        Double turn1 = 0.0; //how much the motor must turn to turn one degree
        Double turn2 = turn1 * angle;
        //Double turn = (turn2.getAsDoubleSupplier());

        //movement for the left(left joystick thing) y on the "joystick"
        DoubleSupplier lefty;
        lefty = () -> 0.0;
        
        //movement fo the left(left joystick thing) x on the "joystick"
        DoubleSupplier leftx;
        leftx = () -> 0.0;
        
        //finaly driving
        m_DriveTrain.drive(() -> 0, () -> 0, () -> 0);
        m_DriveTrain.drive(lefty, leftx, () -> turn2);
        //make this so it ONLY runs once
    }
}