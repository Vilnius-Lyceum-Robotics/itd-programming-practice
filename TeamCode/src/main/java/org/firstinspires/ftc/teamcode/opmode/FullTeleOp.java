package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.pedropathing.follower.Follower;
import com.qualcomm.hardware.lynx.LynxModule;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.config.commands.PrepareChamber;
import org.firstinspires.ftc.teamcode.config.commands.ContractHorizontal;
import org.firstinspires.ftc.teamcode.config.commands.Extend;
import org.firstinspires.ftc.teamcode.config.commands.PrepareWall;
import org.firstinspires.ftc.teamcode.config.commands.SubmersibleGrab;
import org.firstinspires.ftc.teamcode.config.pedropathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.config.pedropathing.constants.LConstants;
import org.firstinspires.ftc.teamcode.config.subsystems.Claw;
//import org.firstinspires.ftc.teamcode.config.subsystems.ExtendSubsystem;
//import org.firstinspires.ftc.teamcode.config.subsystems.IntakeSubsystem;
//import org.firstinspires.ftc.teamcode.config.subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.config.subsystems.HorizontalArm;
import org.firstinspires.ftc.teamcode.config.subsystems.Linkage;
import org.firstinspires.ftc.teamcode.config.subsystems.OuttakeSubsystem;

import java.util.List;

@TeleOp(name = "FullTeleOp", group = "!")
public class FullTeleOp extends CommandOpMode{

    private Follower follower;
    private OuttakeSubsystem outtakeSubsystem;
    private HorizontalArm horizontalArmSubsystem;
    private Linkage linkageSubsystem;
    private GamepadEx driver;
    private GamepadEx operator;
    private Claw clawSubsystem;


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
        linkageSubsystem = new Linkage(hardwareMap, telemetry);
        horizontalArmSubsystem = new HorizontalArm(hardwareMap, telemetry);
        clawSubsystem = new Claw(hardwareMap, telemetry);

        driver = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);


        operator.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(new InstantCommand(() -> {
                    clawSubsystem.rotationIncrement(0.1);
                }));
        operator.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(new InstantCommand(() -> {
                    clawSubsystem.rotationIncrement(-0.1);
                }));

        operator.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenPressed(new Extend(linkageSubsystem, horizontalArmSubsystem));
        operator.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(new ContractHorizontal(linkageSubsystem, horizontalArmSubsystem));

        operator.getGamepadButton(GamepadKeys.Button.CROSS)
                .whenPressed(new SubmersibleGrab(clawSubsystem, horizontalArmSubsystem));
        operator.getGamepadButton(GamepadKeys.Button.CIRCLE)
                .whenPressed(new PrepareChamber(outtakeSubsystem));
        operator.getGamepadButton(GamepadKeys.Button.SQUARE)
                .whenPressed(new PrepareWall(outtakeSubsystem));
        operator.getGamepadButton(GamepadKeys.Button.TRIANGLE)
                .whenPressed(new InstantCommand(clawSubsystem::open));
    }

    @Override
    public void run() {
        super.run(); // DO NOT REMOVE! Runs FTCLib Command Scheduler

        // Pedro field centric movement
        follower.setTeleOpMovementVectors(driver.getLeftY(), driver.getLeftX(), driver.getRightX() * 0.40, false);
        follower.update();


        linkageSubsystem.telemetry();
        horizontalArmSubsystem.telemetry();
        clawSubsystem.telemetry();
        outtakeSubsystem.telemetry();

//        chassis.robotCentricDriving(
//                firstDriver.getLeftX(),
//                firstDriver.getLeftY(),
//                firstDriver.getRightX()
//        );

        telemetry.update(); // DO NOT REMOVE! Needed for telemetry
    }

}
