package qa.java_cource.addressbook.tests;

import org.testng.annotations.Test;
import qa.java_cource.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase{

  @Test
  public void testGroupModification () {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test_1", "test_2", "test_3"));
    app.getGroupHelper().submitGroupModifiction();
    app.getGroupHelper().returnToGroupPage();
  }
}
