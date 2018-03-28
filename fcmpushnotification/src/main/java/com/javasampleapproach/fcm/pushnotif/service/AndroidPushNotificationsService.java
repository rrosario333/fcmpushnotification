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

	private static final String FIREBASE_SERVER_KEY = "AAAA_M6E1hE:APA91bFoVtBkVa6yuul81yBFJwpNEr-2GKd8J3vvX_J_khZ6xEzDSKNYTRI3Ao556COZo4ygm7cibIwFbMymI_67klTqKDGHaJwvJ3TBvVP2Xn4Df8PYcT4vd3Ivs4MePK6QU59PgMmi";
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
