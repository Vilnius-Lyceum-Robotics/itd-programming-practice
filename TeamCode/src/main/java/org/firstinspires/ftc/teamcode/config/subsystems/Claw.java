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
    private double rotationPos;
    private double cachedRotationPos;
    private GrabState state;

    public Claw(HardwareMap hardwareMap, Telemetry telemetry) {
        rotation = hardwareMap.get(Servo.class, SERVO_HORIZONTAL_ROTATION);
        grab = hardwareMap.get(Servo.class, SERVO_HORIZONTAL_GRAB);

        rotation.setDirection(Servo.Direction.REVERSE);
        grab.setDirection(Servo.Direction.REVERSE);

        this.telemetry = telemetry;

        this.rotationPos = 0.6;
        //this.grabPos = CLAW_OPEN;
        this.state = GrabState.CLOSED;
        rotate(0.6);
        setGrabState(GrabState.CLOSED);

    }

    public void rotate(double angle) {
        if (angle >= CLAW_TWIST_MAX) {
            rotation.setPosition(CLAW_TWIST_MAX);
            this.rotationPos = CLAW_TWIST_MAX;
        } else if (angle <= CLAW_TWIST_MIN) {
            rotation.setPosition(CLAW_TWIST_MIN);
            this.rotationPos = CLAW_TWIST_MIN;
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

    public void close(){
        setGrabState(GrabState.CLOSED);
    }
    public void open(){
        setGrabState(GrabState.OPEN);
    }

    public void rotationIncrement(double amount) {
        rotate(this.rotationPos + amount);
    }

    public void setMappedTarget(Double input){
        double clippedInput = Range.clip(input, -1, 1);
        double mappedPos = Range.scale(clippedInput, -1, 1, CLAW_TWIST_MIN, CLAW_TWIST_MAX);
        if (mappedPos == cachedRotationPos) return;
        cachedRotationPos = mappedPos;

        rotate(mappedPos);
    }

    public double getRotationPos() { return this.rotationPos; }
    public GrabState getGrabState() { return this.state; }
    public void telemetry() {
        telemetry.addData("Claw rotation: ", getRotationPos());
        telemetry.addData("Claw grab: ", getGrabState());
    }
}
