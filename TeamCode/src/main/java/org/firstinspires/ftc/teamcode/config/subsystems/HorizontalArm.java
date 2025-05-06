package org.firstinspires.ftc.teamcode.config.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import static org.firstinspires.ftc.teamcode.config.core.RobotConstants.*;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class HorizontalArm extends SubsystemBase {

    // This subsystem contains horizontal arm elbow, wrist and claw

    private Servo leftElbow, rightElbow, wrist, clawRotation, clawGripper;
    private Telemetry telemetry;
    private double elbowPos, wristPos, rotationPos, gripperPos;


    public HorizontalArm(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        leftElbow = hardwareMap.get(Servo.class, "leftElbow");
        rightElbow = hardwareMap.get(Servo.class, "rightElbow");
        //wrist = hardwareMap.get(Servo.class, "wrist");
        //clawRotation = hardwareMap.get(Servo.class, "clawRotation");
        //clawGripper = hardwareMap.get(Servo.class, "clawGripper");

        leftElbow.setDirection(Servo.Direction.FORWARD);
        rightElbow.setDirection(Servo.Direction.REVERSE);
        //wrist.setDirection(Servo.Direction.FORWARD);
        //clawRotation.setDirection(Servo.Direction.FORWARD);
        //clawGripper.setDirection(Servo.Direction.FORWARD);

        this.elbowPos = ELBOW_DOWN;
        setElbowAngle(ELBOW_DOWN);

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



    public double getElbowPos() { return this.elbowPos;}

    public void telemetry() {
        telemetry.addData("Left elbow position: ", getElbowPos());
        telemetry.addData("Right elbow position: ", getElbowPos() / ELBOW_COEF + 0.2);
    }

    public void elbowIncrement(double amount) {
        setElbowAngle(this.elbowPos + amount);
    }



}
