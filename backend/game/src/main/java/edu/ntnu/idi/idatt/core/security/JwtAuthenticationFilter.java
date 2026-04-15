package edu.ntnu.idi.idatt.core.security;

import edu.ntnu.idi.idatt.core.auth.enums.AuthRole;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import edu.ntnu.idi.idatt.core.auth.dto.AuthenticatedUser;

/**
 * Authenticates requests that carry a valid JWT access token.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final String ROLE_CLAIM = "role";

    private final JwtService jwtService;

    /**
     * Extracts an access token from the request and sets authentication.
     *
     * @param request     current request
     * @param response    current response
     * @param filterChain remaining filter chain
     * @throws ServletException if filtering fails
     * @throws IOException      if I/O fails
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);

        if (SecurityContextHolder.getContext().getAuthentication() != null
                || authorizationHeader == null
                || !authorizationHeader.startsWith(BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.substring(BEARER_PREFIX.length()).trim();
        if (token.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            Claims claims = jwtService.extractValidatedClaims(token);
            if (!jwtService.isAccessToken(token)) {
                SecurityContextHolder.clearContext();
                filterChain.doFilter(request, response);
                return;
            }

            AuthenticatedUser authenticatedUser = new AuthenticatedUser(Long.valueOf(claims.getSubject()),
                    claims.get("username", String.class),
                    claims.get("schoolId", Long.class));
            AuthRole role = extractRole(claims);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(

                    authenticatedUser,
                    null,

                    List.of(new SimpleGrantedAuthority("ROLE_" + role.name())));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception exception) {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }

    private AuthRole extractRole(Claims claims) {
        String roleClaim = claims.get(ROLE_CLAIM, String.class);
        if (roleClaim == null || roleClaim.isBlank()) {
            throw new IllegalArgumentException("JWT is missing role claim.");
        }

        return AuthRole.valueOf(roleClaim);
    }
}
