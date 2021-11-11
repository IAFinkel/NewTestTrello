package test;

import manager.ApplManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {
    protected static ApplManager app = new ApplManager();

    @BeforeSuite
    public void setUp() throws InterruptedException {
        app.init();

    }

    @AfterSuite
    public void tearDown() {
        app.stop();

    }

}
