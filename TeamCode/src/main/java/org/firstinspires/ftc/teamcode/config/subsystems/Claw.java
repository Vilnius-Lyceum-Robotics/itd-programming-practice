package org.firstinspires.ftc.teamcode.config.subsystems;

import static org.firstinspires.ftc.teamcode.config.core.RobotConstants.*;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Claw extends SubsystemBase {

    private Servo rotation, grab;
    private Telemetry telemetry;
    private double rotationPos, grabPos;

    public Claw(HardwareMap hardwareMap, Telemetry telemetry, String rotationName, String grabName) {
        rotation = hardwareMap.get(Servo.class, rotationName);
        grab = hardwareMap.get(Servo.class, grabName);

        rotation.setDirection(Servo.Direction.REVERSE);
        grab.setDirection(Servo.Direction.REVERSE);

        this.telemetry = telemetry;

        this.rotationPos = CLAW_ROTATION_MIN;
        this.grabPos = CLAW_OPEN;
        rotate(CLAW_ROTATION_MIN);
        grabMove(CLAW_OPEN);

    }

    public void rotate(double angle) {
        double clippedAngle = Range.clip(angle, CLAW_ROTATION_MIN, CLAW_ROTATION_MAX);
        rotation.setPosition(clippedAngle);
        this.rotationPos = clippedAngle;
    }
    public void grabMove(double angle) {
        double clippedAngle = Range.clip(angle, CLAW_OPEN, CLAW_CLOSED);
        grab.setPosition(clippedAngle);
        this.grabPos = clippedAngle;
    }

    public void rotationIncrement(double amount) {
        rotate(this.rotationPos + amount);
    }

    public void grabIncrement(double amount) {
        grabMove(this.grabPos + amount);
    }

    public double getRotationPos() { return this.rotationPos; }
    public double getGrabPos() { return this.grabPos; }
    public void telemetry() {
        telemetry.addData("Claw rotation: ", getRotationPos());
        telemetry.addData("Claw grab: ", getGrabPos());
    }
}
