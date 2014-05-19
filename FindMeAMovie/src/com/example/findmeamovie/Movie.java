package com.example.findmeamovie;

import com.google.gson.JsonObject;

public class Movie {
	private String m_title;
	private String m_description;
	private int m_audienceScore;
	private JsonObject m_jsonObject;
	
	Movie(String title, String description, int audienceScore)
	{
		m_title = title;
		m_description = description;
		m_audienceScore = audienceScore;
	}
	
	String GetTitle()
	{
		return m_title;
	}
	
	void SetTitle(String title)
	{
		m_title = title;
	}
	
	String GetDescription()
	{
		return m_description;
	}
	
	void SetDescription(String description)
	{
		m_description = description;
	}
	
	int GetAudienceScore()
	{
		return m_audienceScore;
	}
	
	void SetAudinceScore(int score)
	{
		m_audienceScore = score;
	}
	
	void SetJsonObject(JsonObject object)
	{
		m_jsonObject = object;
	}
	
	JsonObject GetJsonObject()
	{
		return m_jsonObject;
	}
	
	
}
