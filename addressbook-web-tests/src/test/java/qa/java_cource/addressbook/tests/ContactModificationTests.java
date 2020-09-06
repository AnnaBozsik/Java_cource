package qa.java_cource.addressbook.tests;

import org.testng.annotations.Test;
import qa.java_cource.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactEditing();
    app.getContactHelper().fillContactForm(new ContactData("An", "Boz", "925-961-6101", "ann.bozsik@gmail.com", null), false);
    app.getContactHelper().initContactUpdate();
    app.getContactHelper().returnToHomePage();
  }
}
