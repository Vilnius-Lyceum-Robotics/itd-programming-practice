package org.firstinspires.ftc.teamcode.config.subsystems;

import com.pedropathing.localization.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Tank {
    private Motor frontRight, frontLeft, rearRight, rearLeft;
    private Telemetry telemetry;
    private MecanumDrive drive;
    private GoBildaPinpointDriver pinpoint;
    public Tank (HardwareMap hardwareMap, Telemetry telemetry) {
        rearRight = new MotorEx(hardwareMap, "rearRight", Motor.GoBILDA.RPM_435);
        rearLeft = new MotorEx(hardwareMap, "rearLeft", Motor.GoBILDA.RPM_435);

        this.telemetry = telemetry;

        rearRight.setInverted(false);
        rearLeft.setInverted(true);

        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class,"pinpoint");
        pinpoint.resetPosAndIMU();

    }

    public void robotCentricDriving(double x, double y, double r) {
        rearRight.set(y + r);
        rearLeft.set(y - r);
    }
}
