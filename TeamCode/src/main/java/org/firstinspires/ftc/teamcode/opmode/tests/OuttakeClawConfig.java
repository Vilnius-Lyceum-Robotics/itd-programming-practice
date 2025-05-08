package org.firstinspires.ftc.teamcode.opmode.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.config.subsystems.OuttakeSubsystem;

import static org.firstinspires.ftc.teamcode.config.core.RobotConstants.*;

@Config
@TeleOp(name = "Outtake Claw setup")
public class OuttakeClawConfig extends CommandOpMode {

    private Follower follower;
    private OuttakeSubsystem outtakeSubsystem;
    //    private LiftSubsystem liftSubsystem;
    private GamepadEx operator;

    //pivot from park to basket -3400


    @Override
    public void initialize(){
        // DO NOT REMOVE! Resetting FTCLib Command Scheduler
        super.reset();

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        outtakeSubsystem = new OuttakeSubsystem(hardwareMap, telemetry);

        operator = new GamepadEx(gamepad1);

//        operator.getGamepadButton(GamepadKeys.Button.DPAD_UP)
//                .whenPressed(new InstantCommand(outtakeSubsystem::open));
//        operator.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
//                .whenPressed(new InstantCommand(outtakeSubsystem::close));
//
//        operator.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
//                .whenPressed(new InstantCommand(() -> outtakeSubsystem.setRotateState(OuttakeSubsystem.RotateState.FLIPPED)));
//        operator.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
//                .whenPressed(new InstantCommand(() -> outtakeSubsystem.setRotateState(OuttakeSubsystem.RotateState.NORMAL)));

        operator.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenPressed(new InstantCommand(() -> outtakeSubsystem.setPivotState(OuttakeSubsystem.PivotState.HUMAN)));
        operator.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(new InstantCommand(() -> outtakeSubsystem.setPivotState(OuttakeSubsystem.PivotState.CHAMBER )));

        operator.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                .whenPressed(new InstantCommand(() -> outtakeSubsystem.setElbowState(OuttakeSubsystem.ElbowState.HUMAN)));
        operator.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
                .whenPressed(new InstantCommand(() -> outtakeSubsystem.setElbowState(OuttakeSubsystem.ElbowState.CHAMBER)));
    }

    @Override
    public void run(){
        super.run(); // DO NOT REMOVE! Runs FTCLib Command Scheduler
//
//        double clippedInput = Range.clip(operator.getLeftY(), -1, 1);
//        double mappedPos = Range.scale(clippedInput, -1, 1, 0, 1);
//
//        outtakeSubsystem.setPivotTarget((int)mappedPos);
        //liftSubsystem.setTarget(liftTargetVar);
//        liftSubsystem.telemetry();
        outtakeSubsystem.telemetry();

        telemetry.update(); // DO NOT REMOVE! Needed for telemetry
    }
}
