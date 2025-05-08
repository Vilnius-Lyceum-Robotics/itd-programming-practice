package org.firstinspires.ftc.teamcode.config.subsystems;

import static org.firstinspires.ftc.teamcode.config.core.RobotConstants.*;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Claw extends SubsystemBase {

    private Servo rotation, grab;
    private Telemetry telemetry;
    private double rotationPos;
    private GrabState state;

    public Claw(HardwareMap hardwareMap, Telemetry telemetry, String rotationName, String grabName) {
        rotation = hardwareMap.get(Servo.class, rotationName);
        grab = hardwareMap.get(Servo.class, grabName);

        rotation.setDirection(Servo.Direction.REVERSE);
        grab.setDirection(Servo.Direction.REVERSE);

        this.telemetry = telemetry;

        this.rotationPos = CLAW_ROTATION_MIN;
        //this.grabPos = CLAW_OPEN;
        this.state = GrabState.CLOSED;
        rotate(CLAW_ROTATION_MIN);
        setGrabState(GrabState.CLOSED);

    }

    public void rotate(double angle) {
        if (angle >= CLAW_ROTATION_MAX) {
            rotation.setPosition(CLAW_ROTATION_MAX);
            this.rotationPos = CLAW_ROTATION_MAX;
        } else if (angle <= CLAW_ROTATION_MIN) {
            rotation.setPosition(CLAW_ROTATION_MIN);
            this.rotationPos = CLAW_ROTATION_MIN;
        } else {
            rotation.setPosition(angle);
            this.rotationPos = angle;
        }
    }

    public void setGrabState(GrabState state) {
        grab.setPosition(state.pos);
        this.state = state;
    }
    public void toggleGrab() {
        if (this.state == GrabState.OPEN) {
            setGrabState(GrabState.CLOSED);
        } else {
            setGrabState(GrabState.OPEN);
        }
    }

    public void rotationIncrement(double amount) {
        rotate(this.rotationPos + amount);
    }

    public double getRotationPos() { return this.rotationPos; }
    public GrabState getGrabState() { return this.state; }
    public void telemetry() {
        telemetry.addData("Claw rotation: ", getRotationPos());
        telemetry.addData("Claw grab: ", getGrabState());
    }
}
