
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
//import frc.robot.subsystems.Shooter;
//import edu.wpi.first.wpilibj.PneumaticHub;


public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;
  //Shooter m_shooter;
  //PneumaticHub pneumaticHub = new PneumaticHub(20);
  
  
  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    //pneumaticHub.enableCompressorAnalog(Constants.minPSI, Constants.maxPSI);
  }


  @Override
  public void robotPeriodic() {
    //SmartDashboard.putNumber("Pressure", pneumaticHub.getPressure(0));
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {

  }

  @Override
  public void disabledPeriodic() {

  }

  @Override
  public void autonomousInit() {

    //m_robotContainer.m_DriveTrain.gyro.reset();

    //m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
   }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {

    //m_robotContainer.m_DriveTrain.gyro.reset();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    
  }

  @Override
  public void teleopPeriodic() {

  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {

  }

  @Override
  public void simulationInit() {
    
  }

  @Override
  public void simulationPeriodic() {

  }
}
