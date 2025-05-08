package org.firstinspires.ftc.teamcode.opmode.tests;

import static org.firstinspires.ftc.teamcode.config.core.RobotConstants.*;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.config.subsystems.Claw;

@TeleOp(name = "nom nom h claw hungry", group = "!")
public class ClawTest extends CommandOpMode {

    private Claw claw;
    private GamepadEx firstDriver;

    @Override
    public void initialize() {
        // DO NOT REMOVE! Resetting FTCLib Command Scheduler
        super.reset();

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        claw = new Claw(hardwareMap, telemetry, H_CLAW_ROTATION_SERVO, H_CLAW_GRAB_SERVO);
        firstDriver = new GamepadEx(gamepad1);

        firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
                .whenPressed(() -> claw.grabIncrement(0.05));
        firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                .whenPressed(() -> claw.grabIncrement(-0.05));
        firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenPressed(() -> claw.rotationIncrement(0.05));
        firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(() -> claw.rotationIncrement(-0.05));

    }

    @Override
    public void run(){
        super.run(); // DO NOT REMOVE! Runs FTCLib Command Scheduler

        claw.telemetry();

        telemetry.update(); // DO NOT REMOVE! Needed for telemetry
    }
}
