package com.hs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpClientDemo {

	public static void main(String[] args) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet("http://localhost:6060/SpringMvcWithRest/getData");
		getRequest.addHeader("accept", "application/json");
		HttpResponse response;
		try {
			response = httpClient.execute(getRequest);
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			httpClient.getConnectionManager().shutdown();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*Map<String,String> map=new HashMap<String, String>();
		map.put("userName", "Raghu");
		map.put("password", "Raj");
		map.put("address", "Bharkuriya");
		map.put("profession", "Army");
		map.put("age", "32");
		map.put("mobile", "32974094399");
		
		post(map);*/
	}
	
	/*public static void post(Map<String,String> map){
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://localhost:6060/SpringMVC/insertRegistrationDetails");
		post.addHeader("accept", "application/json");
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		
		Set<String> set=map.keySet();
		for(String key : set) {  
			urlParameters.add(new BasicNameValuePair(key, map.get(key)));  
	    }    

		try {
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		HttpResponse response=null;;
		try {
			response = httpClient.execute(post);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Response Code : "
		                + response.getStatusLine().getStatusCode());

		BufferedReader rd=null;;
		try {
			rd = new BufferedReader(
			        new InputStreamReader(response.getEntity().getContent()));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		StringBuffer result = new StringBuffer();
		String line = "";
		try {
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
}