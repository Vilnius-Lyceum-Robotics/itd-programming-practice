package org.firstinspires.ftc.teamcode.config.core.paths;

import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathBuilder;
import com.pedropathing.pathgen.PathChain;

public class FourSampPush {

    public static Pose start = new Pose(9, 88, Math.toRadians(0));
    public static Pose score = new Pose(18, 125, Math.toRadians(-45));
    public static Pose toBottomSample = new Pose(62, 121, Math.toRadians(0));
    public static Pose pushedBottomSample = new Pose(9, 124, Math.toRadians(0));
    public static Pose toMiddleSample = new Pose(62, 132, Math.toRadians(0));
    public static Pose pushedMiddleSample = new Pose(18, 132, Math.toRadians(0));
    public static Pose toTopSample = new Pose(62, 137, Math.toRadians(0));
    public static Pose pushedTopSample = new Pose(29, 137, Math.toRadians(0));
    public static Pose park = new Pose(63, 97, Math.toRadians(90));

    public static PathChain scorePreload() {
        return new PathBuilder()
                .addPath(
                        new BezierLine(
                                start, score
                        )
                )
                .setLinearHeadingInterpolation(start.getHeading(), score.getHeading())
                .build();
    }

    public static PathChain pushSamples() {
        return new PathBuilder()
                //prepare 2
                .addPath(
                        new BezierCurve(
                                score,
                                new Pose(54, 95),
                                toBottomSample
                        )
                )
                .setLinearHeadingInterpolation(score.getHeading(), toBottomSample.getHeading())

                //push 2
                .addPath(new BezierLine(toBottomSample, pushedBottomSample))

                //get back to prepare 2
                .addPath(new BezierLine(pushedBottomSample, toBottomSample))

                //prepare 3
                .addPath(new BezierLine(toBottomSample, toMiddleSample))

                //push 3
                .addPath(new BezierLine(toMiddleSample, pushedMiddleSample))

                //get back to prepare 3
                .addPath(new BezierLine(pushedMiddleSample, toMiddleSample))

                //prepare 4
                .addPath(new BezierLine(toMiddleSample, toTopSample))

                //push 4
                .addPath(new BezierLine(toTopSample, pushedTopSample))
                .build();
    }

    public static PathChain park() {
        return new PathBuilder()
                .addPath(
                        new BezierLine(
                                pushedTopSample, park
                        )
                )
                .setLinearHeadingInterpolation(pushedTopSample.getHeading(), park.getHeading())
                .build();

    }

}
