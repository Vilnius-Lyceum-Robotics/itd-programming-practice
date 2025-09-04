package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.pedropathing.follower.Follower;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.config.pedropathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.config.pedropathing.constants.LConstants;
import org.firstinspires.ftc.teamcode.config.subsystems.Chassis;

@TeleOp(name = "FullTeleOp", group = "!")
public class FullTeleOp extends CommandOpMode{

    // TODO fix whatever is going on with the outtake arm

    private Follower follower;
    private GamepadEx firstDriver;
    private GamepadEx secondDriver;
    private Chassis chassis;


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

        chassis = new Chassis(hardwareMap, telemetry);

        firstDriver = new GamepadEx(gamepad1);
        secondDriver = new GamepadEx(gamepad2);

        follower.startTeleopDrive();

//        firstDriver.getGamepadButton(GamepadKeys.Button.CIRCLE)
//                .whenPressed(() -> outtake.close());
    }

    @Override
    public void run() {
        super.run(); // DO NOT REMOVE! Runs FTCLib Command Scheduler

//      outtake.telemetry();

        chassis.robotCentricDriving(
                firstDriver.getLeftX(),
                firstDriver.getLeftY(),
                firstDriver.getRightX()
               );

        telemetry.update(); // DO NOT REMOVE! Needed for telemetry
    }

}
