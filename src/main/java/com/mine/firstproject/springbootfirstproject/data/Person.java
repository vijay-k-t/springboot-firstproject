// package com.mine.firstproject.springbootfirstproject.data;


// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.Id;
// import javax.persistence.Table;
// import javax.persistence.Transient;

// @Entity
// @Table(name="PERSON")
// public class Person {

//     @Id
//     @GeneratedValue
//     @Column(name="ID")
//     private int id;

//     @Column(name="LASTNAME")
//     private String lastName;

//     @Column(name="FIRSTNAME")
//     private String firstName;

//     @Transient
//     private String name;

//     public String getFirstName() {
//         return firstName;
//     }

//     public void setFirstName(String firstName) {
//         this.firstName = firstName;
//     }

//     public int getId() {
//         return id;
//     }

//     public void setId(int id) {
//         this.id = id;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public String getName() {
//         return name;
//     }

//     public String getLastName() {
//         return lastName;
//     }

//     public void setLastName(String lastName) {
//         this.lastName = lastName;
//     }

//     public void concatenateName(){
//         this.setName(this.firstName+" "+this.lastName);
//     }
// }
