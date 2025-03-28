package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.config.subsystems.ExtendSubsystem;

@TeleOp(name = "Drive", group = "!")
public class Drive extends CommandOpMode{

    private ExtendSubsystem extendSubsystem;
    private GamepadEx controlGamepad;

    @Override
    public void initialize(){
        super.reset();

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        extendSubsystem = new ExtendSubsystem(hardwareMap, telemetry);
        controlGamepad = new GamepadEx(gamepad1);

        controlGamepad.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenPressed(new InstantCommand(() -> {
                    extendSubsystem.toFull();
                }));

        controlGamepad.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(new InstantCommand(() -> {
                    extendSubsystem.toZero();
                }));
    }

    @Override
    public void run(){
        super.run();

        extendSubsystem.telemetry();
        telemetry.addLine("hello world");
        telemetry.update();
    }

//    @Override
//    public void runOpMode(){
//        waitForStart();
//         while(opModeIsActive()) {
//             telemetry.update();
//         }
//    }
}
