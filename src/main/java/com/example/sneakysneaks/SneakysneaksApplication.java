package com.example.sneakysneaks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@SpringBootApplication
////@EnableSwagger2
//@ComponentScan("com.example.sneakysneaks.controller")
//@ComponentScan("com.example.sneakysneaks.database")
public class SneakysneaksApplication {
	//private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(Arrays.asList("application/json"));

	public static void main(String[] args) {

		SpringApplication.run(SneakysneaksApplication.class, args);
		try {
			FileInputStream serviceAccount =
				  new FileInputStream(new File("src/main/resources/sneakysneaks-f46f0-firebase-adminsdk-cvhgx-281d4e8e91.json"));
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl("https://sneakysneaks-f46f0.firebaseio.com")
					.build();
			//make sure there are no apps running
			if(FirebaseApp.getApps().isEmpty()) {
				FirebaseApp.initializeApp(options);
			}
				
		}
		catch(FileNotFoundException x) {
			System.out.println("File not found buddy");
			x.printStackTrace();
		} 
		catch (IOException e) {
			System.out.println("nuh uh");
			e.printStackTrace();
		}
		
	}
}