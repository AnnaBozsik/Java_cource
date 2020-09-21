package qa.java_cource.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.java_cource.addressbook.model.ContactData;
import java.util.Set;

public class ContactDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Anna").withLastName("Bozsik").withMobile("925-961-6101").withEmail("ann.bozsik@gmail.com").withGroup("test_1"));
      app.goTo().gotoHome();
    }
  }

  @Test
  public void testContactDeletion() {
    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().gotoHome();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(),before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(before,after);
  }
}


