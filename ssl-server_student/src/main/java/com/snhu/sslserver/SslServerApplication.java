package com.snhu.sslserver;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
		
		ServerController.myHash();
	}

}
//FIXME: Add route to enable check sum return of static data example:  String data = "Hello World Check Sum!";

@RestController
class ServerController{    
    @RequestMapping("/hash")
    public static String myHash(){
    	String data = "Cody Newman Project 2";
    	String checksumValue = "";
    	
    	try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			md.update(data.getBytes());
			byte[] digest = md.digest();
			
			StringBuilder hexString = new StringBuilder();
			for (byte b : digest) {
				hexString.append(String.format("%02x", b));
			}
			checksumValue = hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			return "Algorithm Not Found: " + e.getMessage();
		}
        
        return "<p>data:"+ data + "<br/><p>SHA-256: Checksum Value: " + checksumValue;
    }
}