package com.javasampleapproach.fcm.pushnotif.controller;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.javasampleapproach.fcm.pushnotif.service.AndroidPushNotificationsService;
import com.javasampleapproach.fcm.pushnotif.service.FCMHelper;

@RestController
public class WebController {

	private final String TOPIC = "notification-a60d4";
	
	@Autowired
	AndroidPushNotificationsService androidPushNotificationsService;

	@RequestMapping(value = "/send", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> send() throws JSONException {
		
		/*{
			  "message":{
			    "token" : "bk3RNwTe3H0:CI2k_HHwgIpoDKCIZvvDMExUdFQ3P1...",
			    "notification" : {
			      "body" : "This is an FCM notification message!",
			      "title" : "FCM Message",
			      }
			   }
			}*/
		//JSONObject message = new JSONObject();
		
		
		JSONObject body = new JSONObject();
	  //  body.put("to", "/topics/" + TOPIC);
		body.put("to",  "fr8ZLnotKiE:APA91bFy67egxihx528fE4Fya5Mqxfqwyz5PO47cfGxDHEc-eUXDhXhYHfBSEPkx7oHd1kVV4t3xQ0DO1BcXvBgHmlqHm7wg6SveRD9fnLLHujAljx3kSpcoP4O4c_fUHBY1XclrhSSZ");
		body.put("priority", "high");

		JSONObject notification = new JSONObject();
		notification.put("title", "Notification");
		notification.put("body", "Happy Message!");
		
		JSONObject data = new JSONObject();
		data.put("message", "message");
		data.put("image", " image url");

		body.put("notification", notification);
		body.put("data", data);

		//message.put("message",  body);
		
		/*JsonObject notificationObject = new JsonObject();
		notificationObject.addProperty("title", "JSA Notification");
		notificationObject.addProperty("body", "Happy Message!");
		JsonObject dataObject=new JsonObject();
		dataObject.addProperty("Key-1", "JSA Data 1");
		dataObject.addProperty("Key-2", "JSA Data 2");
		
		String res=null;
		FCMHelper helper=FCMHelper.getInstance();
		try {
			res=helper.sendNotifictaionAndData("to", "fr8ZLnotKiE:APA91bFy67egxihx528fE4Fya5Mqxfqwyz5PO47cfGxDHEc-eUXDhXhYHfBSEPkx7oHd1kVV4t3xQ0DO1BcXvBgHmlqHm7wg6SveRD9fnLLHujAljx3kSpcoP4O4c_fUHBY1XclrhSSZ", notificationObject, dataObject);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		
		
		System.out.println(body.toString());
		
		
		HttpEntity<String> request = new HttpEntity<>(body.toString());

		CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
		CompletableFuture.allOf(pushNotification).join();

		try {
			String firebaseResponse = pushNotification.get();
			
			return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
	
	}
	//	return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
	//}
		
}
