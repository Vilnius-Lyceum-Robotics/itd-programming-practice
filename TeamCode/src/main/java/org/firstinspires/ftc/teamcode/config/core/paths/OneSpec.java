package org.firstinspires.ftc.teamcode.config.core.paths;

import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathBuilder;
import com.pedropathing.pathgen.PathChain;

public class OneSpec {

    public static Pose startPose = new Pose(7, 72, Math.toRadians(180));
    public static Pose score1Pose = new Pose(37, 72, Math.toRadians(180));
    public static Pose parkPose = new Pose(10, 20, Math.toRadians(180));

    public static PathChain score1(){
        return new PathBuilder()
                .addPath(
                        new BezierLine(
                                startPose,
                                score1Pose
                        )
                )
                .setConstantHeadingInterpolation(startPose.getHeading())
//                .setZeroPowerAccelerationMultiplier(6)
                .build();
    }


    public static PathChain park() {
        return new PathBuilder()
                .addPath(
                        new BezierLine(
                                score1Pose,
                                parkPose
                        )
                )
                .setConstantHeadingInterpolation(parkPose.getHeading())
//                .setZeroPowerAccelerationMultiplier(7)
                .build();
    }

}


/*
public class GeneratedPath {

  public GeneratedPath() {
    PathBuilder builder = new PathBuilder();

    builder
      .addPath(
        // Line 1
        new BezierLine(
          new Point(7.000, 72.000, Point.CARTESIAN),
          new Point(37.000, 72.000, Point.CARTESIAN)
        )
      )
      .setConstantHeadingInterpolation(Math.toRadians(180))
      .addPath(
        // Line 2
        new BezierLine(
          new Point(37.000, 72.000, Point.CARTESIAN),
          new Point(10.000, 20.000, Point.CARTESIAN)
        )
      )
      .setConstantHeadingInterpolation(Math.toRadians(180));
  }
}
 */