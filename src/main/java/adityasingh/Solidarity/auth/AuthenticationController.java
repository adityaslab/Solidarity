package adityasingh.Solidarity.auth;

import adityasingh.Solidarity.error.ResourceNotFoundException;
import adityasingh.Solidarity.model.AuthenticationRequest;
import adityasingh.Solidarity.model.AuthenticationResponse;
import adityasingh.Solidarity.model.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {

        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateRequest(
            @RequestBody AuthenticationRequest request
    ) throws ResourceNotFoundException {
        System.out.println(request.getEmail()+"1123");
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
