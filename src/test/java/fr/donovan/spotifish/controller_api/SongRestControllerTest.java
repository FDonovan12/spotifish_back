package fr.donovan.spotifish.controller_api;

import fr.donovan.spotifish.dto.SongDTO;
import fr.donovan.spotifish.repository.UserRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(scripts = "/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class SongRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSongShowReturnSong() throws Exception {
        String songSlug = "prof-gabriel-lambert-14c1593e";
        String songUuid = "14c1593e-2605-4ec2-94ce-fa90e4ae1503";
        String songname = "Prof Gabriel Lambert";

        ResultActions resultActions = mockMvc.perform(
            get("/api/song/" + songSlug));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.body.slug").value(songSlug))
                .andExpect(jsonPath("$.body.uuid").value(songUuid))
                .andExpect(jsonPath("$.body.name").value(songname))
                .andExpect(jsonPath("$.body.permission").exists())
        ;
    }

    @Test
    public void testSongCreateWork() throws Exception {
        SongDTO songDTO = new SongDTO();
        songDTO.setName("une super musique");
        songDTO.setCreatedAt(LocalDate.now());

        ResultActions resultActions = mockMvc.perform(
                post("/api/song/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJsonFromData(songDTO)));

        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.body.slug").exists())
                .andExpect(jsonPath("$.body.uuid").exists())
                .andExpect(jsonPath("$.body.name").value("une super musique"))
                .andExpect(jsonPath("$.body.permission").exists())
        ;
    }

    @Test
    public void testSong2CreateWork() throws Exception {
        SongDTO songDTO = new SongDTO();
        songDTO.setName("une super musique 2");
        songDTO.setCreatedAt(LocalDate.now());

        ResultActions resultActions = mockMvc.perform(
                post("/api/song/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJsonFromData(songDTO)));

        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.body.slug").exists())
                .andExpect(jsonPath("$.body.uuid").exists())
                .andExpect(jsonPath("$.body.name").value("une super musique 2"))
                .andExpect(jsonPath("$.body.permission").exists())
        ;
    }

    private String getJsonFromData(SongDTO songDTO) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", songDTO.getName());
        jsonObject.put("createdAt", songDTO.getCreatedAt());
        return jsonObject.toString();
    }
}