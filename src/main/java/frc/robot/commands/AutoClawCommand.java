package frc.robot.commands;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Claw;


public class AutoClawCommand extends CommandBase {
    Claw m_Claw;
    int Time = 0;
    int Time2 = 0;
    public AutoClawCommand(Claw _Claw) {
     m_Claw = _Claw;
     addRequirements(m_Claw);
    }
    public void initialize() {
    int Time = 0;
    int Time2 = 0;
    }

    @Override
    public void execute() {
        if (m_Claw.SensingPiece == true){
            m_Claw.exampleDoublePH.set(Value.kForward);

            //counts how many thimes this has run because we only want it to close the calw once
            //Time2 = Time + 1;
            //Time = Time2;
            
            //if (Time2 == 1){
            //    //close the claw if it has run once  
            //    m_Claw.exampleDoublePH.set(Value.kForward);
            //
            //} else {
            //    //just a placeholder because now you do nothing
            //}

        } else {
            Time = 0;
            Time2 = 0;
        }
    }
    
}
