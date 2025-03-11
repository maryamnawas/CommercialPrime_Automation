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
import java.util.Scanner;

public class LoginTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://app.primeq.co/");
    }

    @Test
    public void LoginDetails() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Username or Email: ");
        String username = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        // Locate and fill in the username
        WebElement usernameField = driver.findElement(By.xpath("//input[@placeholder='Username or Email']"));
        usernameField.sendKeys(username);

        // Locate and fill in the password
        WebElement passwordField = driver.findElement(By.id("txtPassword"));
        passwordField.sendKeys(password);

        // Locate and click the login button
        WebElement loginButton = driver.findElement(By.className("LoginForm_LoginButton__xSMas"));
        loginButton.click();

        // Validate login (check for successful navigation or specific element)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe("https://app.primeq.co/home"));

        if (Objects.equals(driver.getCurrentUrl(), "https://app.primeq.co/home")) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed.");
        }
    }
}
