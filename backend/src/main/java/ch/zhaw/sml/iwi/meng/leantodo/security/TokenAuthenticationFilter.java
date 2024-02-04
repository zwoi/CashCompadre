package ch.zhaw.sml.iwi.meng.leantodo.security;

import java.io.IOException;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class TokenAuthenticationFilter extends GenericFilterBean {

    public static final Logger LOGGER = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

    private String secret;

    public TokenAuthenticationFilter(String secret) {
        this.secret = secret;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        Cookie tokenCookie = null;

        if (!(request instanceof HttpServletRequest)) {
            // We only handle http requests here.
            // Hand off the request to the rest of the filter chain.
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest httprequest = (HttpServletRequest) request;

        Cookie[] cookies = httprequest.getCookies();

        if (cookies == null || cookies.length < 1) {
            LOGGER.debug("No cookies for authentication present!");
            // Hand off the request to the rest of the filter chain.
            chain.doFilter(request, response);
            return;
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("Authentication")) {
                tokenCookie = cookie;
                break;
            }
        }

        if (tokenCookie == null || tokenCookie.getValue() == null || tokenCookie.getValue().length() == 0) {
            LOGGER.debug("No valid cookies for authentication found!");
            // Hand off the request to the rest of the filter chain.
            chain.doFilter(request, response);
            return;

        }
        String rawJsonWebToken = tokenCookie.getValue();
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));

        try {
            Jws<Claims> jws = Jwts.parser().verifyWith(key).build().parseSignedClaims(rawJsonWebToken);

            // If we get here without exception, we know that the token is valid, signed and
            // has not expired. Hence, we trust it.
            Authentication authentication = new JwtAuthentication(jws);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // Not authorized so no authentication injected into the security context
        }
    }

}