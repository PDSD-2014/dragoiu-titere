package com.example.findmeamovie;

import com.google.gson.Gson;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class MovieDetailsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_details);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.movie_details, menu);
		ShowData();
		return true;
	}

	public void ShowData()
	{
		try
		{
			String serializedData = getIntent().getStringExtra("data");
			Gson deserializer = new Gson();
			Movie receivedMovie = deserializer.fromJson(serializedData, Movie.class);
			
			String movieTitle = receivedMovie.GetJsonObject().getAsJsonPrimitive("title").getAsString();
	    	//String movieyear ="" + receivedMovie.GetJsonObject().getAsJsonPrimitive("year").getAsInt();
			setTitle(movieTitle);
			
			TextView SynopsisContainer = (TextView)findViewById(R.id.SynopsisTextView);
			String synopsis = receivedMovie.GetJsonObject().getAsJsonPrimitive("synopsis").getAsString();
			if(synopsis.length() > 0)
			{
				SynopsisContainer.setText(synopsis);
			}
			else
			{
				SynopsisContainer.setText(receivedMovie.GetJsonObject().getAsJsonPrimitive("synopsis").getAsString());
			}
			
			TextView CriticsContainer = (TextView)findViewById(R.id.CriticsTextView);
			CriticsContainer.setText(receivedMovie.GetJsonObject().getAsJsonPrimitive("critics_consensus").getAsString());
		}
		catch(Exception e)
		{
			/*Lots of possible parsing erros that we can't control. Excetion catch is the fast option*/
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_movie_details,
					container, false);
			return rootView;
		}
	}

}
