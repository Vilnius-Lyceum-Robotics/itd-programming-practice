package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.pedropathing.follower.Follower;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.config.pedropathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.config.pedropathing.constants.LConstants;
import org.firstinspires.ftc.teamcode.config.subsystems.Chassis;
import org.firstinspires.ftc.teamcode.config.subsystems.Claw;
import org.firstinspires.ftc.teamcode.config.subsystems.HorizontalIntake;
import org.firstinspires.ftc.teamcode.config.subsystems.Linkage;
import org.firstinspires.ftc.teamcode.config.subsystems.Outtake;

import java.util.List;

@Autonomous(name = "ParkAuto", group = "!")
public class ParkAuto extends CommandOpMode {
    private Follower follower;
    private Outtake outtake;
    private Linkage linkageSubsystem;
    private Claw clawSubsystem;
    private HorizontalIntake horizontalIntakeSubsystem;
    private Chassis chassisSubsystem;

    @Override
    public void initialize() {
        // DO NOT REMOVE! Resetting FTCLib Command Scheduler
        super.reset();

        // Bulk reads
        List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        follower = new Follower(hardwareMap, FConstants.class, LConstants.class);
        outtake = new Outtake(hardwareMap, telemetry);
        linkageSubsystem = new Linkage(hardwareMap, telemetry);
        horizontalIntakeSubsystem = new HorizontalIntake(hardwareMap, telemetry);
        clawSubsystem = new Claw(hardwareMap, telemetry);
        chassisSubsystem = new Chassis(hardwareMap, telemetry);

        schedule(
                new SequentialCommandGroup(
                        new WaitCommand(1000),
                        new InstantCommand(() -> chassisSubsystem.robotCentricDriving(0, 1, 0)),
                        new WaitCommand(500),
                        new InstantCommand(() -> chassisSubsystem.robotCentricDriving(0, 0, 0))
                )
        );
    }

    @Override
    public void run() {
        super.run(); // DO NOT REMOVE! Runs FTCLib Command Scheduler

        linkageSubsystem.telemetry();
        horizontalIntakeSubsystem.telemetry();
        clawSubsystem.telemetry();
        outtake.telemetry();

        telemetry.update(); // DO NOT REMOVE! Needed for telemetry
    }
}
