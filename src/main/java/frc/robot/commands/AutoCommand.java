package frc.robot.commands;

// import java.io.Serial;
import java.util.function.DoubleSupplier;
// import javax.swing.text.Position;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainPID;

public class AutoCommand extends CommandBase {
  DriveTrainPID m_DriveTrain; // Creates an object DriveTrain
  double leftY; // Creates a Variable for the Left joystick Y position (fake controller)
  double leftX; // Creates a Variable for the Left joystick X position (fake controller)
  double rightX; // Creates a Variable for the right joystick X position (fake controller)
  double counter; // Creates a Variable that counts the amount of time we keep the shooter on
  double rotation; // Rotation Stuff Thingymajig
  int a = 5; // counter if you wnat it put it that way
  int m_AutoMode; // If AutoMode = 1 then run routine 1, if AutoMode = to 2 then run 2, if
                  // AutoMode equal to 3 run routine 3, otherwise don't run.

  public AutoCommand(DriveTrainPID _DriveTrain, int _AutoMode) { // Creates a contrusctor for auto command (How things
                                                                 // get
    // set up)
    m_DriveTrain = _DriveTrain;
    m_AutoMode = _AutoMode;
    addRequirements(m_DriveTrain);
  }

  @Override
  public void initialize() { // Runs once at the beginning of the command
    m_DriveTrain.m_backRight.m_driveMotor.getEncoder().setPosition(0); // Sets the position of the encoder
    m_DriveTrain.m_frontRight.m_driveMotor.getEncoder().setPosition(0);
    m_DriveTrain.m_backLeft.m_driveMotor.getEncoder().setPosition(0);
    m_DriveTrain.m_frontLeft.m_driveMotor.getEncoder().setPosition(0);
    // Sets counter = 0

    m_DriveTrain.drive(0, 0, 0, false, false);

  }

