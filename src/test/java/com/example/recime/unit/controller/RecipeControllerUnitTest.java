package com.example.recime.unit.controller;

import com.example.recime.controller.RecipeController;
import com.example.recime.model.dto.RecipeDTO;
import com.example.recime.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(RecipeController.class)
@TestPropertySource("classpath:application-test.yml")
public class RecipeControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService recipeService;

    @MockBean
    private PagedResourcesAssembler<RecipeDTO> pagedResourcesAssembler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTrendingRecipesShouldReturn200() throws Exception {
        Page<RecipeDTO> page = new PageImpl<>(List.of(RecipeDTO.builder().name("Recipe1").build()));
        PagedModel<EntityModel<RecipeDTO>> pagedModel = PagedModel.of(
                List.of(EntityModel.of(RecipeDTO.builder().name("Recipe1").build())),
                new PagedModel.PageMetadata(10, 0, 1)
        );

        when(recipeService.getTrendingRecipes(anyInt(), anyInt(), anyString(),anyString())).thenReturn(page);
        when(pagedResourcesAssembler.toModel(page)).thenReturn(pagedModel);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/recipe/search/findAllTrendingRecipes")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sortBy", "position")
                        .param("direction", "asc")
                        .contextPath("/api")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.page.size").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.page.totalElements").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.page.totalPages").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.page.number").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.recipeDTOes[0].name").value("Recipe1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.recipeDTOes[0].description").isEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.recipeDTOes[0].imageUrl").isEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.recipeDTOes[0].recipe").isEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.recipeDTOes[0].position").isEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.recipeDTOes[0].difficulty").isEmpty());
    }

    @Test
    void testGetTrendingRecipesByDifficultyShouldReturn200() throws Exception {
        Page<RecipeDTO> page = new PageImpl<>(List.of(RecipeDTO.builder().name("Recipe1").build()));
        PagedModel<EntityModel<RecipeDTO>> pagedModel = PagedModel.of(
                List.of(EntityModel.of(RecipeDTO.builder().name("Recipe1").build())),
                new PagedModel.PageMetadata(10, 0, 1)
        );

        when(recipeService.getTrendingRecipesByDifficulty(anyInt(), anyInt(), anyString(),anyString(), anyString())).thenReturn(page);
        when(pagedResourcesAssembler.toModel(page)).thenReturn(pagedModel);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/recipe/search/findAllTrendingRecipesByDifficulty")
                        .param("page", "0")
                        .param("size", "10")
                        .param("difficulty", "easy")
                        .contextPath("/api")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.page.size").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.page.totalElements").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.page.totalPages").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.page.number").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.recipeDTOes[0].name").value("Recipe1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.recipeDTOes[0].description").isEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.recipeDTOes[0].imageUrl").isEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.recipeDTOes[0].recipe").isEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.recipeDTOes[0].position").isEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.recipeDTOes[0].difficulty").isEmpty());
    }

    @Test
    void testGetTrendingRecipesShouldReturn400() throws Exception {
        Page<RecipeDTO> page = new PageImpl<>(List.of(RecipeDTO.builder().name("Recipe1").build()));
        PagedModel<EntityModel<RecipeDTO>> pagedModel = PagedModel.of(
                List.of(EntityModel.of(RecipeDTO.builder().name("Recipe1").build())),
                new PagedModel.PageMetadata(10, 0, 1)
        );

        when(recipeService.getTrendingRecipes(anyInt(), anyInt(), anyString(),anyString())).thenReturn(page);
        when(pagedResourcesAssembler.toModel(page)).thenReturn(pagedModel);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/recipe/search/findAllTrendingRecipesByDifficulty")
                        .param("page", "0")
                        .param("size", "10")
                        .contextPath("/api")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("A difficulty required for filtering trending recipes"));
    }
}
