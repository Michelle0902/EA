package jwtexample;

import jwtexample.dto.JwtAuthenticationResponse;
import jwtexample.models.Role;
import jwtexample.models.SignInUser;
import jwtexample.models.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@SpringBootApplication
public class SpringBoot3JwtAuthenticationApplication implements CommandLineRunner {

	RestTemplate restTemplate = new RestTemplate();
	private String serverUrl = "http://localhost:8080/";
	Role role;
	public static void main(String[] args) {
		SpringApplication.run(SpringBoot3JwtAuthenticationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		makeGetRestCall("http://localhost:8080/api/all","");
		User user = new User("John", "Doe", "john@hotmail.com", "user", Role.ROLE_USER);
		makeSignUpCall(user, "http://localhost:8080/auth/signup");
		SignInUser signInUser = new SignInUser("john@hotmail.com", "user");
		String token = makeSignInCall(signInUser, "http://localhost:8080/auth/signin");

		makeGetRestCall("http://localhost:8080/api/users",token);

		signInUser = new SignInUser("admin@admin.com", "password");
		String admintoken = makeSignInCall(signInUser, "http://localhost:8080/auth/signin");

		makeGetRestCall("http://localhost:8080/api/admins",admintoken);
	}

	public String makeSignInCall(SignInUser signInUser,String url) {
		String response="";
		try {
			JwtAuthenticationResponse jwtAuthenticationResponse = restTemplate.postForObject(url, signInUser,JwtAuthenticationResponse.class);
			System.out.println("Successful signin with response = "+ jwtAuthenticationResponse.getToken());
			response=jwtAuthenticationResponse.getToken();


		} catch (Exception e) {
			System.out.println("Request Failed "+ e.getMessage());
		}
		return response;
	}
	public String makeSignUpCall(User user, String url){
		String response="";
		try {
			// Perform the GET request using exchange()
			JwtAuthenticationResponse jwtAuthenticationResponse = restTemplate.postForObject(url, user,JwtAuthenticationResponse.class);
			System.out.println("Successful signup with response = "+ jwtAuthenticationResponse.getToken());
			response=jwtAuthenticationResponse.getToken();
		} catch (Exception e) {
			System.out.println("Request Failed "+ e.getMessage());
		}
		return response;
	}
	public void makeGetRestCall(String url, String token){
		// Create HttpHeaders and set custom headers
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)); // Example: Accept JSON response
		if (!token.equals("")) headers.setBearerAuth(token);
//		if (!token.equals("")) {
//			headers.add("Authorization", "Bearer "+token);
//		}
		HttpEntity<String> request = new HttpEntity<>(headers);

		try {
			// Perform the GET request using exchange()
			ResponseEntity<String> response = restTemplate.exchange(
					url,
					HttpMethod.GET,
					request,
					String.class // Expected response type
			);

			// Process the response
			if (response.getStatusCode() == HttpStatus.OK) {
				System.out.println("Request Successful for  URL "+ url+" response = "+ response.getBody());
			}

		} catch (Exception e) {
			System.out.println("Request Failed "+ e.getMessage());
		}
	}

}
