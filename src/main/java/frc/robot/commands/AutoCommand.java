package frc.robot.commands;
import java.util.function.DoubleSupplier;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;
// import frc.robot.subsystems.Shooter;

public class AutoCommand extends CommandBase {
  DriveTrain m_DriveTrain; // Creates an object DriveTrain 
  // Shooter m_Shooter; // Creates an object Shooter
  DoubleSupplier leftY; // Creates a Variable for the Left joystick Y position (fake controller)
  DoubleSupplier leftX; // Creates a Variable for the Left joystick X position (fake controller)
  DoubleSupplier rightX; // Creates a Variable for the right joystick X position (fake controller)
  double counter; // Creates a Variable that counts the amount of time we keep the shooter on

  public AutoCommand(DriveTrain _DriveTrain) { // Creates a contrusctor for auto command (How things get set up)
    m_DriveTrain = _DriveTrain;
   // m_Shooter = _Shooter;
    addRequirements(m_DriveTrain);
  }

  @Override
  public void initialize() { // Runs once at the beginning of the command 
    m_DriveTrain.brDrive.getEncoder().setPosition(0); // Sets the position of the encoder 
    m_DriveTrain.brDrive.setIdleMode(CANSparkMax.IdleMode.kBrake); // Lines 30-33 Setting SparkMax Mode (Break = completely still) (Coast = Move with extra momentum)
    m_DriveTrain.frDrive.setIdleMode(CANSparkMax.IdleMode.kBrake);
    m_DriveTrain.blDrive.setIdleMode(CANSparkMax.IdleMode.kBrake);
    m_DriveTrain.flDrive.setIdleMode(CANSparkMax.IdleMode.kBrake);
    counter = 0; // Sets counter = 0
  }

  @Override
  public void execute() { // Runs multiple times
    leftY = () -> -0.35; // Tells controller to move backwards on the Y axis
    leftX = () -> 0; // Tells controller not to move
    rightX = () -> 0; // Tells controller not to move (No RightY because it doesn't do anything)

   // double distance = m_Shooter.getDistance();
   // double botRPM = /* m_Shooter.getEquationRPM(distance) */3750;
   //System.out.println(m_DriveTrain.brDrive.getEncoder().getPosition()); // Drives until the encoder is at 52 Rotations on the motor 
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
