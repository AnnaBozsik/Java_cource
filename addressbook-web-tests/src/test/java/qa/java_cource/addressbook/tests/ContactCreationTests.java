package qa.java_cource.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import qa.java_cource.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{

  @Test(enabled = false)
  public void testNewContactCreation() throws Exception {
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().gotoEditPage();
    ContactData contact = new ContactData("Anna", "Bozsik", "925-961-6101", "ann.bozsik@gmail.com", "test_1");
    app.getContactHelper().createContact(contact);
    app.goTo().gotoHome();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
