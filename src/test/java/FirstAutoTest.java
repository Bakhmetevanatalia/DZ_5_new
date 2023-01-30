import data.InputFields;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;



public class FirstAutoTest {
    private static final Logger log = Logger.getLogger(String.valueOf(FirstAutoTest.class));
    private final String login = "wasej93407@dni8.com";
    private final String pas = "Natalia12345!";
    private WebDriver driver;
    ChromeOptions options = new ChromeOptions();



    @Test
    public void autoTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://otus.ru");
        loginInOtus();
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".header3__user-info-name")));
        actions.moveToElement(driver.findElement(By.cssSelector(".header3__user-info-name")))
                .click(driver.findElement(By.xpath("//*[normalize-space(text()) = 'Мой профиль']"))).release().perform();
        WebElement firstRusNameField = getFieldByName(InputFields.FIRSTRUSNAME);
        firstRusNameField.clear();
        firstRusNameField.sendKeys("Имя");
        WebElement firstLatNameField = getFieldByName(InputFields.FIRSTLATNAME);
        firstLatNameField.clear();
        firstLatNameField.sendKeys("FName");
        WebElement lastRusNameField = getFieldByName(InputFields.LASTRUSNAME);
        lastRusNameField.clear();
        lastRusNameField.sendKeys("Фамилия");
        WebElement lastLatNameField = getFieldByName(InputFields.LASTLATNAME);
        lastLatNameField.clear();
        lastLatNameField.sendKeys("LName");
        WebElement blogNameField = getFieldByName(InputFields.BLOGNAME);
        blogNameField.clear();
        blogNameField.sendKeys("BlogName");

        driver.findElement(By.xpath("//*[@name='date_of_birth']")).clear();
        driver.findElement(By.xpath("//*[@name='date_of_birth']")).sendKeys("11.11.2000" + Keys.ENTER);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[name='country'] ~ div")));
        driver.findElement(By.cssSelector("[name='country'] ~ div")).click();
        driver.findElement(By.xpath("//ancestor::*[contains(@data-ajax-slave, 'by_country')]//button[@title='Россия']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".js-lk-cv-dependent-slave-city")));
        driver.findElement(By.cssSelector(".js-lk-cv-dependent-slave-city")).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(200,300)");
        driver.findElement(By.cssSelector("input[name='city']+div")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.lk-cv-block__select-option[title='Москва']")));
        driver.findElement(By.cssSelector("button.lk-cv-block__select-option[title='Москва']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[name=english_level]+div")));
        driver.findElement(By.cssSelector("input[name=english_level]+div")).click();
        driver.findElement(By.cssSelector("button.lk-cv-block__select-option[title='Средний (Intermediate)']")).click();
        driver.findElement(By.cssSelector("input[name*='contact-0']~.lk-cv-block__input")).click();
        driver.findElement(By.xpath("//*[@id='id_contact-0-value']")).clear();
        driver.findElement(By.xpath("//ancestor::*[contains(@data-num, '0')]//button[6]")).click();
        driver.findElement(By.xpath("//*[@id='id_contact-0-value']")).sendKeys("111");
        driver.findElement(By.cssSelector(".js-lk-cv-custom-select-add")).click();
        driver.findElement(By.cssSelector("input[name*='contact-1']~.lk-cv-block__input")).click();
        driver.findElement(By.xpath("//*[@id='id_contact-1-value']")).clear();
        driver.findElement(By.xpath("//ancestor::*[contains(@data-num, '1')]//button[6]")).click();
        driver.findElement(By.xpath("//*[@id='id_contact-1-value']")).sendKeys("222");
        driver.findElement(By.xpath("//*[normalize-space(text()) = 'Сохранить и продолжить']")).click();

        driver.manage().deleteAllCookies();
        driver.get("https://otus.ru");
        loginInOtus();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".header3__user-info-name")));
        actions.moveToElement(driver.findElement(By.cssSelector(".header3__user-info-name")))
                .click(driver.findElement(By.xpath("//*[normalize-space(text()) = 'Мой профиль']"))).release().perform();

        Assertions.assertEquals("Имя", driver.findElement(By.xpath("//*[@id='id_fname']")).getAttribute("value"));
        Assertions.assertEquals("Фамилия", driver.findElement(By.xpath("//*[@id='id_lname']")).getAttribute("value"));
        Assertions.assertEquals("FName", driver.findElement(By.xpath("//*[@id='id_fname_latin']")).getAttribute("value"));
        Assertions.assertEquals("LName", driver.findElement(By.xpath("//*[@id='id_lname_latin']")).getAttribute("value"));
        Assertions.assertEquals("BlogName", driver.findElement(By.xpath("//*[@id='id_blog_name']")).getAttribute("value"));
        Assertions.assertEquals("11.11.2000", driver.findElement(By.xpath("//*[@name='date_of_birth']")).getAttribute("value"));
        Assertions.assertEquals("Россия", driver.findElement(By.cssSelector("[name='country'] ~ div")).getText());
        Assertions.assertEquals("Москва", driver.findElement(By.cssSelector(".js-lk-cv-dependent-slave-city")).getText());
        Assertions.assertEquals("111", driver.findElement(By.xpath("//*[@id='id_contact-0-value']")).getAttribute("value"));
        Assertions.assertEquals("222", driver.findElement(By.xpath("//*[@id='id_contact-1-value']")).getAttribute("value"));
        log.info("Запись в лог");
    }

    private void loginInOtus(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.cssSelector(".header3__button-sign-in")).click();
        try {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".new-log-reg"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input:not(.js-remove-field-error).js-email-input[placeholder*=\"Электронная почта\"]")));
        clearAndEnter(By.cssSelector("input:not(.js-remove-field-error).js-email-input[placeholder*=\"Электронная почта\"]"), login);
        clearAndEnter(By.cssSelector(".js-psw-input[placeholder*=\"Введите пароль\"]"), pas);
        driver.findElement(By.xpath("//*[normalize-space(text()) = 'Войти' and @type='submit']")).submit();
    }

    private void clearAndEnter(By by, String text){
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(text);
    }

    private WebElement getFieldByName(InputFields field){
        return driver.findElement(By.xpath(String.format("//*[@id='%s']",field.getName())));
    }

    @AfterEach
    public void close(){
        if (driver != null)
            driver.quit();
    }

}

