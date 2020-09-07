package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.AddContactPage;
import pages.LoginPage;
import pages.MePage;
import suporte.Utils;

import static org.junit.Assert.assertEquals;

public class infoUserPageObjectTest{

    private WebDriver navegador;
    private MePage mePage;
    private AddContactPage addContactPage;
    private String atualMessage;

    @Given("the user opens the browser")
    public void the_user_opens_the_browser() {
        navegador = Utils.iniciarNavegador();
    }

    @Given("the user enter {string} and {string}")
    public void the_user_enter(String login, String password) {
        mePage = new LoginPage(navegador).clickSignIn()
                .fazerLogin(login, password)
                .clicarMe();
    }

    @When("you click on the More About You tab")
    public void you_click_on_the_more_about_you_tab() {
        addContactPage = mePage.clicarAbaMoreAboutYou().clicarBotaoAddMoreDataAboutYou();
    }

    @When("add desired {string} and {string}")
    public void add_desired_type_and_contact(String type , String contact) {
        mePage = addContactPage.adicionarContato(type, contact);
    }

    @Then("a message should be displayed")
    public void a_message_should_be_displayed() {
        atualMessage = mePage.capturaTextoDoToast();
    }

    @Then("we should check if the message is {string}")
    public void we_should_check_if_the_message_is_the_expected_one(String expectMessage) {
        assertEquals(expectMessage, atualMessage);
    }

    @Then("the browser must be closed")
    public void the_browser_must_be_closed() {
        navegador.quit();
    }

}
