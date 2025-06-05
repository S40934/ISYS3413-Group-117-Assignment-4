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

    public int updatePersonalDetails(String personID, String firstName, String lastName, String address, String birthdate){
        int success = 0;
        
        // TODO CONDITION 1: address change only on 18+ aged person
            // implement person date parsing and comparison to current date - 18 years using java.time LocalDateˇ
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthDate = LocalDate.parse(birthdate, formatter);
        LocalDate today = LocalDate.now();
        int age = Period.between(birthDate, today).getYears();
        if (age < 18) {
            return 1; // code 1 return indicates condition 1 failed
        }

        //TODO CONDITION 2: no other change on birthday change (single detail change)
            // check if different birthday param, write and return early if so
        if (!this.person.getBirthdate().equals(birthdate)){
            this.person.setBirthdate(birthdate);
            this.person.writeToFile(filename);
            return 2; // code 2 return indicates condition 2 failed
        }
        
        //TODO CONDITION 3: if 1st char of ID is even, ID cannot be canged
            // check first char of ID, if even, skip ID change
            // return new number to indicate status
        this.person.setPersonID(personID);
        this.person.setFirstName(firstName);
        this.person.setLastName(lastName);
        this.person.setAddress(address);
            
        if (Integer.parseInt(String.valueOf(this.person.getPersonID().charAt(0))) % 2 == 0){ // checking if first char is even integer
            this.person.writeToFile(filename);
            return 3; // code 3 return indicates condition 3 failed
        }
         // change ID if not even first char

        this.person.writeToFile(filename);
        return success;
    }

    private Person readPersonFile(String filename){
        // TODO implement file reading here
        // create person object based on file contents
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            myReader.nextLine(); myReader.next(); String personID = myReader.next(); // read personID line
            myReader.nextLine(); myReader.next(); String firstName = myReader.next(); String lastName = myReader.next(); // read name line
            myReader.nextLine(); myReader.next(); String address = myReader.next(); // read address line
            myReader.nextLine(); myReader.next(); myReader.next(); myReader.next();
            String birthdate = myReader.next(); // read birthdate line

            myReader.close();
            this.person = new Person(personID, firstName, lastName, address, birthdate);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }

        return person;
    }
}