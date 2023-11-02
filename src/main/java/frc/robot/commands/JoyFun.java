package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class JoyFun extends CommandBase{
    static int m_num;
    public static Command vJoyFunc(int _d) {
        m_num = _d;
        System.out.println(m_num);
        return null;
    
    }
}


