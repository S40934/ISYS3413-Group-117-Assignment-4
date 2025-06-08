package au.edu.rmit.sct;

public class Main {

    public static void main(String[] args) {
        //demo main class to demonstrate workins of methods

        Person person = new Person("56s_d%&fAB", "Mary", "Doe", "31|Highland Street|Melbourne|Victoria|Australia", "15-11-2003");

        person.addPerson();

        Person demeritPerson = new Person("65s_d%&AAA", "Mary", "Doe", "15-11-2003", "15-11-2023", 5.0f);
        demeritPerson.addDemeritPoints();

        PersonalDetailsEditor editor = new PersonalDetailsEditor("AddPerson.txt");
        editor.updatePersonalDetails("56s_d%&fAB", "John", "Doe", "32|Highland Street|Melbourne|Victoria|Australia", "15-11-2003");

        System.out.println("DONE");
    }
}
