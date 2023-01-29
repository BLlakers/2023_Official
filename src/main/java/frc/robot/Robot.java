//Hey Jared
package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.TimedRobot;
// import edu.wpi.first.wpilibj.DriverStation;
// import edu.wpi.first.wpilibj.SPI;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.WaitCommand;
// import frc.robot.subsystems.DriveTrain;


public class Robot extends TimedRobot {
  AHRS ahrs;
  
  private Command m_autonomousCommand;
  private Command m_waitcommand;
  private RobotContainer m_robotContainer;
  //Shooter m_shooter;
  //PneumaticHub pneumaticHub = new PneumaticHub(20);
  
  
  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
   //  try {
    // ahrs = new AHRS(SPI.Port.kMXP); /* Alternatives:  SPI.Port.kMXP, I2C.Port.kMXP or SerialPort.Port.kUSB */
  // } catch (Exception ex) {
  //  DriverStation.reportError("Error instantiating NavX kMXP" + ex.getMessage(), true);
  //  }
    
  }


  @Override
  public void robotPeriodic() {
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
    
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    m_waitcommand = new WaitCommand(5);
    
    if (m_autonomousCommand != null) {
      m_waitcommand.schedule();
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
