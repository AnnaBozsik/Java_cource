package qa.java_cource.soap;

import com.lavasoft.GeoIPService;
import com.lavasoft.GetIpLocation;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("2600:8802:3301:3b00:e0c0:ecdb:8505:2401");
    System.out.println(ipLocation);
    //assertEquals(ipLocation.getCountryCode(), "USA");
  }
}
