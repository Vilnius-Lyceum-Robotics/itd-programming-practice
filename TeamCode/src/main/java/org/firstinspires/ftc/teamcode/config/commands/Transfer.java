package org.firstinspires.ftc.teamcode.config.commands;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.config.subsystems.Claw;
import org.firstinspires.ftc.teamcode.config.subsystems.Linkage;
import org.firstinspires.ftc.teamcode.config.subsystems.OuttakeSubsystem;

public class Transfer extends SequentialCommandGroup {
//    public Transfer(OuttakeSubsystem outtakeSubsystem, Claw clawSubsystem, Linkage linkageSubsystem) {
//        addCommands(
//                new InstantCommand(outtakeSubsystem::toTransfer),
//                new InstantCommand(intakeSubsystem::toTransfer),
//                new InstantCommand(linkageSubsystem::toTransfer),
//
//                new WaitCommand(2000), // Wait for linkage to reach target
//
//                new InstantCommand(outtakeSubsystem::close),
//                new InstantCommand(clawSubsystem::),
//                new InstantCommand(intakeSubsystem::toHover)
//        );
//        addRequirements(outtakeSubsystem, intakeSubsystem, linkageSubsystem);
//    }
}
