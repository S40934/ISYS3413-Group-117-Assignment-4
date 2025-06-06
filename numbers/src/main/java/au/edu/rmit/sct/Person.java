package au.edu.rmit.sct;

import java.util.HashMap;
import java.util.Date;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Person{

    private String personID;
    private String firstName;
    private String lastName;
    private String address;
    private String birthdate;
    private HashMap<String, Integer> demeritPoints; 
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
    
    public Demerit(String personID, String firstName, String lastName, String birthDate, String date, int demerit){
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
        int day = 0, 
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
            FileWriter writer = new FileWriter("AddPerson.txt", true);
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
        return valid;
    }

    //update personal details helper methods
    public int writeToFile(String filename){
        // separate file writing portion for PersonalDetailsEditor.updatePersonalDetails() method
        int code = 0;
        try {
            FileWriter writer = new FileWriter(filename, true);
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
    public String getBirthdate(){
        return birthdate;
    }
    public void setBirthdate(String birthdate){
        this.birthdate = birthdate;
    }
    // public boolean updatePersonalDetails(){
    //     return true;
    // }

    public String addDemeritPoints(){

        //Gets date and demerit points from the hashmap
        Map.Entry<String, Integer> entry = this.demeritPoints.entrySet().iterator().next();
        String date = entry.getKey();

        // String date = this.demeritPoints.getKey();
        int demerit = entry.getValue();

        //codition 1 -checking if offence date is valid
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate offenceDate = LocalDate.parse(date, formatter);
        LocalDate today = LocalDate.now();

        //To get the person's age
        //Converts this.birthday which is a string to localDate.
        LocalDate birthDate = LocalDate.parse(this.birthdate, formatter);
        int age = Period.between(birthDate, today).getYears();

        //need to read file to check if person had already lost some demerit points
        BufferedReader fileReader = new BufferedReader(new FileReader ("addDemeritPoints.txt"));
        String line;
        int existingDemerits = 0;
        while ((line = fileReader.readLine()) != null){
            if(line.contains("PersonID: " + this.personID)){
                while ((line = fileReader.readLine()) != null && !line.startsWith("PersonID: ")){
                    if (line.contains("Demerits: ")){
                        existingDemerits = Integer.parseInt(line.replaceAll("\\D+",""));
                        break;
                    }
                }
            }

        }
        fileReader.close();

        int demeritSum= existingDemerits + demerit;

        // Make sure demerit points are whole numbers and between 1 -6 - Condition 2.
        if ((demeritSum % 1 == 0) && (demeritSum > 0) && (demeritSum <= 6)){
            if ((age < 21) && (demeritSum> 6)) { //Condition 3 
                this.isSuspended = true;
            }
            if ((age > 21) && (demeritSum > 12)){ 
                    this.isSuspended = true;
                }
            
            //writes into addDemeritPoints.txt
            try {
            FileWriter fileWriter = new FileWriter("addDemeritPoints.txt", true);
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
            System.out.println("Invalid input. Demerit Points must be a whole number");
            return "Failed";
        }

    }


}