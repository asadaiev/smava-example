package example.selenium.testng;

import static example.pages.SmavaMainPage.LoanCategory.WOHNAN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.testng.annotations.Test;

import example.pages.LoanClarificationPage;
import example.pages.SmavaMainPage;

public class SmavaBasicTest extends BaseTest {

    @Test
    void testSelect_WohnanCategory_With2250_And25Month() {

        SmavaMainPage mainPage = new SmavaMainPage(driver);

        LoanClarificationPage loanClarification = mainPage.open()
                .clickOnLoanCategory()
                .chooseCategory(WOHNAN)
                .clickOnLoanAmount()
                .chooseAmount(2250)
                .clickOnLoanDuration()
                .chooseDuration(24)
                .submitLoan();

        assertThat(loanClarification.anmelden.isDisplayed(), is(true));
        assertThat(loanClarification.birthday.isDisplayed(), is(true));
    }

    @Test
    public void testWrong_Login_Data() {

        SmavaMainPage mainPage = new SmavaMainPage(driver);
        mainPage.open()
                .callAnmeldenForm()
                .setEmail("fakeEmail123@gma1l.com")
                .setPassword("fakePassword")
                .submitLogin();

        assertThat("Login has been successful instead of fail!",
                mainPage.anmeldenSubmitButton.isDisplayed(), is(true));

    }
}
