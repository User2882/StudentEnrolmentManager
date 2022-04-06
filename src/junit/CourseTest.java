package junit;

import course.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldSetCourseID() {
        Course course = new Course();
        course.setCourseID("AcB");
        assertEquals("ACB", course.getCourseID());
    }

    @Test
    void shouldSetCourseName() {
        Course course = new Course();
        course.setCourseName("AcB");
        assertEquals("AcB", course.getCourseName());
    }

    @Test
    void shouldSetCredit() {
        Course course = new Course();
        course.setCredit(9);
        assertEquals(9, course.getCourseCredit());
    }

    @Test
    void getCourseID() {
        Course course = new Course();
        course.setCourseID("AcB");
        assertEquals("ACB", course.getCourseID());
    }

    @Test
    void getCourseName() {
        Course course = new Course();
        course.setCourseName("AcB");
        assertEquals("AcB", course.getCourseName());
    }

    @Test
    void getCourseCredit() {
        Course course = new Course();
        course.setCredit(9);
        assertEquals(9, course.getCourseCredit());
    }
}