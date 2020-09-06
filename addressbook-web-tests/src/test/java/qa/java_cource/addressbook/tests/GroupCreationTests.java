package qa.java_cource.addressbook.tests;

import org.testng.annotations.*;
import qa.java_cource.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().createGroup(new GroupData("test_1", null, null));
  }
}
