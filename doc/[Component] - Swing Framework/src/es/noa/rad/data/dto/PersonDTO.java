package es.noa.rad.data.dto;

 import
  java.util.Date;

 public class PersonDTO {
  private String name;
  private String firstSurname;
  private String secondSurname;
  private Date birthDate;
  private Date deathDate;
  
  public PersonDTO(
         final String nameParam,
         final String firstSurnameParam,
         final String secondSurnameParam,
         final Date birthDateParam,
         final Date deathDateParam) {
   super();
   this.name = nameParam;
   this.firstSurname = firstSurnameParam;
   this.secondSurname = secondSurnameParam;
   this.birthDate = birthDateParam;
   this.deathDate = deathDateParam;
  }
  
  public PersonDTO() {
   super();
   this.name = null;
   this.firstSurname = null;
   this.secondSurname = null;
   this.birthDate = null;
   this.deathDate = null;
  }

  public final String getName() {
   return this.name;
  }

  public final void setName(
         final String nameParam) {
   this.name = nameParam;
  }

  public final String getFirstSurname() {
   return this.firstSurname;
  }

  public final void setFirstSurname(
         final String firstSurnameParam) {
   this.firstSurname = firstSurnameParam;
  }

  public final String getSecondSurname() {
   return this.secondSurname;
  }

  public final void setSecondSurname(
         final String secondSurnameParam) {
   this.secondSurname = secondSurnameParam;
  }

  public final Date getBirthDate() {
   return this.birthDate;
  }

  public final void setBirthDate(
         final Date birthDateParam) {
   this.birthDate = birthDateParam;
  }

  public final Date getDeathDate() {
   return this.deathDate;
  }

  public final void setDeathDate(
         final Date deathDateParam) {
   this.deathDate = deathDateParam;
  }
  
  
 }
