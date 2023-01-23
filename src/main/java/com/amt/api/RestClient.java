package com.amt.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;

import com.amt.testBase.TestBase;

public class RestClient extends TestBase {


		
		
		//1. GET Method without Headers:
		public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); //http get request
		CloseableHttpResponse closebaleHttpResponse =  httpClient.execute(httpget); //hit the GET URL
		
		return closebaleHttpResponse;
			
		}
		
		//2. GET Method with Headers:
			public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(url); //http get request
			
			for(Map.Entry<String,String> entry : headerMap.entrySet()){
				httpget.addHeader(entry.getKey(), entry.getValue());
			}
			CloseableHttpResponse closebaleHttpResponse =  httpClient.execute(httpget); //hit the GET URL
			return closebaleHttpResponse;
				
			}
		
		//3. POST Method:
			public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
				CloseableHttpClient httpClient = HttpClients.createDefault();
				HttpPost httppost = new HttpPost(url); //http post request
				httppost.setEntity(new StringEntity(entityString)); //for payload
				
				//for headers:
				for(Map.Entry<String,String> entry : headerMap.entrySet()){
					httppost.addHeader(entry.getKey(), entry.getValue());
				}
				
				CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httppost);
				return closebaleHttpResponse;
				
				
			}
		
			
		
		
		

	}