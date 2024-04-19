package com.example.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.DemoApplication;
import com.example.DTO.LoginRequest;
import com.example.DTO.Role;
import com.example.model.User;
import com.example.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    private static final String EMAIL_REGEX = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    @GetMapping("/get-all-user")
    public ResponseEntity<?> getAllUser(@RequestHeader("token") String token) {
        try {
            String access_token = token.split(" ")[1];
            Claims claims = Jwts.parser().verifyWith(DemoApplication.key).build().parseSignedClaims(access_token)
                    .getPayload();
            Boolean isAdmin = claims.get("isAdmin", Boolean.class);
            if (isAdmin) {
                List<User> list = userService.getAllUser();
                if (list.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else
                    return ResponseEntity.ok(Map.of(
                            "status", "OK",
                            "message", "SUCCESS",
                            "data", list));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                        "status", "ERR",
                        "message", "Bạn cần có quyền admin để thực hiện việc này"));
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-user/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") String id) {
        try {
            Optional<User> user = userService.getUser(id);
            if (user.isPresent()) {
                return ResponseEntity.ok(Map.of(
                        "status", "OK",
                        "message", "SUCCESS",
                        "data", user.get()));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                        "status", "ERR",
                        "message", "Không tìm thấy người dùng này"));
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            if (pattern.matcher(user.getEmail()).matches()) {
                User created = userService.createUser(user);
                return ResponseEntity.ok(Map.of(
                        "status", "OK",
                        "message", "SUCCESS",
                        "data", created));
            } else
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Map.of(
                        "status", "ERR",
                        "message", "Email đã tồn tại"));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("update-user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") String id, @RequestBody Role role) {
        try {
            User updated = userService.updateUser(id, role.getIsAdmin());
            return ResponseEntity.ok(Map.of(
                    "status", "OK",
                    "message", "SUCCESS",
                    "data", updated));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("delete-user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok(Map.of(
                    "status", "OK",
                    "message", "SUCCESS"));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("sign-in")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            List<String> token = userService.signIn(loginRequest);
            if (token == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                        "status", "ERR",
                        "message", "Sai tên đăng nhập hoặc mật khẩu"));
            } else
                return ResponseEntity.ok(Map.of(
                        "status", "OK",
                        "message", "SUCCESS",
                        "access_token", token.get(0),
                        "refresh_token", token.get(1)));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("refresh-token")
    public ResponseEntity<?> refreshToken(@RequestHeader String ac) {
        try {
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
