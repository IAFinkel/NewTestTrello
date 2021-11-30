package test;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Login extends TestBase {
    @BeforeMethod(alwaysRun = true)
    public void preConditions() {
        if (app.getUser().isLogged(User.builder().email(app.email()).password(app.password())
        )) {
            app.getUser().logOut();
        }

    }

    @Test
    public void testLogin() throws InterruptedException {
        // User user = new User().withEmail("gorbunov.nk@mail.ru").withPassword("1988911444");
        User user = User.builder().email("gorbunov.nk@mail.ru").password("1988911444")
                .build();
        logger.info("Test Login Positive 1 " + user.getEmail() + " " + user.getPassword());
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

    //    @Test(enabled = false)
//    public void negativeLoginwithoutPassword() throws InterruptedException {
//        User user = new User().withEmail("gorbunov.nk@mail.ru").withPassword("");
//        logger.info("Test Login Neganive 1 "+user.getEmail() +" "+user.getPassword());
//        app.getUser().initLogin();
//        //app.getUser().fillLoginForm("juliakliot.jk@gmail.com", "");
//        app.getUser().fillLoginForm(user);
//        app.getUser().submitLogin();
//    }
    @Test(groups = {"logs_neg"})

    public void negativeLoginIncorrectEmail() throws InterruptedException {

        app.getUser().initLogin();
        app.getUser().fillLoginForm("juliakliot.jkgmail.com", "");
    }

    @Test(groups = {"logs_neg"})

    public void negativeLoginwithoutEmail() throws InterruptedException {
        app.getUser().initLogin();
        app.getUser().fillLoginForm("", "");


    }


    @AfterMethod(alwaysRun = true)
    public void postCondition() {
        app.getUser().clickOnLoginButton();
    }
}
