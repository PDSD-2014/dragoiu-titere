package com.example.findmeamovie;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.os.Build;

public class HomeScreen extends Activity implements AsyncListener 
{

	private ArrayList<Movie> m_MovieList;
	private MovieListAdapter m_MovieAdapter;
	MovieServiceClient m_serviceClient;
	Activity m_homeActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        m_homeActivity = this;
        setContentView(R.layout.activity_home_screen);
        m_MovieList = new ArrayList<Movie>();
        m_serviceClient = new MovieServiceClient();
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        onSearchRequested();
     
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
     // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) 
        {
          String query = intent.getStringExtra(SearchManager.QUERY);
          Log.i("Search quey:", query);
          m_serviceClient.GetSearchByTermResponse(query,this);
        }
  		m_MovieAdapter = new MovieListAdapter(this,R.layout.movie_list,m_MovieList);
  		final ListView listView1 = (ListView)findViewById(R.id.MovieList);
		listView1.setClickable(true);
		listView1.setAdapter(m_MovieAdapter);
		listView1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Movie entry= (Movie) parent.getAdapter().getItem(position);
				Intent intent = new Intent(m_homeActivity, MovieDetailsActivity.class);
				Gson serializer = new Gson();
			    intent.putExtra("data", serializer.toJson(entry));
			    startActivity(intent);
			}
			
		});
				
          //doMySearch(query);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id == R.id.search)
        {
        	onSearchRequested();
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
            View rootView = inflater.inflate(R.layout.fragment_home_screen, container, false);
            return rootView;
        }
    }

	@Override
	public void parseResponse(String jsonData) {
		m_MovieList.clear();
		
		JsonElement jelement = new JsonParser().parse(jsonData);
	    JsonObject  jobject = jelement.getAsJsonObject();
	    JsonArray jarray = jobject.getAsJsonArray("movies");
	    
	    for(JsonElement codeHolder : jarray)
	    {
	    	try
	    	{
		    	JsonObject currentMovie = codeHolder.getAsJsonObject();
		    	String movieTitle = currentMovie.getAsJsonPrimitive("title").getAsString();
		    	String movieyear ="" + currentMovie.getAsJsonPrimitive("year").getAsInt();
		    	Movie newMovie = new Movie(movieTitle, movieyear, 1);
		    	newMovie.SetJsonObject(currentMovie);
		    	m_MovieList.add(newMovie);
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    }
	    m_MovieAdapter.notifyDataSetChanged();
	    /*m_MovieAdapter = new MovieListAdapter(this,R.layout.movie_list,m_MovieList);
  		final ListView listView1 = (ListView)findViewById(R.id.MovieList);
		listView1.setClickable(true);
		listView1.setAdapter(m_MovieAdapter);*/
	    
	    /*jobject = jarray.get(0).getAsJsonObject();
	    String result = jobject.get("translatedText").toString();*/
	    
	}

}
