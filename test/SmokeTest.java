import com.thoughtworks.selenium.*;
import org.junit.*;
import com.testingbot.*;

public class SmokeTest extends TestingBotTestCase {
  public void setUp() throws Exception {
    TestingBotSelenium selenium = new TestingBotSelenium(
            "hub.testingbot.com",
            4444,
            "firefox",
            "http://www.google.com/");

    this.selenium = selenium;
    selenium.start("version=10;platform=WINDOWS;screenshot=false");
    
    // print sessionID in output so that our Jenkins plugin maps the sessionID to videos/screenshots
    System.out.println("TestingBotSessionID=" + this.selenium.getEval("selenium.sessionId"));
  }

  public void testGoogle() throws Exception {
    this.selenium.open("/");
    assertEquals("Google", this.selenium.getTitle());
  }

  public void tearDown() throws Exception {
    this.selenium.stop();
  }
}
