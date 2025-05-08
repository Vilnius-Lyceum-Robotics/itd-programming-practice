package org.firstinspires.ftc.teamcode.config.commands;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.command.WaitUntilCommand;

//import org.firstinspires.ftc.teamcode.config.subsystems.ExtendSubsystem;
//import org.firstinspires.ftc.teamcode.config.subsystems.IntakeSubsystem;
//import org.firstinspires.ftc.teamcode.config.subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.config.subsystems.OuttakeSubsystem;

public class Transfer extends SequentialCommandGroup {
//    public Transfer(LiftSubsystem liftSubsystem, OuttakeSubsystem outtakeSubsystem, IntakeSubsystem intakeSubsystem, ExtendSubsystem extendSubsystem) {
//        addCommands(
//                new InstantCommand(liftSubsystem::toTransfer),
//                new InstantCommand(outtakeSubsystem::toTransfer),
//                new InstantCommand(intakeSubsystem::toTransfer),
//                new InstantCommand(extendSubsystem::toTransfer),
//
//                new ParallelCommandGroup(
//                        new WaitUntilCommand(extendSubsystem::reachedTarget),
//                        new WaitUntilCommand(liftSubsystem::reachedTarget),
//                        new WaitCommand(2000) // Wait for outtake pivot to reach target
//                ),
//
//                new InstantCommand(outtakeSubsystem::close),
//                new InstantCommand(intakeSubsystem::open),
//                new InstantCommand(intakeSubsystem::toHover)
//        );
//        addRequirements(liftSubsystem, outtakeSubsystem, intakeSubsystem, extendSubsystem);
//    }
}
