
package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import com.revrobotics.CANSparkMax;
import frc.robot.subsystems.DriveTrain;

public class SwerveDriveCommand extends CommandBase {
  DoubleSupplier m_leftY;
  DoubleSupplier m_leftX;
  DoubleSupplier m_rightX;
  double leftY;
  double leftX;
  double rightX;
  DriveTrain m_DriveTrain;
  double x;
  double y;
  double rot;
  double w1ca;
  double w2ca;
  double w3ca;
  double w4ca;

  public SwerveDriveCommand(DoubleSupplier _leftY, DoubleSupplier _leftX, DoubleSupplier _rightX, DriveTrain _dTrain) {
    m_leftY = _leftY;
    m_leftX = _leftX;
    m_rightX = _rightX;
    m_DriveTrain = _dTrain;
    addRequirements(m_DriveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_DriveTrain.brDrive.setIdleMode(CANSparkMax.IdleMode.kCoast);
    m_DriveTrain.frDrive.setIdleMode(CANSparkMax.IdleMode.kCoast);
    m_DriveTrain.blDrive.setIdleMode(CANSparkMax.IdleMode.kCoast);
    m_DriveTrain.flDrive.setIdleMode(CANSparkMax.IdleMode.kCoast);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    leftY = m_leftY.getAsDouble();
    leftX = m_leftX.getAsDouble();
    rightX = m_rightX.getAsDouble();
    // Finds the X Value of the Left Stick on the Controller and Takes Care of
    // Joystick Drift
    if (Math.abs(leftX) < Constants.deadzone) {
      x = 0;
    } else {
      x = leftX;
    }

    // Finds the Y Value of the Left Stick on the Controller and Takes Care of
    // Joystick Drift
    if (Math.abs(leftY) < Constants.deadzone) {
      y = 0;
    } else {
      y = -leftY;
    }

    // Finds the X Value of the Right Stick on the Controller and Takes Care of
    // Joystick Drift
    if (Math.abs(rightX) < Constants.deadzone) {
      rot = 0;
    } else {
      rot = rightX;
    }

    double vx = x;// desired x speed
    double vy = y;// desired y speed
    double omega = rot;// desired rotation, clockwise pos in deg Check?

    double A = vx - omega * Constants.length * .6;
    double B = vx + omega * Constants.length * .6;
    double C = vy - omega * Constants.length * .6;
    double D = vy + omega * Constants.length * .6;

    // Finds Speeds for Each of the Wheels
    double w1s = Math.sqrt(Math.pow(B, 2) + Math.pow(C, 2)) / 2;
    double w2s = Math.sqrt(Math.pow(B, 2) + Math.pow(D, 2)) / 2;
    double w3s = Math.sqrt(Math.pow(A, 2) + Math.pow(D, 2)) / 2;
    double w4s = Math.sqrt(Math.pow(A, 2) + Math.pow(C, 2)) / 2;

    // Finds the Desired Angle
    double w1a = (Math.atan2(B, C) * (180 / Math.PI)) + 180;
    double w2a = (Math.atan2(B, D) * (180 / Math.PI)) + 180;
    double w3a = (Math.atan2(A, D) * (180 / Math.PI)) + 180;
    double w4a = (Math.atan2(A, C) * (180 / Math.PI)) + 180;

    /*double yaw = m_DriveTrain.gyro.getYaw() + 180;

    if (yaw == 360) {
      yaw = 0;
    }

    if (yaw <= 180) {
      yaw = yaw + 180;
    } else if (w1a > 180) {
      yaw = yaw - 180;
    }

    if (yaw == 360) {
      yaw = 0;
    }*/

    // Manipulates Degree Values so 0 is on top and degree values get bigger when
    // going clockwise

    if (w1a == 360) {
      w1a = 0;
    }
    if (w2a == 360) {
      w2a = 0;
    }
    if (w3a == 360) {
      w3a = 0;
    }
    if (w4a == 360) {
      w4a = 0;
    }

    if (w1a <= 180) {
      w1a = w1a + 180;
    } else if (w1a > 180) {
      w1a = w1a - 180;
    }

    if (w2a <= 180) {
      w2a = w2a + 180;
    } else if (w2a > 180) {
      w2a = w2a - 180;
    }

    if (w3a <= 180) {
      w3a = w3a + 180;
    } else if (w3a > 180) {
      w3a = w3a - 180;
    }

    if (w4a <= 180) {
      w4a = w4a + 180;
    } else if (w4a > 180) {
      w4a = w4a - 180;
    }

    /*
     * if (Math.abs(omega) < .1) {
     * if (yaw < 180) {
     * if (w1a == 360) {
     * w1a = 0;
     * }
     * if (w2a == 360) {
     * w2a = 0;
     * }
     * if (w3a == 360) {
     * w3a = 0;
     * }
     * if (w4a == 360) {
     * w4a = 0;
     * }
     * }
     * 
     * w1a = Math.abs(w1a - yaw);
     * w2a = Math.abs(w2a - yaw);
     * w3a = Math.abs(w3a - yaw);
     * w4a = Math.abs(w4a - yaw);
     * }
     */

    if (w1a == 360) {
      w1a = 0;
    }
    if (w2a == 360) {
      w2a = 0;
    }
    if (w3a == 360) {
      w3a = 0;
    }
    if (w4a == 360) {
      w4a = 0;
    }

    // Finds Complimentary Angle to the Desired Angle
    double w1ra = m_DriveTrain.getRevPosition(w1a);
    double w2ra = m_DriveTrain.getRevPosition(w2a);
    double w3ra = m_DriveTrain.getRevPosition(w3a);
    double w4ra = m_DriveTrain.getRevPosition(w4a);

    // Sets Max Wheel Speed
    double max = w1s;
    if (w2s > max)
      max = w2s;
    if (w3s > max)
      max = w3s;
    if (w4s > max)
      max = w4s;
    if (max > 1) {
      w1s = w1s / max;
      w2s = w2s / max;
      w3s = w3s / max;
      w4s = w4s / max;
    }

    // Finds Actual Angle of Wheels
    w1ca = (-1 * m_DriveTrain.getPosition(m_DriveTrain.frEncoder.get(), 267.4)) + 360;
    w2ca = (-1 * m_DriveTrain.getPosition(m_DriveTrain.flEncoder.get(), 120.7)) + 360;
    w3ca = (-1 * m_DriveTrain.getPosition(m_DriveTrain.blEncoder.get(), 64.7)) + 360;
    w4ca = (-1 * m_DriveTrain.getPosition(m_DriveTrain.brEncoder.get(), 335.2)) + 360;

    if (w1ca == 360) {
      w1a = 0;
    }
    if (w2ca == 360) {
      w2a = 0;
    }
    if (w3ca == 360) {
      w3a = 0;
    }
    if (w4ca == 360) {
      w4a = 0;
    }

    double w1d_1 = w1a - w1ca;
    double w1d_2 = w1ca - w1a;
    double w1d_3 = w1ra - w1ca;
    double w1d_4 = w1ca - w1ra;

    double w2d_1 = w2a - w2ca;
    double w2d_2 = w2ca - w2a;
    double w2d_3 = w2ra - w2ca;
    double w2d_4 = w2ca - w2ra;

    double w3d_1 = w3a - w3ca;
    double w3d_2 = w3ca - w3a;
    double w3d_3 = w3ra - w3ca;
    double w3d_4 = w3ca - w3ra;

    double w4d_1 = w4a - w4ca;
    double w4d_2 = w4ca - w4a;
    double w4d_3 = w4ra - w4ca;
    double w4d_4 = w4ca - w4ra;

    if (w1d_1 < 0) {
      w1d_1 = w1d_1 + 360;
    }
    if (w1d_2 < 0) {
      w1d_2 = w1d_2 + 360;
    }
    if (w1d_3 < 0) {
      w1d_3 = w1d_3 + 360;
    }
    if (w1d_4 < 0) {
      w1d_4 = w1d_4 + 360;
    }

    if (w2d_1 < 0) {
      w2d_1 = w2d_1 + 360;
    }
    if (w2d_2 < 0) {
      w2d_2 = w2d_2 + 360;
    }
    if (w2d_3 < 0) {
      w2d_3 = w2d_3 + 360;
    }
    if (w2d_4 < 0) {
      w2d_4 = w2d_4 + 360;
    }

    if (w3d_1 < 0) {
      w3d_1 = w3d_1 + 360;
    }
    if (w3d_2 < 0) {
      w3d_2 = w3d_2 + 360;
    }
    if (w3d_3 < 0) {
      w3d_3 = w3d_3 + 360;
    }
    if (w3d_4 < 0) {
      w3d_4 = w3d_4 + 360;
    }

    if (w4d_1 < 0) {
      w4d_1 = w4d_1 + 360;
    }
    if (w4d_2 < 0) {
      w4d_2 = w4d_2 + 360;
    }
    if (w4d_3 < 0) {
      w4d_3 = w4d_3 + 360;
    }
    if (w4d_4 < 0) {
      w4d_4 = w4d_4 + 360;
    }

    double value_1 = m_DriveTrain.getOptimalRoute(w1d_1, w1d_2, w1d_3, w1d_4);
    double value_2 = m_DriveTrain.getOptimalRoute(w2d_1, w2d_2, w2d_3, w2d_4);
    double value_3 = m_DriveTrain.getOptimalRoute(w3d_1, w3d_2, w3d_3, w3d_4);
    double value_4 = m_DriveTrain.getOptimalRoute(w4d_1, w4d_2, w4d_3, w4d_4);

    if (value_1 == 1) {
      m_DriveTrain.frDrive.set(w1s);
      if (w1d_1 > Constants.spinTolerance) {
        m_DriveTrain.frSteer.set(0.07);
      } else {
        m_DriveTrain.frSteer.set(0);
      }
    } else if (value_1 == 2) {
      m_DriveTrain.frDrive.set(w1s);
      if (w1d_2 > Constants.spinTolerance) {
        m_DriveTrain.frSteer.set(-0.07);
      } else {
        m_DriveTrain.frSteer.set(0);
      }
    } else if (value_1 == 3) {
      m_DriveTrain.frDrive.set(-w1s);
      if (w1d_3 > Constants.spinTolerance) {
        m_DriveTrain.frSteer.set(0.07);
      } else {
        m_DriveTrain.frSteer.set(0);
      }
    } else if (value_1 == 4) {
      m_DriveTrain.frDrive.set(-w1s);
      if (w1d_4 > Constants.spinTolerance) {
        m_DriveTrain.frSteer.set(-0.07);
      } else {
        m_DriveTrain.frSteer.set(0);
      }
    }

    if (value_2 == 1) {
      m_DriveTrain.flDrive.set(w2s);
      if (w2d_1 > Constants.spinTolerance) {
        m_DriveTrain.flSteer.set(0.07);
      } else {
        m_DriveTrain.flSteer.set(0);
      }
    } else if (value_2 == 2) {
      m_DriveTrain.flDrive.set(w2s);
      if (w2d_2 > Constants.spinTolerance) {
        m_DriveTrain.flSteer.set(-0.07);
      } else {
        m_DriveTrain.flSteer.set(0);
      }
    } else if (value_2 == 3) {
      m_DriveTrain.flDrive.set(-w2s);
      if (w2d_3 > Constants.spinTolerance) {
        m_DriveTrain.flSteer.set(0.07);
      } else {
        m_DriveTrain.flSteer.set(0);
      }
    } else if (value_2 == 4) {
      m_DriveTrain.flDrive.set(-w2s);
      if (w2d_4 > Constants.spinTolerance) {
        m_DriveTrain.flSteer.set(-0.07);
      } else {
        m_DriveTrain.flSteer.set(0);
      }
    }

    if (value_3 == 1) {
      m_DriveTrain.blDrive.set(w3s);
      if (w3d_1 > Constants.spinTolerance) {
        m_DriveTrain.blSteer.set(0.07);
      } else {
        m_DriveTrain.blSteer.set(0);
      }
    } else if (value_3 == 2) {
      m_DriveTrain.blDrive.set(w3s);
      if (w3d_2 > Constants.spinTolerance) {
        m_DriveTrain.blSteer.set(-0.07);
      } else {
        m_DriveTrain.blSteer.set(0);
      }
    } else if (value_3 == 3) {
      m_DriveTrain.blDrive.set(-w3s);
      if (w3d_3 > Constants.spinTolerance) {
        m_DriveTrain.blSteer.set(0.07);
      } else {
        m_DriveTrain.blSteer.set(0);
      }
    } else if (value_3 == 4) {
      m_DriveTrain.blDrive.set(-w3s);
      if (w3d_4 > Constants.spinTolerance) {
        m_DriveTrain.blSteer.set(-0.07);
      } else {
        m_DriveTrain.blSteer.set(0);
      }
    }

    if (value_4 == 1) {
      m_DriveTrain.brDrive.set(w4s);
      if (w4d_1 > Constants.spinTolerance) {
        m_DriveTrain.brSteer.set(0.07);
      } else {
        m_DriveTrain.brSteer.set(0);
      }
    } else if (value_4 == 2) {
      m_DriveTrain.brDrive.set(w4s);
      if (w4d_2 > Constants.spinTolerance) {
        m_DriveTrain.brSteer.set(-0.07);
      } else {
        m_DriveTrain.brSteer.set(0);
      }
    } else if (value_4 == 3) {
      m_DriveTrain.brDrive.set(-w4s);
      if (w4d_3 > Constants.spinTolerance) {
        m_DriveTrain.brSteer.set(0.07);
      } else {
        m_DriveTrain.brSteer.set(0);
      }
    } else if (value_4 == 4) {
      m_DriveTrain.brDrive.set(-w4s);
      if (w4d_4 > Constants.spinTolerance) {
        m_DriveTrain.brSteer.set(-0.07);
      } else {
        m_DriveTrain.brSteer.set(0);
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
