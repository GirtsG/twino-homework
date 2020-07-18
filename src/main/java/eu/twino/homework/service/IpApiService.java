package eu.twino.homework.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
class IpApiService {

    private static final Collection COUNTRIES = Set.of(Locale.getISOCountries());
    private final RestTemplate restTemplate;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${homework.ip.api.country.url}")
    private String apiCountryUrl;

    @Value("${homework.ip.api.country.code.default:LV}")
    private String defaultCountry;

    String getCountryFromIp(String ipAddress) {
        return Optional.ofNullable(requestCountryCodeFromApi(ipAddress))
                .filter(this::isValidCountryCode)
                .orElse(defaultCountry.toUpperCase());
    }

    private String requestCountryCodeFromApi(String ipAddress) {
        try {
            return restTemplate.getForObject(buildUrl(ipAddress), String.class);
        } catch (RestClientException e) {
            logger.info(format("Request to resource %s failed.", buildUrl(ipAddress)), e);
            return null;
        }
    }

    private URI buildUrl(String ipAddress) {
        return UriComponentsBuilder.fromHttpUrl(apiCountryUrl)
                .buildAndExpand(ipAddress)
                .toUri();
    }

    private boolean isValidCountryCode(String countryCode) {
        return COUNTRIES.contains(countryCode);
    }
}
