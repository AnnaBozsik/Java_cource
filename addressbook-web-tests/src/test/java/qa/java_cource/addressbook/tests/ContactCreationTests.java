package qa.java_cource.addressbook.tests;

import org.testng.annotations.*;
import qa.java_cource.addressbook.model.ContactData;
import qa.java_cource.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

  @Test
  public void testNewContactCreation() throws Exception {
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/contact_photo.png");
    ContactData contact = new ContactData().withFirstName("Anna").withLastName("Bozsik")
            .withMobilePhone("222").withPhoto(photo);
    app.contact().create(contact);
    app.goTo().gotoHome();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testBadNewContactCreation() throws Exception {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstName("A'").withLastName("Bozsik").withMobilePhone("222").withEmail("ann.bozsik@gmail.com").withGroup("test_1");
    app.contact().create(contact);
    app.goTo().gotoHome();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }
}
