package ro.pub.cs.systems.eim.colocviu1_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView labelText;
    private StringBuilder directions;
    private int directionCount; // Numărul de puncte cardinale selectate

    // Cheile pentru Bundle pentru a salva valorile
    private static final String KEY_DIRECTION_COUNT = "direction_count";
    private static final String KEY_DIRECTIONS = "directions";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        directions = new StringBuilder();
        directionCount = 0;

        labelText = findViewById(R.id.label_text);

        // Restaurare starea salvata, dacă există
        if (savedInstanceState != null) {
            directionCount = savedInstanceState.getInt(KEY_DIRECTION_COUNT, 0);
            directions.append(savedInstanceState.getString(KEY_DIRECTIONS, ""));
            updateLabelText();
        }

        // Butoanele pentru punctele cardinale
        Button buttonNorth = findViewById(R.id.button_north);
        Button buttonEast = findViewById(R.id.button_east);
        Button buttonSouth = findViewById(R.id.button_south);
        Button buttonWest = findViewById(R.id.button_west);

        // Setează listener pentru fiecare buton, invocând SecondActivity după fiecare apăsare
        buttonNorth.setOnClickListener(view -> {
            appendDirection("Nord");
            openSecondActivityWithHistory();
        });
        buttonEast.setOnClickListener(view -> {
            appendDirection("Est");
            openSecondActivityWithHistory();
        });
        buttonSouth.setOnClickListener(view -> {
            appendDirection("Sud");
            openSecondActivityWithHistory();
        });
        buttonWest.setOnClickListener(view -> {
            appendDirection("Vest");
            openSecondActivityWithHistory();
        });

        // Butonul pentru a lansa a doua activitate fără resetare
        Button buttonNextActivity = findViewById(R.id.button_next_activity);
        buttonNextActivity.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            // Trimite instrucțiunile curente și istoricul direcțiilor
            intent.putExtra(SecondActivity.EXTRA_INSTRUCTIONS, "Acestea sunt instrucțiunile curente");
            intent.putExtra("direction_history", directions.toString()); // Trimite istoricul direcțiilor
            startActivityForResult(intent, 1);
        });
    }

    private void appendDirection(String direction) {
        if (directions.length() > 0) {
            directions.append(" -> ");
        }
        directions.append(direction);

        // Incrementăm direcțiile selectate și actualizăm TextView
        directionCount++;
        updateLabelText();
    }

    private void updateLabelText() {
        labelText.setText("Direcții apăsate: " + directions.toString() + " (" + directionCount + " apăsări)");
    }

    private void openSecondActivityWithHistory() {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra(SecondActivity.EXTRA_INSTRUCTIONS, "Acestea sunt instrucțiunile curente");
        intent.putExtra("direction_history", directions.toString()); // Trimite istoricul direcțiilor
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Salvăm valorile `directionCount` și `directions` în Bundle
        outState.putInt(KEY_DIRECTION_COUNT, directionCount);
        outState.putString(KEY_DIRECTIONS, directions.toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restaurăm valorile salvate în `directionCount` și `directions`
        directionCount = savedInstanceState.getInt(KEY_DIRECTION_COUNT, 0);
        directions = new StringBuilder(savedInstanceState.getString(KEY_DIRECTIONS, ""));
        updateLabelText();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            // Obține ce buton a fost apăsat din SecondActivity
            String buttonClicked = data.getStringExtra("button_clicked");
            if (buttonClicked != null) {
                Toast.makeText(this, "Butonul apăsat: " + buttonClicked, Toast.LENGTH_SHORT).show();
            }

            // Obține instrucțiunile noi și afișează-le
            String newInstructions = data.getStringExtra("new_instructions");
            if (newInstructions != null && !newInstructions.isEmpty()) {
                Toast.makeText(this, "Instrucțiuni noi: " + newInstructions, Toast.LENGTH_LONG).show();
                // Sau poți afișa instrucțiunile într-un TextView, dacă vrei
                labelText.setText("Instrucțiuni noi: " + newInstructions);
            }
        }
    }
}
