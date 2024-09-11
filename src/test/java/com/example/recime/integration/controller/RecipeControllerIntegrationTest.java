package com.example.recime.integration.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class RecipeControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testGetTrendingRecipesShouldReturn200() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/recipe/search/findAllTrendingRecipes")
                        .param("page", "0")
                        .param("size", "10")
                        .contextPath("/api")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.page.size").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.page.totalElements").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("$.page.totalPages").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.page.number").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.recipeDTOes[0].name").value("Classic Cheeseburger"));
    }

    @Test
    public void testGetTrendingRecipesByDifficultyShouldReturn200() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/recipe/search/findAllTrendingRecipesByDifficulty")
                        .param("page", "0")
                        .param("size", "10")
                        .param("difficulty", "easy")
                        .contextPath("/api")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.page.size").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.page.totalElements").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.page.totalPages").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.page.number").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.recipeDTOes[0].name").value("Classic Cheeseburger"))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.recipeDTOes[0].position").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.recipeDTOes[0].difficulty").value("easy"))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.recipeDTOes[1].name").value("Hawaiian Burger"))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.recipeDTOes[1].position").value(8))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.recipeDTOes[1].difficulty").value("easy"));


    }

    @Test
    public void testGetTrendingRecipesByDifficultyShouldReturn404() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/recipe/search/findAllTrendingRecipesByDifficulty")
                        .param("page", "0")
                        .param("size", "10")
                        .contextPath("/api")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("A difficulty required for filtering trending recipes"));
    }
}
