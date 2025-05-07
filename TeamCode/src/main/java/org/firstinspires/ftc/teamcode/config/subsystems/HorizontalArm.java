package org.firstinspires.ftc.teamcode.config.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import static org.firstinspires.ftc.teamcode.config.core.RobotConstants.*;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class HorizontalArm extends SubsystemBase {

    // This subsystem contains horizontal arm elbow, wrist and claw
    // TODO extract the claw to its own subsystem

    private Servo leftElbow, rightElbow, wrist, clawRotation, clawGripper;
    private Telemetry telemetry;
    private double elbowPos, wristPos, rotationPos, gripperPos;


    public HorizontalArm(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        leftElbow = hardwareMap.get(Servo.class, "leftElbow");
        rightElbow = hardwareMap.get(Servo.class, "rightElbow");
        wrist = hardwareMap.get(Servo.class, "wrist");
        clawRotation = hardwareMap.get(Servo.class, "clawRotation");
        clawGripper = hardwareMap.get(Servo.class, "clawGripper");

        leftElbow.setDirection(Servo.Direction.FORWARD);
        rightElbow.setDirection(Servo.Direction.REVERSE);
        wrist.setDirection(Servo.Direction.REVERSE);
        clawRotation.setDirection(Servo.Direction.REVERSE);
        clawGripper.setDirection(Servo.Direction.REVERSE);

        /*this.elbowPos = ELBOW_DOWN;
        setElbowAngle(ELBOW_DOWN);
        this.wristPos = H_WRIST_UP;
        setWristAngle(H_WRIST_UP);*/

        this.rotationPos = CLAW_ROTATION_MIN;
        setClawRotation(CLAW_ROTATION_MIN);
        this.gripperPos = CLAW_OPEN;
        nomnom(CLAW_OPEN);

    }

    public void setElbowAngle(double target) {
        if (target >= ELBOW_UP) {
            leftElbow.setPosition(ELBOW_UP);
            rightElbow.setPosition(ELBOW_UP / ELBOW_COEF + 0.2);
            this.elbowPos = ELBOW_UP;
        } else if (target <= ELBOW_DOWN) {
            leftElbow.setPosition(ELBOW_DOWN);
            rightElbow.setPosition(ELBOW_DOWN / ELBOW_COEF + 0.2);
            this.elbowPos = ELBOW_DOWN;
        } else {
            leftElbow.setPosition(target);
            rightElbow.setPosition(target / ELBOW_COEF + 0.2);
            this.elbowPos = target;
        }
    }

    public void setWristAngle(double target) {
        if (target >= H_WRIST_UP) {
            wrist.setPosition(H_WRIST_UP);
            this.wristPos = H_WRIST_UP;
        } else if (target <= H_WRIST_DOWN) {
            wrist.setPosition(H_WRIST_DOWN);
            this.wristPos = H_WRIST_DOWN;
        } else {
            wrist.setPosition(target);
            this.wristPos = target;
        }
    }

    public void setClawRotation(double angle) {
        if (angle >= CLAW_ROTATION_MAX) {
            clawRotation.setPosition(CLAW_ROTATION_MAX);
            this.rotationPos = CLAW_ROTATION_MAX;
        } else if (angle <= CLAW_ROTATION_MIN) {
            clawRotation.setPosition(CLAW_ROTATION_MIN);
            this.rotationPos = CLAW_ROTATION_MIN;
        } else {
            clawRotation.setPosition(angle);
            this.rotationPos = angle;
        }
    }

    public void setClawBite(double angle) {
        if (angle >= CLAW_CLOSED) {
            clawGripper.setPosition(CLAW_CLOSED);
            this.gripperPos = CLAW_CLOSED;
        } else if (angle <= CLAW_OPEN) {
            clawGripper.setPosition(CLAW_OPEN);
            this.gripperPos = CLAW_OPEN;
        } else {
            clawGripper.setPosition(angle);
            this.gripperPos = angle;
        }
    }
    public double getElbowPos() { return this.elbowPos;}
    public double getWristPos() {return this.wristPos;}
    public double getRotationPos() { return this.rotationPos; }
    public double getGripperPos() { return this.gripperPos; }

    public void telemetry() {
        telemetry.addData("Left elbow position: ", getElbowPos());
        telemetry.addData("Right elbow position: ", getElbowPos() / ELBOW_COEF + 0.2);
        telemetry.addData("Wrist position: ", getWristPos());
        telemetry.addData("Claw rotation angle: ", getRotationPos());
        telemetry.addData("Claw bite angle: ", getGripperPos());
    }

    public void elbowIncrement(double amount) {
        setElbowAngle(this.elbowPos + amount);
    }

    public void wristIncrement(double amount) {
        setWristAngle(this.wristPos + amount);
    }

    public void rotationIncrement(double amount) {
        setClawRotation(this.rotationPos + amount);
    }

    public void nomnom(double amount) {
        setClawBite(this.gripperPos + amount);
    }



}
