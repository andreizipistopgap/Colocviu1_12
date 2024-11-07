package ro.pub.cs.systems.eim.colocviu1_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    public static final String EXTRA_INSTRUCTIONS = "instructions";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Obține instrucțiunile și istoricul direcțiilor transmise de la MainActivity
        Intent intent = getIntent();
        String instructions = intent.getStringExtra(EXTRA_INSTRUCTIONS);
        String directionHistory = intent.getStringExtra("direction_history");

        // Afișează instrucțiunile și istoricul direcțiilor
        TextView instructionsText = findViewById(R.id.instructions_text);
        TextView historyText = findViewById(R.id.history_text);
        EditText editInstructions = findViewById(R.id.edit_instructions);

        if (instructions != null) {
            instructionsText.setText(instructions);
        }

        if (directionHistory != null && !directionHistory.isEmpty()) {
            historyText.setText("Istoric direcții: " + directionHistory);
        } else {
            historyText.setText("Nu există un istoric al direcțiilor.");
        }

        // Butonul Register
        Button registerButton = findViewById(R.id.button_register);
        registerButton.setOnClickListener(view -> {
            // Obține textul din EditText și trimite-l înapoi la MainActivity
            String newInstructions = editInstructions.getText().toString();
            Intent resultIntent = new Intent();
            resultIntent.putExtra("button_clicked", "Register");
            resultIntent.putExtra("new_instructions", newInstructions); // Trimite noile instrucțiuni
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        // Butonul Cancel
        Button cancelButton = findViewById(R.id.button_cancel);
        cancelButton.setOnClickListener(view -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("button_clicked", "Cancel");
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
