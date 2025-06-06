//package org.firstinspires.ftc.teamcode.config.subsystems;
//
//import static org.firstinspires.ftc.teamcode.config.core.RobotConstants.*;
//
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.seattlesolvers.solverslib.command.SubsystemBase;
//import com.seattlesolvers.solverslib.controller.PIDFController;
//import com.seattlesolvers.solverslib.hardware.motors.Motor;
//import com.seattlesolvers.solverslib.hardware.motors.MotorGroup;
//
//import org.firstinspires.ftc.robotcore.external.Telemetry;
//
//public class LiftSubsystem extends SubsystemBase {
//
//    private Telemetry telemetry;
//    private MotorGroup liftMotors;
//    private PIDFController pidf;
//    private int target = 0;
//    private boolean pidOn = false;
//
//    public LiftSubsystem(HardwareMap hardwareMap, Telemetry telemetry){
//        this.telemetry = telemetry;
//
//        Motor leftMotor = new Motor(hardwareMap, MOTOR_LIFT_LEFT, Motor.GoBILDA.RPM_435); // Input correct motor type here
//        Motor rightMotor = new Motor(hardwareMap, MOTOR_LIFT_RIGHT, Motor.GoBILDA.RPM_435); // Input correct motor type here
//        liftMotors = new MotorGroup(leftMotor, rightMotor);
//
//        liftMotors.setRunMode(Motor.RunMode.RawPower);
//        liftMotors.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
//        liftMotors.stopAndResetEncoder();
//
//        pidf = new PIDFController(LIFT_kP, LIFT_kI, LIFT_kD, LIFT_kF);
//        pidf.setTolerance(LIFT_ERROR_TOLERANCE);
//    }
//
//    @Override
//    public void periodic(){
//        if(!pidOn) {
//            liftMotors.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
//            return;
//        }
//        pidf.setPIDF(LIFT_kP, LIFT_kI, LIFT_kD, LIFT_kF);
//        liftMotors.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);
//
//        double encoderPosition = getPos();
//        double power = pidf.calculate(encoderPosition, target);
//
//        if (encoderPosition < 50 && target < 50) {
//            liftMotors.set(0);
//            return;
//        }
//
//        liftMotors.set(power);
//    }
//
//    public void setTarget(int target){
//        pidOn = true;
//        this.target = target;
//        pidf.setSetPoint(target);
//    }
//
//    public boolean reachedTarget(){
//        return pidf.atSetPoint();
//    }
//
//    public int getPos(){
//        return liftMotors.getCurrentPosition();
//    }
//
//    public void toZero(){
//        setTarget(LIFT_ZERO);
//    }
//
//    public void toHighBucket(){
//        setTarget(LIFT_HIGH_BASKET);
//    }
//
//    public void toLowBucket(){
//        setTarget(LIFT_LOW_BASKET);
//    }
//
//    public void toChamber(){
//        setTarget(LIFT_TO_CHAMBER);
//    }
//
//    public void toChamberScore(){
//        setTarget(LIFT_SCORE_CHAMBER);
//    }
//
//    public void toHuman(){
//        setTarget(LIFT_HUMAN_PLAYER);
//    }
//
//    public void toTransfer(){
//        setTarget(LIFT_TRANSFER);
//    }
//
//    public void telemetry(){
//        telemetry.addData("Lift pos:", getPos());
//        telemetry.addData("Lift target pos:", target);
//    }
//}
