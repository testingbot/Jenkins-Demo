import junit.framework.*;

import org.openqa.selenium.By;  
import org.openqa.selenium.Platform;  
import org.openqa.selenium.WebDriver;  
import org.openqa.selenium.WebElement;  
import org.openqa.selenium.remote.DesiredCapabilities;  
import org.openqa.selenium.remote.RemoteWebDriver;  

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;

import java.net.URL;  
  
public class SmokeTest  extends TestCase {
  
  public static void testOne() {
Map<String, String> env = System.getenv();
for (String envName : env.keySet()) {
    System.out.format("%s=%s%n", envName, env.get(envName));
}
    String build = System.getenv("bamboo_TB_BAMBOO_BUILDNUMBER");
    if (System.getenv("TB_BUILD_NAME") != null) {
	build = System.getenv("TB_BUILD_NAME");
    }
    DesiredCapabilities caps = new DesiredCapabilities();  
    caps.setCapability("browserName", "IE");  
    caps.setCapability("version", "9");  
    caps.setCapability("platform", "WINDOWS");  
    caps.setCapability("build", build);
    String apiKey = System.getenv("TESTINGBOT_KEY");
    String apiSecret = System.getenv("TESTINGBOT_SECRET");
    if (apiKey == null) {
	apiKey = System.getenv("bamboo_TESTINGBOT_KEY");
    }
    if (apiSecret == null) {
        apiSecret = System.getenv("bamboo_TESTINGBOT_SECRET");
    }
    try {
      WebDriver driver = new RemoteWebDriver(new URL("http://" + apiKey + ":" + apiSecret + "@hub.testingbot.com/wd/hub"), caps);  
      driver.get("http://www.google.com/ncr");  
      WebElement element = driver.findElement(By.name("q"));  
    
      element.sendKeys("TestingBot");  
      element.submit();  
    
      System.out.println("TestingBotSessionID=" + ((RemoteWebDriver) driver).getSessionId().toString());  
      driver.quit();
    }
    catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
  }  
} 
