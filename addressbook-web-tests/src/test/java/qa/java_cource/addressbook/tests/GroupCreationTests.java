package qa.java_cource.addressbook.tests;

import org.testng.annotations.*;
import qa.java_cource.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("test_1", "test_2", "test_3"));
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupPage();
    app.logout();
  }

}
