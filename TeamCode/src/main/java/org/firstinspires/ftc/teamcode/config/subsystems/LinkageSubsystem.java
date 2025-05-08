package org.firstinspires.ftc.teamcode.config.subsystems;

import static org.firstinspires.ftc.teamcode.config.core.RobotConstants.*;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LinkageSubsystem extends SubsystemBase {

    private Telemetry telemetry;

    private Servo leftServo, rightServo;
    private double pos = 0;


    public LinkageSubsystem(HardwareMap hardwareMap, Telemetry telemetry){
        this.telemetry = telemetry;

        leftServo = hardwareMap.get(Servo.class, SERVO_LINKAGE_LEFT);
//        rightServo = hardwareMap.get(Servo.class, SERVO_LINKAGE_RIGHT);
        leftServo.setDirection(Servo.Direction.FORWARD);
//        rightServo.setDirection(Servo.Direction.FORWARD);
    }

    public void setTarget(Double target){
        this.pos = target;
        leftServo.setPosition(target);
//        rightServo.setPosition(target);
    }

    public void setMappedTarget(Double input){
        double clippedInput = Range.clip(input, -1, 1);
        double mappedPos = Range.scale(clippedInput, -1, 1, LINKAGE_ZERO, LINKAGE_FULL);
        setTarget(mappedPos);
    }

    public double getPos(){
        return pos;
    }

    public void toFull(){
        setTarget(LINKAGE_FULL);
    }

    public void toZero(){
        setTarget(LINKAGE_ZERO);
    }

    public void telemetry(){
        telemetry.addData("Linkage extend pos: ", getPos());
    }

}
