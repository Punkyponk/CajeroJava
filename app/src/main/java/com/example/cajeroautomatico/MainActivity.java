package com.example.cajeroautomatico;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText amountEditText;
    private Button withdrawButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountEditText = findViewById(R.id.amountEditText);
        withdrawButton = findViewById(R.id.withdrawButton);
        resultTextView = findViewById(R.id.resultTextView);

        withdrawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amountText = amountEditText.getText().toString();
                if (!amountText.isEmpty()) {
                    int amountToWithdraw = Integer.parseInt(amountText);
                    calculateWithdrawal(amountToWithdraw);
                }
            }
        });
    }

    private void calculateWithdrawal(int amountToWithdraw) {
        int[] denominations = {500, 200, 100, 50, 20, 10, 5, 1};
        int[] counts = new int[denominations.length];

        for (int i = 0; i < denominations.length; i++) {
            int denomination = denominations[i];
            while (amountToWithdraw >= denomination) {
                counts[i]++;
                amountToWithdraw -= denomination;
            }
        }

        StringBuilder result = new StringBuilder("Billetes/Monedas:\n");
        for (int i = 0; i < denominations.length; i++) {
            if (counts[i] > 0) {
                result.append(denominations[i]).append(": ").append(counts[i]).append("\n");
            }
        }

        resultTextView.setText(result.toString());
    }
}
