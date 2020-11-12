package qa.java_cource.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.subethamail.wiser.Wiser;


public class UserHelper extends HelperBase {

  public UserHelper (WebDriver wd) {
    super(wd);
  }

  public void gotoManageUsersPage() {
    wd.get("http://localhost/mantis/manage_user_page.php");
  }

  public void chooseUser() {
    wd.findElement(By.linkText("AnnaB")).click();
  }

  public void initiatePasswordReset() {
    wd.findElement(By.xpath("//input[@value='Reset Password']")).click();
  }

  public void changePassword(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click((By.cssSelector("input[value='Update User']")));
  }
}


