package qa.java_cource.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.java_cource.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Anna", "Bozsik", "925-961-6101", "ann.bozsik@gmail.com", "test_1"));
      app.getNavigationHelper().gotoHome();
    }
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().selectContact(before - 1);
    app.getContactHelper().initContactEditing();
    app.getContactHelper().fillContactForm(new ContactData("Anna", "Bozsik", "925-961-6101", "ann.bozsik@gmail.com", null)/*, false*/);
    app.getContactHelper().initContactUpdate();
    app.getNavigationHelper().gotoHome();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);
  }
}
