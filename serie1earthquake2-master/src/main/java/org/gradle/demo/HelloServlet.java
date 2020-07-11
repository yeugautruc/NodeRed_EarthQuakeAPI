package org.gradle.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpClient;
import java.sql.Date;
import java.util.*;
import java.text.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@WebServlet(urlPatterns = { "earthquake" })

public class HelloServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpGet requestEarthquake = new HttpGet(
				"https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/significant_week.geojson");

		HttpResponse responseEarthquake = client.execute(requestEarthquake);

		String response3 = EntityUtils.toString(responseEarthquake.getEntity());
		JsonObject object = JsonParser.parseString(response3).getAsJsonObject();
		JsonArray arr = object.get("features").getAsJsonArray();

		String ergebnis = "";
		for (int i = 0; i < arr.size(); i++) {

			JsonObject properties = arr.get(i).getAsJsonObject().get("properties").getAsJsonObject();
			JsonElement mag = properties.get("mag");
			JsonElement time = properties.get("time");
			JsonElement place = properties.get("place");
			
			long unix_seconds = Long.parseLong(time.toString());
			Date date = new Date(unix_seconds);
			SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy" , Locale.ENGLISH);
			dateFormat.setTimeZone(TimeZone.getTimeZone("CET"));
			
			String richtige_time = dateFormat.format(date);
			//System.out.println("\n"+richtige_time+"\n") ;
			
			ergebnis = ergebnis + "Magnitude: " + mag.toString() + "; "+ "Place: " + place.toString() + "; " + "Time: "+ richtige_time + "\n";
			
		}
		response.getWriter().print(ergebnis);
	
	}

}