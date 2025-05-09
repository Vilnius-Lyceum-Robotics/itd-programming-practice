package org.firstinspires.ftc.teamcode.config.subsystems;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;


import org.firstinspires.ftc.robotcore.external.Telemetry;
import static org.firstinspires.ftc.teamcode.config.core.RobotConstants.*;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Linkage extends SubsystemBase {
    private Telemetry telemetry;
    private Servo leftLinkage;
    private Servo rightLinkage;
    private double pos = LINKAGE_ZERO;
    private LinkageState state;

    public Linkage(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        leftLinkage = hardwareMap.get(Servo.class, "leftLinkage");
        rightLinkage = hardwareMap.get(Servo.class, "rightLinkage");
        leftLinkage.setDirection(Servo.Direction.FORWARD);
        rightLinkage.setDirection(Servo.Direction.REVERSE);

        setTarget(this.pos);
    }

    public void setTarget(double target) {
        if (target >= LINKAGE_FULL) {
            this.pos = LINKAGE_FULL;
            leftLinkage.setPosition(LINKAGE_FULL);
            rightLinkage.setPosition(LINKAGE_FULL);
        } else if (target <= LINKAGE_ZERO) {
            this.pos = LINKAGE_ZERO;
            leftLinkage.setPosition(LINKAGE_ZERO);
            rightLinkage.setPosition(LINKAGE_ZERO);
        } else {
            leftLinkage.setPosition(target);
            rightLinkage.setPosition(target);
            this.pos = target;
        }
    }

    public double getPos() {
        return this.pos;
    }
    public LinkageState getState() { return this.state; }

    public void telemetry() {
        telemetry.addData("Linkage servo position: ", getPos());
    }
    public void setMappedTarget(Double input){
        double clippedInput = Range.clip(input, -1, 1);
        double mappedPos = Range.scale(clippedInput, -1, 1, LINKAGE_ZERO, LINKAGE_FULL);
        setTarget(mappedPos);
    }

    public void increment(double increment) {
        setTarget(this.pos + increment);
    }
    public void extend() {
        setTarget(LINKAGE_FULL);
    }
    public void retract() {setTarget(LINKAGE_ZERO);}

    public void setState(LinkageState state) {
        this.state = state;
        leftLinkage.setPosition(state.pos);
        rightLinkage.setPosition(state.pos);
    }
}
