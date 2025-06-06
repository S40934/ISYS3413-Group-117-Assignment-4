package au.edu.rmit.sct;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class TestPersonalDetailsEditor{
    @Test
    void testUpdatePersonalDetails_testCase1(){
        //TestCase 1: Check function with underaged person
        PersonalDetailsEditor testVal = new PersonalDetailsEditor(String.valueOf(getClass().getClassLoader().getResource("editorIN/testUnderagedPerson.txt")).substring(6));
        testVal.setFilename("outUnderagedPerson.txt");
        testVal.updatePersonalDetails("56s_d%&NNN", "Different", "Person", "21|Lowsea Avenue|Newcastle|Orlando|Florida", "15-11-1990");
        assertFalse(testVal.updatePersonalDetails("56s_d%&fB", "John", "Doe", "32|Highland Street|Melbourne|Victoria|Australia", "15-11-1990"));
    }
    @Test
    void testUpdatePersonalDetails_testCase2(){
        //TestCase 2: Check function with birthday change
        PersonalDetailsEditor testVal = new PersonalDetailsEditor(String.valueOf(getClass().getClassLoader().getResource("editorIN/testBirthdayPerson.txt")).substring(6));
        // testVal.setFilename(String.valueOf(getClass().getClassLoader().getResource("editorOUT/outBirthdayPerson.txt")).substring(0));
        testVal.updatePersonalDetails("56s_d%&fB", "John", "Doe", "32|Highland Street|Melbourne|Victoria|Australia", "15-11-1990");
        assertFalse(testVal.updatePersonalDetails("56s_d%&fB", "John", "Doe", "32|Highland Street|Melbourne|Victoria|Australia", "15-11-1990"));
    }
    @Test
    void testUpdatePersonalDetails_testCase3(){
        //TestCase 3: Check function with even first ID char
        PersonalDetailsEditor testVal= new PersonalDetailsEditor(String.valueOf(getClass().getClassLoader().getResource("editorIN/testFirstEvenPerson.txt")).substring(6));
        // testVal.setFilename(String.valueOf(getClass().getClassLoader().getResource("editorOUT/outFirstEvenPerson.txt")).substring(0));
        testVal.updatePersonalDetails("56s_d%&fAB", "John", "Doe", "32|Highland Street|Melbourne|Victoria|Australia", "15-11-1990");
        assertFalse(testVal.updatePersonalDetails("56s_d%&fB", "John", "Doe", "32|Highland Street|Melbourne|Victoria|Australia", "15-11-1990"));
    }
    @Test
    void testUpdatePersonalDetails_testCase4(){
        //TestCase 4: Check function with invalid person
        PersonalDetailsEditor testVal= new PersonalDetailsEditor(String.valueOf(getClass().getClassLoader().getResource("editorIN/testInvalidPerson.txt")).substring(6));
        // testVal.setFilename(String.valueOf(getClass().getClassLoader().getResource("editorOUT/outInvalidPerson.txt")).substring(0));
        testVal.updatePersonalDetails("56s_d%&fAB", "John", "Doe", "32|Highland Street|Melbourne|Victoria|USA", "15-11-1990");
        assertTrue(testVal.updatePersonalDetails("56s_d%&fB", "John", "Doe", "32|Highland Street|Melbourne|Victoria|Australia", "15-11-1990"));
    }
    @Test
    void testUpdatePersonalDetails_testCase5(){
        //TestCase 5: Check function with invalid detail edits / function parameters
        //check if it is format correctly
        PersonalDetailsEditor testVal= new PersonalDetailsEditor(String.valueOf(getClass().getClassLoader().getResource("editorIN/testWrongEditsPerson.txt")).substring(6));
        // testVal.setFilename(String.valueOf(getClass().getClassLoader().getResource("editorOUT/outWrongEditsPerson.txt")).substring(0));
        testVal.updatePersonalDetails("ssssssssss", "John", "Doe", "32|Highland Street|Melbourne|Victoria|Australia", "15-11-1990");
        assertFalse(testVal.updatePersonalDetails("ssssssssss", "John", "Doe", "32|Highland Street|Melbourne|Victoria|Australia", "15-11-1990"));

    }
}