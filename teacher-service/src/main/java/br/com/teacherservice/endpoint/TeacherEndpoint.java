package br.com.teacherservice.endpoint;

import br.com.teacherservice.error.ResourceNotFoundException;
import br.com.teacherservice.model.School;
import br.com.teacherservice.model.Teacher;
import br.com.teacherservice.proxies.SchoolProxy;
import br.com.teacherservice.repository.TeacherRepository;
import br.com.teacherservice.services.SchoolService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("teachers")
public class TeacherEndpoint {
  private final TeacherRepository teacherRepository;
  @Autowired
  private SchoolService schoolService;

  private SchoolProxy schoolProxy;

  @Autowired
  public TeacherEndpoint(TeacherRepository teacherRepository) {
    this.teacherRepository = teacherRepository;
  }

  @GetMapping
  public ResponseEntity<?> listAll(@RequestParam(required = false) List<Long> id) {
    if (id!=null){
      return new ResponseEntity<>(teacherRepository.findAllById(id), HttpStatus.OK);

    }
    return new ResponseEntity<>(teacherRepository.findAll(), HttpStatus.OK);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<?> getTeacherById(@PathVariable("id") Long id) {
    verifyIfTeacherExists(id);
    Teacher teacher = teacherRepository.findById(id).get();
    return new ResponseEntity<>(teacher, HttpStatus.OK);
  }

  @GetMapping(path = "/findByName/{name}")
  public ResponseEntity<?> getTeacherByName(@PathVariable String name) {
      return new ResponseEntity<>(teacherRepository.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
  }

  @GetMapping(path = "/{id}/schools")
  public ResponseEntity<?> getSchoolByTeacher(@PathVariable("id") Long id) {
    verifyIfTeacherExists(id);
    Teacher teacher = teacherRepository.findById(id).get();

    List<String> schoolList= teacher.getSchools();

    List<School> schools = schoolService.getSchools(schoolList);

    return new ResponseEntity<>(schools, HttpStatus.OK);
  }

  @PostMapping
  @Transactional(rollbackFor = Exception.class)
  public ResponseEntity<?> save(@Valid @RequestBody Teacher teacher) {
       return new ResponseEntity<>(teacherRepository.save(teacher), HttpStatus.CREATED);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    verifyIfTeacherExists(id);
    teacherRepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<?> update(@RequestBody Teacher teacher) {
    verifyIfTeacherExists(teacher.getId());
    teacherRepository.save(teacher);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  private void verifyIfTeacherExists(Long id){
    if (!teacherRepository.findById(id).isPresent()){
      throw new ResourceNotFoundException("Teacher not found for ID: "+id);
    }
  }
}
