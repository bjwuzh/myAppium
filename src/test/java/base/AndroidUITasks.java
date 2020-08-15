package base;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import utils.Log;

import java.util.List;

/**
 * @program: myAppium
 * @description: 执行操作类
 * @author: bjwuzh
 * @create: 2020-08-15 14:47
 **/
public class AndroidUITasks {
    /**
     * 单击Save 按钮
     * @param driver
     * @throws Exception
     */
    public static void clickSaveBtn(AppiumDriver driver) throws Exception {
        List<WebElement> btnEles = findObjectsByXPath("//android.widget.Button[contains(@resource-id,'contactSaveButton')]",driver);
        btnEles.get(0).click();
        Log.info("Click ' Save' button!");
    }

    /**
     * 单击Add Contact 按钮
     * @param driver
     * @throws Exception
     */
    public static void clickAddContactBtn(AppiumDriver driver) throws Exception {
        List<WebElement> btnEles = findObjectsByXPath("//android.widget.Button[contains(@resource-id,'addContactButton')]",driver);
        btnEles.get(0).click();
        Log.info("Click ' Add Contact' button!");
    }

    /**
     * 输入 联系人姓名
     * @param driver
     * @throws Exception
     */
    public static void inputContactName(AppiumDriver driver) throws Exception {
        List<WebElement> inEles = findObjectsByXPath("//android.widget.EditText[contains(@resource-id,'contactNameEditText')]",driver);
        inEles.get(0).sendKeys("A San");
        Log.info("Input 'A San' into the Contact Name!");
    }

    /**
     * 输入 Email
     * @param driver
     * @throws Exception
     */
    public static void inputEmail(AppiumDriver driver) throws Exception {
        List<WebElement> inEles = findObjectsByXPath("//android.widget.EditText[contains(@resource-id,'contactEmailEditText')]",driver);
        inEles.get(0).sendKeys("asan@example.com");
        Log.info("Input Email!");
    }

    /**
     * 单击问题提示窗口中的 OK 按钮
     * @param driver
     * @throws Exception
     */
    public static void clickOKBtnOnConfirmUI(AppiumDriver driver) throws Exception {
        List<WebElement> btnEles = findObjectsByXPath("//android.widget.Button[contains(@resource-id,'android:id/button1')]",driver,2);
        btnEles.get(0).click();
        Log.info("Click the OK button");
    }

    /**
     * 找元素  ， 固定最长等待15秒
     * @param xpath
     * @param driver
     * @return
     * @throws UINotFoundException
     */
    private static List<WebElement> findObjectsByXPath(String xpath, AppiumDriver driver) throws UINotFoundException {
        return findObjectsByXPath(xpath, driver, 15);
    }
    /**
     * 找元素
     * @param xpath  元素的xpath定位
     * @param driver  appium driver
     * @param waitMax  最长等待时间
     * @return
     * @throws UINotFoundException
     */
    private static List<WebElement> findObjectsByXPath(String xpath, AppiumDriver driver, int waitMax) throws UINotFoundException{
        int size = 0;
        List<WebElement> objs = null;
        long start = System.currentTimeMillis();
        long now = System.currentTimeMillis();
        while (((now - start) < waitMax * 1000) && (size == 0)) {
            Tools.wait(1);
            objs = driver.findElementsByXPath(xpath);
            if (objs != null){
                size = objs.size();

            }
            now = System.currentTimeMillis();
        }
        if (size == 0) {
            throw new UINotFoundException();
        }else {
            return objs;
        }
    }
}