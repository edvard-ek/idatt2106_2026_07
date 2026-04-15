package edu.ntnu.idi.idatt.core.security;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ntnu.idi.idatt.game.GameApplication;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest(classes = GameApplication.class)
@AutoConfigureMockMvc
@Import(SecurityRolePropagationIntegrationTest.AuthProbeController.class)
class SecurityRolePropagationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Verifies that a teacher access token is translated into a TEACHER authority.
     */
    @Test
    void teacherTokenProducesTeacherAuthority() throws Exception {
        String accessToken = login("alf@osloskolen.no", 1, "Password123!");

        mockMvc.perform(get("/api/test/auth-info")
                .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("AuthenticatedUser[id=1, username=alf, schoolId=1]"))
                .andExpect(jsonPath("$.authorities[0]").value("ROLE_TEACHER"));
    }

    /**
     * Verifies that a pupil access token is translated into a PUPIL authority.
     */
    @Test
    void pupilTokenProducesPupilAuthority() throws Exception {
        String accessToken = login("snorre@osloskolen.no", 1, "Password123!");

        mockMvc.perform(get("/api/test/auth-info")
                .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("AuthenticatedUser[id=2, username=snorre, schoolId=1]"))
                .andExpect(jsonPath("$.authorities[0]").value("ROLE_PUPIL"));
    }

    private String login(String email, int schoolId, String password) throws Exception {
        MvcResult loginResult = mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "email": "%s",
                          "schoolId": %s,
                          "password": "%s"
                        }
                        """.formatted(email, schoolId, password)))
                .andExpect(status().isOk())
                .andReturn();

        JsonNode json = objectMapper.readTree(loginResult.getResponse().getContentAsString());
        return json.get("accessToken").asText();
    }

    @RestController
    @RequestMapping("/api/test")
    static class AuthProbeController {

        @GetMapping("/auth-info")
        AuthInfoResponse authInfo(Authentication authentication) {
            List<String> authorities = authentication.getAuthorities().stream()
                    .map(grantedAuthority -> grantedAuthority.getAuthority())
                    .toList();

            return new AuthInfoResponse(authentication.getName(), authorities);
        }
    }

    record AuthInfoResponse(String name, List<String> authorities) {
    }
}
