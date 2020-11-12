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
    Users before = app.db().users();
    long now = System.currentTimeMillis();
    String password = String.format("password", now);
    String user = "AnnaB";
    String email = "ab@gmail.com";
    app.user().gotoManageUsersPage();
    app.user().chooseUser();
    app.user().initiatePasswordReset();
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 100000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.user().changePassword(confirmationLink, password);
    Users after = app.db().users();
    assertNotEquals(after, (before.stream()
            .map((u) -> new UserData().withId(u.getId()).withUserName(u.getUsername())
                    .withEmail(u.getEmail()).withPassword(u.getPassword()))
                    .collect(Collectors.toSet())));
    HttpSession session = app.newSession();
    assertTrue(app.newSession().login(user, password));
    assertTrue(session.isLoggedInAs("AnnaB"));
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