package ro.pub.cs.systems.eim.colocviu1_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
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

        // Setează listener pentru fiecare buton
        buttonNorth.setOnClickListener(view -> appendDirection("Nord"));
        buttonEast.setOnClickListener(view -> appendDirection("Est"));
        buttonSouth.setOnClickListener(view -> appendDirection("Sud"));
        buttonWest.setOnClickListener(view -> appendDirection("Vest"));

        // Butonul pentru a lansa o nouă activitate
        Button buttonNextActivity = findViewById(R.id.button_next_activity);
        buttonNextActivity.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
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
}
