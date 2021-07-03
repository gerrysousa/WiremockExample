package br.com.schoolservice.repository;

import br.com.schoolservice.model.School;
import java.util.List;
import org.springframework.data.repository.CrudRepository;


public interface SchoolRepository extends CrudRepository<School, Long> {
  List<School> findByNameIgnoreCaseContaining(String name);

}
