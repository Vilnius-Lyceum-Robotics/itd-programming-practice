package org.firstinspires.ftc.teamcode.opmode.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.config.subsystems.Outtake;

@Config
@TeleOp(name = "Constants setup")
public class ConstantSetupOpMode extends CommandOpMode {

    private Follower follower;
    private Outtake outtake;
//    private LiftSubsystem liftSubsystem;
    private GamepadEx operator;
    public static int pivotTargetVar = 0;
    //pivot from park to basket -3400
    public static int liftTargetVar = 0;

    @Override
    public void initialize(){
        // DO NOT REMOVE! Resetting FTCLib Command Scheduler
        super.reset();

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        outtake = new Outtake(hardwareMap, telemetry);
//        liftSubsystem = new LiftSubsystem(hardwareMap, telemetry);

        operator = new GamepadEx(gamepad1);
    }

    @Override
    public void run(){
        super.run(); // DO NOT REMOVE! Runs FTCLib Command Scheduler

        double clippedInput = Range.clip(operator.getLeftY(), -1, 1);
        double mappedPos = Range.scale(clippedInput, -1, 1, 0, -3400);

        outtake.setPivotTarget((int)mappedPos);
        //liftSubsystem.setTarget(liftTargetVar);
//        liftSubsystem.telemetry();
        outtake.telemetry();

        telemetry.update(); // DO NOT REMOVE! Needed for telemetry
    }
}
