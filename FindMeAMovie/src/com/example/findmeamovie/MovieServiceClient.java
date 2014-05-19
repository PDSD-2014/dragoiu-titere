package com.example.findmeamovie;

import java.util.concurrent.atomic.AtomicBoolean;

public class MovieServiceClient {
	private final String SearchByTermLink = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=rq2yuky5pa2y9a4xqzbzkj3r&q=SEARCH_TERM&page_limit=10";
	
	MovieServiceClient()
	{
	}
	
	String GetSearchByTermResponse(String searchTerm,AsyncListener listener)
	{
		String LinkWithData = SearchByTermLink.replace("SEARCH_TERM", searchTerm);
		GetResponseTask searchTask = new GetResponseTask(listener);
		searchTask.execute(LinkWithData);
		
		return "";
	}
}
