package qa.java_cource.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import qa.java_cource.addressbook.model.ContactData;
import java.util.Set;

public class ContactCreationTests extends TestBase{

  @Test
  public void testNewContactCreation() throws Exception {
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withFirstName("Anna").withLastName("Bozsik").withMobile("925-961-6101").withEmail("ann.bozsik@gmail.com").withGroup("test_1");
    app.contact().create(contact);
    app.goTo().gotoHome();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
