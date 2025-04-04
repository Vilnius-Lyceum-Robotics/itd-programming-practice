package org.firstinspires.ftc.teamcode.opmode.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.config.subsystems.LinkageSubsystem;

public class LinkageTest extends CommandOpMode {

    private LinkageSubsystem linkageSubsystem;
    private GamepadEx operator;

    @Override
    public void initialize(){
        // DO NOT REMOVE! Resetting FTCLib Command Scheduler
        super.reset();

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        linkageSubsystem = new LinkageSubsystem(hardwareMap, telemetry);

        operator = new GamepadEx(gamepad1);

        operator.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenPressed(new InstantCommand(linkageSubsystem::toFull));

        operator.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(new InstantCommand(linkageSubsystem::toZero));

        operator.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                .toggleWhenPressed(
                        new RunCommand(
                            () -> linkageSubsystem.setMappedTarget(operator.getLeftY()),
                            linkageSubsystem
                ));

//        linkageSubsystem.setDefaultCommand(new RunCommand(
//                () -> linkageSubsystem.setMappedTarget(operator.getLeftY()),
//                linkageSubsystem
//        ));
    }

    @Override
    public void run(){
        super.run(); // DO NOT REMOVE! Runs FTCLib Command Scheduler

        linkageSubsystem.telemetry();

        telemetry.update(); // DO NOT REMOVE! Needed for telemetry
    }

}
