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


        return true;
    }

    public boolean updatePersonalDetails(){



        return true;
    }

    public String addDemeritPoints(){

    }


}