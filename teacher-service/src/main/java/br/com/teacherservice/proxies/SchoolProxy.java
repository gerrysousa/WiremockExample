package br.com.teacherservice.proxies;

import br.com.teacherservice.model.School;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class SchoolProxy {
  private static final String SCHOOLS = "/v1/schools";
  private RestTemplate restTemplate;
  private String url;

  public SchoolProxy() {
  }

  public SchoolProxy(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Value("${microservices.school.url}")
  public void setUrl(String url) {
    this.url = url;
  }

  public List<School> getSchoolById(List<String> schoolIds) {
    final UriComponentsBuilder builder =
        UriComponentsBuilder.fromUriString(url + SCHOOLS);
    schoolIds.forEach(id -> builder.queryParam("id", id));
    final String urlParametrized = builder.build().encode().toUriString();
    return restTemplate
        .exchange(
            urlParametrized,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<School>>() {})
        .getBody();
  }
}

