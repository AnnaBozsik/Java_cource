package qa.java_cource.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.java_cource.addressbook.model.ContactData;
import qa.java_cource.addressbook.model.Contacts;
import qa.java_cource.addressbook.model.GroupData;
import qa.java_cource.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


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
    for (ContactData eachContact : contactsBefore) {
      System.out.println("Each line contact is " + eachContact);
      int contactInGroupsBefore = eachContact.getGroups().size();
      System.out.println(contactInGroupsBefore);
      if (groupsBefore.size() > contactInGroupsBefore) {
        app.contact().selectContactById(eachContact.getId());
        app.contact().addToGroup();
        int contactInGroupsAfter = (contactInGroupsBefore + 1);
        assertThat(contactInGroupsBefore, equalTo(contactInGroupsAfter));
        break;
      }
    }
    ContactData newAddedContact = new ContactData().withFirstName("Anna_new").withLastName("Bozsik")
            .withMobilePhone("222").withEmail("ab@gmail.com");
    System.out.println("New created contact " + newAddedContact);
    app.contact().create(newAddedContact);
    app.goTo().gotoHome();
    app.contact().selectContactById(newAddedContact.getId());
    app.contact().addToGroup();

  }
}
    