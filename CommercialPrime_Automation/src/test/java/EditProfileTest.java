import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.Objects;

public class EditProfileTest {
    WebDriver driver;

    @BeforeMethod
    public void LoginDetails() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://app.primeq.co/");

        // Locate and fill in the username
        WebElement usernameField = driver.findElement(By.xpath("//input[@placeholder='Username or Email']"));
        usernameField.sendKeys("MNMaryam");

        // Locate and fill in the password
        WebElement passwordField = driver.findElement(By.id("txtPassword"));
        passwordField.sendKeys("mnmaryam#03");

        // Locate and click the login button
        WebElement loginButton = driver.findElement(By.className("LoginForm_LoginButton__xSMas"));
        loginButton.click();

        // Validate login (check for successful navigation or specific element)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://app.primeq.co/home"));

        if (Objects.equals(driver.getCurrentUrl(), "https://app.primeq.co/home")) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed.");
        }
    }


    
}
