import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Objects;

public class HIghPrivacyTest {
    WebDriver driver;

    @BeforeMethod
    public void LoginDetails() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://app.primeq.co/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate and fill in the username
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Username or Email']")
        ));
        usernameField.sendKeys("MNMaryam");

        // Locate and fill in the password
        WebElement passwordField = driver.findElement(By.id("txtPassword"));
        passwordField.sendKeys("mnmaryam#03");

        // Locate and click the login button
        WebElement loginButton = driver.findElement(By.className("LoginForm_LoginButton__xSMas"));
        loginButton.click();

        // Validate login
        wait.until(ExpectedConditions.urlToBe("https://app.primeq.co/home"));

        if (Objects.equals(driver.getCurrentUrl(), "https://app.primeq.co/home")) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed.");
        }
    }

    @Test
    public void HighPrivacyCheck() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for High Privacy toggle switch
        WebElement toggleSwitch = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[class='Home_HighPrivacySwitch__1ghaJ'] button[role='switch']")
        ));

        // Get "aria-checked" attribute value
        String isChecked = toggleSwitch.getAttribute("aria-checked");

        // Click the specified dashboard item
        WebElement dashboardItem = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='Home_DashboardContent__Cig3f']//div[1]//div[1]//div[1]//div[1]")
        ));
        dashboardItem.click();

        // Redirect to the required page
        driver.get("https://primeq.co/MNMaryam");

        // Wait for the page to load
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

        if ("true".equals(isChecked)) {
            // If High Privacy Mode is ON, check for the "Privacy Protected Account" message
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//h1[normalize-space()='Privacy Protected Account']")
            )).isDisplayed();

            System.out.println("High Privacy Mode is ON and the correct page loaded.");

        } else if ("false".equals(isChecked)) {
            // If High Privacy Mode is OFF, check for the profile page elements
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[@class='ProfileCard_ButtonContainer__n8ewG']//button[1]")
            )).isDisplayed();

            System.out.println("High Privacy Mode is OFF and the correct profile page loaded.");

        } else {
            System.out.println("Unable to determine the state of High Privacy Mode.");
        }
    }

    @AfterMethod
    public void closeBrowser() {
        if (driver != null) {
            driver.quit(); // Closes all browser windows and ends the WebDriver session
        }
    }
}
