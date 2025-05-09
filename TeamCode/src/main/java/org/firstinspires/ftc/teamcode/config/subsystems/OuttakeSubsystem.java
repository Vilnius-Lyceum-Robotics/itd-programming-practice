package org.firstinspires.ftc.teamcode.config.subsystems;

import static org.firstinspires.ftc.teamcode.config.core.RobotConstants.*;
import static org.firstinspires.ftc.teamcode.config.core.RobotConstants.OUTTAKE_kD;
import static org.firstinspires.ftc.teamcode.config.core.RobotConstants.OUTTAKE_kF;
import static org.firstinspires.ftc.teamcode.config.core.RobotConstants.OUTTAKE_kI;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDFController;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorGroup;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class OuttakeSubsystem extends SubsystemBase {

    public enum GrabState {
        CLOSED, MIDOPEN, OPEN
    }

    public enum RotateState {
        NORMAL, FLIPPED
    }

    public enum ElbowState {
        TRANSFER, BUCKET, HUMAN, CHAMBER, PARK
    }

    public enum PivotState {
        TRANSFER, BUCKET, HUMAN, PREP_CHAMBER, SCORE_CHAMBER, PARK
    }

    private Telemetry telemetry;

    private Servo grabServo, rotateServo, elbowServo;
    private Motor leftPivotMotor, rightPivotMotor, pivotEncoder;
    private MotorGroup pivotMotors;
    private GrabState grabState = GrabState.CLOSED;
    private RotateState rotateState = RotateState.NORMAL;
    private PivotState pivotState = PivotState.PARK;
    private ElbowState elbowState = ElbowState.PARK;
    private PIDFController pidf;
    private double pivotTarget = 0;
    private boolean pivotPidOn = false;

    public OuttakeSubsystem(HardwareMap hardwareMap, Telemetry telemetry){
        this.telemetry = telemetry;

        grabServo = hardwareMap.get(Servo.class, SERVO_OUTTAKE_GRAB);
        rotateServo = hardwareMap.get(Servo.class, SERVO_OUTTAKE_ROTATE);
        elbowServo = hardwareMap.get(Servo.class, SERVO_OUTTAKE_ELBOW);

        leftPivotMotor = new Motor(hardwareMap, MOTOR_OUTTAKE_PIVOT_LEFT);
        rightPivotMotor = new Motor(hardwareMap, MOTOR_OUTTAKE_PIVOT_RIGHT);
        pivotEncoder = new Motor(hardwareMap, "rearLeft"); // 4th control hub motor port

        rightPivotMotor.setInverted(false);
        leftPivotMotor.setInverted(true);

        pivotMotors = new MotorGroup(leftPivotMotor, rightPivotMotor);

        pidf = new PIDFController(OUTTAKE_kP, OUTTAKE_kI, OUTTAKE_kD, OUTTAKE_kF);
        pidf.setTolerance(OUTTAKE_ERROR_TOLERANCE);

        pivotEncoder.stopAndResetEncoder();

//        setGrabState(grabState);
//        setRotateState(rotateState);
//        setPivotState(pivotState);
    }

    public void setPivotTarget(int target){
        pivotPidOn = true;
        this.pivotTarget = target;
        pidf.setSetPoint(target);
    }

    public boolean reachedTarget(){
        return pidf.atSetPoint();
    }

    public int getPivotPos(){
        return pivotEncoder.getCurrentPosition();
    }


    @Override
    public void periodic(){
        if(!pivotPidOn) {
            pivotMotors.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
            return;
        }
        pidf.setPIDF(OUTTAKE_kP, OUTTAKE_kI, OUTTAKE_kD, OUTTAKE_kF);

        double encoderPosition = getPivotPos();
        double power = pidf.calculate(encoderPosition, pivotTarget);

        pivotMotors.set(power);
    }


    public void setGrabState(GrabState grabState){
        switch (grabState){
            case CLOSED:
                grabServo.setPosition(OUTTAKE_GRAB_CLOSE);
                break;
            case OPEN:
                grabServo.setPosition(OUTTAKE_GRAB_OPEN);
                break;
            case MIDOPEN:
                grabServo.setPosition(OUTTAKE_GRAB_MID_OPEN);
                break;
        }
    }

    public void setRotateState(RotateState rotateState){
        this.rotateState = rotateState;
        switch (rotateState){
            case NORMAL:
                rotateServo.setPosition(OUTTAKE_ROTATE_NORMAL);
                break;
            case FLIPPED:
                rotateServo.setPosition(OUTTAKE_ROTATE_FLIPPED);
                break;
        }
    }

    public void setElbowState(ElbowState elbowState){
        this.elbowState = elbowState;
        switch (elbowState){
            case TRANSFER:
                elbowServo.setPosition(OUTTAKE_ELBOW_TRANSFER);
                break;
//            case BUCKET:
//                setPivotTarget(OUTTAKE_PIVOT_BUCKET);
//                break;
            case HUMAN:
                elbowServo.setPosition(OUTTAKE_ELBOW_HUMAN);
                break;
            case CHAMBER:
                elbowServo.setPosition(OUTTAKE_ELBOW_CHAMBER);
                break;
            case PARK:
                elbowServo.setPosition(OUTTAKE_ELBOW_PARK);
                break;
        }
    }

    public void setPivotState(PivotState pivotState){
        this.pivotState = pivotState;
        switch (pivotState){
            case TRANSFER:
                setPivotTarget(OUTTAKE_PIVOT_TRANSFER);
                break;
//            case BUCKET:
//                setPivotTarget(OUTTAKE_PIVOT_BUCKET);
//                break;
            case HUMAN:
                setPivotTarget(OUTTAKE_PIVOT_HUMAN);
                break;
            case PREP_CHAMBER:
                setPivotTarget(OUTTAKE_PIVOT_PREPARE_CHAMBER);
                break;
            case SCORE_CHAMBER:
                setPivotTarget(OUTTAKE_PIVOT_SCORE_CHAMBER);
                break;
            case PARK:
                setPivotTarget(OUTTAKE_PIVOT_PARK);
                break;
        }
    }


    public void open(){
        setGrabState(OuttakeSubsystem.GrabState.OPEN);
    }
    public void midOpen() { setGrabState(OuttakeSubsystem.GrabState.MIDOPEN); }
    public void close(){
        setGrabState(OuttakeSubsystem.GrabState.CLOSED);
    }

    public void toTransfer(){
        setRotateState(RotateState.NORMAL);
        setPivotState(PivotState.TRANSFER);
        setElbowState(ElbowState.TRANSFER);
        setGrabState(GrabState.OPEN);
    }

    public void toBucket(){
        setRotateState(RotateState.NORMAL);
        setPivotState(PivotState.BUCKET);
        setGrabState(GrabState.CLOSED);
    }

    public void toHuman(){
        setRotateState(RotateState.NORMAL);
        setPivotState(PivotState.HUMAN);
        setElbowState(ElbowState.HUMAN);
        setGrabState(GrabState.OPEN);
    }

    public void toPrepChamber() {
//        setRotateState(RotateState.FLIPPED);
        setRotateState(RotateState.NORMAL);

        setPivotState(PivotState.PREP_CHAMBER);
        setElbowState(ElbowState.CHAMBER);
        setGrabState(GrabState.CLOSED);
    }

    public void toScoreChamber(){
        setGrabState(GrabState.CLOSED);
        setPivotState(PivotState.SCORE_CHAMBER);
    }

    public void toPark(){
        setRotateState(RotateState.NORMAL);
        setPivotState(PivotState.PARK);
        setElbowState(ElbowState.PARK);
        setGrabState(GrabState.CLOSED);
    }

    public void telemetry() {
        telemetry.addData("Outtake Grab State: ", grabState);
        telemetry.addData("Outtake Rotate State: ", rotateState);
        telemetry.addData("Outtake Elbow State: ", elbowState);
        telemetry.addData("Outtake Pivot Position: ", getPivotPos());
        telemetry.addData("Outtake Pivot Target: ", pivotTarget);
    }
}
