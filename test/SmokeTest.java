import com.thoughtworks.selenium.*;
import java.util.Properties;
import java.io.FileInputStream;
 
public class SmokeTest extends SeleneseTestCase {
    public void setUp() throws Exception {
        String apiKey = System.getenv("TESTINGBOT_API_KEY");
        String apiSecret = System.getenv("TESTINGBOT_API_SECRET");
        if (apiKey == null || apiSecret == null){
            System.err.println("No API credentials found for TestingBot.com.");
            System.err.println("Please supply your API key and secret with the variables TESTINGBOT_API_KEY and TESTINGBOT_API_SECRET");
        }
        DefaultSelenium selenium = new DefaultSelenium(
                "hub.testingbot.com",
                4444,
                "",
                "http://testingbot.com/");
        selenium.start();
        this.selenium = selenium;
    }
    public void tearDown() throws Exception {
        if (selenium != null) {
            selenium.stop();
            selenium = null;
        }
    }
    
    public void testDemo() throws Exception {
        this.selenium.open("/");
        assertTrue(this.selenium.isTextPresent("TestingBot"));
    }
}
