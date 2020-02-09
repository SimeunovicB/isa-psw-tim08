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

public class AppointmentControllerE2E {
    public static final String BASE_URL = "http://localhost:4200";

    private WebDriver driver;

    @Test
    public void findClinics(){
        System.setProperty("webdriver.chrome.driver", "src/test/java/TIM8/medicalcenter/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to(BASE_URL + "/login");
        WebElement mail = driver.findElement(By.id("email"));
        mail.sendKeys("pera.peric@gmail.com");
        WebElement pass = driver.findElement(By.id("password"));
        pass.sendKeys("123");
        WebElement btnLog = driver.findElement(By.id("log"));
        btnLog.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.id("klinike")));
        link.click();
        WebElement date = wait.until(ExpectedConditions.elementToBeClickable(By.id("date")));
        date.sendKeys("2020-02-02");
        WebElement type = driver.findElement(By.id("tip"));
        type.sendKeys("Rutinski pregled");
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        WebElement btn = driver.findElement(By.id("pretrazi"));
        btn.click();
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("doctor1")));
        element.click();
        wait = new WebDriverWait(driver, 10);
        WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("zakazi1")));
        element1.click();
    }
}
