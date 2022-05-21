package day02;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class C05_DropDown {
     /*
      ...Exercise6...
   // 1. Amazon.com'a gidelim.
   // 2. DropDown üzerinde Books secelim.(All yazan yerde)
   //    kategorilerin hepsini konsola yazdiralim
   // 3. Arama kutusuna Les Miserables yazalım ve arama yapalim.
   // 4. Sonuc sayisini ekrana yazdiralim.
   // 5. Sonucların Les Miserables i icerdigini assert edelim
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
        driver.quit();
    }
    @Test
    public void test01() {
        driver.get("https://www.amazon.com");
        WebElement dropDown= driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
        Select select=new Select(dropDown);
        select.selectByVisibleText("Books");
        List<WebElement> selectList=select.getOptions();
        selectList.stream().forEach(t-> System.out.println(t.getText()));
        //// 4. Sonuc sayisini ekrana yazdiralim.
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Les Miserables"+ Keys.ENTER);
        WebElement sonuc=driver.findElement(By.xpath("(//div[@class='a-section a-spacing-small a-spacing-top-small'])[1]"));
        System.out.println(sonuc.getText());
        String sonucSayisi=Arrays.stream(sonuc.getText().
                        split(" ")).
                collect(Collectors.toList()).get(3).
                replaceAll("\\D","");
        int sayi= Integer.parseInt(sonucSayisi);
        System.out.println("sayi = " + sayi);

// 5. Sonucların Les Miserables i icerdigini assert edelim
        String expectedSonuc="Les Miserables";
        Assert.assertTrue(sonuc.getText().contains(expectedSonuc));

    }
    }