  @Override
  public void execute() { // Runs multiple times

    leftY = -0.40; // Tells controller to move backwards on the Y axis, was -0.35
    leftX = 0.0; // Tells controller not to move
    rightX = 0.0; // Tells controller not to move (No RightY because it doesn't do anything)
    rotation = 0.9;

    /*
     * double w1ca = (-1 * m_DriveTrain.getPosition(m_DriveTrain.frEncoder.get(),
     * 267.4)) + 360;
     * double w2ca = (-1 * m_DriveTrain.getPosition(m_DriveTrain.flEncoder.get(),
     * 120.7)) + 360;
     * double w3ca = (-1 * m_DriveTrain.getPosition(m_DriveTrain.blEncoder.get(),
     * 64.7)) + 360;
     * double w4ca = (-1 * m_DriveTrain.getPosition(m_DriveTrain.brEncoder.get(),
     * 335.2)) + 360;
     */
    // Author: Jared
    // Date: 1/25/2023
    // The purpose of the following set of instructions is to re-center the wheels
    // to face forward, based on the sensors coming from the swerve drive.
    // If The front right wheel is > 7 degress and < 173 OR > 187 and < 353 it will
    // tell the robot to not move foward and it will re center the wheels

    /*
     * if ((w1ca > 7 && w1ca < 173) || (w1ca > 187 && w1ca < 353)) { // If The front
     * right wheel is > 7 degress and < 173
     * // OR > 187 and < 353 it will tell the robot to not
     * // move foward and it will re center the wheels
     * m_DriveTrain.drive(0, 0, 0, false, false); // says not to drive
     * } else if ((w2ca > 7 && w2ca < 173) || (w2ca > 187 && w2ca < 353)) { // If
     * The front left wheel is > 7 degress and
     * // < 173 OR > 187 and < 353 it will tell the
     * // robot to not move foward and it will re
     * // center the wheels
     * m_DriveTrain.drive(0, 0, 0, false, false); // says not to drive
     * } else if ((w3ca > 7 && w3ca < 173) || (w3ca > 187 && w3ca < 353)) { // If
     * The back left wheel is > 7 degress and
     * // < 173 OR > 187 and < 353 it will tell the
     * // robot to not move foward and it will re
     * // center the wheels
     * m_DriveTrain.drive(0, 0, 0, false, false); // says not to drive
     * } else if ((w4ca > 7 && w4ca < 173) || (w4ca > 187 && w4ca < 353)) { // If
     * The back right wheel is > 7 degress and
     * // < 173 OR > 187 and < 353 it will tell the
     * // robot to not move foward and it will re
     * // center the wheels
     * m_DriveTrain.drive(0, 0, 0, false, false); // says not to drive
     * } else {
     */

    /*
     * if (m_AutoMode == 1) {
     * if
     * (Math.abs(m_DriveTrain.m_backRight.m_driveMotor.getEncoder().getPosition()) <
     * 115.0597 ) { // Drives until the encoder is at
     * // rotations on the motor. 1 motor
     * // rotation = 8.14 wheel rotation
     * //115.0597
     * m_DriveTrain.drive(-leftY, leftX, rightX, false, false);
     * } else {
     * m_DriveTrain.drive(0, 0, 0, false, false);
     * counter = counter + 1;
     * }
     * }
     * 
     * else if (m_AutoMode == 2) {
     * if
     * (Math.abs(m_DriveTrain.m_backRight.m_driveMotor.getEncoder().getPosition()) <
     * 59.7) { // Drives until the encoder is at
     * // rotations on the motor. 1 motor
     * // rotation = 8.14 wheel rotation (11
     * // inches = wheel)
     * m_DriveTrain.drive(-leftY, leftX, rightX, false, false);
     * } else {
     * m_DriveTrain.drive(0, 0, 0, false, true);
     * counter = counter + 1;
     * }
     * }
     * 
     * else if (m_AutoMode == 3) {
     * if
     * (Math.abs(m_DriveTrain.m_backRight.m_driveMotor.getEncoder().getPosition()) <
     * 125.0597) { // Drives until the encoder is at
     * // rotations on the motor. 1 motor
     * // rotation = 8.14 wheel rotation
     * m_DriveTrain.drive(-leftY, leftX, rightX, false, false);
     * } else {
     * m_DriveTrain.drive( 0, 0, 0, false, false);
     * counter = counter + 1;
     */

   //System.out.println(Math.abs(m_DriveTrain.m_backRight.m_driveMotor.getEncoder().getPosition()));
  
    if (m_AutoMode == 4) {
      if (a == 5){
          System.out.println("ONE");
        m_DriveTrain.drive(0, 0, rotation, false, false);
        if (Math.abs(m_DriveTrain.m_backRight.m_driveMotor.getEncoder().getPosition()) > 19.5 ) {
          System.out.println("TWO");
          a = 1;}
      }
      if (Math.abs(m_DriveTrain.m_backRight.m_driveMotor.getEncoder().getPosition()) < 104.5) {
        m_DriveTrain.drive(-leftY, leftX, rightX, false, false);
        System.out.println("THREE");
      } else if (a == 1) {
        System.out.println("TWO");
        m_DriveTrain.drive(0, 0, -rotation, false, false);
        if (Math.abs(m_DriveTrain.m_backRight.m_driveMotor.getEncoder().getPosition()) > 124) {
          System.out.println("THREE");
          a = 2;
        } 
      }
       
      else if (Math.abs(m_DriveTrain.m_backRight.m_driveMotor.getEncoder().getPosition()) < 313) {
        System.out.println("FOUR");
        m_DriveTrain.drive(-leftY, leftX, rightX, false, false);
      }
      else if (a == 2) {
        System.out.println("FIVE");
        m_DriveTrain.drive(0, 0, -rotation, false, false);
        if (Math.abs(m_DriveTrain.m_backRight.m_driveMotor.getEncoder().getPosition()) > 332.5) {
          System.out.println("SIX");
          a = 0;
        }
      }
      if (a == 0) {
        m_DriveTrain.drive(-leftY, leftX, rightX, false, false);
        if (Math.abs(m_DriveTrain.m_backRight.m_driveMotor.getEncoder().getPosition()) > 389.5) {
          m_DriveTrain.drive(0, 0, 0, false, true);
        }
      }
      }
     if (m_AutoMode == 5) {
      // reason why numbers so high because you cant reset neo encoders back to zero.
      // you can on talon motors, but not these.

      if (Math.abs(m_DriveTrain.m_backRight.m_driveMotor.getEncoder().getPosition()) < 132.105011933) {
        m_DriveTrain.drive(-leftY, leftX, rightX, false, false);
        // moves forwards untill 132.105011933 rotations is reached. moves 132.105011933
        // rotations on top of 0 already travled
        // m_DriveTrain.drive( 0, 0, rotation, false, false);
      }

      else if (a == 1) {
        // first turn
        m_DriveTrain.drive(0, 0, rotation, false, false);
        if (Math.abs(m_DriveTrain.m_backRight.m_driveMotor.getEncoder().getPosition()) < 152.097011933) {
          // moves 19.992 rotations on top of 132.105011933 already turned
          // moves 19.992 rotations at the motor, but at the wheels the equates to roughly
          // 2.45 roations which (for 8 inch circumfrance wheels) should turn you 90
          // degreese
          // if wheels not 8 inch, then this number will need to change
          // reads drive motor, not turning motor because the turning motor turns the
          // wheels directin its facing, not the robot itself
          a = 2;
          // this is so it goes to the second turn
        }
      } else if (Math.abs(m_DriveTrain.m_backRight.m_driveMotor.getEncoder().getPosition()) < 315.285556086) {
        // moves forwards untill 315.285556086 rotations is reached. moves 163.188544153
        // rotations on top of 152.097011933 already travled
        m_DriveTrain.drive(-leftY, leftX, rightX, false, false);

        // m_DriveTrain.drive( 0, 0, rotation, false, false);
      }

      else if (a == 2) {
        // second turn
        m_DriveTrain.drive(0, 0, rotation, false, false);
        if (Math.abs(m_DriveTrain.m_backRight.m_driveMotor.getEncoder().getPosition()) < 335.277556086) {
          // moves forwards untill 335.277556086 rotations is reached. moves 19.992
          // rotations on top of 315.285556086 already travled
          a = 0;
          // resets so now no turn
        }
      } else if (Math.abs(m_DriveTrain.m_backRight.m_driveMotor.getEncoder().getPosition()) < 381.9028544153) {
        // moves forwards untill 381.9028544153 rotations is reached. moves
        // 46.6252983293 rotations on top of 335.277556086 already travled
        m_DriveTrain.drive(-leftY, leftX, rightX, false, false);
      }

    }

  }

  @Override
  public void end(boolean interrupted) {
    m_DriveTrain.drive(0, 0, 0, false, true);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
