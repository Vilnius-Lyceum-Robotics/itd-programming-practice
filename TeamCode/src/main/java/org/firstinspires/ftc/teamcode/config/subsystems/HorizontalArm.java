package org.firstinspires.ftc.teamcode.config.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import static org.firstinspires.ftc.teamcode.config.core.RobotConstants.*;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class HorizontalArm extends SubsystemBase {

    // This subsystem contains horizontal arm elbow, wrist and claw


    private Servo leftElbow, rightElbow, wrist;
    private Telemetry telemetry;
    private double elbowPos, wristPos;
    private HorizontalArmState state;


    public HorizontalArm(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        leftElbow = hardwareMap.get(Servo.class, SERVO_HORIZONTAL_ELBOW_LEFT);
        rightElbow = hardwareMap.get(Servo.class, SERVO_HORIZONTAL_ELBOW_RIGHT);
        wrist = hardwareMap.get(Servo.class, SERVO_HORIZONTAL_WRIST);

        leftElbow.setDirection(Servo.Direction.FORWARD);
        rightElbow.setDirection(Servo.Direction.REVERSE);
        wrist.setDirection(Servo.Direction.FORWARD);

        setState(HorizontalArmState.IN_ROBOT);

    }

    /*public void setElbowAngle(double target) {
        double clippedTarget = Range.clip(target, ELBOW_DOWN, ELBOW_UP);
        leftElbow.setPosition(clippedTarget);
        rightElbow.setPosition(clippedTarget / ELBOW_COEF + 0.2);
        this.elbowPos = clippedTarget;
    }

    public void setWristAngle(double target) {
        double clippedTarget = Range.clip(target, H_WRIST_DOWN, H_WRIST_UP);
        wrist.setPosition(clippedTarget);
        this.wristPos = clippedTarget;
    }*/


    public double getElbowPos() { return this.elbowPos;}
    public double getWristPos() {return this.wristPos;}
    public HorizontalArmState getState() { return this.state; }

    public void telemetry() {
        telemetry.addData("Left elbow position: ", getElbowPos());
        telemetry.addData("Right elbow position: ", getElbowPos() / ELBOW_COEF + 0.2);
        telemetry.addData("Wrist position: ", getWristPos());
        telemetry.addData("Horizontal arm state: ", getState());
    }

    public void setState(HorizontalArmState state) {
        this.state = state;
        rightElbow.setPosition(state.elbowPos / ELBOW_COEF + 0.2);
        leftElbow.setPosition(state.elbowPos);
        wrist.setPosition(state.wristPos);
        this.wristPos = state.wristPos;
        this.elbowPos = state.elbowPos;
    }

    public void ground(){
        setState(HorizontalArmState.INTAKE);
    }
    public void hover(){
        setState(HorizontalArmState.HOVER);
    }
}
