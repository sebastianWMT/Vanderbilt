package WMT.Vanderbilt;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class base {
	
	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement> driver;
	
	public AppiumDriverLocalService startServer()
	{
		boolean flag=checkIfServerIsRunnning(4723);
		if(!flag) {
			
			service=AppiumDriverLocalService.buildDefaultService();
			service.start();
		}

			return service;
	}
	
	public static boolean checkIfServerIsRunnning(int port) {
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch(IOException e) {
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}
	
	public static void startEmulator() throws IOException, InterruptedException {
		
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\startEmulator.bat");
		Thread.sleep(9000);
	}
	
	
	public AndroidDriver<AndroidElement> runCapabilities(String appName,Boolean cloud) throws IOException, InterruptedException
		{
			if(cloud) {
				return cloudCapabilities(appName);
			}
			{
				return capabilities(appName);
			}
		}
	
	// -------------------------------------Cloud-----------------------------------------------
	public static AndroidDriver<AndroidElement> cloudCapabilities(String appName) throws IOException, InterruptedException
	{
	
		FileInputStream  fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\WMT\\Vanderbilt\\global.properties");
		Properties prop=new Properties();
		prop.load(fis);
		
		DesiredCapabilities cap = new DesiredCapabilities();
		
		String device=System.getProperty("deviceName");
		if(device.contains("emulator")) 
		{
			startEmulator();
			
		}
		 
		
		cap.setCapability("browserstack.user", "wmt7");
		cap.setCapability("browserstack.key", "jop63svHE4oZjzgmxxTR");
		if(appName.equalsIgnoreCase("mwcMobileAndroidApp"))
		{
			cap.setCapability("app", "bs://ee626f9a1717e4485bc1887f754e6cec469f2d34");
		
			
		}
		cap.setCapability("device", device);
		cap.setCapability("os_version", "12.0");
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://hub.browserstack.com/wd/hub"),cap);
		return driver;
		
		
		//Kill server
		//taskkill /F /IM node.exe
		
		// Run specific device
		// mvn test -DdeviceName=Pixel5
		
	}
	
	public static AndroidDriver<AndroidElement> capabilities(String appName) throws IOException, InterruptedException
	{
	
		FileInputStream  fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\WMT\\KentuckyAndroidApp\\global.properties");
		Properties prop=new Properties();
		prop.load(fis);
		
		
		File appDir = new File("src");
		File app = new File(appDir, (String) prop.get(appName));
		
		DesiredCapabilities cap = new DesiredCapabilities();
		
		//String device=(String) prop.get("device");
		String device=System.getProperty("deviceName");
		if(device.contains("emulator")) 
		{
			startEmulator();
			
		}
		
	
		cap.setCapability(MobileCapabilityType.DEVICE_NAME,device);
		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		return driver;
		
		
		//Kill server
		//taskkill /F /IM node.exe
		
		// Run specific device
		// mvn test -DdeviceName=Pixel5
		
	}
	
	
	
	public static void getScreenshote(String s) throws IOException
	{
		File scrfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrfile, new File(System.getProperty("user.dir")+"\\"+s+".png"));
	
		
	}
	
	

}
