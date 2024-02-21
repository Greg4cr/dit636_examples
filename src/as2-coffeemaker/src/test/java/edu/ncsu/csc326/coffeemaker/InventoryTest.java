package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//unit tests for inventory

public class InventoryTest {
	
	private Inventory inventory;
	private Recipe recipe;
	private Recipe recipe2;

	@BeforeEach
	public void setUp() throws Exception {
		//creating new inventory object
		inventory = new Inventory();

		//creating recipe object where inventory has enough ingredients
		recipe = new Recipe();
		recipe.setName("The Flex");
		recipe.setAmtMilk("1");
		recipe.setAmtChocolate("1");
		recipe.setAmtCoffee("1");
		recipe.setAmtSugar("1");
		recipe.setPrice("50000");

		//creating recipe object where ingredients are not enough
		recipe2 = new Recipe();
		recipe2.setName("The One Way Ticket");
		recipe2.setAmtMilk("0");
		recipe2.setAmtChocolate("0");
		recipe2.setAmtCoffee("9999");
		recipe2.setAmtSugar("0");
		recipe2.setPrice("1");
	}

	//testing the coffee adder under normal operation
	@Test
	public void addCoffee_normal() {
		try {
			inventory.addCoffee("10");
		} catch (InventoryException e) {
			fail("Input was valid, exception misfire");
		}
		int expected = 25;
		int actual = inventory.getCoffee();
		//coffee should be 10 + default
		assertEquals(expected, actual, "Inventory should be equal to 25, instead was " + actual);
	}

	//testing the coffee adder with invalid data
	@Test
	public void addCoffee_exception() {
		assertThrows(InventoryException.class, () -> {
			inventory.addCoffee("oh noes!");
		}, "Inventory exception should have been thrown, instead value was accepted");
	}

	//testing the recipe checker with enough ingredients
	@Test
	public void inStock() {
		boolean actual = inventory.enoughIngredients(recipe);
		boolean expected = true;
		//should be true
		assertEquals(expected, actual, "Ingredients should have had enough in stock, instead false was returned");
	}

	//testing the recipe checker with not enough ingredients
	@Test
	public void outOfStock() {
		boolean actual = inventory.enoughIngredients(recipe2);
		boolean expected = false;
		//should be false
		assertEquals(expected, actual, "Should not have been enough ingredients in inventory, instead recipe was ok'd");
	}

	//testing making recipe when in stock
	@Test
	public void make_ok() {
		//correct output
		boolean expected_bool = true;
		boolean actual_bool = inventory.useIngredients(recipe);

		//correct amts
		int expected_milk = 14;
		int expected_sugar = 14;
		int expected_chocolate = 14;
		int expected_coffee = 14;

		int actual_milk = inventory.getMilk();
		int actual_sugar = inventory.getSugar();
		int actual_chocolate = inventory.getChocolate();
		int actual_coffee = inventory.getCoffee();

		//oracle time
		assertEquals(expected_bool, actual_bool, "False was returned, when coffee should've been made");
		assertEquals(expected_chocolate, actual_chocolate, "New stock should show 14 chocolate, instead showed " + actual_chocolate);
		assertEquals(expected_coffee, actual_coffee, "New stock should show 14 coffee, instead showed " + actual_coffee);
		assertEquals(expected_milk, actual_milk, "New stock should show 14 milk, instead showed " + actual_milk);
		assertEquals(expected_sugar, actual_sugar, "New stock should show 14 sugar, instead showed " + actual_sugar);
	}

	//testing trying to make a coffee when there is not enough stock
	@Test
	public void make_error() {
		boolean actual = inventory.useIngredients(recipe2);
		boolean expected = false;
		//should not allow coffee to be made
		assertEquals(expected, actual, "Coffee should not be possible to be made, instead coffee was made");
	}
}
