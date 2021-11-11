package test;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Login extends TestBase{
    @BeforeMethod
    public void preConditions() {
        if (app.getUser().isLogged()) {
            app.getUser().logOut();
        }

    }
    @Test
    public void testLogin() throws InterruptedException {
        User user = new User().withEmail("gorbunov.nk@mail.ru").withPassword("1988911444");
        logger.info("Test Login Positive 1 "+user.getEmail() +" "+user.getPassword());
        app.getUser().initLogin();
        // app.getUser().fillLoginForm("juliakliot.jk@gmail.com", "misha240613");
        app.getUser().fillLoginForm(user);
        Thread.sleep(15000);
        app.getUser().submitLogin();
        app.getUser().isLoggedSuccess();
        Assert.assertTrue(app.getUser().isLoggedSuccess());

        //Assert.assertTrue(app.getUser().isLoggedSuccess());
        //Assert.assertTrue(app.getUser().returnToHomePage();
    }
    @Test
    public void negativeLoginwithoutPassword() throws InterruptedException {
        User user = new User().withEmail("gorbunov.nk@mail.ru").withPassword("");
        logger.info("Test Login Neganive 1 "+user.getEmail() +" "+user.getPassword());
        app.getUser().initLogin();
        //app.getUser().fillLoginForm("juliakliot.jk@gmail.com", "");
        app.getUser().fillLoginForm(user);
        app.getUser().submitLogin();
    }
    @Test
    public void negativeLoginIncorrectEmail() throws InterruptedException {

        app.getUser().initLogin();
        app.getUser().fillLoginForm("juliakliot.jkgmail.com", "");
    }

    @Test
    public void negativeLoginwithoutEmail() throws InterruptedException {
        app.getUser().initLogin();
        app.getUser().fillLoginForm("", "");


    }



    @AfterMethod
    public void postCondition(){
        app.getUser().clickOnLoginButton();
    }
}
