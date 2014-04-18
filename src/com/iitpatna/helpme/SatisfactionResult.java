package com.iitpatna.helpme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class SatisfactionResult extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.satisfaction_result);
		String score = getIntent().getExtras().getString("score");
		score = score.trim();
		int Score = Integer.parseInt(score);
		System.out.println(score);
		TextView scored;
		scored = (TextView) findViewById(R.id.tVScore);
		float Scored = ((float) Score / 7);

		scored.setText(score);
		RatingBar bar;
		bar = (RatingBar) findViewById(R.id.ratingBarSatisfaction);
		bar.setRating((Float) Scored);
		Button home = (Button) findViewById(R.id.bHomeS);
		home.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.bHomeS) {
			startActivity(new Intent(this, FullscreenActivity.class));
			finish();
		}
	}

}
