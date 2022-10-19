package WMT.Vanderbilt;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.io.IOException;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.offset.ElementOption;

public class TestCase_008_SplashScreen extends base {
	
	@Test
	public void TestCase_005_SplashScreen() throws IOException, InterruptedException
	{

	AndroidDriver<AndroidElement> driver = runCapabilities("mwcMobileAndroidApp",true);
	TouchAction t = new TouchAction(driver);
	Thread.sleep(30000);
	
	driver.quit();

	} 

}
