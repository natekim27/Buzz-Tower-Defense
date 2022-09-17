package com.example.towerdefense;

import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.view.View;

import com.example.towerdefense.enemies.Enemy;

public class EnemyAnimator {
    private ObjectAnimator objectAnimator;
    private Enemy enemy;
    private Game game;
    private View enemyView;

    public EnemyAnimator(View enemyView, Path path, Enemy enemy, Game game) {
        objectAnimator = ObjectAnimator.ofFloat(enemyView, View.X, View.Y, path);
        this.enemy = enemy;
        this.game = game;
        this.enemyView = enemyView;
    }

    public void start(int enemySpeed, int delay) {
        // duration
        objectAnimator.setDuration((long) (600L * (45 - enemySpeed * 1.5)));
        objectAnimator.setStartDelay(delay);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                game.checkEnemyStatus(enemy, enemyView);
            }
        });
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(android.animation.Animator animation) {
                super.onAnimationEnd(animation);
                enemyView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(android.animation.Animator animation) {
                super.onAnimationEnd(animation);
                enemyView.setVisibility(View.GONE);

                if (enemy.getHealth() > 0) {
                    game.damageMonument(enemy.getDamage());
                    game.removeEnemy(enemy, false);
                }
            }
        });
        objectAnimator.start();
    }
}
