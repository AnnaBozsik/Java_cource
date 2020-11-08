package qa.java_cource.mantis.tests;

import org.junit.Test;

public class PasswordChangeTests extends TestBase {

  @Test
  public void testChangePassword() throws Exception {
    app.user().gotoManageUsersPage();
    app.user().chooseUser();
    app.user().initiatePasswordReset();
  }
}
