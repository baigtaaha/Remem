package com.example.memorypowertest;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private TextView questionText, commentText;
    private Button option1, option2, option3;
    private int currentQuestion = 0, score = 0;
    private List<String[]> questions = new ArrayList<>();
    private final Random random = new Random();

    // 🃏 Sarcastic funny comments
    private String getFunnyComment(boolean correct) {
        if (correct) {
            String[] funny = {
                    "🎯 Lucky guess or genius? Either way, nice job!",
                    "🧠 Someone’s neurons are firing today!",
                    "🔥 You remembered that? Show-off!",
                    "👏 Brain power level: OVER 9000!",
                    "😎 Genius alert!"
            };
            return funny[(int) (Math.random() * funny.length)];
        } else {
            String[] funny = {
                    "🤦‍♀️ Oops! Memory not found.",
                    "😂 You forgot faster than my phone battery drains.",
                    "🧃 Maybe have some juice and try again?",
                    "🥴 Your brain just said ‘Nope!’",
                    "💤 Did you even try remembering?"
            };
            return funny[(int) (Math.random() * funny.length)];
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        questionText = findViewById(R.id.questionText);
        commentText = findViewById(R.id.commentText); // Make sure you added this in XML
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);

        // 🧠 Add memory-based questions
        questions.add(new String[]{"ACTIVITY, FRAGMENT, INTENT", "Which one is NOT an Android layout component?", "Fragment", "Activity", "LinearLayout", "LinearLayout"});

        questions.add(new String[]{"KOTLIN, JAVA, PYTHON", "Which language is commonly used for Android development?", "Python", "Kotlin", "C++", "Kotlin"});

        questions.add(new String[]{"XML, JAVA, HTML", "Which file type is used for UI design in Android?", "XML", "HTML", "TXT", "XML"});

        questions.add(new String[]{"ANDROID STUDIO, XCODE, VS CODE", "Which IDE is mainly used for Android development?", "Android Studio", "Xcode", "IntelliJ IDEA", "Android Studio"});

        questions.add(new String[]{"BUTTON, TEXTVIEW, INTENT", "Which one is used for navigation between activities?", "TextView", "Button", "Intent", "Intent"});

        questions.add(new String[]{"ONCREATE, ONPAUSE, ONSTOP", "Which lifecycle method is called first when Activity starts?", "onCreate()", "onPause()", "onStop()", "onCreate()"});

        questions.add(new String[]{"MANIFEST, RES, BUILD", "Which file declares app permissions?", "AndroidManifest.xml", "strings.xml", "activity_main.xml", "AndroidManifest.xml"});

        questions.add(new String[]{"TOAST, SNACKBAR, TOY", "Which is NOT used for showing messages?", "Toast", "Snackbar", "Toy", "Toy"});

        questions.add(new String[]{"IMAGEVIEW, TEXTVIEW, VIDEO", "Which one is NOT a valid Android View?", "TextView", "Video", "ImageView", "Video"});

        questions.add(new String[]{"INTENT, BUNDLE, PACKAGE", "Which object is used to pass data between activities?", "Bundle", "Intent", "String", "Intent"});


        showMemoryScreen();
    }

    private void showMemoryScreen() {
        if (currentQuestion < questions.size()) {
            String[] q = questions.get(currentQuestion);
            questionText.setText("🧠 Memorize: " + q[0]);
            commentText.setText("");

            // Disable buttons
            option1.setEnabled(false);
            option2.setEnabled(false);
            option3.setEnabled(false);

            option1.setText("");
            option2.setText("");
            option3.setText("");

            // Show recall screen after 3 seconds
            new Handler().postDelayed(() -> showRecallScreen(q), 3000);
        } else {
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
            finish();
        }
    }

    private void showRecallScreen(String[] q) {
        questionText.setText(q[1]);
        commentText.setText("");
        option1.setText(q[2]);
        option2.setText(q[3]);
        option3.setText(q[4]);

        option1.setEnabled(true);
        option2.setEnabled(true);
        option3.setEnabled(true);

        option1.setOnClickListener(v -> checkAnswer(q[2], q[5]));
        option2.setOnClickListener(v -> checkAnswer(q[3], q[5]));
        option3.setOnClickListener(v -> checkAnswer(q[4], q[5]));
    }

    private void checkAnswer(String chosen, String correct) {
        boolean correctAns = chosen.equals(correct);

        // Step 1: show correct/wrong feedback
        if (correctAns) {
            questionText.setText("✅ Correct!");
            score += 10;
        } else {
            questionText.setText("❌ Wrong! Correct answer: " + correct);
        }

        // Disable buttons while showing feedback
        option1.setEnabled(false);
        option2.setEnabled(false);
        option3.setEnabled(false);

        // Step 2: after 1 second, show sarcastic comment with animation
        new Handler().postDelayed(() -> {
            String funnyComment = getFunnyComment(correctAns);
            commentText.setText(funnyComment);

            // 💫 Pop-up animation
            commentText.setScaleX(0f);
            commentText.setScaleY(0f);
            commentText.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(400)
                    .setInterpolator(new android.view.animation.OvershootInterpolator())
                    .start();

        }, 1000);

        // Step 3: move to next question after a pause
        new Handler().postDelayed(() -> {
            currentQuestion++;
            showMemoryScreen();
        }, 2800);
    }

}
