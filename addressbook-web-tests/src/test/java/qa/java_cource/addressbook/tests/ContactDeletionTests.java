package qa.java_cource.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.java_cource.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion() {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Anna", "Bozsik", "925-961-6101", "ann.bozsik@gmail.com", "test_1"));
      app.getNavigationHelper().gotoHome();
    }
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactDeletion();
    app.getContactHelper().closeConfirmationWindow();
    app.getNavigationHelper().gotoHome();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after,before - 1);
  }
}


