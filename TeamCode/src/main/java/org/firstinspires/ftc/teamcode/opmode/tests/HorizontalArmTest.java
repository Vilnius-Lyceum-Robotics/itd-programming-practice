package org.firstinspires.ftc.teamcode.opmode.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.config.subsystems.HorizontalArm;
@TeleOp(name = "KazkoksNepanamintasHorizontalArmTest", group = "!")
public class HorizontalArmTest extends CommandOpMode {

    private HorizontalArm horizontalArm;
    private GamepadEx firstDriver;

    @Override
    public void initialize() {
        super.reset();

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        horizontalArm = new HorizontalArm(hardwareMap, telemetry);

        firstDriver = new GamepadEx(gamepad1);

        firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(() -> horizontalArm.elbowIncrement(-0.1));

        firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenPressed(() -> horizontalArm.elbowIncrement(0.1));

        firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                .whenPressed(() -> horizontalArm.wristIncrement(0.1));
        firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
                .whenPressed(() -> horizontalArm.wristIncrement(-0.1));
    }

    @Override
    public void run(){
        super.run(); // DO NOT REMOVE! Runs FTCLib Command Scheduler

        horizontalArm.telemetry();

        telemetry.update(); // DO NOT REMOVE! Needed for telemetry
    }
}
