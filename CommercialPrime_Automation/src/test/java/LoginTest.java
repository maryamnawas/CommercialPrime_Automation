import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Objects;

public class LoginTest {
    WebDriver driver;

    @BeforeMethod
    public void LoginDetails() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://play.primeq.co/");

        // Locate and fill in the username
        WebElement usernameField = driver.findElement(By.xpath("//input[@placeholder='Username or Email']"));
        usernameField.sendKeys("Marii");

        // Locate and fill in the password
        WebElement passwordField = driver.findElement(By.id("txtPassword"));
        passwordField.sendKeys("1234");

        // Locate and click the login button
        WebElement loginButton = driver.findElement(By.className("LoginForm_LoginButton__xSMas"));
        loginButton.click();

        // Validate login (check for successful navigation or specific element)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://play.primeq.co/home"));

        if (Objects.equals(driver.getCurrentUrl(), "https://play.primeq.co/home")) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed.");
        }
    }

    @Test
    public void HighPrivacyCheck() {
        // Wait until the High Privacy toggle switch is visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement toggleSwitch = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[class='Home_HighPrivacySwitch__1ghaJ'] button[role='switch']")
        ));

        // Check the value of the "aria-checked" attribute
        String isChecked = toggleSwitch.getAttribute("aria-checked");

        // Validate the state of the High Privacy Mode
        if ("true".equals(isChecked)) {
            System.out.println("High Privacy Mode is ON.");
        } else if ("false".equals(isChecked)) {
            System.out.println("High Privacy Mode is OFF.");
        } else {
            System.out.println("Unable to determine the state of High Privacy Mode.");
        }
    }
}
