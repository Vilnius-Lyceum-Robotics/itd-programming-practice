package org.firstinspires.ftc.teamcode.config.subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDController;
import com.seattlesolvers.solverslib.controller.PIDFController;
import com.seattlesolvers.solverslib.solversHardware.SolversMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.config.core.RobotConstants;
import org.firstinspires.ftc.teamcode.config.core.RobotConstants.*;

public class ExtendSubsystem extends SubsystemBase {

    public enum ExtendState {
        ZERO, FULL, TRANSFER
    }

    private Telemetry telemetry;
    private SolversMotor extendMotor;
    private ExtendState extendState = ExtendState.ZERO;
    private PIDFController pidf;
    private boolean pidOn = false;
    private int target;

    public ExtendSubsystem(HardwareMap hardwareMap, Telemetry telemetry){
        this.telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        extendMotor = new SolversMotor(hardwareMap.get(DcMotor.class, RobotConstants.EXTEND_MOTOR), RobotConstants.POWER_THRESHOLD);

        extendMotor.setDirection(DcMotorSimple.Direction.FORWARD); // Todo: find correct direction
        extendMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extendMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extendMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        pidf = new PIDFController(RobotConstants.kP, RobotConstants.kI, RobotConstants.kD, RobotConstants.kF);
        pidf.setTolerance(RobotConstants.ERROR_TOLERANCE);
    }

    @Override
    public void periodic(){
        if(pidOn) {
            pidf.setPIDF(RobotConstants.kP, RobotConstants.kI, RobotConstants.kD, RobotConstants.kF);

            extendMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

            double encoderPosition = getPos();
            double power = pidf.calculate(encoderPosition, target);

//            if(encoderPosition < 50 && target < 50) extendMotor.setPower(0); // Copied from Indubitables
            extendMotor.setPower(power);
        } else {
            extendMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

        telemetry();
    }

    public void setTarget(ExtendState state){
        extendState = state;
        pidOn = true;

        switch(state) {
            case FULL:
                target = RobotConstants.EXTEND_FULL;
                break;
            case ZERO:
                target = RobotConstants.EXTEND_ZERO;
                break;
            case TRANSFER:
                target = RobotConstants.EXTEND_TRANSFER;
                break;
        }
    }

    public int getPos(){
        return extendMotor.getPosition();
    }

    public ExtendState getState(){
        return extendState;
    }

    public boolean reachedTarget(){
        return pidf.atSetPoint(); // Math.abs(errorVal_p) < errorTolerance_p
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
