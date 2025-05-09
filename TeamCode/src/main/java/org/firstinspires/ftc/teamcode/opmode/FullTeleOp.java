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
import org.firstinspires.ftc.teamcode.config.commands.Extension;
import org.firstinspires.ftc.teamcode.config.commands.PrepareWall;
import org.firstinspires.ftc.teamcode.config.commands.SubmersibleGrab;
import org.firstinspires.ftc.teamcode.config.commands.Transition;
import org.firstinspires.ftc.teamcode.config.pedropathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.config.pedropathing.constants.LConstants;
import org.firstinspires.ftc.teamcode.config.subsystems.Chassis;
import org.firstinspires.ftc.teamcode.config.subsystems.Claw;
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
    private GamepadEx firstDriver;
    private GamepadEx secondDriver;
    private Claw clawSubsystem;
    private Chassis chassis;


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
        chassis = new Chassis(hardwareMap, telemetry);

        firstDriver = new GamepadEx(gamepad1);
        secondDriver = new GamepadEx(gamepad2);


        secondDriver.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(new InstantCommand(() -> {
                    clawSubsystem.rotationIncrement(0.1);
                }));
        secondDriver.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(new InstantCommand(() -> {
                    clawSubsystem.rotationIncrement(-0.1);
                }));

        secondDriver.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenPressed(new Extension(linkageSubsystem, horizontalArmSubsystem, clawSubsystem));
        secondDriver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(new ContractHorizontal(linkageSubsystem, horizontalArmSubsystem));

        secondDriver.getGamepadButton(GamepadKeys.Button.CROSS)
                .whenPressed(new SubmersibleGrab(clawSubsystem, horizontalArmSubsystem));
        secondDriver.getGamepadButton(GamepadKeys.Button.CIRCLE)
                .whenPressed(new PrepareChamber(outtakeSubsystem));
        secondDriver.getGamepadButton(GamepadKeys.Button.SQUARE)
                .whenPressed(new PrepareWall(outtakeSubsystem));
        secondDriver.getGamepadButton(GamepadKeys.Button.TRIANGLE)
                .whenPressed(new InstantCommand(clawSubsystem::open));

        firstDriver.getGamepadButton(GamepadKeys.Button.TRIANGLE)
                .whenPressed(new Transition(outtakeSubsystem, horizontalArmSubsystem, linkageSubsystem));
    }

    @Override
    public void run() {
        super.run(); // DO NOT REMOVE! Runs FTCLib Command Scheduler

        // Pedro field centric movement
        follower.setTeleOpMovementVectors(firstDriver.getLeftY(), firstDriver.getLeftX(), firstDriver.getRightX() * 0.40, false);
        follower.update();


        linkageSubsystem.telemetry();
        horizontalArmSubsystem.telemetry();
        clawSubsystem.telemetry();
        outtakeSubsystem.telemetry();

        chassis.robotCentricDriving(
                firstDriver.getLeftX(),
                firstDriver.getLeftY(),
                firstDriver.getRightX()
               );

        telemetry.update(); // DO NOT REMOVE! Needed for telemetry
    }

}
