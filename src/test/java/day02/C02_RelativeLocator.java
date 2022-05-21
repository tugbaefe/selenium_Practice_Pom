package day02;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.time.Duration;

public class C02_RelativeLocator {
     /*
    ...Exercise2...
    http://www.bestbuy.com 'a gidin,
    Sayfa basliginin "Best" icerdigini(contains) dogrulayin
    Ayrica Relative Locator kullanarak;
        logoTest => BestBuy logosunun gorunutulenip goruntulenmedigini dogrulayin
        mexicoLinkTest => Linkin gorunutulenip goruntulenmedigini dogrulayin
 */
    static WebDriver driver;
    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    @AfterClass
    public static void tearDown(){

      //  driver.close();
    }
    @Test
    public void test01(){
        //http://www.bestbuy.com 'a gidin,
        driver.get("http://www.bestbuy.com");
        //    Sayfa basliginin "Best" icerdigini(contains) dogrulayin
        Assert.assertTrue(driver.getTitle().contains("Best"));
        //    Ayrica Relative Locator kullanarak;
        //        logoTest => BestBuy logosunun gorunutulenip goruntulenmedigini dogrulayin
       // WebElement logoTest= driver.findElement(By.xpath("(//img[@class='logo'])[1]"));
        //Assert.assertTrue(logoTest.isDisplayed());
        WebElement logoTest= driver.findElement(By.xpath("(//div[@class='heading'])[1]"));
        WebElement bestBuyTest= driver.findElement(RelativeLocator.with(By.tagName("img")).above(logoTest));
        Assert.assertTrue(logoTest.isDisplayed());
        //        mexicoLinkTest => Linkin gorunutulenip goruntulenmedigini dogrulayin
        WebElement unitedStatesLink= driver.findElement(By.xpath("(//img[@alt='United States'])[1]"));
        WebElement mexicoLinkTest=driver.findElement(RelativeLocator.with(By.tagName("img")).toRightOf(unitedStatesLink));
        Assert.assertTrue(mexicoLinkTest.isDisplayed());



    }
}
