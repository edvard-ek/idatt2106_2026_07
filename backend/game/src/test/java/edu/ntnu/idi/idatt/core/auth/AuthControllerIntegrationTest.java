package edu.ntnu.idi.idatt.core.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ntnu.idi.idatt.game.GameApplication;
import edu.ntnu.idi.idatt.core.security.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest(classes = GameApplication.class)
@AutoConfigureMockMvc
class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtService jwtService;

    @Test
    void teacherLoginReturnsRoleAndProfile() throws Exception {
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "email": "alf@osloskolen.no",
                                  "schoolId": 1,
                                  "password": "Password123!"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.role").value("TEACHER"))
                .andExpect(jsonPath("$.user.username").value("alf"))
                .andExpect(jsonPath("$.user.schoolName").value("Uranienborg Skole"))
                .andExpect(jsonPath("$.user.classroomId").value(nullValue()));
    }

    @Test
    void pupilLoginReturnsRoleAndClassroom() throws Exception {
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "email": "snorre@osloskolen.no",
                                  "schoolId": 1,
                                  "password": "Password123!"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.role").value("PUPIL"))
                .andExpect(jsonPath("$.user.username").value("snorre"))
                .andExpect(jsonPath("$.user.classroomName").value("8A"));
    }

    @Test
    void loginFailsWithWrongPassword() throws Exception {
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "email": "alf@osloskolen.no",
                                  "schoolId": 1,
                                  "password": "wrong-password"
                                }
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void loginFailsWithWrongSchool() throws Exception {
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "email": "alf@osloskolen.no",
                                  "schoolId": 2,
                                  "password": "Password123!"
                                }
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void publicSchoolsEndpointExposesEmailSuffixWithoutAuthentication() throws Exception {
        mockMvc.perform(get("/api/schools"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Uranienborg Skole"))
                .andExpect(jsonPath("$[0].emailSuffix").value("@osloskolen.no"));
    }

    @Test
    void protectedEndpointRejectsUnauthenticatedRequests() throws Exception {
        mockMvc.perform(get("/api/teachers/1"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void refreshReturnsSameRolePayload() throws Exception {
        MvcResult loginResult = mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "email": "alf@osloskolen.no",
                                  "schoolId": 1,
                                  "password": "Password123!"
                                }
                                """))
                .andExpect(status().isOk())
                .andReturn();

        JsonNode loginJson = objectMapper.readTree(loginResult.getResponse().getContentAsString());
        String refreshToken = loginJson.get("refreshToken").asText();

        mockMvc.perform(post("/auth/refresh")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "refreshToken": "%s"
                                }
                                """.formatted(refreshToken)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.role").value("TEACHER"))
                .andExpect(jsonPath("$.user.username").value("alf"));
    }

    @Test
    void issuedTokensContainRoleClaim() throws Exception {
        MvcResult loginResult = mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "email": "alf@osloskolen.no",
                                  "schoolId": 1,
                                  "password": "Password123!"
                                }
                                """))
                .andExpect(status().isOk())
                .andReturn();

        JsonNode loginJson = objectMapper.readTree(loginResult.getResponse().getContentAsString());
        String accessToken = loginJson.get("accessToken").asText();
        String refreshToken = loginJson.get("refreshToken").asText();

        assertEquals("TEACHER", jwtService.extractValidatedClaims(accessToken).get("role", String.class));
        assertEquals("TEACHER", jwtService.extractValidatedClaims(refreshToken).get("role", String.class));
    }
}
