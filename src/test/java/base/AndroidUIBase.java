package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.Log;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @program: myAppium
 * @description:
 * @author: bjwuzh
 * @create: 2020-08-15 14:28
 **/
public class AndroidUIBase {
    private String appiumURL = "http://127.0.0.1:4723/wd/hub"; //appium服务器URL地址
    private String platFormName = "Android"; //平台名称
    private String deviceName = "Android Emulator"; //设备名称
    private String appPackage = "com.example.android.contactmanager";//安卓应用包名
    private String appActivity = ".ContactManager"; //安卓activity类
    private String androidUid = Tools.getAndroidDeviceId(); //安卓设备Uid(不能是假的)
    private String platformVersion = Tools.getDeviceRelease(androidUid); //安卓设备平台版本

    protected AppiumDriver driver;
    protected String testcaseName = "";

    @BeforeEach
    public void beforeEach() throws MalformedURLException {
        //安卓apk文件路径
        File classpathRoot = new File(System.getProperty("user.dir"));//项目所在路径G:\huogewozi\jenkins\myAppium
        File appDir = new File(classpathRoot,"apps");//apps路径：G:\huogewozi\jenkins\myAppium\apps
        File app = new File(appDir,"ContactManager.apk");//app路径：G:\huogewozi\jenkins\myAppium\apps\ContactManager.apk
        String absolutePath = app.getAbsolutePath();
        Log.info("apk full path : " + absolutePath);

        //设置Desired Capabilities相关参数
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("platformName", platFormName);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("platformVersion", platformVersion);

        //设置安卓系统的uid
        capabilities.setCapability("udid", androidUid);

        //设置apk文件
        capabilities.setCapability("app", absolutePath);

        //设置app的主包名和主类名
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);
        capabilities.setCapability("unicodeKeyboard", false);
        capabilities.setCapability("resetKeyboard", false);
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("autoAcceptAlerts", true);
        capabilities.setCapability("automationName", "UiAutomator2");

        driver = new AndroidDriver(new URL(appiumURL), capabilities);
        Log.info("Implement driver instance ...");
    }
    @AfterEach
    public void afterEach() {
        Log.info("Automaion test " + testcaseName + "finished.");
        if (driver == null) {
            return;
        }
        driver.quit();
        Tools.uninstallPackage(androidUid);
    }
}