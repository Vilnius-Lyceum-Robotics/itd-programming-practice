package org.firstinspires.ftc.teamcode.config.subsystems;

import static org.firstinspires.ftc.teamcode.config.core.RobotConstants.*;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class OuttakeSubsystem extends SubsystemBase {

    public enum GrabState {
        CLOSED, OPEN
    }

    public enum RotateState {
        NORMAL, FLIPPED
    }

    public enum PivotState {
        TRANSFER, BUCKET, HUMAN, CHAMBER, PARK
    }

    private Telemetry telemetry;

    private Servo grabServo, rotateServo, leftPivotServo, rightPivotServo;
    private GrabState grabState = GrabState.CLOSED;
    private RotateState rotateState = RotateState.NORMAL;
    private PivotState pivotState = PivotState.PARK;

    public OuttakeSubsystem(HardwareMap hardwareMap, Telemetry telemetry){
        this.telemetry = telemetry;

        grabServo = hardwareMap.get(Servo.class, SERVO_OUTTAKE_GRAB);
        rotateServo = hardwareMap.get(Servo.class, SERVO_OUTTAKE_ROTATE);
        leftPivotServo = hardwareMap.get(Servo.class, SERVO_OUTTAKE_PIVOT_LEFT);
        rightPivotServo = hardwareMap.get(Servo.class, SERVO_OUTTAKE_PIVOT_RIGHT);

        setGrabState(grabState);
        setRotateState(rotateState);
        setPivotState(pivotState);
    }

    public void setGrabState(GrabState grabState){
        switch (grabState){
            case CLOSED:
                grabServo.setPosition(OUTTAKE_GRAB_CLOSE);
                break;
            case OPEN:
                grabServo.setPosition(OUTTAKE_GRAB_OPEN);
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

    public void setPivotState(PivotState pivotState){
        this.pivotState = pivotState;
        switch (pivotState){
            case TRANSFER:
                leftPivotServo.setPosition(OUTTAKE_PIVOT_TRANSFER);
                rightPivotServo.setPosition(OUTTAKE_PIVOT_TRANSFER);
                break;
            case BUCKET:
                leftPivotServo.setPosition(OUTTAKE_PIVOT_BUCKET);
                rightPivotServo.setPosition(OUTTAKE_PIVOT_BUCKET);
                break;
            case HUMAN:
                leftPivotServo.setPosition(OUTTAKE_PIVOT_HUMAN);
                rightPivotServo.setPosition(OUTTAKE_PIVOT_HUMAN);
                break;
            case CHAMBER:
                leftPivotServo.setPosition(OUTTAKE_PIVOT_CHAMBER);
                rightPivotServo.setPosition(OUTTAKE_PIVOT_CHAMBER);
                break;
            case PARK:
                leftPivotServo.setPosition(OUTTAKE_PIVOT_PARK);
                rightPivotServo.setPosition(OUTTAKE_PIVOT_PARK);
                break;
        }
    }

    public void open(){
        setGrabState(OuttakeSubsystem.GrabState.OPEN);
    }

    public void close(){
        setGrabState(OuttakeSubsystem.GrabState.CLOSED);
    }

    public void toTransfer(){
        setRotateState(RotateState.NORMAL);
        setPivotState(PivotState.TRANSFER);
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
        setGrabState(GrabState.OPEN);
    }

    public void toChamber() {
        setRotateState(RotateState.FLIPPED);
        setPivotState(PivotState.CHAMBER);
        setGrabState(GrabState.CLOSED);
    }

    public void toPark(){
        setRotateState(RotateState.NORMAL);
        setPivotState(PivotState.PARK);
        setGrabState(GrabState.CLOSED);
    }

    public void telemetry() {
        telemetry.addData("Outtake Grab State: ", grabState);
        telemetry.addData("Outtake Rotate State: ", rotateState);
        telemetry.addData("Outtake Pivot State: ", pivotState);
    }
}
