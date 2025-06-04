package au.edu.rmit.sct;

import java.util.HashMap;
import java.util.Date;

import java.util.Scanner;

public class Person{

    private String personID;
    private String firstName;
    private String lastName;
    private String address;
    private String birthdate;
    private HashMap<Date, Integer> demeritPoints; //
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

        
        return true;
    }

    public boolean updatePersonalDetails(){



        return true;
    }

    public String addDemeritPoints(){

    }


}