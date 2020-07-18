package eu.twino.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.twino.homework.model.LoanApplicationModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LoanApplicationControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldLoanApplicationRequestRespondCreated() throws Exception {
        mvc.perform(
                post("/loan-application/submit")
                        .content(objectMapper.writeValueAsString(createLoanApplicationModel()))
                        .contentType(APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }

    private LoanApplicationModel createLoanApplicationModel() {
        return new LoanApplicationModel(
                BigDecimal.valueOf(100L),
                30,
                "asdf",
                "Juris",
                "Valdis",
                "randomip"
        );
    }
}