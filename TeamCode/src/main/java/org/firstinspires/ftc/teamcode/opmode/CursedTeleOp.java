package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.config.commands.ContractHorizontal;
import org.firstinspires.ftc.teamcode.config.commands.Extension;
import org.firstinspires.ftc.teamcode.config.commands.PrepareChamber;
import org.firstinspires.ftc.teamcode.config.commands.PrepareWall;
import org.firstinspires.ftc.teamcode.config.commands.ScoreChamber;
import org.firstinspires.ftc.teamcode.config.commands.SubmersibleGrab;
import org.firstinspires.ftc.teamcode.config.commands.Transition;
import org.firstinspires.ftc.teamcode.config.pedropathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.config.pedropathing.constants.LConstants;
import org.firstinspires.ftc.teamcode.config.subsystems.Chassis;
import org.firstinspires.ftc.teamcode.config.subsystems.Claw;
import org.firstinspires.ftc.teamcode.config.subsystems.HorizontalArm;
import org.firstinspires.ftc.teamcode.config.subsystems.Linkage;
import org.firstinspires.ftc.teamcode.config.subsystems.OuttakeSubsystem;
import org.firstinspires.ftc.teamcode.config.subsystems.Tank;

@TeleOp(name = "Tank teleop", group = "!")
public class CursedTeleOp extends CommandOpMode{

    // TODO fix whatever is going on with the outtake arm

    private Follower follower;
    private OuttakeSubsystem outtakeSubsystem;
    private HorizontalArm horizontalArmSubsystem;
    private Linkage linkageSubsystem;
    private GamepadEx firstDriver;
    private GamepadEx secondDriver;
    private Claw clawSubsystem;
    private Tank chassis;


    @Override
    public void initialize(){
        // DO NOT REMOVE! Resetting FTCLib Command Scheduler
        super.reset();

        // Bulk reads
        /*List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }*/

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        follower = new Follower(hardwareMap, FConstants.class, LConstants.class);

        outtakeSubsystem = new OuttakeSubsystem(hardwareMap, telemetry);
        linkageSubsystem = new Linkage(hardwareMap, telemetry);
        horizontalArmSubsystem = new HorizontalArm(hardwareMap, telemetry);
        clawSubsystem = new Claw(hardwareMap, telemetry);
        chassis = new Tank(hardwareMap, telemetry);

        firstDriver = new GamepadEx(gamepad1);
        secondDriver = new GamepadEx(gamepad2);


        /*secondDriver.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(new InstantCommand(() -> {
                    clawSubsystem.rotationIncrement(0.1);
                }));
        secondDriver.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(new InstantCommand(() -> {
                    clawSubsystem.rotationIncrement(-0.1);
                }));*/

        // Intake arm controls

        secondDriver.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(new Extension(linkageSubsystem, horizontalArmSubsystem, clawSubsystem));
        secondDriver.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(new ContractHorizontal(linkageSubsystem, horizontalArmSubsystem));
        secondDriver.getGamepadButton(GamepadKeys.Button.CIRCLE)
                .whenPressed(new SubmersibleGrab(clawSubsystem, horizontalArmSubsystem));
        secondDriver.getGamepadButton(GamepadKeys.Button.CROSS)
                .whenPressed(new InstantCommand(clawSubsystem::toggleGrab));

        // Outtake arm controls

        secondDriver.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                .whenPressed(new PrepareWall(outtakeSubsystem));
        secondDriver.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
                .whenPressed(new PrepareChamber(outtakeSubsystem));
        secondDriver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(new ScoreChamber(outtakeSubsystem));
        secondDriver.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenPressed(new Transition(outtakeSubsystem, horizontalArmSubsystem, linkageSubsystem));

//        follower.startTeleopDrive();
        firstDriver.getGamepadButton(GamepadKeys.Button.CIRCLE)
                .whenPressed(() -> outtakeSubsystem.close());
        firstDriver.getGamepadButton(GamepadKeys.Button.SQUARE)
                .whenPressed(() -> outtakeSubsystem.open());
    }

    @Override
    public void run() {
        super.run(); // DO NOT REMOVE! Runs FTCLib Command Scheduler

        // Pedro field centric movement
//        follower.setTeleOpMovementVectors(firstDriver.getLeftY(), firstDriver.getLeftX(), firstDriver.getRightX() * 0.40, false);
//        follower.update();

        clawSubsystem.setMappedTarget(secondDriver.getLeftX());

        if(firstDriver.isDown(GamepadKeys.Button.TRIANGLE)){
            outtakeSubsystem.mappedPivot(secondDriver.getRightY());
        }

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
