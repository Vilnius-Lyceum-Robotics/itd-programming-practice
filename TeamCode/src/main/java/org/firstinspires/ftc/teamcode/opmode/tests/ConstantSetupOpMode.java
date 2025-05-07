package org.firstinspires.ftc.teamcode.opmode.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.pedropathing.follower.Follower;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.config.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.config.subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.config.subsystems.OuttakeSubsystem;

import static org.firstinspires.ftc.teamcode.config.core.RobotConstants.*;

@Config
public class ConstantSetupOpMode extends CommandOpMode {

    private Follower follower;
    private ExtendSubsystem extendSubsystem;
    private LiftSubsystem liftSubsystem;
    private IntakeSubsystem intakeSubsystem;
    private OuttakeSubsystem outtakeSubsystem;
    private GamepadEx operator;

    @Override
    public void initialize(){
        // DO NOT REMOVE! Resetting FTCLib Command Scheduler
        super.reset();

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        extendSubsystem = new ExtendSubsystem(hardwareMap, telemetry);
        liftSubsystem = new LiftSubsystem(hardwareMap, telemetry);
        intakeSubsystem = new IntakeSubsystem(hardwareMap, telemetry);
        outtakeSubsystem = new OuttakeSubsystem(hardwareMap, telemetry);

        operator = new GamepadEx(gamepad1);
    }

    @Override
    public void run(){
        super.run(); // DO NOT REMOVE! Runs FTCLib Command Scheduler

        // Write code for servo that needs to be moved
        extendSubsystem.setTarget(LIFT_HIGH_BASKET);

        extendSubsystem.telemetry();
        liftSubsystem.telemetry();
        intakeSubsystem.telemetry();
        outtakeSubsystem.telemetry();

        telemetry.update(); // DO NOT REMOVE! Needed for telemetry
    }
}
