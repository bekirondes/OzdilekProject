import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class BasePage extends BaseTest {

    Logger logger = LogManager.getLogger(BaseTest.class);
    //Logger kullanımı
    //         logger.info("Alınan text: " + value); Örnek-1
    //         logger.info("Alınan text: " + attribute);; Örnek-2


    @Step("<int> saniye kadar bekle")
    public void waitForSecond(int second) throws InterruptedException {
        Thread.sleep(1000 * second);
    }

    @Step("Log - 1 çalışıyor")
    public void setLogger() {
        logger.info("Uygulama açıldı");
    }


    @Step("Id <id> li elementi bul ve tıkla")
    public void clickByid(String id) {
        appiumDriver.findElement(By.id(id)).click();
        logger.info("Alınan yer: " + id);
    }

    @Step("<id> elementin sayfada görünür olduğunu kontrol et")
    public void findByElementEndClick(String id) {
        MobileElement mobileElement = appiumDriver.findElement(By.id(id));

        if (mobileElement.isDisplayed()) {
            mobileElement.click();
        } else {
            System.out.println("Kontrol edilen element bulunamadı. Tekrar Ara!");
        }

    }

    @Step("Xpath <xpath> li elementi bul ve tıkla.")
    public void clickByxpath(String xpath) {
        appiumDriver.findElement(By.xpath(xpath)).click();
    }

    @Step("<Email> adres ile giris yap")
    public void emailSend(String email) {
        appiumDriver.findElementById(email).sendKeys("asd@hotmail.com");
    }

    @Step("<Email sifresi> ile giris yap")
    public void passwordSend(String password) {
        appiumDriver.findElementById(password).sendKeys("asdfgh");
    }


    @Step("<random> rastgele bir ürün seçilir")
    public void randomProduct(String product) {
        List<MobileElement> elements = appiumDriver.findElements(By.xpath(" //*[@resource-id='com.ozdilek.ozdilekteyim:id/imageView']"));
        Random rnd = new Random();
        int rndInt = rnd.nextInt(elements.size());
        elements.get(rndInt).click();
    }

    @Step("Sayfayı aşağı doğru kaydır")
    public void swipeUp() {
        final int ANIMATION_TIME = 200; // ms
        final int PRESS_TIME = 200; // ms
        int edgeBorder = 10; // better avoid edges
        PointOption pointOptionStart, pointOptionEnd;
        // init screen variables
        Dimension dims = appiumDriver.manage().window().getSize();
        System.out.println("Telefon Ekran Boyutu " + dims);
        // init start point = center of screen
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
        System.out.println("Baslangic noktasi " + pointOptionStart);
        pointOptionEnd = PointOption.point(dims.width / 6, dims.height / 10);
        System.out.println("Bitis noktasi " + pointOptionEnd);
        for (byte i = 0; i < 2; i++) {


            try {
                new TouchAction(appiumDriver)
                        .press(pointOptionStart)
                        // a bit more reliable when we add small wait
                        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                        .moveTo(pointOptionEnd)
                        .release().perform();
            } catch (Exception e) {
                System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
                return;
            }

            // always allow swipe action to complete
            try {
                Thread.sleep(ANIMATION_TIME);
            } catch (InterruptedException e) {
                // ignore
            }
        }
    }

    @Step(" <Back Button> Geri butonuna ile iki kere geri git")
    public void backButton(int j){
        for (int i = 0; i < 2; i++) {
            appiumDriver.findElement(By.id("com.ozdilek.ozdilekteyim:id/ivBack")).click();

        }

    }


    /*@Step("Id <id> li elementi bul ve tıkla")
    public void doubleClickw(String id) {
        appiumDriver.findElement(By.id(id)).click();
    }

     */

}







