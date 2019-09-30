package com.outreach.interviews.map.builder;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.outreach.interviews.map.enums.MapOperations;

public class UOttawaInterviewAndrewLe
{
	public static class GetLatAndLng {

		private String address;
		private JsonObject result;
		private MapOperations operation;

		private final String URL = "https://maps.googleapis.com/maps/api/";
		private CloseableHttpClient httpclient = HttpClients.createDefault();
		
		/**
		 * Set the address 
		 * @param origin String representing the starting point
		 * @return {@link GetLatAndLng}
		 */
		public GetLatAndLng setAddress(String address)  {
			this.address = address;
			return this;
		}
		
		/**
		 * Perform the HTTP request and retrieve the data from the HttpClient object
		 * @return {@link latAndLng}  
		 * @throws UnsupportedOperationException Thrown to indicate that the requested operation is not supported.
		 * @throws IOException Thrown to indicate that the requested operation is not supported.
		 * @throws IllegalArgumentException Thrown to indicate that a method has been passed an illegal orinappropriate argument.
		 */
		public String build() throws UnsupportedOperationException, IOException, IllegalArgumentException {
			String requestURL = this.getURL()  	+ "?address=" + getAddress()
												+ "&key=" + this.getAPIKey();
			HttpGet httpGet = new HttpGet(requestURL);
			CloseableHttpResponse response = httpclient.execute(httpGet);
			try {
				HttpEntity entity = response.getEntity();
				String jsonBody = IOUtils.toString(entity.getContent(), "UTF-8");
				this.result = new JsonParser().parse(jsonBody).getAsJsonObject();
			}
			finally {
				response.close();
			}
			String latAndLng = "Latitude = " + this.getLat() + " and Longitude = " + this.getLng();	
			return latAndLng;
		}
		

		//*************************For Internal Use Only***********************************//

		// returns the lattitude point for the given address
		private String getLat(){
			String lat = this.result.get("results").getAsJsonArray().get(0).getAsJsonObject()
					.get("geometry").getAsJsonObject()
					.get("location").getAsJsonObject()
					.get("lat").getAsString();

			return lat;
		}

		// returns the longitude point for the given address
		private String getLng(){
			String lng = this.result.get("results").getAsJsonArray().get(0).getAsJsonObject()
					.get("geometry").getAsJsonObject()
					.get("location").getAsJsonObject()
					.get("lng").getAsString();

			return lng;
		}

		// returns the URL
		private final String getURL() {
			return "https://maps.googleapis.com/maps/api/geocode/json";
		}

		//// returns the API key
		private final String getAPIKey() {
			return System.getenv("OUTREACH_MAPS_KEY");
		}
		
		// returns the address
		private final String getAddress() {
			if(this.address == null)
				throw new IllegalArgumentException("Address cannot be empty");
			return this.address;
		}
	}
}

