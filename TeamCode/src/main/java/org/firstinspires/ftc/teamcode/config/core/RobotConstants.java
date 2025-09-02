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
//    public static int OUTTAKE_PIVOT_BUCKET = 0;
//    public static int OUTTAKE_PIVOT_HUMAN = -400;
//    public static int OUTTAKE_PIVOT_PREPARE_CHAMBER = -3500;
//    public static int OUTTAKE_PIVOT_SCORE_CHAMBER = -800;
    public static int OUTTAKE_PIVOT_HUMAN = 400;
    public static int OUTTAKE_PIVOT_PREPARE_CHAMBER = 3500;
    public static int OUTTAKE_PIVOT_SCORE_CHAMBER = 800;
    public static int OUTTAKE_PIVOT_PARK = 0;
    public static double OUTTAKE_ELBOW_TRANSFER = 0;
    public static double OUTTAKE_ELBOW_HUMAN = 0.6;
    public static double OUTTAKE_ELBOW_CHAMBER = 0.5;
    public static double OUTTAKE_ELBOW_SCORE = 0.46;


//    // Intake (horizontal arm claw)
//    public static final String SERVO_INTAKE_GRAB = "intakeGrab";
//    public static final String SERVO_INTAKE_PIVOT = "intakePivot";
//    public static final String SERVO_INTAKE_ROTATE = "intakeRotate";
//
//    public static double INTAKE_GRAB_CLOSE = 0;
//    public static double INTAKE_GRAB_OPEN = 0;
//    public static double INTAKE_ROTATE_MIN = 0; // Position that corresponds to -90 degree rotation
//    public static double INTAKE_ROTATE_MAX = 0; // Position that corresponds to +90 degree rotation
//    public static double INTAKE_PIVOT_GROUND = 0;
//    public static double INTAKE_PIVOT_HOVER = 0;
//    public static double INTAKE_PIVOT_TRANSFER = 0;
//    public static double INTAKE_PIVOT_PARK = 0;

    // Linkage slides *for testing* (horizontal extension)
    public static final String SERVO_LINKAGE_LEFT = "linkageLeft";
    public static final String SERVO_LINKAGE_RIGHT = "linkageRight";

    public static double LINKAGE_ZERO = 0.45;
    public static double LINKAGE_FULL = 0.77;

    // Because we need the 5turn and 300 degrees moving at the same rate
    public static double ELBOW_COEF = 6;
    public enum ElbowState { //not used for now
        DOWN(0),
        TRANSFER(0.75),
        INTAKE(0.35);

        public final double pos;

        ElbowState(double pos) { this.pos = pos; }
    }

    public static String SERVO_HORIZONTAL_ELBOW_LEFT = "rotLeft";
    public static String SERVO_HORIZONTAL_ELBOW_RIGHT = "rotRight";

    public enum WristState {
        TRANSFER(0.8),
        DOWN(0.3),
        UP(1);

        public final double pos;

        WristState(double pos) { this.pos = pos; }
    }
    public static String SERVO_HORIZONTAL_WRIST = "inAngle";
    public static double CLAW_ROTATION_MIN = 0.4;
    public static double CLAW_ROTATION_MAX = 0.6;
    public enum GrabState {
        OPEN(0),
        CLOSED(0.225);

        public final double pos;

        GrabState(double pos) { this.pos = pos; }
    }
    public static String SERVO_HORIZONTAL_GRAB = "inGrab";

    public static String SERVO_HORIZONTAL_ROTATION = "inTwist";

    public enum HorizontalArmState {
        TRANSFER(0.78, 0.8),
        INTAKE(0.4, 0.3),
        IN_ROBOT(0.78, 0.9),
        CLEAR_BAR(0.6, 0.4),
        HOVER(0.5, 0.3);

        public final double elbowPos, wristPos;

        HorizontalArmState(double elbowPos, double wristPos) {
            this.elbowPos = elbowPos;
            this.wristPos = wristPos;
        }
    }

    public enum LinkageState {
        RETRACTED(0.77),
        EXTENDED(1),
        TRANSFER(0.87);

        public final double pos;

        LinkageState(double pos) { this.pos = pos; }
    }


}
