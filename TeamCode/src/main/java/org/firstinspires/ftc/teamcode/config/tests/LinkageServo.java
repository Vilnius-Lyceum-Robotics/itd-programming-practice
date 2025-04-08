package org.firstinspires.ftc.teamcode.config.tests;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import static org.firstinspires.ftc.teamcode.config.core.RobotConstants.*;

public class LinkageServo extends SubsystemBase {

    private Telemetry telemetry;
    private Servo sadLittleServo;
    private double pos = 0;

    public LinkageServo(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        sadLittleServo = hardwareMap.get(Servo.class, "sadLittleServo");
        sadLittleServo.setDirection(Servo.Direction.FORWARD);

        setTarget(0);
    }

    public void setTarget(double target) {
        if (this.pos + target >= 0) {
            this.pos = target;
            sadLittleServo.setPosition(target);
        }
    }

    public double getPos() {
        return pos;
    }

    public void telemetry() {
        telemetry.addData("Linkage servo position: ", getPos());
    }

    public void increment(double increment) {
        setTarget(this.pos + increment);
    }
    public void extend() {
        setTarget(H_ARM_FULL);
    }

    public void retract() {
        setTarget(H_ARM_DEFAULT);
    }


}
