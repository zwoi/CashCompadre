package ch.zhaw.sml.iwi.meng.leantodo.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ch.zhaw.sml.iwi.meng.leantodo.entity.User;
import ch.zhaw.sml.iwi.meng.leantodo.entity.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenGenerator {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration = 3600L;

    @Autowired
    private UserRepository userRepository;

    public UserAuthResponse generateJWT(String username) {

        UserAuthResponse userAuthResponse = new UserAuthResponse();

        User user = userRepository.findById(username).get();
        if (user == null) {
            return null;
        }
        userAuthResponse.setLoginName(user.getLoginName());

        Map<String, Object> claimsMap = new HashMap<>();
        String rolesCSV = "";
        for (int i = 0; i < user.getRoles().size(); i++) {
            String roleName = user.getRoles().get(i).getRoleName();
            rolesCSV = rolesCSV + roleName;
            userAuthResponse.getRoles().add(roleName);
            if (i < user.getRoles().size() - 1) {
                rolesCSV = rolesCSV + ",";
            }
        }

        claimsMap.put("ROLES", rolesCSV);

        Date creationDate = new Date();
        Date expirationDate = new Date(creationDate.getTime() + expiration * 1000);

        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));

        try {
            String token = Jwts.builder()
                    .claims().empty().add(claimsMap).and()
                    .subject(user.getLoginName())
                    .issuedAt(creationDate)
                    .expiration(expirationDate)
                    .signWith(key)
                    .compact();
            userAuthResponse.setExpiresAt(expirationDate);
            userAuthResponse.setJwsToken(token);
            return userAuthResponse;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }

    }

}