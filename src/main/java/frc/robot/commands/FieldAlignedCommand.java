package frc.robot.commands;

import java.util.function.DoubleSupplier;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;



public class FieldAlignedCommand extends CommandBase {
    DriveTrain m_DriveTrain;
    RobotContainer m_RobotContainer;

    public FieldAlignedCommand(DriveTrain _DriveTrain) {
        m_DriveTrain = _DriveTrain;
        addRequirements(m_DriveTrain);

    }

    @Override
    public void initialize() {

    }
    /*  Here we will need to make a while press statement for while we press button b it will 
    move in either direction to get aligned within 5-10 degrees of absolute zero 
    for negative to the left for positve to the right
    */
    
    @Override
    public void execute() {

            if (m_DriveTrain.getGyroYaw() - m_DriveTrain.startYaw > 0){
                m_DriveTrain.drive(() -> 0, () -> 0, () -> 1);
            }
            if (m_DriveTrain.getGyroYaw() - m_DriveTrain.startYaw < 0) {
                m_DriveTrain.drive(() -> 0, () -> 0, () -> 1);
            }        
    }   
    
    @Override
    public void end(boolean interrupted) {
        
    }
/// Hello?

    @Override
    public boolean isFinished() {
        return false;
    }
}
