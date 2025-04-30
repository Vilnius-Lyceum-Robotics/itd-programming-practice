package org.firstinspires.ftc.teamcode.opmode.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.config.tests.LinkageServo;

@TeleOp(name = "sadLittleServoTest", group = "!")
public class LinkageServoTest extends CommandOpMode {

    private LinkageServo sadLittleServo;
    private GamepadEx firstDriver;

    @Override
    public void initialize() {

        super.reset();

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        sadLittleServo = new LinkageServo(hardwareMap, telemetry);
        firstDriver = new GamepadEx(gamepad1);

        firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenPressed(() -> sadLittleServo.extend());

        firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(() -> sadLittleServo.retract());

        firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                .whenPressed(() -> sadLittleServo.increment(0.25));
        firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
                .whenPressed(() -> sadLittleServo.increment(-0.25));



        //adjustPos(firstDriver.getRightY());

    }
    @Override
    public void run(){
        super.run(); // DO NOT REMOVE! Runs FTCLib Command Scheduler

        sadLittleServo.telemetry();

        telemetry.update(); // DO NOT REMOVE! Needed for telemetry
    }

    public void adjustPos(double y) {
        sadLittleServo.increment(y);
    }

}
