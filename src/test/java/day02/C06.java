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

import java.time.Duration;
import java.util.List;

public class C06 {/*
     ...Exercise3...
    // http://the-internet.herokuapp.com/add_remove_elements/
    // click on the "Add Element" button 100 times
    // write a function that takes a number, and clicks the "Delete" button
    // given number of times, and then validates that given number of
    // buttons was deleted
    1.method : createButtons(100)
    2.deleteButtonsAndValidate()
 */
    static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @AfterClass
    public static void tearDown() {
        //driver.quit();
    }

    @Test
    public void test01() {
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");
        // click on the "Add Element" button 100 times
        int clickSayisi=0;
        while (clickSayisi!=100){

            driver.findElement(By.xpath("//button[@onclick='addElement()']")).click();
            clickSayisi++;

        }
        // write a function that takes a number, and clicks the "Delete" button

        int deleteSayisi=0;
        int  silinenDeleteSayisi=0;
        WebElement  deleteButonu;
        while (deleteSayisi!=80){

            driver.findElement(By.xpath(" //button[@class='added-manually']")).click();
            deleteSayisi++;

        }
        int kalan=clickSayisi-deleteSayisi;
        System.out.println("kalan = " + kalan);
        List<WebElement> deleteList=driver.findElements(By.xpath("//button[@class='added-manually']"));
        System.out.println(deleteList.size());
        Assert.assertEquals(deleteList.size(),kalan);









    }
}