package org.firstinspires.ftc.teamcode.opmode.tests;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.config.subsystems.ExtendSubsystem;

@TeleOp(name = "ExtendTest", group = "!")
public class ExtendTest extends CommandOpMode{

    private Telemetry telemetry;
    private ExtendSubsystem extendSubsystem;

    @Override
    public void initialize(){
        extendSubsystem = new ExtendSubsystem(hardwareMap, telemetry);
    }

    @Override
    public void runOpMode(){
            schedule(
                    new SequentialCommandGroup(
                            new InstantCommand(() -> extendSubsystem.toFull()),
                            new WaitCommand(5000),
                            new InstantCommand(() -> extendSubsystem.toZero()),
                            new WaitCommand(5000)
                    )
            );
            telemetry.update();
    }
}
