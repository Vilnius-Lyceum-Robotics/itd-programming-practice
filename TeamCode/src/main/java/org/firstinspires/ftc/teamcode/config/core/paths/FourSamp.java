package org.firstinspires.ftc.teamcode.config.core.paths;

import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.Path;
import com.pedropathing.pathgen.PathBuilder;
import com.pedropathing.pathgen.PathChain;

import org.opencv.core.Mat;

public class FourSamp {

    public static Pose start = new Pose(9, 88, Math.toRadians(0));
    public static Pose score = new Pose(18, 125, Math.toRadians(-45));
    public static Pose grabBottomSample = new Pose(30, 121, Math.toRadians(0));
    public static Pose grabMiddleSample = new Pose(30, 132, Math.toRadians(0));
    public static Pose grabTopSample = new Pose(45.5, 126, Math.toRadians(90));
    public static Pose park = new Pose(62, 97, Math.toRadians(90));

    public static PathChain scorePreload() {
        return new PathBuilder()
                .addPath(
                        new BezierLine(start, score)
                )
                .setLinearHeadingInterpolation(start.getHeading(), score.getHeading())
                .build();
    }

    public static PathChain grabBottomSample() {
        return new PathBuilder()
                .addPath(new BezierLine(score, grabBottomSample))
                .setLinearHeadingInterpolation(score.getHeading(), grabBottomSample.getHeading())
                .build();
    }

    public static PathChain scoreBottomSample() {
        return new PathBuilder()
                .addPath(new BezierLine(grabBottomSample, score))
                .setLinearHeadingInterpolation(grabBottomSample.getHeading(), score.getHeading())
                .build();
    }

    public static PathChain grabMiddleSample() {
        return new PathBuilder()
                .addPath(new BezierLine(score, grabMiddleSample))
                .setLinearHeadingInterpolation(score.getHeading(), grabMiddleSample.getHeading())
                .build();
    }

    public static PathChain scoreMiddleSample() {
        return new PathBuilder()
                .addPath(new BezierLine(grabMiddleSample, score))
                .setLinearHeadingInterpolation(grabMiddleSample.getHeading(), score.getHeading())
                .build();
    }

    public static PathChain grabTopSample() {
        return new PathBuilder()
                .addPath(new BezierLine(score, grabTopSample))
                .setLinearHeadingInterpolation(score.getHeading(), grabTopSample.getHeading())
                .build();
    }

    public static PathChain scoreTopSample() {
        return new PathBuilder()
                .addPath(new BezierLine(grabTopSample, score))
                .setLinearHeadingInterpolation(grabTopSample.getHeading(), score.getHeading())
                .build();
    }

    public static PathChain park() {
        return new PathBuilder()
                .addPath(
                        new BezierCurve(
                                score,
                                new Pose(62, 121),
                                park
                        )
                )
                .setLinearHeadingInterpolation(score.getHeading(), park.getHeading())
                .build();

    }
}
