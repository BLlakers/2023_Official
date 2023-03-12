package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
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

            if (m_DriveTrain.getGyroYaw() - m_DriveTrain.startYaw > 10){
                m_DriveTrain.drive(() -> 0, () -> 0, () -> 1, false);
            }
            if (m_DriveTrain.getGyroYaw() - m_DriveTrain.startYaw < -10) {
                m_DriveTrain.drive(() -> 0, () -> 0, () -> -1, false);
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
