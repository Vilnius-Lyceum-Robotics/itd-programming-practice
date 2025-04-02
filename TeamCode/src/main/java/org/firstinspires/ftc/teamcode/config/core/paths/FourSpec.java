package org.firstinspires.ftc.teamcode.config.core.paths;

import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathBuilder;
import com.pedropathing.pathgen.PathChain;

public class FourSpec {
    public static Pose startPose = new Pose(7, 72, Math.toRadians(180));
    public static Pose score1Pose = new Pose(37, 72, Math.toRadians(180));
    public static Pose toTopSample = new Pose();
    public static Pose pushedTopSample = new Pose();
    public static Pose toMidSample = new Pose();
    public static Pose pushedMidSample = new Pose();
    public static Pose toBottomSample = new Pose();
    public static Pose pushedBottomSample = new Pose();
    public static Pose parkPose = new Pose(10, 20, Math.toRadians(180));

    public static PathChain score1(){
        return new PathBuilder()
                .addPath(
                        new BezierLine(
                                startPose,
                                score1Pose
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
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
                .setConstantHeadingInterpolation(Math.toRadians(180))
//                .setZeroPowerAccelerationMultiplier(7)
                .build();
    }

}

/*
Push paths
public class GeneratedPath {

  public GeneratedPath() {
    PathBuilder builder = new PathBuilder();

    builder
      .addPath(
        // Line 1
        new BezierLine(
          new Point(8.000, 65.000, Point.CARTESIAN),
          new Point(35.000, 65.000, Point.CARTESIAN)
        )
      )
      .setConstantHeadingInterpolation(Math.toRadians(180))
      .addPath(
        // Line 2
        new BezierCurve(
          new Point(35.000, 65.000, Point.CARTESIAN),
          new Point(20.000, 30.000, Point.CARTESIAN),
          new Point(63.000, 50.000, Point.CARTESIAN),
          new Point(63.000, 27.000, Point.CARTESIAN)
        )
      )
      .setConstantHeadingInterpolation(Math.toRadians(180))
      .addPath(
        // Line 3
        new BezierLine(
          new Point(63.000, 27.000, Point.CARTESIAN),
          new Point(15.000, 27.000, Point.CARTESIAN)
        )
      )
      .setConstantHeadingInterpolation(Math.toRadians(180))
      .addPath(
        // Line 4
        new BezierCurve(
          new Point(15.000, 27.000, Point.CARTESIAN),
          new Point(65.000, 28.000, Point.CARTESIAN),
          new Point(63.000, 16.000, Point.CARTESIAN)
        )
      )
      .setConstantHeadingInterpolation(Math.toRadians(180))
      .addPath(
        // Line 5
        new BezierLine(
          new Point(63.000, 16.000, Point.CARTESIAN),
          new Point(15.000, 16.000, Point.CARTESIAN)
        )
      )
      .setConstantHeadingInterpolation(Math.toRadians(180))
      .addPath(
        // Line 6
        new BezierCurve(
          new Point(15.000, 16.000, Point.CARTESIAN),
          new Point(65.000, 16.000, Point.CARTESIAN),
          new Point(63.000, 9.000, Point.CARTESIAN)
        )
      )
      .setConstantHeadingInterpolation(Math.toRadians(180))
      .addPath(
        // Line 7
        new BezierLine(
          new Point(63.000, 9.000, Point.CARTESIAN),
          new Point(15.000, 9.000, Point.CARTESIAN)
        )
      )
      .setTangentHeadingInterpolation();
  }
}

 */
