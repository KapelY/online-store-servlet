package com.study.service;

import com.study.domain.User;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class SecurityService {
    private final UserService userService;
    /**
     * Map<String token, Session session>
     */
    private final Map<String, Session> sessionContext = new ConcurrentHashMap<>();

    public SecurityService(UserService userService) {
        this.userService = userService;
    }

    public void logout(String token) {
        if (token != null) {
            sessionContext.remove(token);
        }
    }

    public String registerTokenNewUser(String email, String password) {
        String salt = getSalt();
        String passwordHash = encryptPassword(password, salt);
        User user = User.builder()
                .email(email)
                .passwordHash(passwordHash)
                .salt(salt).build();

        Integer userId = userService.saveUser(user);
        user.setId(userId);

        LocalDateTime expirationTime = LocalDateTime.now().plusHours(4) ;
        String token = getSalt();
        sessionContext.put(token, new Session(expirationTime, user));
        return token;
    }

    public String registerTokenCurrentUser(String email, String password) {
        if (validCredentials(email, password)) {
            String token = getSalt();
            Session session = new Session(LocalDateTime.now(), userService.getUserByEmail(email));
            removePreviousSession(email);

            sessionContext.put(token, session);
            return token;
        }
        return null;
    }

    private void removePreviousSession(String email) {
        Iterator<Map.Entry<String, Session>> iterator = sessionContext.entrySet().iterator();
        while (iterator.hasNext()){
            if (iterator.next().getValue().user().getEmail().equals(email)) {
                iterator.remove();
                break;
            }
        }
    }

    private String getSalt() {
        return UUID.randomUUID().toString();
    }

    public boolean validCredentials(String email, String password) {
        User user = userService.getUserByEmail(email);
        if (!Objects.equals(user, null)) {
            String salt = user.getSalt();
            return user.getPasswordHash().equals(encryptPassword(password, salt));
        }
        return false;
    }

    @SneakyThrows
    private String encryptPassword(String passwordToHash, String salt) {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes(StandardCharsets.UTF_8));
        byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public boolean validToken(String token) {
        return sessionContext.containsKey(token);
    }

    public boolean contains(String email) {
        return userService.getUserByEmail(email) != null;
    }
}
