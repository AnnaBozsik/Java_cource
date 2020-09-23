package qa.java_cource.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.java_cource.addressbook.model.ContactData;
import qa.java_cource.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Anna").withLastName("Bozsik")
              .withHomePhone("111").withMobilePhone("222").withWorkPhone("333")
              .withEmail("ann.bozsik@gmail.com").withGroup("test_1"));
      app.goTo().gotoHome();
    }
  }

  @Test
  public void testContactDeletion() {
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().gotoHome();
    Contacts after = app.contact().all();
    assertEquals(after.size(),before.size() - 1);
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}


