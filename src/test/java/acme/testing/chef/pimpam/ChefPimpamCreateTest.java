package acme.testing.chef.pimpam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefPimpamCreateTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/pimpam/create-pimpam-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(4)
	public void positiveChefPimpamCreateTest(final int recordIndex, final String key, final String atitle, final String acode, 
		final String astartDate, final String aendDate, final String adescription, final String abudget, final String alink, final String item) {
		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "List my pimpams");
	   
		super.checkListingExists();

		super.clickOnButton("Create a new pimpam");
		
		super.fillInputBoxIn("atitle", atitle);
		super.fillInputBoxIn("acode", acode);
		super.fillInputBoxIn("astartDate", astartDate);
		super.fillInputBoxIn("aendDate", aendDate);
		super.fillInputBoxIn("adescription", adescription);
		super.fillInputBoxIn("abudget", abudget);
		super.fillInputBoxIn("alink", alink);
		
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Chef", "List my pimpams");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, atitle);
		super.checkColumnHasValue(recordIndex, 1, item);
		super.checkColumnHasValue(recordIndex, 2, abudget);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("atitle", atitle);
		super.checkInputBoxHasValue("acode", acode);
		super.checkInputBoxHasValue("astartDate", astartDate);
		super.checkInputBoxHasValue("aendDate", aendDate);
		super.checkInputBoxHasValue("adescription", adescription);
		super.checkInputBoxHasValue("abudget", abudget);
		super.checkInputBoxHasValue("alink", alink);
		super.checkInputBoxHasValue("itemName", item);
			
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/pimpam/create-pimpam-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(3)
	public void negativeChefPimpamCreateTest(final int recordIndex, final String key, final String atitle, final String acode, 
		final String astartDate, final String aendDate, final String adescription, final String abudget, final String alink, final String item) {
		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "List my pimpams");
		
		super.clickOnButton("Create a new pimpam");
		
		super.fillInputBoxIn("atitle", atitle);
		super.fillInputBoxIn("acode", acode);
		super.fillInputBoxIn("astartDate", astartDate);
		super.fillInputBoxIn("aendDate", aendDate);
		super.fillInputBoxIn("adescription", adescription);
		super.fillInputBoxIn("abudget", abudget);
		super.fillInputBoxIn("alink", alink);
		
		super.clickOnSubmit("Create");

		super.checkErrorsExist();
		super.signOut();
	}

	@Test
	@Order(1)
	public void hackingTest() {
		super.navigate("/chef/pimpam/create");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/chef/pimpam/create");
		super.checkPanicExists();
		super.signOut();

		super.signIn("epicure1", "epicure1");
		super.navigate("/chef/pimpam/create");
		super.checkPanicExists();
		super.signOut();
	
	}
	
}
