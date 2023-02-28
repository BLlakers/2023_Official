package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Robot extends TimedRobot {
  
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;
  
  
  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();  
    CameraServer.startAutomaticCapture();

  }


  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    cameraTest();
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
    
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
   }
  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    cameraTest();
   
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

  public void cameraTest() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    double camerax = tx.getDouble(0.0);
    double cameray = ty.getDouble(0.0);
    var aligncamera = camerax;

    SmartDashboard.putNumber("Limelight X", camerax);
    SmartDashboard.putNumber("Limelight Y", cameray);

    SmartDashboard.putNumber("Limelight X", camerax);
    SmartDashboard.putNumber("Limelight Y", cameray);

    if (13.5 <= camerax && 5.7 <= camerax) {
    //  System.out.println("alligned");

    } else if (13.5 >= camerax && 5.7 >= camerax) { 
     // System.out.println("NOT alligned");
      //this works, but i has tons of delay and i think it is because of the procesing power of the robo rio
    } 
    }

  

      //this works, but i has tons of delay and i think it is because of the procesing power of the robo rio
    } 
   
   
      

    
  

    //System.out.println(x, cameraTest());
  
  

