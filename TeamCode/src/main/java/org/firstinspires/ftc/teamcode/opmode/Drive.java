package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.config.subsystems.ExtendSubsystem;
import org.firstinspires.ftc.teamcode.config.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.config.subsystems.LiftSubsystem;

@TeleOp(name = "Drive", group = "!")
public class Drive extends CommandOpMode{

    private ExtendSubsystem extendSubsystem;
    private LiftSubsystem liftSubsystem;
    private IntakeSubsystem intakeSubsystem;
    private GamepadEx controlGamepad;

    @Override
    public void initialize(){
        super.reset();

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        extendSubsystem = new ExtendSubsystem(hardwareMap, telemetry);
        liftSubsystem = new LiftSubsystem(hardwareMap, telemetry);
        intakeSubsystem = new IntakeSubsystem(hardwareMap, telemetry);
        controlGamepad = new GamepadEx(gamepad1);

        controlGamepad.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenPressed(new InstantCommand(() -> {
                    extendSubsystem.toFull();
                }));
        controlGamepad.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(new InstantCommand(() -> {
                    extendSubsystem.toZero();
                }));

        controlGamepad.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
                .whenPressed(new InstantCommand(() -> {
                    liftSubsystem.toHighBucket();
                }));
        controlGamepad.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                .whenPressed(new InstantCommand(() -> {
                    liftSubsystem.toZero();
                }));

        controlGamepad.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(new InstantCommand(() -> {
                    intakeSubsystem.rotateCycle(true);
                }));
        controlGamepad.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(new InstantCommand(() -> {
                    intakeSubsystem.rotateCycle(false);
                }));
    }

    @Override
    public void run(){
        super.run();

        extendSubsystem.telemetry();
        liftSubsystem.telemetry();

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
