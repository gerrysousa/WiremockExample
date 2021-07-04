package br.com.teacherservice.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class School extends AbstractEntity{
  @NotEmpty(message = "Field 'name' is mandatory!")
  private  String name;
  @NotEmpty
  private String abbreviation;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAbbreviation() {
    return abbreviation;
  }

  public void setAbbreviation(String email) {
    this.abbreviation = email;
  }
}

