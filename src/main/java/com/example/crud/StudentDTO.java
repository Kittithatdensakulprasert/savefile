package com.example.crud;

public class StudentDTO {
   private String userName;
   private String type;
   private String engName;
   private String email;
   private String faculty;

   public StudentDTO() {
   }

   public String getUserName() {
      return this.userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getEngName() {
      return this.engName;
   }

   public void setEngName(String engName) {
      this.engName = engName;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getFaculty() {
      return this.faculty;
   }

   public void setFaculty(String faculty) {
      this.faculty = faculty;
   }
}