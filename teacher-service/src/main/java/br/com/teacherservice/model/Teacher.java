package br.com.teacherservice.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Entity
public class Teacher extends AbstractEntity{
  @NotEmpty(message = "Field 'name' is mandatory!")
  private  String name;
  @NotEmpty
  @Email
  private String email;
  @ElementCollection
  private List<String> schools;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<String> getSchools() {
    return schools;
  }

  public void setSchools(List<String> schools) {
    this.schools = schools;
  }

}
