package qa.java_cource.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import qa.java_cource.addressbook.model.ContactData;
import qa.java_cource.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper (WebDriver wd) {
    super(wd);
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void submitNewContactForm() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contactData/*, boolean creation*/) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("email"), contactData.getEmail());

    /*if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }*/
  }

  public void gotoEditPage() {
    click(By.linkText("add new"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void initContactDeletion() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void closeConfirmationWindow() {
    wd.switchTo().alert().accept();
    wd.findElement(By.cssSelector("div.msgbox"));
  }

  public void initContactEditing(int id) {
    wd.findElement(By.xpath("//a[contains(@href,'edit.php?id=" + id + "')]")).click();
  }

  public void initContactUpdate() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }

  public void create(ContactData contact) {
    gotoEditPage();
    fillContactForm(contact);
    submitNewContactForm();
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    initContactEditing(contact.getId());
    fillContactForm(contact);
    initContactUpdate();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    initContactDeletion();
    closeConfirmationWindow();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.xpath("//img[@alt='Edit']")).size();
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows) {
      String lastName = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String firstName = row.findElement(By.cssSelector("td:nth-child(3)")).getText();
      int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
    }
    return contacts;
  }
}

