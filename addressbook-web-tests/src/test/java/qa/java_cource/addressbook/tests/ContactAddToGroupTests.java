package qa.java_cource.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.java_cource.addressbook.model.ContactData;
import qa.java_cource.addressbook.model.Contacts;
import qa.java_cource.addressbook.model.GroupData;
import qa.java_cource.addressbook.model.Groups;

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
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    ContactData selectedContact = contacts.iterator().next();
    ContactData contact = new ContactData()
            .withId(selectedContact.getId()).withFirstName("Anna").withLastName("Bozsik")
            .withMobilePhone("222").withEmail("ann.bozsik@gmail.com").inGroup(groups.iterator().next());

    app.contact().selectContactById(contact.getId());
    if (app.db().groups().size() == 1) {
      app.contact().addToGroup();
    } else {
      Select selectGroup = new Select(app.contact().selectGroupFromDropDown());
      selectGroup.selectByVisibleText(app.db().groups().iterator().next().getName());
      app.contact().addToGroup();
    }


  }
}
    