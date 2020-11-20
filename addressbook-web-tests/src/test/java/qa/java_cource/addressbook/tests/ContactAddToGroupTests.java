package qa.java_cource.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.java_cource.addressbook.model.ContactData;
import qa.java_cource.addressbook.model.Contacts;
import qa.java_cource.addressbook.model.GroupData;
import qa.java_cource.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContactAddToGroupTests extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Anna").withLastName("Bozsik")
              .withMobilePhone("222").withEmail("ab@gmail.com"));
      app.goTo().gotoHome();
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test_1"));
      app.goTo().gotoHome();
    }
  }

  @Test
  public void testContactAddToGroup() {
    Contacts before = app.db().contacts();
    Groups groups = app.db().groups();
    for (ContactData contact : before) {
      System.out.println(contact);
      if (groups == null) {
        app.contact().selectContactById(contact.getId());
        app.contact().addToGroup();
      }
    }
    for (GroupData group : groups) {
      System.out.println(group);
    }
  }
}
    