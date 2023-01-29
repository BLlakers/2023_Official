package frc.robot.commands;

// import java.io.Serial;
import java.util.function.DoubleSupplier;
// import javax.swing.text.Position;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class AutoCommand extends CommandBase {
  DriveTrain m_DriveTrain; // Creates an object DriveTrain 
  DoubleSupplier leftY; // Creates a Variable for the Left joystick Y position (fake controller)
  DoubleSupplier leftX; // Creates a Variable for the Left joystick X position (fake controller)
  DoubleSupplier rightX; // Creates a Variable for the right joystick X position (fake controller)
  double counter; // Creates a Variable that counts the amount of time we keep the shooter on
  int m_AutoMode = 3; // If AutoMode = 1 then run routine 1, if AutoMode = to 2 then run 2, if AutoMode equal to 3 run routine 3, otherwise don't run. 
  public AutoCommand(DriveTrain _DriveTrain) { // Creates a contrusctor for auto command (How things get set up)
    m_DriveTrain = _DriveTrain;
    addRequirements(m_DriveTrain);
  }

  @Override
  public void initialize() { // Runs once at the beginning of the command 
    m_DriveTrain.brDrive.getEncoder().setPosition(0); // Sets the position of the encoder 
    m_DriveTrain.frDrive.getEncoder().setPosition(0);
    m_DriveTrain.blDrive.getEncoder().setPosition(0);
    m_DriveTrain.flDrive.getEncoder().setPosition(0);
    m_DriveTrain.brDrive.setIdleMode(CANSparkMax.IdleMode.kBrake); // Lines 30-33 Setting SparkMax Mode (Break = completely still) (Coast = Move with extra momentum)
    m_DriveTrain.frDrive.setIdleMode(CANSparkMax.IdleMode.kBrake);
    m_DriveTrain.blDrive.setIdleMode(CANSparkMax.IdleMode.kBrake);
    m_DriveTrain.flDrive.setIdleMode(CANSparkMax.IdleMode.kBrake);
    counter = 0; // Sets counter = 0

    m_DriveTrain.drive(() -> 0, () -> 0, () -> 0);

  }

  @Override
  public void execute() { // Runs multiple times
    
    if (m_AutoMode == 1) {
    leftY = () -> -0.35; // Tells controller to move backwards on the Y axis
    leftX = () -> 0; // Tells controller not to move
    rightX = () -> 0; // Tells controller not to move (No RightY because it doesn't do anything)

  double w1ca = (-1 * m_DriveTrain.getPosition(m_DriveTrain.frEncoder.get(), 267.4)) + 360;
  double w2ca = (-1 * m_DriveTrain.getPosition(m_DriveTrain.flEncoder.get(), 120.7)) + 360;
  double w3ca = (-1 * m_DriveTrain.getPosition(m_DriveTrain.blEncoder.get(), 64.7)) + 360;
  double w4ca = (-1 * m_DriveTrain.getPosition(m_DriveTrain.brEncoder.get(), 335.2)) + 360;
  
  //System.out.println(w1ca);   
  //System.out.println(w2ca);
  //System.out.println(w3ca);
  //System.out.println(w4ca);
  //System.out.println(Math.abs(m_DriveTrain.brDrive.getEncoder().getPosition()));

  

// Author: Jared
// Date: 1/25/2023
// The purpose of the following set of instructions is to re-center the wheels to face forward, based on the sensors coming from the swerve drive.
// If The front right wheel is > 7 degress and < 173 OR > 187 and < 353 it will tell the robot to not move foward and it will re center the wheels 

if ((w1ca > 7 && w1ca < 173) || (w1ca > 187 && w1ca < 353)) { // If The front right wheel is > 7 degress and < 173 OR > 187 and < 353 it will tell the robot to not move foward and it will re center the wheels 
 m_DriveTrain.drive(() -> 0, () -> 0, () -> 0); // says not to drive 
}
else if ((w2ca > 7 && w2ca < 173) || (w2ca > 187 && w2ca < 353)) { // If The front left wheel is > 7 degress and < 173 OR > 187 and < 353 it will tell the robot to not move foward and it will re center the wheels 
  m_DriveTrain.drive(() -> 0, () -> 0, () -> 0); // says not to drive
 }
else if ((w3ca > 7 && w3ca < 173) || (w3ca > 187 && w3ca < 353)) { // If The back left wheel is > 7 degress and < 173 OR > 187 and < 353 it will tell the robot to not move foward and it will re center the wheels 
  m_DriveTrain.drive(() -> 0, () -> 0, () -> 0); // says not to drive
 }
else if ((w4ca > 7 && w4ca < 173) || (w4ca > 187 && w4ca < 353)) { // If The back right wheel is > 7 degress and < 173 OR > 187 and < 353 it will tell the robot to not move foward and it will re center the wheels 
  m_DriveTrain.drive(() -> 0, () -> 0, () -> 0); // says not to drive 
 }// else if (w1ca > 187 && w1ca < 353) {
 //m_DriveTrain.drive(() -> 0, () -> 0, () -> 0);
//} 
 else {  
 
  if (Math.abs(m_DriveTrain.brDrive.getEncoder().getPosition()) < 8.14) { // Drives until the encoder is at rotations on the motor. 1 motor rotation = 8.14 wheel rotation 
      m_DriveTrain.drive(leftY, leftX, rightX);
    } else {
      m_DriveTrain.drive(() -> 0, () -> 0, () -> 0);
     // m_Shooter.shoot(1750, botRPM);
      //if (m_Shooter.shooterReady(1750, botRPM)) {
      //  m_Shooter.ballUp();
        counter = counter + 1;
      }
    }
  } 
  if (m_AutoMode == 2) {
    leftY = () -> -0.35; // Tells controller to move backwards on the Y axis
    leftX = () -> 0; // Tells controller not to move
    rightX = () -> 0; // Tells controller not to move (No RightY because it doesn't do anything)

  double w1ca = (-1 * m_DriveTrain.getPosition(m_DriveTrain.frEncoder.get(), 267.4)) + 360;
  double w2ca = (-1 * m_DriveTrain.getPosition(m_DriveTrain.flEncoder.get(), 120.7)) + 360;
  double w3ca = (-1 * m_DriveTrain.getPosition(m_DriveTrain.blEncoder.get(), 64.7)) + 360;
  double w4ca = (-1 * m_DriveTrain.getPosition(m_DriveTrain.brEncoder.get(), 335.2)) + 360;
  
  //System.out.println(w1ca);   
  //System.out.println(w2ca);
  //System.out.println(w3ca);
  //System.out.println(w4ca);
  //System.out.println(Math.abs(m_DriveTrain.brDrive.getEncoder().getPosition()));

  

// Author: Jared
// Date: 1/25/2023
// The purpose of the following set of instructions is to re-center the wheels to face forward, based on the sensors coming from the swerve drive.
// If The front right wheel is > 7 degress and < 173 OR > 187 and < 353 it will tell the robot to not move foward and it will re center the wheels 

if ((w1ca > 7 && w1ca < 173) || (w1ca > 187 && w1ca < 353)) { // If The front right wheel is > 7 degress and < 173 OR > 187 and < 353 it will tell the robot to not move foward and it will re center the wheels 
 m_DriveTrain.drive(() -> 0, () -> 0, () -> 0); // says not to drive 
}
else if ((w2ca > 7 && w2ca < 173) || (w2ca > 187 && w2ca < 353)) { // If The front left wheel is > 7 degress and < 173 OR > 187 and < 353 it will tell the robot to not move foward and it will re center the wheels 
  m_DriveTrain.drive(() -> 0, () -> 0, () -> 0); // says not to drive
 }
else if ((w3ca > 7 && w3ca < 173) || (w3ca > 187 && w3ca < 353)) { // If The back left wheel is > 7 degress and < 173 OR > 187 and < 353 it will tell the robot to not move foward and it will re center the wheels 
  m_DriveTrain.drive(() -> 0, () -> 0, () -> 0); // says not to drive
 }
else if ((w4ca > 7 && w4ca < 173) || (w4ca > 187 && w4ca < 353)) { // If The back right wheel is > 7 degress and < 173 OR > 187 and < 353 it will tell the robot to not move foward and it will re center the wheels 
  m_DriveTrain.drive(() -> 0, () -> 0, () -> 0); // says not to drive 
 }// else if (w1ca > 187 && w1ca < 353) {
 //m_DriveTrain.drive(() -> 0, () -> 0, () -> 0);
//} 
 else {  
 
  if (Math.abs(m_DriveTrain.brDrive.getEncoder().getPosition()) < 103.4) { // Drives until the encoder is at rotations on the motor. 1 motor rotation = 8.14 wheel rotation 
      m_DriveTrain.drive(leftY, leftX, rightX);
    } else {
      m_DriveTrain.drive(() -> 0, () -> 0, () -> 0);
     // m_Shooter.shoot(1750, botRPM);
      //if (m_Shooter.shooterReady(1750, botRPM)) {
      //  m_Shooter.ballUp();
        counter = counter + 1;
      }
    }
  } 
  if (m_AutoMode == 3) {
    leftY = () -> -0.35; // Tells controller to move Foward on the Y axis
    leftX = () -> 0; // Tells controller not to move
    rightX = () -> 0; // Tells controller not to move (No RightY because it doesn't do anything)

  double w1ca = (-1 * m_DriveTrain.getPosition(m_DriveTrain.frEncoder.get(), 267.4)) + 360;
  double w2ca = (-1 * m_DriveTrain.getPosition(m_DriveTrain.flEncoder.get(), 120.7)) + 360;
  double w3ca = (-1 * m_DriveTrain.getPosition(m_DriveTrain.blEncoder.get(), 64.7)) + 360;
  double w4ca = (-1 * m_DriveTrain.getPosition(m_DriveTrain.brEncoder.get(), 335.2)) + 360;
  
  //System.out.println(w1ca);   
  //System.out.println(w2ca);
  //System.out.println(w3ca);
  //System.out.println(w4ca);
  //System.out.println(Math.abs(m_DriveTrain.brDrive.getEncoder().getPosition()));

  

// Author: Jared
// Date: 1/25/2023
// The purpose of the following set of instructions is to re-center the wheels to face forward, based on the sensors coming from the swerve drive.
// If The front right wheel is > 7 degress and < 173 OR > 187 and < 353 it will tell the robot to not move foward and it will re center the wheels 

if ((w1ca > 7 && w1ca < 173) || (w1ca > 187 && w1ca < 353)) { // If The front right wheel is > 7 degress and < 173 OR > 187 and < 353 it will tell the robot to not move foward and it will re center the wheels 
 m_DriveTrain.drive(() -> 0, () -> 0, () -> 0); // says not to drive 
}
else if ((w2ca > 7 && w2ca < 173) || (w2ca > 187 && w2ca < 353)) { // If The front left wheel is > 7 degress and < 173 OR > 187 and < 353 it will tell the robot to not move foward and it will re center the wheels 
  m_DriveTrain.drive(() -> 0, () -> 0, () -> 0); // says not to drive
 }
else if ((w3ca > 7 && w3ca < 173) || (w3ca > 187 && w3ca < 353)) { // If The back left wheel is > 7 degress and < 173 OR > 187 and < 353 it will tell the robot to not move foward and it will re center the wheels 
  m_DriveTrain.drive(() -> 0, () -> 0, () -> 0); // says not to drive
 }
else if ((w4ca > 7 && w4ca < 173) || (w4ca > 187 && w4ca < 353)) { // If The back right wheel is > 7 degress and < 173 OR > 187 and < 353 it will tell the robot to not move foward and it will re center the wheels 
  m_DriveTrain.drive(() -> 0, () -> 0, () -> 0); // says not to drive 
 }// else if (w1ca > 187 && w1ca < 353) {
 //m_DriveTrain.drive(() -> 0, () -> 0, () -> 0);
//} 
 else {  
 
  if (Math.abs(m_DriveTrain.brDrive.getEncoder().getPosition()) < 40.7) { // Drives until the encoder is at rotations on the motor. 1 motor rotation = 8.14 wheel rotation 
      m_DriveTrain.drive(leftY, leftX, rightX);
    } else {
      m_DriveTrain.drive(() -> 0, () -> 0, () -> 0);
     // m_Shooter.shoot(1750, botRPM);
      //if (m_Shooter.shooterReady(1750, botRPM)) {
      //  m_Shooter.ballUp();
        counter = counter + 1;
      }
    }
  } 
}



  @Override
  public void end(boolean interrupted) {
    //m_Shooter.shooterOff();
    //m_Shooter.retract();
  }

  @Override
  public boolean isFinished() {
    if(counter < 40) {
      return false;
    } else {
      return true;
    }
  }
} 
