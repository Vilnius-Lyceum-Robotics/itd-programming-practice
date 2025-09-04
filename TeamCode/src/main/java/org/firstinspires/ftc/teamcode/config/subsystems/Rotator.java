package org.firstinspires.ftc.teamcode.config.subsystems;

import static org.firstinspires.ftc.teamcode.config.core.RobotConstants.*;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Rotator {
    private Servo angle;
    private double anglePos;
    private Telemetry telemetry;
    public Rotator(HardwareMap hardwareMap, Telemetry telemetry){
        this.telemetry = telemetry;

        angle = hardwareMap.get(Servo.class, SERVO_HORIZONTAL_ANGLE);

        angle.setDirection(Servo.Direction.FORWARD);

        setAngle(IN_ANGLE_UP);
    }

    public void setAngle(double angle){
        anglePos = Range.clip(angle, IN_ANGLE_UP, IN_ANGLE_DOWN);
        this.angle.setPosition(anglePos);
    }

    public void setAngleRel(double change){
        setAngle(anglePos + change);
    }

    public double getAnglePos(){
        return anglePos;
    }
    public void telemetry(){
        telemetry.addData("Angle position: ", getAnglePos());
    }
}
