package acme.testing.chef.recipe;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefRecipeCreateAndPublishTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/recipe-create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveChefIngredientCreateTest(final int recordIndex, final String code, final String heading, final String description,
		final String preparationNotes, final String info) {
		super.signIn("chef3", "chef3");

		super.clickOnMenu("Chef", "List my recipes");
		super.clickOnButton("Create");

		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("preparationNotes", preparationNotes);
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Chef", "List my recipes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, heading);
		super.checkColumnHasValue(recordIndex, 3, "false");


		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("heading", heading);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("info", info);
		super.checkInputBoxHasValue("preparationNotes", preparationNotes);
			
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/recipe-create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeChefIngredientCreateTest(final int recordIndex, final String key, final String itemType, final String name, final String code, final String description, final String retailPrice,final String link) {
		super.signIn("chef3", "chef3");

		super.clickOnMenu("Chef", "List my recipes");
		super.clickOnSubmit("Create");
		
		super.checkFormExists();
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("itemType", itemType);
		super.clickOnSubmit("Create");
		
		super.checkErrorsExist();
		super.signOut();
	}

	@Test
	@Order(30)
	public void hackingTest() {
		super.navigate("/chef/item/create");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/chef/item/create");
		super.checkPanicExists();
		super.signOut();

		super.signIn("epicure1", "epicure1");
		super.navigate("/chef/item/create");
		super.checkPanicExists();
		super.signOut();
	
	}
	
}
