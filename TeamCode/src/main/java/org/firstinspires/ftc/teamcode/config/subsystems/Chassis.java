package org.firstinspires.ftc.teamcode.config.subsystems;

import com.pedropathing.localization.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import static org.firstinspires.ftc.teamcode.config.core.RobotConstants.*;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Chassis {
    private Motor frontRight, frontLeft, rearRight, rearLeft;
    private Telemetry telemetry;
    private MecanumDrive drive;
    private GoBildaPinpointDriver pinpoint;
    public Chassis (HardwareMap hardwareMap, Telemetry telemetry) {
        frontRight = new MotorEx(hardwareMap, RIGHT_FRONT_MOTOR, Motor.GoBILDA.RPM_435);
        frontLeft = new MotorEx(hardwareMap, LEFT_FRONT_MOTOR, Motor.GoBILDA.RPM_435);
        rearRight = new MotorEx(hardwareMap, RIGHT_REAR_MOTOR, Motor.GoBILDA.RPM_435);
        rearLeft = new MotorEx(hardwareMap, LEFT_REAR_MOTOR, Motor.GoBILDA.RPM_435);

        this.telemetry = telemetry;

        frontRight.setInverted(true);
        frontLeft.setInverted(true);
        rearRight.setInverted(true);
        rearLeft.setInverted(true);

        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class,"pinpoint");
        pinpoint.resetPosAndIMU();

        drive = new MecanumDrive(frontLeft, frontRight, rearLeft, rearRight);

    }

    public void robotCentricDriving(double x, double y, double r) {
        drive.driveRobotCentric(x, y, r, false);
    }
}
