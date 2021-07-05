package br.com.schoolservice.endpoint;

import br.com.schoolservice.error.ResourceNotFoundException;
import br.com.schoolservice.model.School;
import br.com.schoolservice.repository.SchoolRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
@RequestMapping("schools")
public class SchoolEndpoint {
  private final SchoolRepository schoolRepository;

  @Autowired
  public SchoolEndpoint(SchoolRepository schoolRepository) {
    this.schoolRepository = schoolRepository;
  }

  @GetMapping
  public ResponseEntity<?> listAll(@RequestParam(required = false) List<Long> id) {
    if (id!=null){
      return new ResponseEntity<>(schoolRepository.findAllById(id), HttpStatus.OK);

    }
    return new ResponseEntity<>(schoolRepository.findAll(), HttpStatus.OK);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<?> getSchoolById(@PathVariable("id") Long id) {
    verifyIfSchoolExists(id);
    School school = schoolRepository.findById(id).get();
    return new ResponseEntity<>(school, HttpStatus.OK);
  }

  @GetMapping(path = "/findByName/{name}")
  public ResponseEntity<?> getSchooltByName(@PathVariable String name) {
      return new ResponseEntity<>(schoolRepository.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
  }

  @PostMapping
  @Transactional(rollbackFor = Exception.class)
  public ResponseEntity<?> save(@Valid @RequestBody School school) {
       return new ResponseEntity<>(schoolRepository.save(school), HttpStatus.CREATED);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    verifyIfSchoolExists(id);
    schoolRepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<?> update(@RequestBody School school) {
    verifyIfSchoolExists(school.getId());
    schoolRepository.save(school);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  private void verifyIfSchoolExists(Long id){
    if (!schoolRepository.findById(id).isPresent()){
      throw new ResourceNotFoundException("School not found for ID: "+id);
    }
  }
}
