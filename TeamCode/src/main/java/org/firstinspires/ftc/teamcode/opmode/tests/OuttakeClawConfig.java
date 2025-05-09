package org.firstinspires.ftc.teamcode.opmode.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.config.subsystems.OuttakeSubsystem;
import org.firstinspires.ftc.teamcode.config.commands.PrepareChamber;
import org.firstinspires.ftc.teamcode.config.commands.PrepareWall;

@Config
@TeleOp(name = "Outtake Claw setup")
public class OuttakeClawConfig extends CommandOpMode {

    private Follower follower;
    private OuttakeSubsystem outtakeSubsystem;
    private GamepadEx operator;


    @Override
    public void initialize(){
        // DO NOT REMOVE! Resetting FTCLib Command Scheduler
        super.reset();

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        outtakeSubsystem = new OuttakeSubsystem(hardwareMap, telemetry);
//        liftSubsystem = new LiftSubsystem(hardwareMap, telemetry);

        operator = new GamepadEx(gamepad1);

        operator.getGamepadButton(GamepadKeys.Button.TRIANGLE)
                .whenPressed(new PrepareChamber(outtakeSubsystem));
        operator.getGamepadButton(GamepadKeys.Button.SQUARE)
                .whenPressed(new PrepareWall(outtakeSubsystem));

//        operator.getGamepadButton(GamepadKeys.Button.DPAD_UP)
//                .whenPressed(new InstantCommand(outtakeSubsystem::open));
//        operator.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
//                .whenPressed(new InstantCommand(outtakeSubsystem::close));
//
//        operator.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
//                .whenPressed(new InstantCommand(() -> outtakeSubsystem.setRotateState(OuttakeSubsystem.RotateState.FLIPPED)));
//        operator.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
//                .whenPressed(new InstantCommand(() -> outtakeSubsystem.setRotateState(OuttakeSubsystem.RotateState.NORMAL)));
//
//        operator.getGamepadButton(GamepadKeys.Button.TRIANGLE)
//                .whenPressed(new );
//        operator.getGamepadButton(GamepadKeys.Button.CROSS)
//                .whenPressed(new PrepareWall(outtakeSubsystem, intakeSubsystem, liftSubsystem));
    }

    @Override
    public void run(){
        super.run(); // DO NOT REMOVE! Runs FTCLib Command Scheduler

        outtakeSubsystem.telemetry();

        telemetry.update(); // DO NOT REMOVE! Needed for telemetry
    }
}
