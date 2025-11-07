package com.example.memorypowertest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.memorypowertest.data.*;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        LinearLayout historyContainer = findViewById(R.id.historyContainer);
        Button backBtn = findViewById(R.id.backBtn);

        List<ResultEntity> results = AppDatabase.getInstance(this).resultDao().getAllResults();

        if (results.isEmpty()) {
            TextView empty = new TextView(this);
            empty.setText("No history yet. Play the test to build memory!");
            historyContainer.addView(empty);
        } else {
            for (ResultEntity r : results) {
                TextView item = new TextView(this);
                item.setText("🗓 " + r.date + "\n" + r.level + " | Score: " + r.score + "\n");
                item.setPadding(10, 20, 10, 20);
                historyContainer.addView(item);
            }
        }

        backBtn.setOnClickListener(v -> finish());
    }
}
