package adityasingh.Solidarity.auth;

import adityasingh.Solidarity.entity.Role;
import adityasingh.Solidarity.entity.User;
import adityasingh.Solidarity.error.ResourceNotFoundException;
import adityasingh.Solidarity.model.AuthenticationRequest;
import adityasingh.Solidarity.model.AuthenticationResponse;
import adityasingh.Solidarity.model.RegisterRequest;
import adityasingh.Solidarity.repository.UserRepository;
import adityasingh.Solidarity.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.decode(request.getRole()))
                .build();
        userRepository.save(user);
        var token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .name(user.getFirstName()+" "+user.getLastName())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws ResourceNotFoundException {
        System.out.println(request.getEmail()+"in auth service "+request.getPassword());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        System.out.println("after auth manager");
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException(request.getEmail()+" not found!")); //some exception
        System.out.println(user.getUsername());
        var token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .id(user.getUserId())
                .token(token)
                .name(user.getFirstName()+" "+user.getLastName())
                .build();
    }
}
