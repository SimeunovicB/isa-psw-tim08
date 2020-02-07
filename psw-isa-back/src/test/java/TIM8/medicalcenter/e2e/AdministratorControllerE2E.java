package TIM8.medicalcenter.e2e;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class AdministratorControllerE2E {

    public static final String BASE_URL = "http://localhost:4200";

    private WebDriver driver;

    @Test
    public void rooms(){
        System.setProperty("webdriver.chrome.driver", "src/test/java/TIM8/medicalcenter/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to(BASE_URL + "/searchrooms");
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        WebElement date = driver.findElement(By.id("termin1"));
        date.click();
        WebElement ime = driver.findElement(By.id("ime"));
        ime.sendKeys("Sala za preglede");
        WebElement broj = driver.findElement(By.id("broj"));
        broj.sendKeys("1");
        WebElement datum = driver.findElement(By.id("date"));
        datum.sendKeys("2020-07-01");
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        WebElement btn = driver.findElement(By.id("pretrazi"));
        btn.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("1")));
        element1.click();

    }
}
