package org.firstinspires.ftc.teamcode.opmode.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.config.commands.ContractHorizontal;
import org.firstinspires.ftc.teamcode.config.commands.Extension;
import org.firstinspires.ftc.teamcode.config.commands.ScoreChamber;
import org.firstinspires.ftc.teamcode.config.commands.SubmersibleGrab;
import org.firstinspires.ftc.teamcode.config.subsystems.Claw;
import org.firstinspires.ftc.teamcode.config.subsystems.HorizontalIntake;
import org.firstinspires.ftc.teamcode.config.subsystems.Linkage;
import org.firstinspires.ftc.teamcode.config.subsystems.Outtake;
import org.firstinspires.ftc.teamcode.config.commands.PrepareChamber;
import org.firstinspires.ftc.teamcode.config.commands.PrepareWall;

@Config
@TeleOp(name = "Outtake Claw setup")
public class OuttakeClawConfig extends CommandOpMode {

    private Follower follower;
    private Outtake outtake;
    private Linkage linkageSubsystem;
    private HorizontalIntake horizontalIntakeSubsystem;
    private Claw clawSubsystem;
    private GamepadEx operator;


    @Override
    public void initialize(){
        // DO NOT REMOVE! Resetting FTCLib Command Scheduler
        super.reset();

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        outtake = new Outtake(hardwareMap, telemetry);
        linkageSubsystem = new Linkage(hardwareMap, telemetry);
        horizontalIntakeSubsystem = new HorizontalIntake(hardwareMap, telemetry);
        clawSubsystem = new Claw(hardwareMap, telemetry);
//        liftSubsystem = new LiftSubsystem(hardwareMap, telemetry);

        operator = new GamepadEx(gamepad1);

        operator.getGamepadButton(GamepadKeys.Button.TRIANGLE)
                .whenPressed(new PrepareChamber(outtake));
        operator.getGamepadButton(GamepadKeys.Button.SQUARE)
                .whenPressed(new PrepareWall(outtake));
        operator.getGamepadButton(GamepadKeys.Button.CIRCLE)
                .whenPressed(new ScoreChamber(outtake));
        operator.getGamepadButton(GamepadKeys.Button.CROSS)
                .whenPressed(new SubmersibleGrab(clawSubsystem, horizontalIntakeSubsystem));

        operator.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenPressed(new Extension(linkageSubsystem, horizontalIntakeSubsystem, clawSubsystem));
        operator.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(new ContractHorizontal(linkageSubsystem, horizontalIntakeSubsystem));
        operator.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                .whenPressed(new InstantCommand(horizontalIntakeSubsystem::hover));
//        operator.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
//                .whenPressed(new Transition(outtakeSubsystem, horizontalArmSubsystem, linkageSubsystem));
        operator.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
                .whenPressed(new InstantCommand(outtake::open));
        operator.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(new InstantCommand(outtake::close ));

//        operator.getGamepadButton(GamepadKeys.Button.TRIANGLE)
//                .whenPressed(new );
//        operator.getGamepadButton(GamepadKeys.Button.CROSS)
//                .whenPressed(new PrepareWall(outtakeSubsystem, intakeSubsystem, liftSubsystem));
    }

    @Override
    public void run(){
        super.run(); // DO NOT REMOVE! Runs FTCLib Command Scheduler

        outtake.telemetry();

        telemetry.update(); // DO NOT REMOVE! Needed for telemetry
    }
}
