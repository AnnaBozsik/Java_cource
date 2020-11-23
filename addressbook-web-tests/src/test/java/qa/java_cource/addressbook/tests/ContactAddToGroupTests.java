package qa.java_cource.addressbook.tests;

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
    Contacts contactsBefore = app.db().contacts();
    Groups groupsBefore = app.db().groups();
    for (ContactData contact : contactsBefore) {
      System.out.println(contact);
      System.out.println(contact.getGroups().size());
      if (groupsBefore.size() > contact.getGroups().size()) {
        app.contact().selectContactById(contact.getId());
        app.contact().addToGroup();
        //Contacts after = app.db().contacts();
        //System.out.println(after);

      } else {
        app.contact().create(new ContactData().withFirstName("Anna").withLastName("Bozsik")
                .withMobilePhone("222").withEmail("ab@gmail.com"));
        app.goTo().gotoHome();
        app.contact().selectContactById(contact.getId());
        app.contact().addToGroup();
      }
    }
  }
}
    