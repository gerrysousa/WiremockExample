package br.com.teacherservice.repository;

import br.com.teacherservice.model.Teacher;
import java.util.List;
import org.springframework.data.repository.CrudRepository;


public interface TeacherRepository extends CrudRepository<Teacher, Long> {
  List<Teacher> findByNameIgnoreCaseContaining(String name);

}
