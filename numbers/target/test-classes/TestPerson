package au.edu.rmit.sct;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class TestPerson{
    @Test
    void testAddPerson_testCase1(){
    //TestCase 1: Check funtion with valid Inputs
        Person testVal1 = new Person("56s_d%&fAB", "John", "Doe", "32|Highland Street|Melbourne|Victoria|Australia", "15-11-1990");
        assertTrue(testVal1.addPerson());
    }
    @Test
    void testAddPerson_testCase2(){
    //TestCase 1: Check funtion with invalid length
        Person testVal1 = new Person("56s_d%&fA", "John", "Doe", "32|Highland Street|Melbourne|Victoria|Australia", "15-11-1990");
        assertFalse(testVal1.addPerson());
    }
    @Test
    void testAddPerson_testCase3(){
    //TestCase 1: Check funtion with invalid State
        Person testVal1 = new Person("56s_d%&fAB", "John", "Doe", "32|Highland Street|Melbourne|Victori|Australia", "15-11-1990");
        assertFalse(testVal1.addPerson());
    }
    @Test
    void testAddPerson_testCase4(){
    //TestCase 1: Check funtion with country not aus
        Person testVal1 = new Person("56s_d%&fAB", "John", "Doe", "32|Highland Street|Melbourne|Victoria|USA", "15-11-1990");
        assertFalse(testVal1.addPerson());
    }
    @Test
    void testAddPerson_testCase5(){
    //TestCase 1: Check funtion with birthday 
    //check if it is format correctly
        Person testVal1 = new Person("56s_d%&fAB", "John", "Doe", "32|Highland Street|Melbourne|Victoria|Australia", "32-11-1990");
        assertFalse(testVal1.addPerson());
        Person testVal2 = new Person("56s_d%&fAB", "John", "Doe", "32|Highland Street|Melbourne|Victoria|Australia", "1990-11-31");
        assertFalse(testVal2.addPerson());
    }
}

class TestDemerits{
    //Test for Condition 1
    @Test
    void testAddDemerit_testCase1(){
    //TestCase 1: Checks if offence date is in correct format.
        Person testCase1 = new Person("56s_d%&fAB", "John", "Doe", "15-11-1990", "2025-06-01", 3);
        assertEquals("Success",testCase1.addDemeritPoints()); // Expected outcome
    }

    //Test for Condition 2 - TestCase2 and 3.
    @Test
    void testAddDemerit_testCase2(){
    //TestCase 2: Checks if demerit points are whole numbers. 
        Person testCase2 = new Person("56s_d%&fAB", "John", "Doe", "15-11-1990", "01-06-2005", 3.5);
        assertEquals("Success", testCase2.addDemeritPoints());
        
    }

    @Test
    void testAddDemerit_testCase3(){
    //TestCase 3: Checks if demerit points is between 1 - 6
    Person testCase3 = new Person("56s_d%&fAB", "John", "Doe", "15-11-1990", "01-06-2025", 7);
        assertEquals("Success", testCase3.addDemeritPoints());
        
    }

    //Test for Condition 3
    //TestCase4 and 5 checks if condition 3 is met - if amount of demerit points of a person exceeds an amount in a time frame of 2 yrs
    @Test
    void testAddDemerit_testCase4(){
    //TestCase 4: Checks if over demerit points for under 21.
        Person testCase4 = new Person("56s_d%&fAB", "John", "Doe", "15-11-2005", "25-04-2024", 5);
        assertEquals("Success", testCase4.addDemeritPoints());
    }

    @Test
    void testAddDemerit_testCase5(){
    //TestCase 5: Checks if over demerit points for under 21 within 2 years.
        Person testCase5 = new Person("56s_d%&fAB", "John", "Doe", "15-11-2005", "01-06-2025", 3);
        assertEquals("Success", testCase5.addDemeritPoints());
    }

}

