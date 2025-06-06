package au.edu.rmit.sct;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class TestPersonalDetailsEditor{
    @Test
    void testUpdatePersonalDetails_testCase1(){
        //TestCase 1: Check function with underaged person
        PersonalDetailsEditor testVal = new PersonalDetailsEditor("pp.txt");
        testVal.updatePersonalDetails("56s_d%&fAB", "John", "Doe", "32|Highland Street|Melbourne|Victoria|Australia", "15-11-1990");
        assertFalse(testVal.updatePersonalDetails("56s_d%&fB", "John", "Doe", "32|Highland Street|Melbourne|Victoria|Australia", "15-11-1990"));
    }
    @Test
    void testUpdatePersonalDetails_testCase2(){
        //TestCase 2: Check function with birthday change
        PersonalDetailsEditor testVal = new PersonalDetailsEditor("testBirthdayPerson.txt");
        testVal.updatePersonalDetails("56s_d%&fB", "John", "Doe", "32|Highland Street|Melbourne|Victoria|Australia", "15-11-1990");
        assertFalse(testVal.updatePersonalDetails("56s_d%&fB", "John", "Doe", "32|Highland Street|Melbourne|Victoria|Australia", "15-11-1990"));
    }
    @Test
    void testUpdatePersonalDetails_testCase3(){
        //TestCase 3: Check function with birthday change
        PersonalDetailsEditor testVal= new PersonalDetailsEditor("testFirstEvenPerson.txt");
        testVal.updatePersonalDetails("56s_d%&fAB", "John", "Doe", "32|Highland Street|Melbourne|Victoria|Australia", "15-11-1990");
        assertFalse(testVal.updatePersonalDetails("56s_d%&fB", "John", "Doe", "32|Highland Street|Melbourne|Victoria|Australia", "15-11-1990"));
    }
    @Test
    void testUpdatePersonalDetails_testCase4(){
        //TestCase 4: Check function with invalid person
        PersonalDetailsEditor testVal= new PersonalDetailsEditor("testInvalidPerson.txt");
        testVal.updatePersonalDetails("56s_d%&fAB", "John", "Doe", "32|Highland Street|Melbourne|Victoria|USA", "15-11-1990");
        assertFalse(testVal.updatePersonalDetails("56s_d%&fB", "John", "Doe", "32|Highland Street|Melbourne|Victoria|Australia", "15-11-1990"));
    }
    @Test
    void testUpdatePersonalDetails_testCase5(){
        //TestCase 5: Check function with invalid detail edits / function parameters
        //check if it is format correctly
        PersonalDetailsEditor testVal= new PersonalDetailsEditor("testWrongEditsPerson.txt");
        testVal.updatePersonalDetails("ssssssssss", "John", "Doe", "32|Highland Street|Melbourne|Victoria|Australia", "32-11-1990");
        assertFalse(testVal.updatePersonalDetails("ssssssssss", "John", "Doe", "32|Highland Street|Melbourne|Victoria|Australia", "15-11-1990"));

    }
}