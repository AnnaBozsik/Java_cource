package qa.java_cource.addressbook.tests;

import org.junit.Test;
import org.testng.annotations.BeforeMethod;
import qa.java_cource.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

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
  public void testContactPhones() {
    app.goTo().gotoHome();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getHomePhone(), equalTo(contactInfoFromEditForm.getHomePhone()));
    assertThat(contact.getMobilePhone(), equalTo(contactInfoFromEditForm.getMobilePhone()));
    assertThat(contact.getWorkPhone(), equalTo(contactInfoFromEditForm.getWorkPhone()));
  }
}
