package org.firstinspires.ftc.teamcode.config.subsystems;

import static org.firstinspires.ftc.teamcode.config.core.RobotConstants.*;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IntakeSubsystem extends SubsystemBase {

    public enum GrabState {
        CLOSED, OPEN
    }

    public enum PivotState {
        TRANSFER, GROUND, HOVER, PARK
    }

    private Telemetry telemetry;

    private Servo grabServo, rotateServo, pivotServo;
    private GrabState grabState = GrabState.CLOSED;
    private PivotState pivotState = PivotState.PARK;
    private double rotateDegrees = 0;

    public IntakeSubsystem(HardwareMap hardwareMap, Telemetry telemetry){
        this.telemetry = telemetry;

        grabServo = hardwareMap.get(Servo.class, SERVO_INTAKE_GRAB);
        rotateServo = hardwareMap.get(Servo.class, SERVO_INTAKE_ROTATE);
        pivotServo = hardwareMap.get(Servo.class, SERVO_INTAKE_PIVOT);

        setGrabState(grabState);
        setRotateDegrees(0);
        setPivotState(pivotState);
    }

    public void setGrabState(GrabState grabState) {
        this.grabState = grabState;
        switch (grabState){
            case CLOSED:
                grabServo.setPosition(INTAKE_GRAB_CLOSE);
                break;
            case OPEN:
                grabServo.setPosition(INTAKE_GRAB_OPEN);
                break;
        }
    }

    public void setPivotState(PivotState pivotState) {
        this.pivotState = pivotState;
        switch(pivotState){
            case TRANSFER:
                pivotServo.setPosition(INTAKE_PIVOT_TRANSFER);
                break;
            case GROUND:
                pivotServo.setPosition(INTAKE_PIVOT_GROUND);
                break;
            case HOVER:
                pivotServo.setPosition(INTAKE_PIVOT_HOVER);
                break;
            case PARK:
                pivotServo.setPosition(INTAKE_PIVOT_PARK);
                break;
        }
    }

    public void setRotateDegrees(double rotateDegrees){
        this.rotateDegrees = Range.clip(rotateDegrees, -90, 90);
        double servoPos = Range.scale(rotateDegrees, -90, 90, INTAKE_ROTATE_MIN, INTAKE_ROTATE_MAX);
        rotateServo.setPosition(servoPos);
    }

    public void rotateCycle(boolean clockwise) {
        if(rotateDegrees >= 90 || rotateDegrees <= -90) return;

        if (clockwise) {
            rotateDegrees += 45;
        } else {
            rotateDegrees -= 45;
        }

        setRotateDegrees(rotateDegrees);
        setPivotState(PivotState.HOVER);
        setGrabState(GrabState.OPEN);
    }

    public void toggleGrabState(){
        switch (grabState){
            case OPEN:
                setGrabState(GrabState.CLOSED);
                break;
            case CLOSED:
                setGrabState(GrabState.OPEN);
                break;
        }
    }

    public void open(){
        setGrabState(GrabState.OPEN);
    }

    public void close(){
        setGrabState(GrabState.CLOSED);
    }

    public void toTransfer(){
        setRotateDegrees(0);
        setPivotState(PivotState.TRANSFER);
        setGrabState(GrabState.CLOSED);
    }

    public void toGround(){
        setGrabState(GrabState.OPEN);
        setPivotState(PivotState.GROUND);
    }

    public void toHover(){
        setRotateDegrees(0);
        setPivotState(PivotState.HOVER);
    }

    public void toPark(){
        setRotateDegrees(0);
        setPivotState(PivotState.PARK);
    }

    public void telemetry(){
        telemetry.addData("Intake Grab State: ", grabState);
        telemetry.addData("Intake Pivot State: ", pivotState);
        telemetry.addData("Intake Rotate Degrees: ", rotateDegrees);
    }
}
