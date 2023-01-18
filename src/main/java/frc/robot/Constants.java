
package frc.robot;

import frc.robot.Other.Gains;

public final class Constants {
    
    //Controller
    public static int DriverControllerChannel = 0;
    public static int ManipControllerChannel = 1;
    public static int buttonA = 1;
    public static int buttonB = 2;
    public static int buttonX = 3;
    public static int buttonY = 4;
    public static int buttonLeft = 5;
    public static int buttonRight = 6;
    public static double deadzone = 0.1;

    //Drive Train
    public static int blSteerMotorChannel = 1;
    public static int blDriveMotorChannel = 2;
    public static int flDriveMotorChannel = 3;
    public static int flSteerMotorChannel = 4;
    public static int frSteerMotorChannel = 5;
    public static int frDriveMotorChannel = 6;
    public static int brDriveMotorChannel = 7;
    public static int brSteerMotorChannel = 8;
    public static int blEncoderChannel = 0;
    public static int flEncoderChannel = 1;
    public static int frEncoderChannel = 2;
    public static int brEncoderChannel = 3;
    public static double spinTolerance = 5;
    public static double length = 0.58;
    public static double width = 0.58;

    //Shooter
    public static int topShooterMotorChannel = 9;
    public static int botShooterMotorChannel = 10;
    public static double topRPM= 1750;
    public static double botRPM= 6250;
    public static int kPIDLoopIdx = 0;
    public static int kTimeoutMs = 30;
    public static Gains kGains_Velocity = new Gains( 0.045, 0, 0, 0, 0,  1.00);
    public static double acceptableRpmError = 0.05;

    //Intake
    public static int intakeMotorChannel = 11;
    public static int upperIntakeMotorChannel = 13;
    public static int intakeDown = 7;
    public static int intakeUp = 8;
    public static int ballUp = 9;
    public static int ballDown = 10;
    public static int frontIRChannel = 0;
    public static int backIRChannel = 1;
    public static double spinSpeed = .85;

    //Endgame
    public static double minPSI = 90;
    public static double maxPSI = 120;
    public static int barTwoWinchChannel = 12;
    public static int barThreeWinchChannel = 14;
    public static int barFourWinchChannel = 13;
    public static int barTwoArmUp = 6;
    public static int barTwoArmDown = 11;
    public static int barTwoClamp= 2;
    public static int barTwoNoClamp = 3;
    public static int barThreeArmUp = 12;
    public static int barThreeArmDown = 13;
    public static int barThreeClamp = 14;
    public static int barThreeNoClamp = 15;
    public static int barFourArmUp = 4;
    public static int barFourArmDown = 5;
    public static double winchSpeed = -.75;
    public static double barTwoWinchEncoderTicks;
    public static double barThreeWinchEncoderTicks;
    public static double barFourWinchEncoderTicks;
    

}