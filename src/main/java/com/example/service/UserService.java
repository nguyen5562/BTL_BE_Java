package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.LoginRequest;
import com.example.model.User;
import com.example.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(String id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        String hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        return userRepository.save(new User(
                user.getName(),
                user.getEmail(),
                hash,
                user.isAdmin()));
    }

    public User updateUser(String id, Boolean isAdmin) {
        Optional<User> updated = userRepository.findById(id);
        User _user = updated.get();
        _user.setAdmin(isAdmin);
        return userRepository.save(_user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public List<String> signIn(LoginRequest loginRequest) {
        User db = userRepository.findByEmail(loginRequest.getEmail());
        List<String> token = new ArrayList<>();

        if (db == null) {
            return null;
        } else if (BCrypt.checkpw(loginRequest.getPassword(), db.getPassword())) {
            String access_token = jwtService.generalAccessToken(db.getId(), db.isAdmin());
            token.add(access_token);
            String refresh_token = jwtService.generalRefreshToken(db.getId(), db.isAdmin());
            token.add(refresh_token);
            return token;
        } else
            return null;
    }
}
