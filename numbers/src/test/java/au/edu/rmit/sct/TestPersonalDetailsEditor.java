package au.edu.rmit.sct;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.File;


class TestPersonalDetailsEditor{
    @Test
    void testUpdatePersonalDetails_testCase1(){
        //TestCase 1: Check function with underaged person
        String fname = "editorIN/testUnderagedPerson.txt";
        PersonalDetailsEditor testVal;
        if (!new File(fname).exists()) {
            fname = String.valueOf(getClass().getClassLoader().getResource(fname)).substring(6);
        }
        testVal = new PersonalDetailsEditor(fname);
        testVal.setFilename("out1.txt");
        assertFalse(testVal.updatePersonalDetails("56s_d%&NNN", "John", "Doe", "32|Highland Street|Melbourne|Victoria|Australia", "15-11-2010"));
        //Expected: False, case 1 fails, and output has unchanged address
    }
    @Test
    void testUpdatePersonalDetails_testCase2(){
        //TestCase 2: Check function with birthday change
        String fname = "editorIN/testBirthdayPerson.txt";
        PersonalDetailsEditor testVal;
        if (!new File(fname).exists()) {
            fname = String.valueOf(getClass().getClassLoader().getResource(fname)).substring(6);
        }
        testVal = new PersonalDetailsEditor(fname);
        testVal.setFilename("out2.txt");
        assertFalse(testVal.updatePersonalDetails("56s_d%&NNN", "John", "Doe", "32|Highland Street|Melbourne|Victoria|Australia", "15-11-1990"));
        //Expected: False, case 2 fails, and output has no changes other than birthday
    }
    @Test
    void testUpdatePersonalDetails_testCase3(){
        //TestCase 3: Check function with even first ID char
        String fname = "editorIN/testFirstEvenPerson.txt";
        PersonalDetailsEditor testVal;
        if (!new File(fname).exists()) {
            fname = String.valueOf(getClass().getClassLoader().getResource(fname)).substring(6);
        }
        testVal = new PersonalDetailsEditor(fname);
        testVal.setFilename("out3.txt");
        assertFalse(testVal.updatePersonalDetails("56s_d%&DIF", "Notjohn", "notDoe", "21|Lowland Avenue|Melbourne|Victoria|Australia", "13-10-1999"));
        //Expected: False, case 3 fails, and output has changes but same ID
    }
    @Test
    void testUpdatePersonalDetails_testCase4(){
        //TestCase 4: Check function with invalid person
        String fname = "editorIN/testInvalidPerson.txt";
        PersonalDetailsEditor testVal;
        if (!new File(fname).exists()) {
            fname = String.valueOf(getClass().getClassLoader().getResource(fname)).substring(6);
        }
        testVal = new PersonalDetailsEditor(fname);
        testVal.setFilename("out4.txt");
        assertTrue(testVal.updatePersonalDetails("56s_d%&NNN", "John", "Doe", "32|Highland Street|Melbourne|Victoria|Australia", "15-11-1990"));
        //Expected: True, all changes written as they are valid
    }
    @Test
    void testUpdatePersonalDetails_testCase5(){
        //TestCase 5: Check function with invalid detail edits / function parameters
        String fname = "editorIN/testInvalidPerson.txt";
        PersonalDetailsEditor testVal;
        if (!new File(fname).exists()) {
            fname = String.valueOf(getClass().getClassLoader().getResource(fname)).substring(6);
        }
        testVal = new PersonalDetailsEditor(fname);
        testVal.setFilename("out5.txt");
        assertFalse(testVal.updatePersonalDetails("ssssssssss", "John", "Doe", "32|Highland Street|Melbourne|Victoria|Australia", "15-11-1990"));
        //Expected: False, case 5 fails, and output has no changes as they are invalid
    }
}