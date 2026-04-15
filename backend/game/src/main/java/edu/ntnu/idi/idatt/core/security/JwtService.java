package edu.ntnu.idi.idatt.core.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Handles creation and validation of JWT authentication tokens.
 */
@Service
public class JwtService {

    private static final String TOKEN_TYPE_CLAIM = "token_type";
    private static final String ACCESS_TOKEN_TYPE = "access";
    private static final String REFRESH_TOKEN_TYPE = "refresh";

    private final SecretKey signingKey;
    
    // Adjust access token duration
    private final long accessTokenExpirationMs;

    // Adjust refresh token duration 
    private final long refreshTokenExpirationMs;

    public JwtService(
            @Value("${app.jwt.secret:change-this-secret-key-to-at-least-32-characters}") String secret,
            @Value("${app.jwt.access-token-expiration-ms:3600000}") long accessTokenExpirationMs,
            @Value("${app.jwt.refresh-token-expiration-ms:604800000}") long refreshTokenExpirationMs
    ) {
        this.signingKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessTokenExpirationMs = accessTokenExpirationMs;
        this.refreshTokenExpirationMs = refreshTokenExpirationMs;
    }

    /**
     * Creates an access token for a subject.
     *
     * @param subject token subject
     * @return signed access token
     */
    public String generateToken(String subject) {
        return generateAccessToken(subject, Map.of());
    }

    /**
     * Creates an access token with extra claims.
     *
     * @param subject token subject
     * @param extraClaims additional claims
     * @return signed access token
     */
    public String generateToken(String subject, Map<String, Object> extraClaims) {
        return generateAccessToken(subject, extraClaims);
    }

    /**
     * Creates an access token for a subject.
     *
     * @param subject token subject
     * @return signed access token
     */
    public String generateAccessToken(String subject) {
        return generateAccessToken(subject, Map.of());
    }

    /**
     * Creates an access token with extra claims.
     *
     * @param subject token subject
     * @param extraClaims additional claims
     * @return signed access token
     */
    public String generateAccessToken(String subject, Map<String, Object> extraClaims) {
        return generateToken(subject, extraClaims, ACCESS_TOKEN_TYPE, accessTokenExpirationMs);
    }

    /**
     * Creates a refresh token for a subject.
     *
     * @param subject token subject
     * @return signed refresh token
     */
    public String generateRefreshToken(String subject) {
        return generateRefreshToken(subject, Map.of());
    }

    /**
     * Creates a refresh token with extra claims.
     *
     * @param subject token subject
     * @param extraClaims additional claims
     * @return signed refresh token
     */
    public String generateRefreshToken(String subject, Map<String, Object> extraClaims) {
        return generateToken(subject, extraClaims, REFRESH_TOKEN_TYPE, refreshTokenExpirationMs);
    }

    private String generateToken(
            String subject,
            Map<String, Object> extraClaims,
            String tokenType,
            long expirationMs
    ) {
        Instant now = Instant.now();
        Instant expiresAt = now.plusMillis(expirationMs);

        return Jwts.builder()
                .claims(extraClaims)
                .claim(TOKEN_TYPE_CLAIM, tokenType)
                .subject(subject)
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiresAt))
                .signWith(signingKey)
                .compact();
    }

    /**
     * Validates a token and returns its claims.
     *
     * @param token JWT to validate
     * @return validated claims
     */
    public Claims extractValidatedClaims(String token) {
        return Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Extracts the subject from a valid token.
     *
     * @param token JWT to inspect
     * @return token subject
     */
    public String extractSubject(String token) {
        return extractValidatedClaims(token).getSubject();
    }

    /**
     * Checks whether a token is an access token.
     *
     * @param token JWT to inspect
     * @return true if the token is an access token
     */
    public boolean isAccessToken(String token) {
        return ACCESS_TOKEN_TYPE.equals(extractTokenType(token));
    }

    /**
     * Checks whether a token is a refresh token.
     *
     * @param token JWT to inspect
     * @return true if the token is a refresh token
     */
    public boolean isRefreshToken(String token) {
        return REFRESH_TOKEN_TYPE.equals(extractTokenType(token));
    }

    /**
     * Checks whether an access token is valid.
     *
     * @param token JWT to validate
     * @return true if the token is valid and of type access
     */
    public boolean isAccessTokenValid(String token) {
        return isTokenValidForType(token, ACCESS_TOKEN_TYPE);
    }

    /**
     * Checks whether a refresh token is valid.
     *
     * @param token JWT to validate
     * @return true if the token is valid and of type refresh
     */
    public boolean isRefreshTokenValid(String token) {
        return isTokenValidForType(token, REFRESH_TOKEN_TYPE);
    }

    private String extractTokenType(String token) {
        return extractValidatedClaims(token).get(TOKEN_TYPE_CLAIM, String.class);
    }

    /**
     * Checks whether a token is structurally valid.
     *
     * @param token JWT to validate
     * @return true if the token is valid
     */
    public boolean isTokenValid(String token) {
        try {
            extractValidatedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException exception) {
            return false;
        }
    }

    private boolean isTokenValidForType(String token, String expectedTokenType) {
        try {
            return expectedTokenType.equals(extractTokenType(token));
        } catch (JwtException | IllegalArgumentException exception) {
            return false;
        }
    }
}
