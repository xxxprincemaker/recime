package com.example.recime.service;

import com.example.recime.model.dto.RecipeDTO;
import com.example.recime.model.entity.Recipe;
import com.example.recime.repository.RecipeRepository;
import com.example.recime.util.mapper.RecipeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public Page<RecipeDTO> getTrendingRecipes(Integer page, Integer size, String sortsBy, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction),sortsBy));
        Page<Recipe> recipePage = recipeRepository.findAll(pageable);
        List<RecipeDTO> recipeDTOPage = recipePage.getContent().stream().map(RecipeMapper::convertToDTO).toList();
        return new PageImpl<>(recipeDTOPage, pageable, recipePage.getTotalElements());
    }

    public Page<RecipeDTO> getTrendingRecipesByDifficulty(Integer page, Integer size, String sortsBy, String direction,String difficulty) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction),sortsBy));
        Page<Recipe> recipePage = recipeRepository.findAllByDifficulty(difficulty, pageable);
        List<RecipeDTO> recipeDTOPage = recipePage.getContent().stream().map(RecipeMapper::convertToDTO).toList();
        return  new PageImpl<>(recipeDTOPage, pageable, recipePage.getTotalElements());
    }



}
