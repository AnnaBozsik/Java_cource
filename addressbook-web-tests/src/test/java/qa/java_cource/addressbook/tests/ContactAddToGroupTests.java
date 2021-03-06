package qa.java_cource.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.java_cource.addressbook.model.ContactData;
import qa.java_cource.addressbook.model.Contacts;
import qa.java_cource.addressbook.model.GroupData;
import qa.java_cource.addressbook.model.Groups;

import java.util.Iterator;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactAddToGroupTests extends TestBase {


  public GroupData group;
  public ContactData contact;

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
      Groups groups = app.db().groups();
      for (GroupData eachGroup : groups) {
        for (ContactData eachContact : contacts) {
          if (!eachContact.getGroups().contains(eachGroup)) {
            group = eachGroup;
            contact = eachContact;
            break;
          }
        }
      }
    }
  }

  @Test
  public void testContactAddToGroup() {
    Contacts before = app.db().contacts();
    contact = before.stream().filter(c -> (c.getId() == contact.getId())).collect(Collectors.toSet()).iterator().next();
    Groups groupsBefore = contact.getGroups();
    app.goTo().gotoHome();
    app.contact().addToGroup(contact, group);
    Contacts after = app.db().contacts();
    contact = after.stream().filter(c -> (c.getId() == contact.getId())).collect(Collectors.toSet()).iterator().next();
    Groups groupsAfter = contact.getGroups();
    assertThat(groupsAfter, equalTo(groupsBefore.withAdded(group)));
  }
}





    