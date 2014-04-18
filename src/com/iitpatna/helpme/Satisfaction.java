package com.iitpatna.helpme;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class Satisfaction extends FragmentActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments representing each object in a collection. We use a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter} derivative,
	 * which will destroy and re-create fragments as needed, saving and
	 * restoring their state in the process. This is important to conserve
	 * memory and is a best practice when allowing navigation between objects in
	 * a potentially large collection.
	 */
	DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;

	static int selected[] = new int[5];

	/**
	 * The {@link android.support.v4.view.ViewPager} that will display the
	 * object collection.
	 */
	ViewPager mViewPager;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.satisfaction);

		for (int i = 0; i < 5; i++) {
			selected[i] = 1;
		}
		// Create an adapter that when requested, will return a fragment
		// representing an object in
		// the collection.
		//
		// ViewPager and its adapters use support library fragments, so we must
		// use
		// getSupportFragmentManager.
		mDemoCollectionPagerAdapter = new DemoCollectionPagerAdapter(
				getSupportFragmentManager());

		// Set up action bar.
		final ActionBar actionBar = getActionBar();

		// Specify that the Home button should show an "Up" caret, indicating
		// that touching the
		// button will take the user one step up in the application's hierarchy.
		actionBar.setDisplayHomeAsUpEnabled(true);

		// Set up the ViewPager, attaching the adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mDemoCollectionPagerAdapter);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This is called when the Home (Up) button is pressed in the action
			// bar.
			// Create a simple intent that starts the hierarchical parent
			// activity and
			// use NavUtils in the Support Package to ensure proper handling of
			// Up.
			Intent upIntent = new Intent(this, FullscreenActivity.class);
			if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
				// This activity is not part of the application's task, so
				// create a new task
				// with a synthesized back stack.
				TaskStackBuilder.from(this)
				// If there are ancestor activities, they should be added here.
						.addNextIntent(upIntent).startActivities();
				finish();
			} else {
				// This activity is part of the application's task, so simply
				// navigate up to the hierarchical parent activity.
				NavUtils.navigateUpTo(this, upIntent);
			}
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A {@link android.support.v4.app.FragmentStatePagerAdapter} that returns a
	 * fragment representing an object in the collection.
	 */
	public static class DemoCollectionPagerAdapter extends
			FragmentStatePagerAdapter {

		public DemoCollectionPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int i) {
			Fragment fragment = new DemoObjectFragment();
			Bundle args = new Bundle();
			args.putInt(DemoObjectFragment.ARG_OBJECT, i + 1); // Our object is
																// just an
																// integer :-P
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// For this contrived example, we have a 100-object collection.
			return 5;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return "Question " + (position + 1);
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 * 
	 * 
	 */

	public static class DemoObjectFragment extends Fragment {

		public static final String ARG_OBJECT = "question";
		Bundle args;

		RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				selected[args.getInt(ARG_OBJECT) - 1] = checkedId - R.id.radio0
						+ 1;
			}
		};

		View.OnClickListener l = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int amountOfsatisfaction = 0;
				for (int i = 0; i < 5; i++)
					amountOfsatisfaction += selected[i];
				// Intent intent = new
				// Intent("com.iitpatna.helpme.SATISFACTIONRESULT");
				Intent intent = new Intent(v.getContext(),
						SatisfactionResult.class);
				intent.putExtra("score", amountOfsatisfaction + " ");
				startActivity(intent);
			}
		};

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.question_pages,
					container, false);
			Button submit;
			RadioGroup rd;
			rd = (RadioGroup) rootView.findViewById(R.id.rgSatisfaction);
			rd.setOnCheckedChangeListener(listener);
			args = getArguments();

			switch (args.getInt(ARG_OBJECT)) {
			case 1:
				((TextView) rootView.findViewById(R.id.textView1))
						.setText("In most ways, my life is close to ideal");
				break;
			case 2:
				((TextView) rootView.findViewById(R.id.textView1))
						.setText("The conditions of my life are excellent");
				break;
			case 3:
				((TextView) rootView.findViewById(R.id.textView1))
						.setText("I am satisfied with my life");
				break;
			case 4:
				((TextView) rootView.findViewById(R.id.textView1))
						.setText("So far I have gotten the important things I want in life");
				MarginLayoutParams params = (MarginLayoutParams) rd
						.getLayoutParams();
				params.setMargins(0, 150, 0, 0);
				rd.setLayoutParams(params);

				break;
			case 5:
				((TextView) rootView.findViewById(R.id.textView1))
						.setText("If I could live my life over, I would change almost nothing");
				submit = (Button) rootView.findViewById(R.id.bSatisfysub);
				MarginLayoutParams param2s = (MarginLayoutParams) rd
						.getLayoutParams();
				param2s.topMargin = 150;
				rd.setLayoutParams(param2s);
				submit.setVisibility(View.VISIBLE);
				submit.setOnClickListener(l);
				break;

			}
			return rootView;
		}
	}
}