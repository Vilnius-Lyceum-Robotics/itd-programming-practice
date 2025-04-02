package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.pedropathing.follower.Follower;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;

import org.firstinspires.ftc.teamcode.config.commands.Chamber;
import org.firstinspires.ftc.teamcode.config.commands.Park;
import org.firstinspires.ftc.teamcode.config.pedropathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.config.pedropathing.constants.LConstants;
import org.firstinspires.ftc.teamcode.config.subsystems.ExtendSubsystem;
import org.firstinspires.ftc.teamcode.config.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.config.subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.config.subsystems.OuttakeSubsystem;

import org.firstinspires.ftc.teamcode.config.core.paths.OneSpec;

import java.util.List;

@Autonomous(name = "SpecimenAuto", group = "!")
public class SpecimenAuto extends CommandOpMode {
    private Follower follower;
    private LiftSubsystem liftSubsystem;
    private OuttakeSubsystem outtakeSubsystem;
    private IntakeSubsystem intakeSubsystem;
    private ExtendSubsystem extendSubsystem;

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
        liftSubsystem = new LiftSubsystem(hardwareMap, telemetry);
        outtakeSubsystem = new OuttakeSubsystem(hardwareMap, telemetry);
        intakeSubsystem = new IntakeSubsystem(hardwareMap, telemetry);
        extendSubsystem = new ExtendSubsystem(hardwareMap, telemetry);

        schedule(
                new RunCommand(() -> follower.update()),

                new SequentialCommandGroup(
                        new ParallelCommandGroup(
                                new FollowPathCommand(follower, OneSpec.score1()),
                                new SequentialCommandGroup(
                                        new WaitCommand(500),
                                        new Chamber(outtakeSubsystem, liftSubsystem)
                                )
                        ),
                        new InstantCommand(outtakeSubsystem::open),
                        new ParallelCommandGroup(
                                new FollowPathCommand(follower, OneSpec.park()),
                                new Park(outtakeSubsystem, liftSubsystem, intakeSubsystem, extendSubsystem)
                        )
                )
        );
    }

    @Override
    public void run(){
        super.run(); // DO NOT REMOVE! Runs FTCLib Command Scheduler

        extendSubsystem.telemetry();
        liftSubsystem.telemetry();
        intakeSubsystem.telemetry();
        outtakeSubsystem.telemetry();

        telemetry.update(); // DO NOT REMOVE! Needed for telemetry
    }
}
