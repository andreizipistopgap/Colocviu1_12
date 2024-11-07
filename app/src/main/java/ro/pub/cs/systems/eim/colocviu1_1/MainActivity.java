package ro.pub.cs.systems.eim.colocviu1_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView labelText;
    private StringBuilder directions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        directions = new StringBuilder();

        labelText = findViewById(R.id.label_text);

        // Butoanele punctelor cardinale
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
            directions.append(" -> "); // Delimitatorul dintre direcții
        }
        directions.append(direction);
        labelText.setText("Direcții apăsate: " + directions.toString());
    }
}
