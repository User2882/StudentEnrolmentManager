package junit;

import utilities.Dates;
import student.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void setStudentID() {
        Student std = new Student();
        assertTrue(std.setStudentID("S123456"));
    }

    @Test
    void setStudentName() {
        Student std = new Student();
        std.setStudentName("John");
        assertEquals("John", std.getStudentName());
    }

    @Test
    void setDate() {
        Dates date = new Dates();
        date.setDate(8,28,2000);
        Student std = new Student();
        assertTrue(std.setDate(date));
    }

    @Test
    void getStudentID() {
        Student std = new Student();
        std.setStudentID("S123456");
        assertEquals("S123456", std.getStudentID());
    }

    @Test
    void getStudentName() {
        Student std = new Student();
        std.setStudentName("S123456");
        assertEquals("S123456", std.getStudentName());
    }

    @Test
    void getDayOfBirth() {
        Student std = new Student();
        Dates date = new Dates();
        date.setDate(8,28,2000);
        std.setDate(date);
        assertEquals(date.getDay(), std.getDayOfBirth().getDay());
    }
}