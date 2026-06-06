package com.example.jnidemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Déclarations des méthodes natives
    public native String helloFromJNI();
    public native int factorial(int n);
    public native String reverseString(String s);
    public native int sumArray(int[] values);

    // Chargement de la bibliothèque native
    static {
        System.loadLibrary("native-lib");
    }

    private TextView tvResult;
    private EditText etFactorial, etReverse, etArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);
        etFactorial = findViewById(R.id.etFactorial);
        etReverse = findViewById(R.id.etReverse);
        etArray = findViewById(R.id.etArray);

        Button btnHello = findViewById(R.id.btnHello);
        Button btnFact = findViewById(R.id.btnFact);
        Button btnReverse = findViewById(R.id.btnReverse);
        Button btnArray = findViewById(R.id.btnArray);

        btnHello.setOnClickListener(v -> {
            String result = helloFromJNI();
            appendResult("Hello JNI", result);
        });

        btnFact.setOnClickListener(v -> {
            String input = etFactorial.getText().toString();
            if (input.isEmpty()) {
                Toast.makeText(this, "Entrez un nombre", Toast.LENGTH_SHORT).show();
                return;
            }
            int n = Integer.parseInt(input);
            int fact = factorial(n);
            if (fact >= 0) {
                appendResult("Factoriel (" + n + ")", String.valueOf(fact));
            } else if (fact == -1) {
                appendResult("Factoriel (" + n + ")", "Erreur : n négatif");
            } else if (fact == -2) {
                appendResult("Factoriel (" + n + ")", "Overflow (valeur trop grande)");
            } else {
                appendResult("Factoriel (" + n + ")", "Erreur inconnue");
            }
        });

        btnReverse.setOnClickListener(v -> {
            String input = etReverse.getText().toString();
            String reversed = reverseString(input);
            appendResult("Inversion", "\"" + input + "\" → \"" + reversed + "\"");
        });

        btnArray.setOnClickListener(v -> {
            String input = etArray.getText().toString();
            if (input.isEmpty()) {
                Toast.makeText(this, "Entrez des nombres séparés par des virgules", Toast.LENGTH_SHORT).show();
                return;
            }
            String[] parts = input.split(",");
            int[] numbers = new int[parts.length];
            try {
                for (int i = 0; i < parts.length; i++) {
                    numbers[i] = Integer.parseInt(parts[i].trim());
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Format numérique invalide", Toast.LENGTH_SHORT).show();
                return;
            }
            int sum = sumArray(numbers);
            appendResult("Somme tableau", sum + " (taille = " + numbers.length + ")");
        });
    }

    private void appendResult(String title, String message) {
        String current = tvResult.getText().toString();
        String newEntry = "\n\n--- " + title + " ---\n" + message;
        tvResult.setText(current + newEntry);
        // Scroll automatique vers le bas (optionnel)
        // tvResult.post(() -> tvResult.scrollTo(0, tvResult.getBottom()));
    }
}