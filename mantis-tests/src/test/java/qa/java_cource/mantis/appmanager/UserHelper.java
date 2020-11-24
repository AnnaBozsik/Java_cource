package qa.java_cource.mantis.appmanager;

import org.apache.http.client.methods.HttpPost;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.subethamail.wiser.Wiser;
import qa.java_cource.mantis.model.UserData;


public class UserHelper extends HelperBase {

  public UserHelper (WebDriver wd) {
    super(wd);
  }

  public void gotoManageUsersPage() {
    wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
  }

  public void selectUserById(int id) {
    wd.findElement(By.xpath("//a[contains(@href,'manage_user_edit_page.php?user_id=" + id + "')]")).click();
  }

  public void initiatePasswordReset() {
    wd.findElement(By.xpath("//input[@value='Reset Password']")).click();
  }

  public void initiatePasswordChange(UserData user) {
    selectUserById(user.getId());
    initiatePasswordReset();
  }

  public void changePasswordFromEmail(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click((By.cssSelector("input[value='Update User']")));
  }
}


