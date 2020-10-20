package qa.java_cource.mantis.tests;

import org.junit.Test;
import org.openqa.selenium.By;

public class PasswordChangeTests extends TestBase {

  @Test
  public void testChangePassword() throws Exception {
    gotoManageUsersPage();
    chooseUser();
    initiatePasswordReset();
  }

  private void initiatePasswordReset() {
    app.findElement(By.xpath("//input[@value='Reset Password']")).click();
  }

  private void chooseUser() {
    app.findElement(By.linkText("AnnaB")).click();
  }

  private void gotoManageUsersPage() {
    app.get("http://localhost/mantis/manage_user_page.php");
  }
}
