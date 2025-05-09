package org.firstinspires.ftc.teamcode.opmode.tests;

import static org.firstinspires.ftc.teamcode.config.core.RobotConstants.*;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.config.subsystems.Claw;
import org.firstinspires.ftc.teamcode.config.subsystems.HorizontalArm;
import org.firstinspires.ftc.teamcode.config.subsystems.Linkage;

@TeleOp(name = "A hungry vertically-challenged nomnom system", group = "!")
public class FullHorizontalArmTest extends CommandOpMode {

    private Linkage linkage;
    private Claw claw;
    private HorizontalArm arm;
    private GamepadEx firstDriver;

    @Override
    public void initialize() {
        super.reset();

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        linkage = new Linkage(hardwareMap, telemetry);
        arm = new HorizontalArm(hardwareMap, telemetry);
        claw = new Claw(hardwareMap, telemetry);

        firstDriver = new GamepadEx(gamepad1);

        firstDriver.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(() -> linkage.setState(LinkageState.EXTENDED));
        firstDriver.getGamepadButton(GamepadKeys.Button.Y)
                .whenPressed(() -> linkage.setState(LinkageState.RETRACTED));
        firstDriver.getGamepadButton(GamepadKeys.Button.B)
                .whenPressed(() -> claw.toggleGrab());
        firstDriver.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(() -> linkage.setState(LinkageState.TRANSFER));
        firstDriver.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(() -> claw.rotationIncrement(-0.05));
        firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(() -> arm.setState(HorizontalArmState.TRANSFER));

        firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenPressed(() -> arm.setState(HorizontalArmState.IN_ROBOT));

        firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                .whenPressed(() -> arm.setState(HorizontalArmState.INTAKE));
        //firstDriver.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
        //        .whenPressed(() -> arm.wristIncrement(-0.1));
    }

    @Override
    public void run(){
        super.run(); // DO NOT REMOVE! Runs FTCLib Command Scheduler

        linkage.telemetry();
        arm.telemetry();
        claw.telemetry();

        telemetry.update(); // DO NOT REMOVE! Needed for telemetry
    }
}
