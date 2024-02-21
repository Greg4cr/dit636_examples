package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class RecipeBookTest {

    private RecipeBook recipeBook;
    private Recipe recipeArray[];
    //mock recipes
    private Recipe r1;
    private Recipe r2;
    private Recipe r3;
    private Recipe r4;
    private Recipe r5;


    //beforeEach setup create object if needed and it's attributes define

    @BeforeEach
    public void setUp() throws Exception{

        //set up recipe book
        recipeBook= new RecipeBook();
        recipeArray= new Recipe[4];


        //Set up for r1
        r1 = new Recipe();
        r1.setName("Coffee");
        r1.setAmtChocolate("0");
        r1.setAmtCoffee("3");
        r1.setAmtMilk("1");
        r1.setAmtSugar("1");
        r1.setPrice("50");

        //Set up for r2
        r2 = new Recipe();
        r2.setName("Mocha");
        r2.setAmtChocolate("20");
        r2.setAmtCoffee("3");
        r2.setAmtMilk("1");
        r2.setAmtSugar("1");
        r2.setPrice("75");

        //Set up for r3
        r3 = new Recipe();
        r3.setName("Latte");
        r3.setAmtChocolate("0");
        r3.setAmtCoffee("3");
        r3.setAmtMilk("3");
        r3.setAmtSugar("1");
        r3.setPrice("100");

        //Set up for r4
        r4 = new Recipe();
        r4.setName("Hot Chocolate");
        r4.setAmtChocolate("4");
        r4.setAmtCoffee("0");
        r4.setAmtMilk("1");
        r4.setAmtSugar("1");
        r4.setPrice("65");

        //Set up for r4
        r5 = new Recipe();
        r5.setName("Chai");
        r5.setAmtChocolate("2");
        r5.setAmtCoffee("2");
        r5.setAmtMilk("10");
        r5.setAmtSugar("3");
        r5.setPrice("110");
    }

    //create tests both normal and errororenous each

    @Test
    public void testGetRecipesNormal(){
        recipeArray[0]= r1;
        recipeArray[1]= r2;
        recipeArray[2]= r3;
        recipeArray[3]= r4;

        Recipe[] expectedRecipes = {r1, r2, r3, r4};
        Recipe[] actualRecipes = recipeBook.getRecipes();
        assertArrayEquals(expectedRecipes, actualRecipes, "Failed to find Recipes.");
    }

    @Test
    public void testGetRecipesEmpty() {
        Recipe[] expectedRecipes = new Recipe[0];
        Recipe[] actualRecipes = recipeBook.getRecipes();
        assertArrayEquals(expectedRecipes, actualRecipes, "Expected empty recipe list.");
    }

    @Test
    public void testAddRecipeNormal(){
        recipeArray[0]= r1;
        recipeArray[1]= r2;
        assertTrue(recipeBook.addRecipe(r3)); //should be true as it isn't added yet
    }

    @Test
    public void testAddRecipeAlreadyExisting(){
        recipeArray[0]= r1;
        recipeArray[1]= r2;
        assertFalse(recipeBook.addRecipe(r2)); //should be false as it is already added
    }

    @Test
    public void testAddRecipeNoSpace(){
        recipeArray[0]= r1;
        recipeArray[1]= r2;
        recipeArray[2]= r3;
        recipeArray[3]= r4;
        assertFalse(recipeBook.addRecipe(r5)); //should be false as we have no space
    }

    @Test
    public void deleteRecipeNormal(){
        //return name of recipe deleted at specified position
        recipeArray[0]= r1;
        recipeArray[1]= r2;
        recipeArray[2]= r3;

        String expectedName = "Latte"; //mock data
        String actualName = recipeBook.deleteRecipe(2); //index given as input for r3
        assertEquals(expectedName, actualName, "Expected a different recipe to be deleted.");

    }
    @Test
    public void deleteRecipeNonExistent(){
        //return null if recipe doesn't exist in array
        //return name of recipe deleted at specified position
        recipeArray[0]= r1;
        recipeArray[1]= r2;

        String actualName = recipeBook.deleteRecipe(2); //index given as input for r3
        assertNull( actualName, "Expected null as recipe doesn't exist.");
    }

    @Test
    public void editRecipeNormal(){
        //if recipe exist returns the name of the recipe edited at the position specified
        recipeArray[0]= r1;
        recipeArray[1]= r2;
        recipeArray[2]= r3;

        String expectedName = "Latte"; //mock data r3
        String actualName = recipeBook.editRecipe(2, r4); //index of old recipe, new recipe to add as input
        assertEquals(expectedName, actualName, "Expected a different recipe name after editing.");
    }
    @Test
    public void editRecipeNonExistent(){
        //return null if the recipe does not exist
        recipeArray[0]= r1;
        recipeArray[1]= r2;

        String actualName = recipeBook.editRecipe(2, r4); //index of old recipe, new recipe to add as input
        assertNull(actualName, "Expected null as recipe doesn't exist.");
    }

    @Test
    public void editRecipeWeird(){
        //if no change made
        recipeArray[0]= r1;
        recipeArray[1]= r2;
        recipeArray[2]= r3;

        String expectedName = "Latte"; //mock data r3
        String actualName = recipeBook.editRecipe(2, r3); //index of old recipe, new recipe to add as input
        assertEquals(expectedName, actualName, "Expected no change as same data is entered.");
    }

}
