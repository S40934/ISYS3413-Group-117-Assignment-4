import java.time

public class PersonalDetailsEditor {

    private String filename;
    private Person person;
    
    public PersonalDetailsEditor(String filename){
        this.filename = filename;
        this.person = readPersonFile(filename);
    }

    public int updatePersonalDetails(String personID, String firstName, String lastName, String address, String birthdate){
        int code = 0;
        
        // TODO CONDITION 1: address change only on 18+ aged person
            // implement person date parsing and comparison to current date - 18 years using java.time LocalDate

        //TODO CONDITION 2: no other change on birthday change (single detail change)
            // check if different birthday param, write and return early if so

        //TODO CONDITION 3: if 1st char of ID is even, ID cannot be canged
            // check first char of ID, if even, skip ID change
            // return new number to indicate status

        this.person.writeToFile(filename);
        return code;
    }

    private Person readPersonFile(String filename){
        // TODO implement file reading here
        // create person object based on file contents
    }