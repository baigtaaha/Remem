package com.example.memorypowertest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import com.example.memorypowertest.data.*;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView resultText = findViewById(R.id.resultText);
        Button retryBtn = findViewById(R.id.retryBtn);

        int score = getIntent().getIntExtra("score", 0);
        String level;

        if (score >= 40)
            level = "Excellent Memory 🧠✨";
        else if (score >= 20)
            level = "Good Memory 👍";
        else
            level = "Needs Practice 😅";

        resultText.setText(level + "\nYour Score: " + score);

        // ✅ Save to Room DB
        String date = new SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()).format(new Date());
        AppDatabase db = AppDatabase.getInstance(this);
        db.resultDao().insertResult(new ResultEntity(date, score, level));

        retryBtn.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));
    }
}
