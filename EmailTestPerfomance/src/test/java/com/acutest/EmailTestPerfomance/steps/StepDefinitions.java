package com.acutest.EmailTestPerfomance.steps;

import com.acutest.EmailTestPerfomance.ReceiveMailUsingAuthentication;
import com.acutest.EmailTestPerfomance.SendMailUsingAuthentication;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class StepDefinitions {

	private String newstyleemailaccount = null;

	// Sender & receiver engine classes
	ReceiveMailUsingAuthentication receiver = new ReceiveMailUsingAuthentication();
	SendMailUsingAuthentication sender = new SendMailUsingAuthentication();

	// message properties
	private static String MESS_BODY = "Hello this is a test message";
	private static String MESS_SUBJ = "Test message from Cucumber";

	// Scenario properties
	Scenario scenario = null;

	@Before
	public void BeforeScenario(Scenario scenario) {
		this.scenario = scenario;
	}

	@Given("^I have an Old Style exchange account \"([^\"]*)\"$")
	public void i_have_an_Old_Style_exchange_account(String arg1) throws Throwable {

		String oldstyleemailaccount = arg1; // unused at the moment

	}

	@Given("^exchange account is migrated to New Style account \"([^\"]*)\"$")
	public void exchange_account_is_migrated_to_New_Style_account(String arg1) throws Throwable {

		newstyleemailaccount = arg1;
	}

	@Given("^\"([^\"]*)\" exists as an Old Style email account$")
	public void exists_as_an_Old_Style_email_account(String arg1) throws Throwable {

		String emailcorrespondentos = arg1; // unused at the moment
	}

	@Given("^\"([^\"]*)\" exists as a New Styile email acocunt$")
	public void exists_as_a_New_Styile_email_acocunt(String arg1) throws Throwable {

		String emailcorrespondentns = arg1; // unused at the moment
	}

	@When("^I send an email from my new style account to \"([^\"]*)\"$")
	public void i_send_an_email_from_my_new_style_account_to(String arg1) throws Throwable {

		String[] from = { arg1 };

		sender.sendEmail(MESS_BODY, MESS_SUBJ, from, newstyleemailaccount);
	}

	@Then("^the message is received by \"([^\"]*)\"$")
	public void the_message_is_received_by(String arg1) throws Throwable {

		String email = receiver.getMail(newstyleemailaccount, MESS_SUBJ, arg1);

		if (email != null)
			scenario.write(email);

		Assert.assertNotNull("Mail not found", email);

	}

}
