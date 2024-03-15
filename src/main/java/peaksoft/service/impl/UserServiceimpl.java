package peaksoft.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.Role;
import peaksoft.dto.request.SignInRequest;
import peaksoft.dto.request.SignUpRequest;
import peaksoft.dto.response.SignResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.models.User;
import peaksoft.repo.UserRepository;
import peaksoft.service.UserService;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class UserServiceimpl implements UserService {
private final UserRepository userRepository;
private final PasswordEncoder passwordEncoder;
@PostConstruct
    private void saveAdmin(){
   String encode =  passwordEncoder.encode("123");
    userRepository.save(User.builder()
            .email("admin@gmail.com")
                    .password(encode)
                            .role(Role.ADMIN).
            build());
}

    @Override
    public SimpleResponse signUp(SignUpRequest signUpRequest) {
        boolean exists = userRepository.existsByEmail(signUpRequest.getEmail());
        if (exists) throw  new RuntimeException("Email : "+ signUpRequest.getEmail()+" already exist");
    //    if (!signUpRequest.getPassword().equals(signUpRequest.getPasswordConfirm())) throw new RuntimeException("Invalid password");

        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(Role.ADMIN);
        userRepository.save(user);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully saved")
                .build();
    }

    @Override
    public SignResponse signIn(SignInRequest signInRequest) {
        User user =  userRepository.findByEmail(signInRequest.email()).orElseThrow(() ->
                new NoSuchElementException("User with email: " + signInRequest.email()+" not found!"));

        String encodePassword = user.getPassword();
        String password = signInRequest.password();

        boolean matches = passwordEncoder.matches(password, encodePassword);//салыштырат

        if (!matches) throw  new RuntimeException("Invalid password");

        return SignResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }


}


