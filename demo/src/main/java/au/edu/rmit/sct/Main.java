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



    public boolean addPerson(){




        return true;
    }

    public boolean updatePersonalDetails(){



        return true;
    }

    public String addDemeritPoints(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter demerit points");

        int demeritPoints = scanner.nextLine();
        if (demeritPoints % 1 == 0){
            if (birthdate - Date < 21 && demeritPoints > 6) {
                isSuspended = true;
            }
            else{
                if (birthdate - Date > 21 && demeritPoints > 12){
                    isSuspended = true;
                }
            }

        return "Sucess";

        }
        else{
            System.out.println("Invalid input. Demerit Points must be a whole number")
            scanner.close()

        }


        return "fail";
    }


}