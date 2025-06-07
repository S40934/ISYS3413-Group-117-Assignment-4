package au.edu.rmit.sct;

import java.util.HashMap;
import java.util.Map;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.Period;

// import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Person{

    private String personID;
    private String firstName;
    private String lastName;
    private String address;
    private String birthdate;
    private HashMap<String, Float> demeritPoints; 
    private boolean isSuspended;

    public Person(String personID, String firstName, String lastName, String address, String birthdate) {
            this.personID = personID;
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
            this.birthdate = birthdate;
            this.demeritPoints = new HashMap<>();
            this.isSuspended = false;
        }
    
    public Person(String personID, String firstName, String lastName, String birthDate, String date, Float demerit){
        this.personID = personID;    
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthDate;

        this.demeritPoints = new HashMap<>();
        this.demeritPoints.put(date, demerit);
        this.isSuspended = false;
    }

    public boolean addPerson(){
        int idLimit = 10;
        int specCount = 0;
        
        boolean valid = false;
        //Condition 1 complete;
        if (personID.length() != idLimit){
            return false;
        }else{
            if(!Character.isDigit(personID.charAt(0)) || !Character.isDigit(personID.charAt(1))){
                return false;
            }
            if (!Character.isUpperCase(personID.charAt(8)) || !Character.isUpperCase(personID.charAt(9))) {
                return false;
            }
            for(int i = 2; i < personID.length(); i++){
                if(!Character.isDigit(personID.charAt(i)) && !Character.isLetter(personID.charAt(i))){
                    specCount++;
                }
                
            }
            if(specCount >= 2){
                    valid = true;
                }else{
                    System.out.println("Invalid ID");
                    return false;
                }
        }

        //Condidtion 2 
        String number ="";
        String street = "";
        String city ="";
        String state ="";
        String country="";
        String variant="";
        int count = 4;
        int liner = 0;
        for(int i = 0; i < address.length() ;i++){
            if(address.charAt(i)=='|'){
                
                variant = address.substring(liner , i);
                liner = i+1;
                if(count == 4){
                    number = variant;
                }else if (count == 3){
                    street = variant;

                }else if(count == 2){
                    city = variant;
                                        

                }else if (count == 1){
                    state = variant;
                    
                }
                count--;

            }
        }
        variant = address.substring(liner);
        country = variant;
        
        if(count !=0){
            return false;
        }
        if(!state.equals("Victoria")){
            valid = false;
            System.out.println("Please make sure, it is in Victoria");
        }
        if(!country.equals("Australia")){
            valid = false;
            System.out.println("Please make sure, it is in Australia");
        }
        //Condition 3
        int day = 0; 
        int month = 0;
        int year = 0;
        int Bcount = 3;
        int Bliner = 0;
        variant = "";
        for (int i = 0; i < birthdate.length(); i++) {
            if (birthdate.charAt(i) == '-') {
                variant = birthdate.substring(Bliner, i);
                Bliner = i + 1;

                // check if only numbers
                if (!variant.matches("\\d+")) {
                    return false;
                }

                if (Bcount == 3) {
                    day = Integer.parseInt(variant);
                } else if (Bcount == 2) {
                    month = Integer.parseInt(variant);
                }
                Bcount--;
            }
        }
        variant = birthdate.substring(Bliner);
        if (!variant.matches("\\d+")) {
            System.out.println("Invalid year part: " + variant);
            return false;
        }
        year = Integer.parseInt(variant);
        Bcount--;

        if (Bcount != 0) {
            System.out.println("Invalid birthdate format.");
            return false;
        }
        if (month < 1 || month > 12) {
            System.out.println("Invalid month: " + month);
            return false;
        }
         if (day < 1 || day > 31) {
            System.out.println("Invalid day: " + day);
            return false;
        }
        try {
        LocalDate.of(year, month, day);
        } catch (Exception e) {
            System.out.println("Invalid date combination: " + e.getMessage());
            return false;
        }
        if(valid){
            try {
                FileWriter writer = new FileWriter("AddPerson.txt", false);
                writer.write("*--------------------------*\n");
                writer.write("PersonID: " + personID + "\n");
                writer.write("PersonName: " + firstName + " " + lastName + "\n");
                writer.write("Address: " + address + "\n");
                writer.write("Date of Birth: " + birthdate + "\n");
                writer.write("///--------/////--------///\n");
                writer.close();
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file: " + e.getMessage());
            }
        }
        return valid;
    }

    //update personal details helper methods
    public int writeToFile(String filename){
        // separate file writing portion for PersonalDetailsEditor.updatePersonalDetails() method
        int code = 0;
        try {

            FileWriter writer = new FileWriter(filename, false);
            writer.write("*--------------------------*\n");
            writer.write("PersonID: " + personID + "\n");
            writer.write("PersonName: " + firstName + " " + lastName + "\n");
            writer.write("Address: " + address + "\n");
            writer.write("Date of Birth: " + birthdate + "\n");
            writer.write("///--------/////--------///\n");
            writer.close();
        } catch (IOException e) {
            code = 1;
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
        return code;
    }
    public String getPersonID(){
        return personID;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getAddress(){
        return address;
    }
    public String getBirthdate(){
        return birthdate;
    }
    public void setPersonID(String personID){
        this.personID = personID;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setBirthdate(String birthdate){
        this.birthdate = birthdate;
    }
    // public boolean updatePersonalDetails(){
    //     return true;
    // }

    public String addDemeritPoints(){

        //Gets date and demerit points from the hashmap
        Map.Entry<String, Float> entry = this.demeritPoints.entrySet().iterator().next();
        String date = entry.getKey();

        // String date = this.demeritPoints.getKey();
        float demerit = entry.getValue();

        //codition 1 -checking if offence date is valid
        DateTimeFormatter incorrectformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter correctformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
        LocalDate offenceDate  = null;

        try{
            offenceDate = LocalDate.parse(date, correctformatter); // checks if its the right format
        }
        catch (Exception e1){ // if not checks if it's incorrect
            try{
                offenceDate = LocalDate.parse(date, incorrectformatter);
            }
            catch (Exception e2){
                System.out.println("Invalid input. Incorrect date format");
                return "Failed";

            } 
        
        }

        LocalDate today = LocalDate.now();

        //To get the person's age
        //Converts this.birthday which is a string to localDate.
        LocalDate birthDate = LocalDate.parse(this.birthdate, correctformatter);
        int age = Period.between(birthDate, today).getYears();

        //need to read file to check if person had already lost some demerit points/ in demerit file
        String line;
        float existingDemerits = 0.0f;
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader ("AddDemeritPoints.txt"));
            while ((line = fileReader.readLine()) != null){
                if(line.contains("PersonID: " + this.personID)){
                    while ((line = fileReader.readLine()) != null && !line.startsWith("PersonID: ")){
                        if (line.contains("Demerits: ")){
                            existingDemerits = Float.parseFloat(line.replaceAll("[^0-9.]","")); // removes any symbols, excluding decimals
                            break;
                        }
                    }
                }
            }
            fileReader.close();

        } catch (Exception e) {
            System.out.println("No records found");
        }

        float demeritSum= existingDemerits + demerit;

        // Make sure demerit points are whole numbers and between 1 -6 - Condition 2.
        if ((demeritSum % 1 == 0) && (demeritSum >= 1.0f) && (demeritSum <= 6.0f)){
            if ((age < 21) && (demeritSum > 6.0f)) { //Condition 3 
                this.isSuspended = true;
            }
            if ((age > 21) && (demeritSum > 12.0f)){ 
                    this.isSuspended = true;
                }
                
            //writes into addDemeritPoints.txt
            try {
                FileWriter fileWriter = new FileWriter("AddDemeritPoints.txt", true);
                fileWriter.write("PersonID: " + personID);
                fileWriter.write("\nName: " + firstName + " " + lastName);
                fileWriter.write("\nDate of Birth: " + birthdate);
                fileWriter.write("\nDate of Offence: " + offenceDate);
                fileWriter.write("\nDemerits: " + demeritSum);
                fileWriter.write("\nSuspended: " + (this.isSuspended ? "True" : "False"));
                fileWriter.close();
            }
            catch (IOException e){
                System.out.println("An error occurred while writing to the file: " + e.getMessage());
            }

            return "Success";
        }
        else{
            System.out.println("Invalid input. Demerit Points must be a whole number and between 1 and 6.");
            return "Failed";
        }
    }


}