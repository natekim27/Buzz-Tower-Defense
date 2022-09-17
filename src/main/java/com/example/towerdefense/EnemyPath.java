package com.example.towerdefense;

import android.graphics.Path;

import java.util.Random;

public class EnemyPath {

    public static Path makePath() {
        Random rand = new Random();

        switch (rand.nextInt(4) + 1) {
        case 1: return buildPathB();
        case 2: return buildPathC();
        default: return buildPathA();
        }
    }


    public static Path buildPathA() {
        // 1, 3, 7
        Path path = buildSection1();
        buildSection3(path);
        buildSection7(path);
        return path;
    }

    public static Path buildPathB() {
        // 1, 2, 4, 6, 7
        Path path = buildSection1();
        buildSection2(path);
        buildSection4(path);
        buildSection6(path);
        buildSection7(path);
        return path;
    }

    public static Path buildPathC() {
        // 1, 2, 5, 6, 7
        Path path = buildSection1();
        buildSection2(path);
        buildSection5(path);
        buildSection6(path);
        buildSection7(path);
        return path;
    }

    private static Path buildSection1() {
        Path path = new Path();
        path.moveTo(1710, -150);
        path.rLineTo(0, 305);
        return path;
    }

    private static Path buildSection2(Path path) {
        path.rLineTo(0, 310);
        return path;
    }

    private static Path buildSection3(Path path) {
        path.rLineTo(-250, 0);
        path.rLineTo(0, 100);
        path.rLineTo(-255, 0);
        path.rLineTo(0, -200);
        path.rLineTo(-470, 0);
        path.rLineTo(0, 100);
        path.rLineTo(-250, 0);
        path.rLineTo(0, 210);
        path.rLineTo(-230, 0);
        return path;
    }

    private static Path buildSection4(Path path) {
        path.rLineTo(-610, 0);
        path.rLineTo(0, 100);
        return path;
    }

    private static Path buildSection5(Path path) {
        path.rLineTo(-120, 0);
        path.rLineTo(0, 200);
        path.rLineTo(-120, 0);
        path.rLineTo(0, 100);
        path.rLineTo(-250, 0);
        path.rLineTo(0, -100);
        path.rLineTo(-120, 0);
        path.rLineTo(0, -100);
        return path;
    }

    private static Path buildSection6(Path path) {
        path.rLineTo(-370, 0);
        path.rLineTo(0, 100);
        path.rLineTo(-480, 0);
        path.rLineTo(0, -300);
        return path;
    }

    private static Path buildSection7(Path path) {
        path.rLineTo(-180, 0);
        return path;
    }






}

