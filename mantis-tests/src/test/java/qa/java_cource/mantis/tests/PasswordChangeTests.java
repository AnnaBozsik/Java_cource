package qa.java_cource.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.java_cource.mantis.appmanager.HttpSession;
import qa.java_cource.mantis.model.MailMessage;
import qa.java_cource.mantis.model.UserData;
import qa.java_cource.mantis.model.Users;
import ru.lanwen.verbalregex.VerbalExpression;
import java.util.List;
import java.util.stream.Collectors;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

public class PasswordChangeTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testChangePassword() throws Exception {
    long now = System.currentTimeMillis();
    String password = String.format("password", now);
    Users before = app.db().users();
    for (UserData currentUser : before) {
      System.out.println(currentUser);
      UserData userToChangePassword = before.iterator().next();
      UserData user = new UserData()
              .withId(userToChangePassword.getId()).withUserName(userToChangePassword.getUsername())
              .withEmail(userToChangePassword.getEmail()).withPassword(userToChangePassword.getPassword());
      System.out.println(user.getUsername());
      if (user.getUsername() == "administrator") {
        System.out.println("Not allowed");
      } else {
        app.user().gotoManageUsersPage();
        app.user().initiatePasswordChange(user);
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 100000);
        String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());
        app.user().changePasswordFromEmail(confirmationLink, password);
        Users after = app.db().users();
        assertNotEquals(after, (before.stream()
                .map((u) -> new UserData().withId(u.getId()).withUserName(u.getUsername())
                        .withEmail(u.getEmail()).withPassword(u.getPassword()))
                .collect(Collectors.toSet())));
        HttpSession session = app.newSession();
        assertTrue(app.newSession().login(user.getUsername(), password));
        assertTrue(session.isLoggedInAs(user.getUsername()));
      }
    }
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}