package springframework.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import springframework.commands.RecipeCommand;
import springframework.converters.RecipeCommandToRecipe;
import springframework.converters.RecipeToRecipeCommand;
import springframework.domain.Recipe;
import springframework.exceptions.NotFoundException;
import springframework.repositories.RecipeRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        /*recipeService = new RecipeServiceImpl(recipeRepository);*/
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipes() throws Exception {

        Recipe recipe = new Recipe();
        HashSet recipeData = new HashSet<>();
        when(recipeRepository.findAll()).thenReturn(recipeData);

        Set<Recipe> recipes = recipeService.getRecipes();
        assertEquals(recipes.size(), 0);
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void getRecipeByIdTest() {

        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> optionalRecipe = Optional.of(recipe);
        when(recipeRepository.findById(any())).thenReturn(optionalRecipe);

        Recipe recipeReturned = recipeService.findById(1L);

        assertNotNull("Null recipe returned", recipeReturned);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void getRecipesTest() throws Exception {

        Recipe recipe = new Recipe();
        HashSet receipesData = new HashSet();
        receipesData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(receipesData);

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
        verify(recipeRepository, never()).findById(anyLong());
    }

    @Test
    public void getRecipeCoomandByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        when(recipeToRecipeCommand.convert(any())).thenReturn(recipeCommand);

        RecipeCommand commandById = recipeService.findCommandById(1L);

        assertNotNull("Null recipe returned", commandById);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void deleteById() throws Exception {

        //Given
        Long idToDelete = Long.valueOf(2L);

        //when
        recipeService.deleteById(idToDelete);

        //then
        verify(recipeRepository, times(1)).deleteById(anyLong());

    }

    @Test(expected = NotFoundException.class)
    public void getRecipeByIdTestNotFound() throws Exception {

        Optional<Recipe> recipeOptional = Optional.empty();

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);

        //should go boom
    }
}