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
        boolean success = true; boolean failed = false;
        
        // CONDITION 1: address change only on 18+ aged person
            // implement person date parsing and comparison to current date - 18 years using java.time LocalDateˇ
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birthDateToComp = LocalDate.parse(this.person.getBirthdate(), formatter);
        LocalDate today = LocalDate.now();
        int age = Period.between(birthDateToComp, today).getYears();
        System.out.println("READING PERSON FILE");
        System.out.println(filename);
        System.out.println("THIS PERSON IS");
        System.out.println(age);
        System.out.println("YEARS OLD");
        System.out.println("AND IS");
        System.out.println(this.person.getFirstName());
        System.out.println(this.person.getLastName());
        System.out.println(this.person.getAddress());
        System.out.println(this.person.getBirthdate());

        if (age > 18) {
            this.person.setAddress(address);
            System.out.println("THIS IS SUPPOSED TO FAIL\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
        }
        else {
            success = false;
            // condition 1 failed
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~FAILED C1~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }

        //CONDITION 2: no other change on birthday change (single detail change)
            // check if different birthday param, write and return early if so
        if (!this.person.getBirthdate().equals(birthdate)){
            this.person.setBirthdate(birthdate);
            this.person.writeToFile(filename);

            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~FAILED C2~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            return failed; // condition 2 failed
        }
        
        //CONDITION 3: if 1st char of ID is even, ID cannot be canged
            // check first char of ID, if even, skip ID change
            // return new number to indicate status
        this.person.setFirstName(firstName);
        this.person.setLastName(lastName);
        
        if (Integer.parseInt(String.valueOf(this.person.getPersonID().charAt(0))) % 2 == 0){ // checking if first char is even integer
            this.person.writeToFile(filename);
            
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~FAILED C3~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");    
            return failed; // condition 3 failed
        }
        this.person.setPersonID(personID);
            // change ID if not even first char
        if (this.person.addPerson()){
            return failed; //  invalid new details
        }
        return success;
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
}