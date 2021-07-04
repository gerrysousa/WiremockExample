package br.com.teacherservice.services;

import br.com.teacherservice.model.School;
import br.com.teacherservice.proxies.SchoolProxy;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SchoolService {

  private SchoolProxy schoolProxy;

  public SchoolService(SchoolProxy schoolProxy) {
    this.schoolProxy = schoolProxy;
  }

  public List<School> getSchools(final List<String> schoolIds) {
    return schoolProxy.getSchoolById(schoolIds);
  }
}