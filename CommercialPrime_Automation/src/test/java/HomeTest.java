import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomeTest {
    WebDriver driver;

    @BeforeMethod
    public void OpenLinkTestPage(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://play.primeq.co/home");
    }

    @Test
    public void HighPrivacyCheck(){
        // Locate the toggle switch element
        WebElement toggleSwitch = driver.findElement(By.cssSelector("button[role='switch']"));

        // Get the value of the "aria-checked" attribute
        String isChecked = toggleSwitch.getAttribute("aria-checked");

        // Validate the state of the High Privacy Mode
        if (isChecked.equals("true")) {
            System.out.println("High Privacy Mode is ON.");
        } else if (isChecked.equals("false")) {
            System.out.println("High Privacy Mode is OFF.");
        } else {
            System.out.println("Unable to determine the state of High Privacy Mode.");
        }

    }
}
