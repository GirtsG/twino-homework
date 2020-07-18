package eu.twino.homework.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class IpApiServiceTest {

    @Autowired
    private IpApiService service;

    @Test
    void testCountryLvByIpAddress() {
        String countryLv = service.getCountryFromIp("212.70.163.70");
        assertThat(countryLv).isEqualTo("LV");
    }

    @Test
    void testCountryEeByIpAddress() {
        String countryEe = service.getCountryFromIp("195.80.123.145");
        assertThat(countryEe).isEqualTo("EE");
    }

    @Test
    void testNonExistingIp() {
        assertThat(service.getCountryFromIp("asdf")).isEqualTo("LV");
    }
}