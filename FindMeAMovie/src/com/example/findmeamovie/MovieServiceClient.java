package com.example.findmeamovie;

import java.util.concurrent.atomic.AtomicBoolean;

public class MovieServiceClient {
	private final String SearchByTermLink = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=rq2yuky5pa2y9a4xqzbzkj3r&q=SEARCH_TERM&page_limit=10";
	
	MovieServiceClient()
	{
	}
	
	String GetSearchByTermResponse(String searchTerm,AsyncListener listener)
	{
		String query="";
		try {
			query = URLEncoder.encode(searchTerm, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String LinkWithData = SearchByTermLink.replace("SEARCH_TERM", query);
		GetResponseTask searchTask = new GetResponseTask(listener);
		searchTask.execute(LinkWithData);
		
		return "";
	}
}
