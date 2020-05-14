package com.myapplicationdev.android.p04_revisionnotes;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    EditText etNote;
    Button btnInsert, btnShowList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.buttonInsertNote);
        btnShowList = findViewById(R.id.buttonShowList);
        etNote = findViewById(R.id.editTextNote);

        radioGroup = findViewById(R.id.radioGroupStars);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper db = new DBHelper(MainActivity.this);
                String toastMessage = "";

                if(etNote.getText().toString().length() < 1 || radioGroup.getCheckedRadioButtonId() == -1){
                    toastMessage = "Invalid input";
                }
                else{
                    toastMessage = "Inserted";

                    String content = etNote.getText().toString();
                    RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                    int stars = Integer.parseInt(radioButton.getText().toString());

                    db.insertNote(content, stars);
                    db.close();
                }

                Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_LONG).show();
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);

            }
        });
    }
}
