/*
 * EmployerJobUpdateTest.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing.chef.pimpam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefPimpamUpdateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/pimpam/update-pimpam-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String key, final String atitle, final String acode, 
		final String astartDate, final String aendDate, final String adescription, final String abudget, final String alink, final String item) {
		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "List my pimpams");
		super.checkListingExists();
		
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		
		super.fillInputBoxIn("atitle", atitle);
		super.fillInputBoxIn("astartDate", astartDate);
		super.fillInputBoxIn("adescription", adescription);
		super.fillInputBoxIn("aendDate", aendDate);
		super.fillInputBoxIn("abudget", abudget);
		super.fillInputBoxIn("alink", alink);
		
		super.clickOnSubmit("Update");
		super.checkListingExists();
		
		super.clickOnListingRecord(recordIndex);

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
	@CsvFileSource(resources = "/chef/pimpam/update-pimpam-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String key, final String atitle, final String acode, 
		final String astartDate, final String aendDate, final String adescription, final String abudget, final String alink, final String item) {
		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "List my pimpams");
		super.checkListingExists();
		
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		
		super.fillInputBoxIn("atitle", atitle);
		super.fillInputBoxIn("astartDate", astartDate);
		super.fillInputBoxIn("aendDate", aendDate);
		super.fillInputBoxIn("adescription", adescription);
		super.fillInputBoxIn("abudget", abudget);
		super.fillInputBoxIn("alink", alink);
		
		super.clickOnSubmit("Update");
		
		super.checkErrorsExist();

		super.signOut();
	}

	
}
