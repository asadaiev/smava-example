package example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoanClarificationPage extends BasePage {

    public LoanClarificationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"form_LeadStep2Kreditnehmer_ContactInfo\"]/div/div[2]/div/div/span/span")
    public WebElement anmelden;
    @FindBy(name = "applicant0.personal.firstName")
    public WebElement firstName;

    @FindBy(name = "applicant0.personal.lastName")
    public WebElement lastName;

    @FindBy(name = "applicant0.personal.birthDate")
    public WebElement birthday;



    public LoanClarificationPage clickOnAnmelden(){
        anmelden.click();
        return this;
    }
}
