package com.example.findmeamovie;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MovieListAdapter extends ArrayAdapter<Movie> 
{
	private Context mContext;
	private int mID;
	private List<Movie>mItems;

	public MovieListAdapter(Context context, int textViewResourceId,
			List<Movie> objects) {
		super(context, textViewResourceId, objects);
		mContext = context;
		mID = textViewResourceId;
		mItems = objects;
	}

	public Movie getItem(int i)
	 {
		 return mItems.get(i);
	 }
	 @Override
	public View getView(int position, View convertView, ViewGroup parent) {
          View v = convertView;
          if (v == null) {
              LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
              v = vi.inflate(mID, null);
          }
          final Movie o = mItems.get(position);
          if (o != null) {
                  TextView t1 = (TextView) v.findViewById(R.id.MovieTitle);
                  TextView t2 = (TextView) v.findViewById(R.id.MovieDescription);
                  
                  if(t1!=null)
                  	t1.setText(o.GetTitle());
                  if(t2!=null)
                  	t2.setText(o.GetDescription());
                  
          }
          return v;
	 }
}
