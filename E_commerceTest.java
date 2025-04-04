import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ECommerceTest {
    WebDriver driver;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "path/to/java/chromedriver"); // Update with your path
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void testLogin() {
        driver.get("https://google.com/login"); 

        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.id("loginButton"));

        username.sendKeys("testuser");
        password.sendKeys("testpassword");
        loginBtn.click();

        String expectedUrl = "https://google.com/dashboard"; 
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Login Failed!");
    }

    @Test(priority = 2, dependsOnMethods = {"testLogin"})
    public void testProductSearch() {
        WebElement searchBox = driver.findElement(By.id("searchBar")); // Update with actual search bar ID
        WebElement searchButton = driver.findElement(By.id("searchButton")); // Update with actual button ID

        searchBox.sendKeys("Laptop"); // Enter product name
        searchButton.click();

        WebElement firstResult = driver.findElement(By.className("product-item")); // Change as per website's HTML
        Assert.assertTrue(firstResult.isDisplayed(), "Product Search Failed!");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
