package org.firstinspires.ftc.teamcode.config.core;

import com.acmerobotics.dashboard.config.Config;

@Config
public class RobotConstants {

    // CHASSIS ---
    public static final String RIGHT_FRONT_MOTOR = "rightFront";
    public static final String RIGHT_REAR_MOTOR = "rightRear";
    public static final String LEFT_FRONT_MOTOR = "leftFront";
    public static final String LEFT_REAR_MOTOR = "leftRear";
    // Outtake (vertical arm claw + pivot)
    public static final String SERVO_OUTTAKE_GRAB = "outGrab";
    public static final String SERVO_OUTTAKE_ROTATE = "outTwist";
    public static final String SERVO_OUTTAKE_ELBOW = "outAngle";
    public static final String MOTOR_OUTTAKE_PIVOT_RIGHT = "pivotRight";
    public static final String MOTOR_OUTTAKE_PIVOT_LEFT = "pivotLeft";

    public static double OUTTAKE_kP = 0.0008; // Proportional gain
    public static double OUTTAKE_kI = 0; // Integral gain
    public static double OUTTAKE_kD = 0.00005; // Derivative gain
    public static double OUTTAKE_kF = 0; // Feedforward gain
    public static double OUTTAKE_ERROR_TOLERANCE = 10;

    public static double OUTTAKE_GRAB_CLOSE = 0;
    public static double OUTTAKE_GRAB_MID_OPEN = 0.05;
    public static double OUTTAKE_GRAB_OPEN = 0.2;
    public static double OUTTAKE_ROTATE_NORMAL = 0;
    public static double OUTTAKE_ROTATE_FLIPPED = 0.75;
    public static int OUTTAKE_PIVOT_TRANSFER = 0;
    public static int OUTTAKE_PIVOT_HUMAN = 400;
    public static int OUTTAKE_PIVOT_PREPARE_CHAMBER = 3500;
    public static int OUTTAKE_PIVOT_SCORE_CHAMBER = 800;
    public static int OUTTAKE_PIVOT_PARK = 0;
    public static double OUTTAKE_ELBOW_TRANSFER = 0;
    public static double OUTTAKE_ELBOW_HUMAN = 0.6;
    public static double OUTTAKE_ELBOW_CHAMBER = 0.5;
    public static double OUTTAKE_ELBOW_SCORE = 0.46;

    // Linkage slides
    public static final String SERVO_LINKAGE_LEFT = "linkageLeft";
    public static final String SERVO_LINKAGE_RIGHT = "linkageRight";

    public static double LINKAGE_ZERO = 0.45;
    public static double LINKAGE_FULL = 0.77;

    // Because we need the 5turn and 300 degrees moving at the same rate
    public static double ROTATOR_COEF = 0;
    public static String SERVO_HORIZONTAL_ROTATOR_LEFT = "rotLeft";
    public static String SERVO_HORIZONTAL_ROTATOR_RIGHT = "rotRight";
    public static String SERVO_HORIZONTAL_ANGLE = "inAngle";
    public static double CLAW_TWIST_MIN = 0.55;
    public static double CLAW_TWIST_DEFAULT = 0.6;
    public static double CLAW_TWIST_MAX = 0.65;
    public static double CLAW_TWIST_FLIPPED = 0.5;
    public enum GrabState {
        OPEN(0),
        CLOSED(0.225);

        public final double pos;

        GrabState(double pos) { this.pos = pos; }
    }
    public static String SERVO_HORIZONTAL_GRAB = "inGrab";
    public static String SERVO_HORIZONTAL_ROTATION = "inTwist";

    public static double ROTATOR_DOWN = 0;
    public static double ROTATOR_UP = 1;
    public static double IN_ANGLE_UP = 0.3;
    public static double IN_ANGLE_DOWN = 0.9;

    public enum LinkageState {
        RETRACTED(0.77),
        EXTENDED(1),
        TRANSFER(0.87);

        public final double pos;

        LinkageState(double pos) { this.pos = pos; }
    }


}
