package com.example.memorypowertest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private View logoCard;
    private ImageView appLogo;
    private View startQuizCard;
    private View viewHistoryCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        logoCard = findViewById(R.id.logoCard);
        appLogo = findViewById(R.id.appLogo);
        startQuizCard = findViewById(R.id.startQuizCard);
        viewHistoryCard = findViewById(R.id.viewHistoryCard);

        // Initialize animations
        initAnimations();

        // Setup click listeners
        setupClickListeners();
    }

    private void initAnimations() {
        // Animate logo on start
        AnimationHelper.bounceAnimation(logoCard);

        // Rotate logo continuously
        AnimationHelper.rotateAnimation(appLogo);

        // Animate buttons with staggered delay
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                AnimationHelper.slideInFromRight(startQuizCard);
            }
        }, 300);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                AnimationHelper.slideInFromRight(viewHistoryCard);
            }
        }, 500);
    }

    private void setupClickListeners() {
        // Start Quiz Button
        startQuizCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationHelper.pulseAnimation(v);
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, GameActivity.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }
                }, 300);
            }
        });

        // View History Button
        viewHistoryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationHelper.pulseAnimation(v);
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    }
                }, 300);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Restart logo rotation if needed
        if (appLogo != null) {
            AnimationHelper.rotateAnimation(appLogo);
        }
    }
}
