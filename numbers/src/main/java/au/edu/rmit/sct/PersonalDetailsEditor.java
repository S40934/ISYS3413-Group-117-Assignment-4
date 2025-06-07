package au.edu.rmit.sct;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PersonalDetailsEditor {

    private String filename;
    private Person person;
    
    public PersonalDetailsEditor(String filename){
        this.filename = filename;
        this.person = readPersonFile(filename); // the given file is assumed to have no errors as writing is a part of the Person class
    }

    public void setFilename(String filename){
        this.filename = filename;
    }

    public boolean updatePersonalDetails(String personID, String firstName, String lastName, String address, String birthdate){
        boolean result = true; // assume success by default

        // System.out.println("READING PERSON FILE");
        // System.out.println(filename);
        // System.out.println("THIS PERSON IS");
        // System.out.println(age);
        // System.out.println("YEARS OLD");
        // System.out.println("AND IS");
        // System.out.println(this.person.getFirstName());
        // System.out.println(this.person.getLastName());
        // System.out.println(this.person.getAddress());
        // System.out.println(this.person.getBirthdate());
        
        //CONDITION 2: no other change on birthday change (single detail change)
        // check if different birthday param, write and return early if so
        // structured first as it takes precedence
        System.out.println(birthdate);
        System.out.println(this.person.getBirthdate());
        if (! (this.person.getBirthdate().equals(birthdate)) ){
            this.person.setBirthdate(birthdate);

            if (this.person.addPerson()){   System.out.println("INVALID NEW DETAILS, NO CHANGES MADE TO FILE"); } // check if invalid birthday
            else {  this.person.writeToFile(filename);    } // write to file if valid
            return false; // condition 2 failed
        }
        
        // CONDITION 1: address change only on 18+ aged person
        // implement person date parsing and comparison to current date - 18 years using java.time LocalDateˇ
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birthDateToComp = LocalDate.parse(this.person.getBirthdate(), formatter);
        LocalDate today = LocalDate.now();
        int age = Period.between(birthDateToComp, today).getYears();
        System.out.println("this person is");
        System.out.println(age);
        if (age >= 18) {    this.person.setAddress(address);   }
        else {  result = false; }
       

        this.person.setFirstName(firstName);
        this.person.setLastName(lastName);
        
        //CONDITION 3: if 1st char of ID is even, ID cannot be canged
            // check first char of ID, if even, skip ID change
            // return new number to indicate status

        String newID = personID;

        String firstStringID = String.valueOf(this.person.getPersonID().charAt(0));
        if (isInteger(firstStringID) && Integer.parseInt(firstStringID) % 2 == 0){ // checking if first char is even integer
            newID = this.person.getPersonID(); // keep same ID if even first number
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~FAILED C3~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");    
            result = false;
        }

        this.person.setPersonID(newID); // change ID based on condition

            // change ID if not even first char\
        this.person.addPerson();
        if (this.person.addPerson()){
            System.out.println("INVALID NEW DETAILS, NO CHANGES MADE TO FILE");
            return false; //  invalid new details
        }

        // this.person.writeToFile(filename);
        if (!result){
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~FAILED~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
        else {System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~SUCCESS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");}
        return result;
    }

    private Person readPersonFile(String filename){
        // file reading here
        // create person object based on file contents
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            myReader.nextLine();
            myReader.next(); String personID = myReader.next(); myReader.nextLine(); // read personID line
            // System.out.println("---------------------------I HAVE READ THE ID------------------");
            // System.out.println(personID);
            myReader.next(); String firstName = myReader.next(); String lastName = myReader.next(); // read name line
            myReader.next(); String address = myReader.nextLine(); address = address.substring(1); // read address line
            myReader.next(); myReader.next(); myReader.next(); String birthdate = myReader.next(); myReader.nextLine(); // read birthdate line

            myReader.close();
            this.person = new Person(personID, firstName, lastName, address, birthdate);
        } catch (FileNotFoundException e) {
            System.out.println("Wrong filename provided.");
            System.out.println(filename);
        }

        return person;
    }

    public boolean isInteger (String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}