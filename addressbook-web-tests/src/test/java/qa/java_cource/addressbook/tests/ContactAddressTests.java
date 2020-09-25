package qa.java_cource.addressbook.tests;

import org.testng.annotations.Test;
import org.testng.annotations.*;
import qa.java_cource.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.stream.Collectors;


public class ContactAddressTests extends TestBase {

  @Test
  public void testContactAddresses () {
    app.goTo().gotoHome();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllAddresses(), equalTo(mergeAddresses(contactInfoFromEditForm)));
  }

  private String mergeAddresses (ContactData contact) {
    return Arrays.asList(contact.getAddress(), contact.getAddress2())
            .stream().filter((s) -> ! s.equals("")).collect(Collectors.joining("\n"));
  }
}
