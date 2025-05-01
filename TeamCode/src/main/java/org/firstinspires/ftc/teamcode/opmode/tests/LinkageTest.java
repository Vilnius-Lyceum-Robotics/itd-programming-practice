package org.firstinspires.ftc.teamcode.opmode.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.config.subsystems.Linkage;
@TeleOp(name = "notSoSadLinkageTest", group = "!")
public class LinkageTest extends CommandOpMode {

    private Linkage linkage;
    private GamepadEx firstDriver;

    @Override
    public void initialize(){
        // DO NOT REMOVE! Resetting FTCLib Command Scheduler
        super.reset();

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        linkage = new Linkage(hardwareMap, telemetry);

        firstDriver = new GamepadEx(gamepad1);

        firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenPressed(() -> linkage.extend());

        firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(() -> linkage.retract());

        firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                .whenPressed(() -> linkage.force());

        //firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(new InstantCommand(linkageSubsystem::toFull));

        //firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(new InstantCommand(linkageSubsystem::toZero));

        /*firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                .toggleWhenPressed(
                        new RunCommand(
                            () -> linkageSubsystem.setMappedTarget(operator.getLeftY()),
                            linkageSubsystem
                ));

//        linkageSubsystem.setDefaultCommand(new RunCommand(
//                () -> linkageSubsystem.setMappedTarget(operator.getLeftY()),
//                linkageSubsystem
//        ));*/
    }

    @Override
    public void run(){
        super.run(); // DO NOT REMOVE! Runs FTCLib Command Scheduler

        linkage.telemetry();

        telemetry.update(); // DO NOT REMOVE! Needed for telemetry
    }

}
