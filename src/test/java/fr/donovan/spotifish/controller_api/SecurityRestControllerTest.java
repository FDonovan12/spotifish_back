package fr.donovan.spotifish.controller_api;

import fr.donovan.spotifish.entity.User;
import fr.donovan.spotifish.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class SecurityRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLoginSuccess() throws Exception {
        ResultActions resultActions = mockMvc.perform(
            post("/api/security/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getLoginJsonFromData("eva.petit@yahoo.fr", "12345")));

        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.accessToken").exists());
    }

    @Test
    public void testLoginFailedWithBadPassword() throws Exception {
        ResultActions resultActions = mockMvc.perform(
            post("/api/security/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getLoginJsonFromData("eva.petit@yahoo.fr", "123456")));

        resultActions.andExpect(status().is4xxClientError());
    }

    @Test
    public void testLoginFailedWithBadEmail() throws Exception {
        ResultActions resultActions = mockMvc.perform(
            post("/api/security/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getLoginJsonFromData("eva.grand@yahoo.fr", "12345")));

        resultActions.andExpect(status().is4xxClientError());
    }

    private String getLoginJsonFromData(String username, String password) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("password", password);
        return jsonObject.toString();
    }
}