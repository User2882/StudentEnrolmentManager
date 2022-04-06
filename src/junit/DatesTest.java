package junit;

import utilities.Dates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static utilities.Dates.isLeapYear;
import static utilities.Dates.isValidDate;
import static org.junit.jupiter.api.Assertions.*;

class DatesTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldIsLeapYear() {
        assertTrue(isLeapYear(2012));
        assertFalse(isLeapYear(2013));
    }

    @Test
    void shouldIsValidDate() {
        assertTrue(isValidDate(1,1,2012));
        assertFalse(isValidDate(32,1,2012));
    }

    @Test
    void getDay() {
        Dates test = new Dates();
        test.setDate(8,28,2000);
        assertEquals(28,test.getDay());
    }

    @Test
    void getMonth() {
        Dates test = new Dates();
        test.setDate(8,28,2000);
        assertEquals(8,test.getMonth());
    }

    @Test
    void getYear() {
        Dates test = new Dates();
        test.setDate(8,28,2000);
        assertEquals(2000,test.getDay());
    }

    @Test
    void setDate() {
        Dates test = new Dates();
        assertTrue(test.setDate(8,28,2000));
    }
}