package qa.java_cource.mantis.appmanager;

import org.openqa.selenium.By;
import org.subethamail.wiser.Wiser;


public class UserHelper extends HelperBase {

  private ApplicationManager app;

  public UserHelper(ApplicationManager app) {
    super(app);
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

}


