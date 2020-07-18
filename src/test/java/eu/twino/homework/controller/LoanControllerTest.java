package eu.twino.homework.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LoanControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldApprovedLoanListRespondOk() throws Exception {
        mvc.perform(
                get("/loan/list/approved")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    void shouldApprovedLoanListByPersonalIdRespondOk() throws Exception {
        mvc.perform(
                get("/loan/list/approved/person/{personalId}", "randomPersonalId")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        );
    }
}