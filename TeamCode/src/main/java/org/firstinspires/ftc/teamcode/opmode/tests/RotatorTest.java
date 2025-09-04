package org.firstinspires.ftc.teamcode.opmode.tests;

import static org.firstinspires.ftc.teamcode.config.core.RobotConstants.IN_ANGLE_DOWN;
import static org.firstinspires.ftc.teamcode.config.core.RobotConstants.IN_ANGLE_UP;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.config.subsystems.Rotator;

public class RotatorTest extends CommandOpMode {
    private Rotator rotator;
    private GamepadEx firstDriver;

    @Override
    public void initialize() {
        super.reset();

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        rotator = new Rotator(hardwareMap, telemetry);
        firstDriver = new GamepadEx(gamepad1);

        firstDriver.getGamepadButton(GamepadKeys.Button.RIGHT_STICK_BUTTON)
                .whenPressed(() -> rotator.setAngleRel(0.1));
        firstDriver.getGamepadButton(GamepadKeys.Button.LEFT_STICK_BUTTON)
                .whenPressed(() -> rotator.setAngleRel(-0.1));
        firstDriver.getGamepadButton(GamepadKeys.Button.TRIANGLE)
                .whenPressed(() -> rotator.setAngle(IN_ANGLE_UP));
        firstDriver.getGamepadButton(GamepadKeys.Button.CROSS)
                .whenPressed(() -> rotator.setAngle(IN_ANGLE_DOWN));
    }

    @Override
    public void run(){
        super.run(); // DO NOT REMOVE! Runs FTCLib Command Scheduler

        rotator.telemetry();

        telemetry.update(); // DO NOT REMOVE! Needed for telemetry
    }
}
