package com.javasampleapproach.fcm.pushnotif.service;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AndroidPushNotificationsService {

	private static final String FIREBASE_SERVER_KEY = "AAAAxAhyM1U:APA91bFLgdr5635Nh1xe2kMXOwo6JaZ7wQV35cGszm9iSnvuwCghesYfaG3eMq-vEKmIZHB09bp6P3gvbA6x7RXRBVCAGNr-1dnZ1Us6OjNYuMbTbZuKdKDMcHS5wVLd8FGPmSkOHj4i";
    private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";
	

	
	@Async
	public CompletableFuture<String> send(HttpEntity<String> entity) {

		RestTemplate restTemplate = new RestTemplate();

		ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
		interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
		restTemplate.setInterceptors(interceptors);

		String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);
		

		return CompletableFuture.completedFuture(firebaseResponse);
	}
	
}
