package org.firstinspires.ftc.teamcode.config.subsystems;

import static org.firstinspires.ftc.teamcode.config.core.RobotConstants.*;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Rotator {
    private Servo angle;
    private Servo rotator300, rotator5turn;
    private double anglePos;
    private double rotator300Pos;
    private Telemetry telemetry;
    public Rotator(HardwareMap hardwareMap, Telemetry telemetry){
        this.telemetry = telemetry;

        angle = hardwareMap.get(Servo.class, SERVO_HORIZONTAL_ANGLE);
        rotator5turn = hardwareMap.get(Servo.class, SERVO_HORIZONTAL_ROTATOR_LEFT);
        rotator300 = hardwareMap.get(Servo.class, SERVO_HORIZONTAL_ROTATOR_RIGHT);

        angle.setDirection(Servo.Direction.FORWARD);
        rotator5turn.setDirection(Servo.Direction.FORWARD);
        rotator300.setDirection(Servo.Direction.REVERSE);

        setAngle(IN_ANGLE_UP);
        setRotator(ROTATOR_UP);
    }

    public void setAngle(double newAnglePos){
        anglePos = Range.clip(newAnglePos, IN_ANGLE_UP, IN_ANGLE_DOWN);
        angle.setPosition(anglePos);
    }
    public void setAngleRel(double change){
        setAngle(anglePos + change);
    }

    public void setRotator(double newRotator300Pos){
        rotator300Pos = Range.clip(newRotator300Pos, ROTATOR_DOWN, ROTATOR_UP);
        rotator300.setPosition(rotator300Pos);
        rotator5turn.setPosition(rotator300Pos * ROTATOR_COEF);
    }
    public void setRotatorRel(double change){
        setAngle(rotator300Pos + change);
    }

    public double getAnglePos(){
        return anglePos;
    }
    public double getRotatorPos(){
        return rotator300Pos;
    }
    public void telemetry(){
        telemetry.addData("Angle position: ", getAnglePos());
        telemetry.addData("Rotator position: ", getRotatorPos());
    }
}
