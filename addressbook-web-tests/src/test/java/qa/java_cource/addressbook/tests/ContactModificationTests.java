package qa.java_cource.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.java_cource.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test(enabled = false)
  public void testContactModification() {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Anna", "Bozsik", "925-961-6101", "ann.bozsik@gmail.com", "test_1"));
      app.goTo().gotoHome();
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().initContactEditing();
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Anna", "Bozsik", "925-961-6101", "ann.bozsik@gmail.com", null);/*, false*/
    app.getContactHelper().fillContactForm(contact);
    app.getContactHelper().initContactUpdate();
    app.goTo().gotoHome();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
