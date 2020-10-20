package qa.java_cource.mantis.appmanager;

import org.openqa.selenium.By;

public class UserHelper extends HelperBase {

  public UserHelper(ApplicationManager app) {
    super(app);
  }

  public void initiatePasswordReset() {
    click(By.xpath("//input[@value='Reset Password']");
  }

  public void chooseUser() {
    click(By.linkText("AnnaB");
  }

  public void gotoManageUsersPage() {
    wd.get("http://localhost/mantis/manage_user_page.php");
  }
}
