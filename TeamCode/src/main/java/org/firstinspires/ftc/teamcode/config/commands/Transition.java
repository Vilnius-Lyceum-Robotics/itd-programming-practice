package org.firstinspires.ftc.teamcode.config.commands;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.config.core.RobotConstants;
import org.firstinspires.ftc.teamcode.config.subsystems.HorizontalArm;
import org.firstinspires.ftc.teamcode.config.subsystems.Linkage;
import org.firstinspires.ftc.teamcode.config.subsystems.OuttakeSubsystem;

// Perejimas nuo sample rinkimo i specimen kabinima.
// Reikalingas, kad nesusidauztu vertical ir horizontal claws tarpusavyje

public class Transition extends SequentialCommandGroup {
    public Transition(OuttakeSubsystem outtakeSubsystem, HorizontalArm horizontalArmSubsystem, Linkage linkageSubsystem){
        addCommands(
                new InstantCommand(linkageSubsystem::extend),
                new InstantCommand(outtakeSubsystem::toHuman),
                new WaitCommand(300),
                new InstantCommand(() -> horizontalArmSubsystem.setState(RobotConstants.HorizontalArmState.IN_ROBOT)),
                new WaitCommand(400),
                new InstantCommand(linkageSubsystem::retract)
        );
        addRequirements(outtakeSubsystem);
    }
}