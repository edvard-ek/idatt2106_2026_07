package edu.ntnu.idi.idatt.core.auth.service;

import edu.ntnu.idi.idatt.core.auth.dto.AuthResponse;
import edu.ntnu.idi.idatt.core.auth.dto.LoginRequest;
import edu.ntnu.idi.idatt.core.auth.dto.RefreshTokenRequest;
import edu.ntnu.idi.idatt.core.security.JwtService;
import edu.ntnu.idi.idatt.core.user.entity.Pupil;
import edu.ntnu.idi.idatt.core.user.entity.Teacher;
import edu.ntnu.idi.idatt.core.user.entity.User;
import edu.ntnu.idi.idatt.core.user.repository.UserRepository;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Handles authentication operations for login and token refresh.
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    /**
     * Authenticates a user and issues tokens.
     *
     * @param request login request
     * @return issued access and refresh tokens
     */
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email, school, or password."));

        if (!belongsToSchool(user, request.schoolId())) {
            throw new IllegalArgumentException("Invalid email, school, or password.");
        }

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email, school, or password.");
        }

        String subject = user.getId().toString();
        Map<String, Object> claims = Map.of(
                "email", user.getEmail(),
                "schoolId", request.schoolId()
        );

        String accessToken = jwtService.generateAccessToken(subject, claims);
        String refreshToken = jwtService.generateRefreshToken(subject, claims);

        return new AuthResponse(accessToken, refreshToken);
    }

    /**
     * Issues a new access token from a valid refresh token.
     *
     * @param request refresh token request
     * @return a new access token and the current refresh token
     */
    public AuthResponse refresh(RefreshTokenRequest request) {
        String refreshToken = request.refreshToken();

        if (!jwtService.isRefreshTokenValid(refreshToken)) {
            throw new IllegalArgumentException("Invalid refresh token.");
        }

        String subject = jwtService.extractSubject(refreshToken);
        Long userId = parseUserId(subject);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid refresh token."));

        Long schoolId = extractSchoolId(user);
        Map<String, Object> claims = Map.of(
                "email", user.getEmail(),
                "schoolId", schoolId
        );
        String accessToken = jwtService.generateAccessToken(subject, claims);

        return new AuthResponse(accessToken, refreshToken);
    }

    private boolean belongsToSchool(User user, Long schoolId) {
        return extractSchoolId(user).equals(schoolId);
    }

    private Long extractSchoolId(User user) {
        if (user instanceof Teacher teacher) {
            if (teacher.getSchool() == null || teacher.getSchool().getId() == null) {
                throw new IllegalArgumentException("User is not assigned to a school.");
            }
            return teacher.getSchool().getId();
        }

        if (user instanceof Pupil pupil) {
            if (pupil.getClassroom() == null
                    || pupil.getClassroom().getSchool() == null
                    || pupil.getClassroom().getSchool().getId() == null) {
                throw new IllegalArgumentException("User is not assigned to a school.");
            }
            return pupil.getClassroom().getSchool().getId();
        }

        throw new IllegalArgumentException("Unsupported user type for authentication.");
    }

    private Long parseUserId(String subject) {
        try {
            return Long.parseLong(subject);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Invalid refresh token.", exception);
        }
    }
}
