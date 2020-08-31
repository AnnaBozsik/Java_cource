package qa.java_cource.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import qa.java_cource.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper (FirefoxDriver wd) {
    super(wd);
  }

  public void returnToHomePage() {
    click(By.linkText("home"));
  }

  public void submitNewContactForm() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("email"), contactData.getEmail());
  }
  public void gotoEditPage() {
    click(By.linkText("add new"));
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void initContactDeletion() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void closeConfirmationWindow() {
    wd.switchTo().alert().accept();
  }

  public void initContactEditing() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void initContactUpdate() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }
}

