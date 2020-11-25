package qa.java_cource.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.java_cource.addressbook.model.ContactData;
import qa.java_cource.addressbook.model.Contacts;
import qa.java_cource.addressbook.model.GroupData;
import qa.java_cource.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactDeleteFromGroupTests extends TestBase {

  public ContactData contact;
  public GroupData group ;

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

    if (app.db().contacts().size() != 0 && app.db().groups().size() != 0) {
      Contacts contacts = app.db().contacts();
      for (ContactData eachContact : contacts) {
        if (eachContact.getGroups().stream().count() != 0) {
          contact = eachContact;
          group = eachContact.getGroups().iterator().next();
          break;
        }
      }
    }
  }

    @Test
    public void testContactDeleteFromGroup() {
    Contacts before = app.db().contacts();
    contact = before.stream().filter(c -> (c.getId() == contact.getId())).collect(Collectors.toSet()).iterator().next();
    Groups groupsForContactBefore = contact.getGroups();
    app.goTo().gotoHome();
    app.contact().removeFromGroup(contact, group);
    Contacts after = app.db().contacts();
    contact = after.stream().filter(c -> (c.getId() == contact.getId())).collect(Collectors.toSet()).iterator().next();
    Groups groupsAfter = contact.getGroups();
    assertThat(groupsAfter, equalTo(groupsForContactBefore.without(group)));
  }
}

