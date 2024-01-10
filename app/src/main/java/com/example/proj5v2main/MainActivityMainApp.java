package com.example.proj5v2main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivityMainApp extends AppCompatActivity {

    //receiving
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result ->
            {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) handleResult(data);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //sending
    public void run(View view) {
        TextView tv1 = findViewById(R.id.arg1);
        TextView tv2 = findViewById(R.id.arg2);
        emptyCheck(tv1);
        emptyCheck(tv2);

        Intent intent = new Intent();
        intent.putExtra("key1", tv1.getText().toString());
        intent.putExtra("key2", tv2.getText().toString());
        intent.setAction("com.finity_proj5");
        intent.setType("text/plain");
        launcher.launch(intent);

    }

    private void emptyCheck(TextView tv) {
        if (tv.getText().length() == 0) tv.setText("0");
    }

    private void handleResult(Intent data) {
        TextView operationText = findViewById(R.id.operationId);
        TextView resultText = findViewById(R.id.resultId);

        String operation = data.getStringExtra("operation");
        Integer result = data.getIntExtra("result", 0);

        if (operation != null) {
            showToast(operation);
            operationText.setText(operation);

            if (result != null) {
                resultText.setText(result.toString());
            } else {
                showToast("Result is null");
                // Handle the case when result is null
            }
        } else {
            showToast("Operation is null");
            // Handle the case when operation is null
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}