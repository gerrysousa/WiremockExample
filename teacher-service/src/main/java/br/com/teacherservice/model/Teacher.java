package br.com.teacherservice.model;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Entity
public class Teacher extends AbstractEntity{
  @NotEmpty(message = "Field 'name' is mandatory!")
  private  String name;
  @NotEmpty
  @Email
  private String email;

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
}
