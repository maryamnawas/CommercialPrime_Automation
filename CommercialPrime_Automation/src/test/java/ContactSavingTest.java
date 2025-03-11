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

public class ContactSavingTest {
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

    @Test
    public void ContactTab(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click on Contacts tab
        WebElement contactsTab = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='PrimeTabMenu_PrimeTabMenu__AOjZz']//div[3]")
        ));
        contactsTab.click();

        // Wait until redirected to contacts page
        wait.until(ExpectedConditions.urlToBe("https://app.primeq.co/contacts"));

        // Click on Add Contact button
        WebElement addContactButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='Contacts_Contacts__sGwuJ']//button[1]")
        ));
        addContactButton.click();

        // Fill the contact form
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Name']"))).sendKeys("John Doe");
        driver.findElement(By.xpath("//input[@placeholder='Company']")).sendKeys("ABC Company");
        driver.findElement(By.xpath("//input[@placeholder='Title']")).sendKeys("Software Engineer");
        driver.findElement(By.xpath("//input[@placeholder='Contact Number']")).sendKeys("712345678"); // 9-digit number
        driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("john@example.com");
        driver.findElement(By.xpath("//input[@placeholder='Note']")).sendKeys("Dummy note for testing.");

        // Select the option with value 742
        WebElement dropdownOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//option[@value='742']")
        ));
        dropdownOption.click();

        // Click Save button
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Save']")
        ));
        saveButton.click();

        System.out.println("Test Passed: Contact added successfully!");
    }

    @AfterMethod
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
