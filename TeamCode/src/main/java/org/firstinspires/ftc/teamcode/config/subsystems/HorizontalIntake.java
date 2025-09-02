package org.firstinspires.ftc.teamcode.config.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import static org.firstinspires.ftc.teamcode.config.core.RobotConstants.*;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class HorizontalIntake extends SubsystemBase {

    // This subsystem contains horizontal arm elbow, wrist and claw


    private Servo leftRot, rightRot, inAngle;
    private Telemetry telemetry;
    private double rotatorPos, anglePos;
    private HorizontalArmState state;


    public HorizontalIntake(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        leftRot = hardwareMap.get(Servo.class, SERVO_HORIZONTAL_ROTATOR_LEFT);
        rightRot = hardwareMap.get(Servo.class, SERVO_HORIZONTAL_ROTATOR_RIGHT);
        inAngle = hardwareMap.get(Servo.class, SERVO_HORIZONTAL_ANGLE);

        leftRot.setDirection(Servo.Direction.FORWARD);
        rightRot.setDirection(Servo.Direction.REVERSE);
        inAngle.setDirection(Servo.Direction.FORWARD);

        setRotatorAngle(ROTATOR_DOWN);
        setIntakeAngle(IN_ANGLE_UP);

    }

    public void setRotatorAngle(double target) {
        double clippedTarget = Range.clip(target, ROTATOR_DOWN, ROTATOR_UP);
        leftRot.setPosition(clippedTarget);
        rightRot.setPosition(clippedTarget / ROTATOR_COEF + 0.2);
        this.rotatorPos = clippedTarget;
    }

    public void setIntakeAngle(double target) {
        double clippedTarget = Range.clip(target, IN_ANGLE_UP, IN_ANGLE_DOWN);
        inAngle.setPosition(clippedTarget);
        this.anglePos = clippedTarget;
    }

    public void incrementRotator(double amount) {
        double target = this.rotatorPos + amount;
        setRotatorAngle(target);
    }

    public void incrementAngle(double amount) {
        double target = this.anglePos + amount;
        setIntakeAngle(target);
    }

    public double getRotatorPos() { return this.rotatorPos;}
    public double getAnglePos() {return this.anglePos;}
    public HorizontalArmState getState() { return this.state; }

    public void telemetry() {
        telemetry.addData("Left elbow position: ", getRotatorPos());
        telemetry.addData("Right elbow position: ", getRotatorPos() / ROTATOR_COEF + 0.2);
        telemetry.addData("Wrist position: ", getAnglePos());
        telemetry.addData("Horizontal arm state: ", getState());
    }

    public void setState(HorizontalArmState state) {
        this.state = state;
        rightRot.setPosition(state.elbowPos / ROTATOR_COEF + 0.2);
        leftRot.setPosition(state.elbowPos);
        inAngle.setPosition(state.wristPos);
        this.anglePos = state.wristPos;
        this.rotatorPos = state.elbowPos;
    }

    public void ground(){
        setState(HorizontalArmState.INTAKE);
    }
    public void clear(){
        setState(HorizontalArmState.CLEAR_BAR);
    }
    public void hover(){
        setState(HorizontalArmState.HOVER);
    }
}
