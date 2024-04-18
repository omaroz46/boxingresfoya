package ch.ozan.omar.m295.boxingresfoya;



import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;


import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import ch.ozan.omar.m295.boxingresfoya.entity.BoxingClub;
import ch.ozan.omar.m295.boxingresfoya.repository.BoxingClubRepository;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RestControllerTest {

    @Autowired
    private MockMvc api;

    @Autowired
    private BoxingClubRepository boxingClubRepository;

    @BeforeAll
    void setup() {
        this.boxingClubRepository.save(new BoxingClub("FightClub44", "Swiss Mega Park", "+41764140356"));
        this.boxingClubRepository.save(new BoxingClub("Noble Art Boxing", "Frenkendorf", "+41764652356"));
    }

    @Test
    @Order(1)
    void testGetBoxingClub() throws Exception {
        String accessToken = obtainAccessToken();
        api.perform(get("/api/boxingclub").header("Authorization", "Bearer " + accessToken)
                        .with(csrf()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("FightClub44")));
    }

    @Test
    @Order(2)
    void testGetBoxingClubById() throws Exception{
        String accessToken = obtainAccessToken();
        api.perform(get("/api/boxingclub/1").header("Authorization", "Bearer " + accessToken)
                        .with(csrf()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("FightClub44")));
    }

    @Test
    @Order(3)
    void testNewBoxingClub() throws Exception {
        String accessToken = obtainAccessToken();
        BoxingClub boxingClub = new BoxingClub("Glattbrugg", "Glatt Zentrum", "+41794140356");
        ObjectMapper objectMapper = new ObjectMapper();
        String boxingJson = objectMapper.writeValueAsString(boxingClub);

        api.perform(post("/api/boxingclub")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(boxingJson)
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @Order(4)
    void testDeleteBoxingClub() throws Exception{
        String accessToken = obtainAccessToken();
        api.perform(delete("/api/boxingclub/1")
                        .header("Authorization", "Bearer " + accessToken).with(csrf()))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    private String obtainAccessToken() {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String body = "client_id=boxingresfoya&" +
                "grant_type=password&" +
                "scope=openid profile roles offline_access&" +
                "username=admin&" +
                "password=admin";
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> resp = rest.postForEntity("http://localhost:8080/realms/ILV/protocol/openid-connect/token", entity, String.class);
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resp.getBody()).get("access_token").toString();
    }
}
