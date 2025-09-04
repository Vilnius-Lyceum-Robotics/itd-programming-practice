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
    private static final double ROTATOR_TURNS_PER_SEC = 0.5;
    private static final double ANGLE_TURNS_PER_SEC = 0.5;
    private int rotatorState = 0;

    @Override
    public void initialize() {
        super.reset();

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        rotator = new Rotator(hardwareMap, telemetry);
        firstDriver = new GamepadEx(gamepad1);

        firstDriver.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(() -> {
                    if(rotatorState == 0) rotatorState = 1;
                }).whenReleased(() -> {
                    if(rotatorState == 1) rotatorState = 0;
                });
        firstDriver.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(() -> {
                    if(rotatorState == 0) rotatorState = -1;
                }).whenReleased(() -> {
                    if(rotatorState == -1) rotatorState = 0;
                });
        firstDriver.getGamepadButton(GamepadKeys.Button.TRIANGLE)
                .whenPressed(() -> rotator.setAngle(IN_ANGLE_UP));
        firstDriver.getGamepadButton(GamepadKeys.Button.CROSS)
                .whenPressed(() -> rotator.setAngle(IN_ANGLE_DOWN));
    }

    private long lastTick = System.nanoTime();
    @Override
    public void run(){
        super.run(); // DO NOT REMOVE! Runs FTCLib Command Scheduler

        double mult = (System.nanoTime() - lastTick / 1e9);
        lastTick = System.nanoTime();

        double angleState = firstDriver.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) - firstDriver.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER);

        rotator.setRotatorRel(mult * ROTATOR_TURNS_PER_SEC * rotatorState);
        rotator.setAngleRel(mult * ANGLE_TURNS_PER_SEC * angleState);

        rotator.telemetry();

        telemetry.update(); // DO NOT REMOVE! Needed for telemetry
    }
}
