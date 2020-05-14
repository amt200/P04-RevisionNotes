package com.myapplicationdev.android.p04_revisionnotes;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

	ListView lv;
	RevisionNotesArrayAdapter revisionNotesArrayAdapter;
	ArrayList<Note> notes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		//TODO implement the Custom ListView
		DBHelper db = new DBHelper(SecondActivity.this);
		notes = db.getAllNotes();
		revisionNotesArrayAdapter = new RevisionNotesArrayAdapter(SecondActivity.this, R.layout.row, notes);

		lv = findViewById(R.id.lv);

		lv.setAdapter(revisionNotesArrayAdapter);
		revisionNotesArrayAdapter.notifyDataSetChanged();
	}
}
