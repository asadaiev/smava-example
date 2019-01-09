package example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SmavaMainPage extends BasePage {

    public SmavaMainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='field loan-selection']//div[@class='field__label' and contains(., 'Verwendung')]//following::div[1]//span[@class='Select-value-label' and starts-with(@id,'react-select-')]")
    public WebElement loanCategory;

    @FindBy(xpath = "//*[@id=\"react-select-2--option-2\"]")
    public WebElement loanCategoryWohnan;

    @FindBy(xpath = "//div[@class='field loan-selection']//div[@class='field__label' and contains(., 'Nettokreditbetrag')]//following::div[1]//span[@class='Select-value-label' and starts-with(@id,'react-select-')]")
    public WebElement loanAmount;

    @FindBy(xpath = "//*[@id=\"react-select-3--option-")
    public WebElement loanAmountValue;

    @FindBy(xpath = "//div[@class='field loan-selection']//div[@class='field__label' and contains(., 'Laufzeit')]//following::div[1]//span[@class='Select-value-label' and starts-with(@id,'react-select-')]")
    public WebElement loanDuration;

    @FindBy(xpath = "//*[@id=\"react-select-4--option-")
    public WebElement loanDurationValue;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[1]/div/div/div[1]/div[2]/div[1]/a/button")
    public WebElement submitLoanButton;


    @FindBy(xpath = "//*[@id=\"root\"]/div/div/header/div[1]/div/div/div[2]/nav/div[1]/div")
    public WebElement anmelden;

    @FindBy (name = "email")
    public WebElement emailField;

    @FindBy (name = "password")
    public WebElement passwordField;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/header/div[1]/div/div/div[2]/nav/div[1]/section/div/form/button")
    public WebElement anmeldenSubmitButton;

    final String basicUrl = "http://smava.de";


    public SmavaMainPage open() {
        driver.get(basicUrl);
        PageFactory.initElements(driver, this);
        return this;
    }

    public SmavaMainPage clickOnLoanCategory() {
        waitElementToBeClickable(loanCategory).click();
        return this;
    }

    public SmavaMainPage clickOnLoanAmount() {
        waitElementToBeClickable(loanAmount).click();
        return this;
    }

    public SmavaMainPage clickOnLoanDuration() {
        waitElementToBeClickable(loanDuration).click();
        return this;
    }

    public SmavaMainPage chooseCategory(LoanCategory category) {

        switch (category) {
            case WOHNAN:
                loanCategoryWohnan.click();
                break;

            case UMSCHULDING:
                break;

            case AUTO:
                break;

            case FREIE:
                break;

            case GEWERBE:
                break;
        }
        return this;
    }

    /**
     *
     * @param amount - lowest for positive scenarios should be 500 with step 250 for each next one;
     * @return
     */
    public SmavaMainPage chooseAmount(int amount) {

        final int index = amount / 250 - 2;
        driver.findElement(By.xpath(loanAmountValue.toString().
                substring(loanAmountValue.toString().lastIndexOf(" "),
                        loanAmountValue.toString().length() - 1) + index + "\"]")).click();

        return this;
    }


    /**
     * @param amount - lowest for positive scenarios should be 12 with step 12 for each next one;
     * @return
     */
    public SmavaMainPage chooseDuration(int amount) {
        final int index = amount / 12 - 1;
        driver.findElement(By.xpath(loanDurationValue.toString().
                substring(loanDurationValue.toString().lastIndexOf(" "),
                        loanDurationValue.toString().length() - 1) + index + "\"]")).click();
        return this;
    }

    public LoanClarificationPage submitLoan() {
        submitLoanButton.click();
        return new LoanClarificationPage(driver);
    }

    public SmavaMainPage callAnmeldenForm(){
        waitElementToBeClickable(anmelden).click();
        return this;
    }

    public SmavaMainPage setEmail(String value){
        waitElementToBeClickable(emailField).sendKeys(value);
        return this;
    }

    public SmavaMainPage setPassword(String value){
        waitElementToBeClickable(passwordField).sendKeys(value);
        return this;
    }

    public SmavaMainPage submitLogin(){
        waitElementToBeClickable(anmeldenSubmitButton).click();
        return this;
    }


    public enum LoanCategory {
        UMSCHULDING,
        AUTO,
        WOHNAN,
        GEWERBE,
        FREIE
    }
}


