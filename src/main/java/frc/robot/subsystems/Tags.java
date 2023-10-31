package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Tags extends SubsystemBase {
    boolean distance;
    public static Double tx2 = 0.0;
    public static Double s8 = 0.0;

    @Override
    public void periodic() {
        // camera stuff, from the documentation
        // the .lime is to look for the network table instance called lime which is the
        // refective tape thing, .april would be the april tag thing
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-april");
        NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ty = table.getEntry("ty");
        NetworkTableEntry ta = table.getEntry("ta");
        double tx2 = tx.getDouble(0.0);
        double ty2 = ty.getDouble(0.0);
        double s7 = ta.getDouble(0.0);
        Double s8 = s7 * 0.023;

        // ta is the distance
        SmartDashboard.putNumber("distance", s8);
        SmartDashboard.putNumber("Limelight2 tx", tx2);
        SmartDashboard.putNumber("Limelight2 ty", ty2);

        // distance, mabeye?????
        // if (s7 < 279){
        // distance = false;
        //
        // if (s7)

    }

}
