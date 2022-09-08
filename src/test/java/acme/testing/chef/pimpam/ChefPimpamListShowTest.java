package acme.testing.chef.pimpam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefPimpamListShowTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/pimpam/pimpam-list-show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveChefPimpamListTest(final int recordIndex, final String key, final String atitle, final String acode, final String ainstantationDate, 
		final String astartDate, final String aendDate, final String adescription,final String abudget,final String alink,final String item) {
		super.signIn("chef1", "chef1");

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
		super.checkInputBoxHasValue("ainstantationDate", ainstantationDate);
		super.checkInputBoxHasValue("astartDate", astartDate);
		super.checkInputBoxHasValue("aendDate", aendDate);
		super.checkInputBoxHasValue("adescription", adescription);
		super.checkInputBoxHasValue("abudget", abudget);
		super.checkInputBoxHasValue("alink", alink);
		super.checkInputBoxHasValue("itemName", item);
		
		
		super.signOut();
	}


}
