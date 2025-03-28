package org.firstinspires.ftc.teamcode.config.core;

import com.acmerobotics.dashboard.config.Config;

@Config
public class RobotConstants {
    @Config
    public static class DriveConstants {
        public static final String MOTOR_LEFT_FRONT = "leftFront";
        public static final String MOTOR_RIGHT_FRONT = "rightFront";
        public static final String MOTOR_LEFT_BACK = "leftRear";
        public static final String MOTOR_RIGHT_BACK = "rightRear";

        public static final String PINPOINT_NAME = "pinpoint";
    }

    public static final String EXTEND_MOTOR = "rightFront"; // Temporary
//        public static final GoBILDA MOTOR_TYPE = GoBILDA.RPM_435; // Don't know the correct motor
    public static double POWER_THRESHOLD = 0.01;
    public static double kP = 0; // Proportional gain
    public static double kI = 0; // Integral gain
    public static double kD = 0; // Derivative gain
    public static double kF = 0; // Feedforward gain
    public static double ERROR_TOLERANCE = 5;

    public static int EXTEND_ZERO = 0;
    public static int EXTEND_TRANSFER = 0;
    public static int EXTEND_FULL = 300;
}
