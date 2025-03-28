package org.firstinspires.ftc.teamcode.config.subsystems;

import static org.firstinspires.ftc.teamcode.config.core.RobotConstants.*;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDController;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.solversHardware.SolversMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ExtendSubsystem extends SubsystemBase {

    public enum ExtendState {
        ZERO, FULL, TRANSFER
    }

    private Telemetry telemetry;
    private Motor extendMotor;
    private ExtendState extendState = ExtendState.ZERO;
    private PIDController pid;
    private boolean pidOn = false;
    private int target;

    public ExtendSubsystem(HardwareMap hardwareMap, Telemetry telemetry){
        this.telemetry = telemetry;

        extendMotor = new Motor(hardwareMap, MOTOR_EXTEND, Motor.GoBILDA.RPM_435); // Don't know the correct motor
        extendMotor.setInverted(false); // Todo: find correct direction
        extendMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        extendMotor.setRunMode(Motor.RunMode.RawPower);
        extendMotor.stopAndResetEncoder();

        pid = new PIDController(EXTEND_kP, EXTEND_kI, EXTEND_kD);
        pid.setTolerance(EXTEND_ERROR_TOLERANCE);
    }

    @Override
    public void periodic(){
        if(!pidOn) {
            extendMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
            return;
        }
        if(reachedTarget()) {
            extendMotor.set(0);
            return;
        }

        pid.setPID(EXTEND_kP, EXTEND_kI, EXTEND_kD);
        extendMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);
        double encoderPosition = getPos();
        double power = pid.calculate(encoderPosition);
//            if(encoderPosition < 50 && target < 50) extendMotor.setPower(0); // Copied from Indubitables
        extendMotor.set(power);
    }

    public void setTarget(ExtendState state){
        extendState = state;
        pidOn = true;

        switch(state) {
            case FULL:
                target = EXTEND_FULL;
                break;
            case ZERO:
                target = EXTEND_ZERO;
                break;
            case TRANSFER:
                target = EXTEND_TRANSFER;
                break;
        }

        pid.setSetPoint(target);
    }

    public int getPos(){
        return extendMotor.getCurrentPosition();
    }

    public ExtendState getState(){
        return extendState;
    }

    public boolean reachedTarget(){
        return pid.atSetPoint(); // Math.abs(errorVal_p) < errorTolerance_p
    }

    public void toFull(){
        setTarget(ExtendState.FULL);
    }

    public void toZero(){
        setTarget(ExtendState.ZERO);
    }

    public void toTransfer(){
        setTarget(ExtendState.TRANSFER);
    }

    public void telemetry(){
        telemetry.addData("Extend pos: ", getPos());
        telemetry.addData("Target pos: ", target);
    }
}