package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class RevisionNotesArrayAdapter extends ArrayAdapter<Note> {
	Context context;
	ArrayList<Note> notes;
	int resource;
	ImageView[] imageViews;

	public RevisionNotesArrayAdapter(Context context, int resource, ArrayList<Note> notes) {
		super(context, resource, notes);
		this.context = context;
		this.notes = notes;
		this.resource = resource;
	}

	@Override
	public void add(@Nullable Note object) {
		notes.add(object);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(resource, parent, false);

		//Match the UI components with Java variables

		Note note = notes.get(position);

		String description = note.getNoteContent();

		int stars = note.getStars();

		imageViews = new ImageView[5];

		imageViews[0] = rowView.findViewById(R.id.imageView1star);
		imageViews[1] = rowView.findViewById(R.id.imageView2star);
		imageViews[2] = rowView.findViewById(R.id.imageView3star);
		imageViews[3] = rowView.findViewById(R.id.imageView4star);
		imageViews[4] = rowView.findViewById(R.id.imageView5star);

		TextView textView = rowView.findViewById(R.id.textViewNote);

		//Check if the property for starts >= 5, if so, "light" up the stars
		for(int i = 0; i < stars; i++){
			imageViews[i].setImageResource(android.R.drawable.btn_star_big_on);
		}

		textView.setText(description);

		return rowView;
	}



}
