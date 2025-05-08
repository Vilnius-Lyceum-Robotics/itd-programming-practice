package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.pedropathing.follower.Follower;
import com.qualcomm.hardware.lynx.LynxModule;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.config.commands.Chamber;
import org.firstinspires.ftc.teamcode.config.commands.ContractHorizontal;
import org.firstinspires.ftc.teamcode.config.commands.Extend;
import org.firstinspires.ftc.teamcode.config.commands.HighBucket;
import org.firstinspires.ftc.teamcode.config.commands.PrepareWall;
import org.firstinspires.ftc.teamcode.config.commands.ScoreSample;
import org.firstinspires.ftc.teamcode.config.commands.SubmersibleGrab;
import org.firstinspires.ftc.teamcode.config.commands.Transfer;
import org.firstinspires.ftc.teamcode.config.pedropathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.config.pedropathing.constants.LConstants;
import org.firstinspires.ftc.teamcode.config.subsystems.Chassis;
import org.firstinspires.ftc.teamcode.config.subsystems.Claw;
//import org.firstinspires.ftc.teamcode.config.subsystems.ExtendSubsystem;
//import org.firstinspires.ftc.teamcode.config.subsystems.IntakeSubsystem;
//import org.firstinspires.ftc.teamcode.config.subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.config.subsystems.Linkage;
import org.firstinspires.ftc.teamcode.config.subsystems.OuttakeSubsystem;

import java.util.List;

@TeleOp(name = "FullTeleOp", group = "!")
public class FullTeleOp extends CommandOpMode{

    private Follower follower;
    private OuttakeSubsystem outtakeSubsystem;
    private Linkage linkage;
    private Chassis chassis;
    private GamepadEx driver;
    private GamepadEx operator;
    private Claw claw;


    @Override
    public void initialize(){
        // DO NOT REMOVE! Resetting FTCLib Command Scheduler
        super.reset();

        // Bulk reads
        List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        follower = new Follower(hardwareMap, FConstants.class, LConstants.class);

        outtakeSubsystem = new OuttakeSubsystem(hardwareMap, telemetry);
        linkage = new Linkage(hardwareMap, telemetry);
        chassis = new Chassis(hardwareMap, telemetry);

        driver = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);


        operator.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(new InstantCommand(() -> {
                    claw.rotationIncrement(0.1);
                }));
        operator.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(new InstantCommand(() -> {
                    claw.rotationIncrement(-0.01);
                }));

//        operator.getGamepadButton(GamepadKeys.Button.CROSS)
//                .whenPressed(new SubmersibleGrab(intakeSubsystem));
//        operator.getGamepadButton(GamepadKeys.Button.CIRCLE)
//                .whenPressed(new Transfer(liftSubsystem, outtakeSubsystem, intakeSubsystem, extendSubsystem));
//        operator.getGamepadButton(GamepadKeys.Button.TRIANGLE)
//                .whenPressed(new Chamber(outtakeSubsystem, liftSubsystem));
//        operator.getGamepadButton(GamepadKeys.Button.SQUARE)
//                .whenPressed(new PrepareWall(outtakeSubsystem, intakeSubsystem, liftSubsystem));

        operator.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenPressed(new InstantCommand(() -> linkage.extend()));
        operator.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(new InstantCommand(() -> linkage.retract()));

    }

    @Override
    public void run() {
        super.run(); // DO NOT REMOVE! Runs FTCLib Command Scheduler

        // Pedro field centric movement
        follower.setTeleOpMovementVectors(driver.getLeftY(), driver.getLeftX(), driver.getRightX() * 0.40, false);
        follower.update();

//        extendSubsystem.telemetry();
//        liftSubsystem.telemetry();
//        intakeSubsystem.telemetry();
        outtakeSubsystem.telemetry();

//        chassis.robotCentricDriving(
//                firstDriver.getLeftX(),
//                firstDriver.getLeftY(),
//                firstDriver.getRightX()
//        );

        telemetry.update(); // DO NOT REMOVE! Needed for telemetry
    }

}
