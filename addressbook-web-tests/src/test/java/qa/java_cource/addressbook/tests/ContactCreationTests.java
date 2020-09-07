package qa.java_cource.addressbook.tests;

import org.testng.annotations.*;
import qa.java_cource.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

  @Test
  public void testNewContactCreation() throws Exception {
    app.getContactHelper().gotoEditPage();
    app.getContactHelper().createContact(new ContactData("Anna", "Bozsik", "925-961-6101", "ann.bozsik@gmail.com", "test_1"));
    app.getNavigationHelper().gotoHome();
  }
}
