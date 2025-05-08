package org.firstinspires.ftc.teamcode.config.core;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.localization.Pose;

@Config
public class RobotConstants {

//    public enum AllianceColor {
//        RED,
//        BLUE
//    }
//
//    public enum PoseLocationName {
//        BUCKET,
//        OBSERVATION
//    }
//
//    public static AllianceColor allianceColor = AllianceColor.RED;
//    public static PoseLocationName poseLocationName;
//
//    public static Pose subSample1 = new Pose(62.000, 93.700, Math.toRadians(90));
//    public static Pose subSample2 = new Pose(62.000, 93.700, Math.toRadians(90));
//    public static Pose autoEndPose = new Pose(0, 0, Math.toRadians(0));
//
//    // Robot Width and Length (in centimeters)
//    public static double ROBOT_WIDTH = 0;
//    public static double ROBOT_LENGTH = 0;

    // Extend (horizontal extension)
    public static final String MOTOR_EXTEND = "extend";

    public static double EXTEND_kP = 0.005; // Proportional gain
    public static double EXTEND_kI = 0; // Integral gain
    public static double EXTEND_kD = 0.0001; // Derivative gain
    public static double EXTEND_ERROR_TOLERANCE = 5;

    public static int EXTEND_ZERO = 0;
    public static int EXTEND_TRANSFER = 0;
    public static int EXTEND_FULL = 300;


    // Lift (vertical lift)
    public static final String MOTOR_LIFT_LEFT = "leftLift";
    public static final String MOTOR_LIFT_RIGHT = "rightLift";

    public static double LIFT_kP = 0.005; // Proportional gain
    public static double LIFT_kI = 0; // Integral gain
    public static double LIFT_kD = 0.0001; // Derivative gain
    public static double LIFT_kF = 0; // Feedforward gain
    public static double LIFT_ERROR_TOLERANCE = 10;

    public static int LIFT_ZERO = 0;
    public static int LIFT_HIGH_BASKET = 0;
    public static int LIFT_LOW_BASKET = 0;
    public static int LIFT_TO_CHAMBER = 0;
    public static int LIFT_SCORE_CHAMBER = 0;
    public static int LIFT_HUMAN_PLAYER = 0;
    public static int LIFT_TRANSFER = 0;


    // Outtake (vertical arm claw + pivot)
    public static final String SERVO_OUTTAKE_GRAB = "outtakeGrab";
    public static final String SERVO_OUTTAKE_ROTATE = "outtakeRotate";
    public static final String MOTOR_OUTTAKE_PIVOT_RIGHT = "pivotR";
    public static final String MOTOR_OUTTAKE_PIVOT_LEFT = "pivotL";


    public static double OUTTAKE_kP = 0.005; // Proportional gain
    public static double OUTTAKE_kI = 0; // Integral gain
    public static double OUTTAKE_kD = 0; // Derivative gain
    public static double OUTTAKE_kF = 0; // Feedforward gain
    public static double OUTTAKE_ERROR_TOLERANCE = 20;

    public static double OUTTAKE_GRAB_CLOSE = 0;
    public static double OUTTAKE_GRAB_OPEN = 0;
    public static double OUTTAKE_ROTATE_NORMAL = 0;
    public static double OUTTAKE_ROTATE_FLIPPED = 0;
    public static int OUTTAKE_PIVOT_TRANSFER = 0;
    public static int OUTTAKE_PIVOT_BUCKET = 0;
    public static int OUTTAKE_PIVOT_HUMAN = 0;
    public static int OUTTAKE_PIVOT_CHAMBER = 0;
    public static int OUTTAKE_PIVOT_PARK = 0;

    // Intake (horizontal arm claw)
    public static final String SERVO_INTAKE_GRAB = "intakeGrab";
    public static final String SERVO_INTAKE_PIVOT = "intakePivot";
    public static final String SERVO_INTAKE_ROTATE = "intakeRotate";

    public static double INTAKE_GRAB_CLOSE = 0;
    public static double INTAKE_GRAB_OPEN = 0;
    public static double INTAKE_ROTATE_MIN = 0; // Position that corresponds to -90 degree rotation
    public static double INTAKE_ROTATE_MAX = 0; // Position that corresponds to +90 degree rotation
    public static double INTAKE_PIVOT_GROUND = 0;
    public static double INTAKE_PIVOT_HOVER = 0;
    public static double INTAKE_PIVOT_TRANSFER = 0;
    public static double INTAKE_PIVOT_PARK = 0;

    // Linkage slides *for testing* (horizontal extension)
    public static final String SERVO_LINKAGE_LEFT = "linkageLeft";
    public static final String SERVO_LINKAGE_RIGHT = "linkageRight";

    public static double LINKAGE_ZERO = 0.77;
    public static double LINKAGE_FULL = 1;
    public static double ELBOW_DOWN = 0;
    public static double ELBOW_INTAKE = 0.5;
    public static double ELBOW_UP = 1;

    // Because we need the 5turn and 300 degrees moving at the same rate
    public static double ELBOW_COEF = 6;
    enum ElbowState { //not used for now
        DOWN(0),
        TRANSFER(1),
        INTAKE(0.5);

        public final double pos;

        ElbowState(double pos) { this.pos = pos; }
    }

    public static double H_WRIST_UP = 1;
    public static double H_WRIST_DOWN = 0;

    // TODO figure out the right claw positions
    public static double CLAW_ROTATION_MIN = 0.4;
    public static double CLAW_ROTATION_MAX = 0.6;
    public static double CLAW_OPEN = 0;
    public static double CLAW_CLOSED = 1;

    public static String H_CLAW_ROTATION_SERVO = "horizontalClawRotation";
    public static String H_CLAW_GRAB_SERVO = "horizontalClawGrab";


}
