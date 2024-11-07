package ro.pub.cs.systems.eim.colocviu1_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    public static final String EXTRA_INSTRUCTIONS = "instructions";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Obține instrucțiunile transmise de la MainActivity
        Intent intent = getIntent();
        String instructions = intent.getStringExtra(EXTRA_INSTRUCTIONS);

        // Afișează instrucțiunile în TextView
        TextView instructionsText = findViewById(R.id.instructions_text);
        if (instructions != null) {
            instructionsText.setText(instructions);
        }

        // Butonul Register
        Button registerButton = findViewById(R.id.button_register);
        registerButton.setOnClickListener(view -> {
            // Setează rezultatul și finalizează activitatea
            Intent resultIntent = new Intent();
            resultIntent.putExtra("button_clicked", "Register");
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        // Butonul Cancel
        Button cancelButton = findViewById(R.id.button_cancel);
        cancelButton.setOnClickListener(view -> {
            // Setează rezultatul și finalizează activitatea
            Intent resultIntent = new Intent();
            resultIntent.putExtra("button_clicked", "Cancel");
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
