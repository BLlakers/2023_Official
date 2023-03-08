package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

//some imports no longer needed but leaving them here untill final version

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
  }

  @Override
  public void disabledInit() {

  }

  @Override
  public void disabledPeriodic() {

  }

  @Override
  public void autonomousInit() {
    m_robotContainer.m_Arm.ArmPosition = 1;
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    m_robotContainer.m_DriveTrain.startYaw = m_robotContainer.m_DriveTrain.getGyroYaw();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {
   m_robotContainer.m_Arm.ArmPosition = 1;
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
      
    }
    //m_robotContainer.m_DriveTrain.startYaw = m_robotContainer.m_DriveTrain.getGyroYaw();

  }

  @Override
  public void teleopPeriodic() {
    //WP - Was not compiling as of 3/4, to be addressed
    //cameraTest();
    SmartDashboard.putNumber("Start Yaw", m_robotContainer.m_DriveTrain.startYaw);
   
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
  

