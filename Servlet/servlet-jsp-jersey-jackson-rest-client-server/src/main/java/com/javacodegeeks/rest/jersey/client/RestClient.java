package com.javacodegeeks.rest.jersey.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RestClient {

	Client client  = Client.create();
	String getUrl  = "http://localhost:8080/servlet-jsp-jersey-jackson-rest-client-server/rest/student/data/get";
	String postUrl = "http://localhost:8080/servlet-jsp-jersey-jackson-rest-client-server/rest/student/data/post";
	
	public void getRequest(){
		// create and send client request
		WebResource webResource = client.resource(getUrl);
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		
		//check server response
		if(response.getStatus()!=200){
			throw new RuntimeException("HTTP Error: "+ response.getStatus());
		}
		
		//print server response
		String result = response.getEntity(String.class);
		System.out.println("Response from the Server: ");
		System.out.println(result);
	}
	
	public void postRequest(){
		// create and send client request
		WebResource webResource = client.resource(postUrl);
		String inputData = "{\"firstName\":\"Alice\",\"lastName\":\"Brown\",\"school\":\"Bright Stars\",\"standard\":\"Three\",\"rollNumber\":1212}";
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class,inputData);
		
		//check server response
		if(response.getStatus()!=201){
			throw new RuntimeException("HTTP Error: "+ response.getStatus());
		}
		
		//print server response
		String result = response.getEntity(String.class);
		System.out.println("Response from the Server: ");
		System.out.println(result);
	}
	
	public static void main(String[] args) {
		RestClient restClient = new RestClient();
		
		//fire the get request
		restClient.getRequest();
		
		//fire the post request
		restClient.postRequest();
	}
}
