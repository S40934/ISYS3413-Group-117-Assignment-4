package au.edu.rmit.sct;

public class LocalRun {
    public static int main() {
        //demo main class to demonstrate workins of methods

        Person person = new Person("56s_d%&fAB", "Mary", "Doe", "31|Highland Street|Melbourne|Victoria|Australia", "15-11-2003");

        person.addPerson();

        person.addDemeritPoints();

        PersonalDetailsEditor editor = new PersonalDetailsEditor("addPerson.txt");
        editor.updatePersonalDetails("88s_d%&fAB", "John", "Doe", "32|Highland Street|Melbourne|Victoria|Australia", "15-11-1990");

        return 0;
    }
}
