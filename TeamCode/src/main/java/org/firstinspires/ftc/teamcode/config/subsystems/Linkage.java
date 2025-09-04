package org.firstinspires.ftc.teamcode.config.subsystems;

import com.pedropathing.localization.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import static org.firstinspires.ftc.teamcode.config.core.RobotConstants.*;

import org.firstinspires.ftc.robotcore.external.Telemetry;
public class Linkage {

    private Servo servoLeft, servoRight;
    private Telemetry telemetry;
    private double rotationPos;

    public Linkage(HardwareMap hardwareMap, Telemetry telemetry)
    {
        servoLeft = hardwareMap.get(Servo.class, SERVO_LINKAGE_LEFT);
        servoRight = hardwareMap.get(Servo.class, SERVO_LINKAGE_RIGHT);

        servoRight.setDirection(Servo.Direction.REVERSE);

        setTarget(LINKAGE_ZERO);

        this.telemetry = telemetry;
    }
    public void setTarget(double angle) {
        if (angle >= LINKAGE_FULL) {
            servoLeft.setPosition(LINKAGE_FULL);
            servoRight.setPosition(LINKAGE_FULL);
            this.rotationPos = LINKAGE_FULL;
        } else if (angle <= LINKAGE_ZERO) {
            servoLeft.setPosition(LINKAGE_ZERO);
            servoRight.setPosition(LINKAGE_ZERO);
            this.rotationPos = LINKAGE_ZERO;
        } else {
            servoLeft.setPosition(angle);
            servoRight.setPosition(angle);
            this.rotationPos = angle;
        }
    }
    public void rotationIncrement(double amount) {
        setTarget(this.rotationPos + amount);
    }
    public double getRotationPos() {return this.rotationPos;}
    public void telemetry()
    {
        telemetry.addData("Linkage rotation: ", getRotationPos());
    }
    public void rotateFull()
    {
        setTarget(LINKAGE_FULL);
    }
    public void rotateZero()
    {
        setTarget(LINKAGE_ZERO);
    }
}
