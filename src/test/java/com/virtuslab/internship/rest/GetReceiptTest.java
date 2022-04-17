package com.virtuslab.internship.rest;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes=com.virtuslab.internship.application.BasketApplication.class)
@AutoConfigureMockMvc
public class GetReceiptTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getReturnDefaultMessage() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/api")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Hello Virtuslab!"));
    }

    @Test
    void postReturn200ok() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/api")
                        .content("[\"Apple\",\"Bread\"]")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void postReturnValidReceipt() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/api")
                        .content("[\"Apple\",\"Bread\",\"Pork\",\"Butter\"]")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.receipt.entries[*].product.name",
                        Matchers.containsInAnyOrder("Apple", "Bread", "Pork", "Butter")));
    }

    @Test
    void postReturnValidReceiptWithDiscount() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/api")
                        .content("[\"Steak\",\"Steak\",\"Steak\"]")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.receipt.discounts").isNotEmpty());
    }
}
