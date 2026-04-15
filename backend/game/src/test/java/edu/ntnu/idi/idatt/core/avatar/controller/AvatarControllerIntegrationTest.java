package edu.ntnu.idi.idatt.core.avatar.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ntnu.idi.idatt.game.GameApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

/**
 * Tests {@link AvatarController}.
 */
@SpringBootTest(classes = GameApplication.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class AvatarControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  /**
   * Tests that avatar endpoints reject unauthenticated requests.
   */
  @Test
  void avatarEndpointsRejectUnauthenticatedRequests() throws Exception {
    mockMvc.perform(get("/api/avatars/me"))
        .andExpect(status().is4xxClientError());

    mockMvc.perform(put("/api/avatars/me")
            .contentType(MediaType.APPLICATION_JSON)
            .content(validAvatarRequest()))
        .andExpect(status().is4xxClientError());
  }

  /**
   * Tests that an authenticated user can update and fetch an avatar.
   */
  @Test
  void authenticatedUserCanUpdateAndFetchOwnAvatar() throws Exception {
    String accessToken = loginAsPupil();

    mockMvc.perform(put("/api/avatars/me")
            .header("Authorization", "Bearer " + accessToken)
            .contentType(MediaType.APPLICATION_JSON)
            .content(validAvatarRequest()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.userId").value(2))
        .andExpect(jsonPath("$.genderItemId").value(1))
        .andExpect(jsonPath("$.hatItemId").value(15));

    mockMvc.perform(get("/api/avatars/me")
            .header("Authorization", "Bearer " + accessToken))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.userId").value(2))
        .andExpect(jsonPath("$.upperBodyClothingItemId").value(11))
        .andExpect(jsonPath("$.lowerBodyClothingItemId").value(13));
  }

  /**
   * Tests that slot item lookup returns only matching items.
   */
  @Test
  void authenticatedUserCanFetchItemsForSlot() throws Exception {
    String accessToken = loginAsPupil();

    mockMvc.perform(get("/api/avatars/items/slot/HAT")
            .header("Authorization", "Bearer " + accessToken))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(2))
        .andExpect(jsonPath("$[0].slot").value("HAT"))
        .andExpect(jsonPath("$[1].slot").value("HAT"));
  }

  private String loginAsPupil() throws Exception {
    MvcResult loginResult = mockMvc.perform(post("/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {
                  "email": "snorre@osloskolen.no",
                  "schoolId": 1,
                  "password": "Password123!"
                }
                """))
        .andExpect(status().isOk())
        .andReturn();

    JsonNode json = objectMapper.readTree(loginResult.getResponse().getContentAsString());
    return json.get("accessToken").asText();
  }

  private String validAvatarRequest() {
    return """
        {
          "genderId": 1,
          "eyeColorId": 3,
          "skinColorId": 5,
          "hairColorId": 7,
          "hairStyleId": 9,
          "upperBodyClothingId": 11,
          "lowerBodyClothingId": 13,
          "hatId": 15
        }
        """;
  }
}
