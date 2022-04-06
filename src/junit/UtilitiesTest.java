package junit;

import course.Course;
import enrolment.StudentEnrolment;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static utilities.Utilities.*;

class UtilitiesTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

    }

    @org.junit.jupiter.api.Test
    void shouldReadUserInput() {
        String user_input = "wada awda";
        InputStream in = new ByteArrayInputStream(user_input.getBytes());
        System.setIn(in);
        String input = readUserInput();
        assertEquals("wada awda", input);
    }

    @org.junit.jupiter.api.Test
    void shouldReadCourseFromUser() {
        Course course = new Course();
        course.setCourseID("C001");
        course.setCourseName("Name");
        course.setCredit(12);

        String[] c1_string = {"C001", "Name", "12"};

        assertEquals(course.getCourseID(), readCourseFromUser(c1_string).getCourseID());
        assertEquals(course.getCourseName(), readCourseFromUser(c1_string).getCourseName());
        assertEquals(course.getCourseCredit(), readCourseFromUser(c1_string).getCourseCredit());
    }

    @org.junit.jupiter.api.Test
    void shouldAskFilePath() {
        String user_input_1 = "1";
        String user_input_2 = "2";
        String user_answer_for_in_2 = "awdawdsd";
        InputStream in_1 = new ByteArrayInputStream(user_input_1.getBytes());
        InputStream in_2 = new ByteArrayInputStream(user_input_2.getBytes());

        System.setIn(in_1);
        assertEquals("Default.csv", askFilePath());
        System.setIn(in_2);
    }

    @org.junit.jupiter.api.Test
    void askSaveFilePath() {

    }

    @org.junit.jupiter.api.Test
    void shouldAskSTDIDandSemester() {
        String[] correct = {"S0001", "2020C"};
        String[] input = {"s0001", "2020c"};
        InputStream in_0 = new ByteArrayInputStream(input[0].getBytes());
        InputStream in_1 = new ByteArrayInputStream(input[1].getBytes());
        System.setIn(in_0);
        System.setIn(in_1);
        assertEquals(correct, askSTDIDandSemester());
    }

    @org.junit.jupiter.api.Test
    void askSearchMethod() {
    }

    @org.junit.jupiter.api.Test
    void shouldAskToSaveFile() {
        String input = "y";
        InputStream in_0 = new ByteArrayInputStream(input.getBytes());
        System.setIn(in_0);
        assertTrue(askToSaveFile());

        input = "n";
        in_0 = new ByteArrayInputStream(input.getBytes());
        System.setIn(in_0);
        assertFalse(askToSaveFile());
    }

    @org.junit.jupiter.api.Test
    void shouldIsSaveDefault() {
        String input = "1";
        InputStream in_0 = new ByteArrayInputStream(input.getBytes());
        System.setIn(in_0);
        assertTrue(isSaveDefault());

        input = "2";
        in_0 = new ByteArrayInputStream(input.getBytes());
        System.setIn(in_0);
        assertFalse(isSaveDefault());
    }

    @org.junit.jupiter.api.Test
    void shouldReadDate() {
        String input_date = "12/8/2020";
        assertEquals(12,readDate(input_date).getMonth());
        assertEquals(8,readDate(input_date).getDay());
        assertEquals(2020, readDate(input_date).getYear());
    }

    @org.junit.jupiter.api.Test
    void shouldReadStudent() {
        String[] student_string = { "S012345", "Name", "12/8/2020" };
        assertEquals("S012345", readStudent(student_string).getStudentID());
        assertEquals("Name", readStudent(student_string).getStudentName());
    }

    @org.junit.jupiter.api.Test
    void shouldReadCourse() {
        String[] course_string = {"", "", "", "C ID", "C NAME", "9"};
        assertEquals("C ID", readCourse(course_string).getCourseID());
        assertEquals(9, readCourse(course_string).getCourseCredit());
    }

    @org.junit.jupiter.api.Test
    void shouldCreateProfile() {
        String input = "S103192,Ngan Thu Vo,3/9/1998,BUS2232,Business Law,3,2020B";
        assertEquals("S103192", createProfile(input).getStudent().getStudentID());
        assertEquals("2020B", createProfile(input).getSemester());
    }

    @org.junit.jupiter.api.Test
    void shouldCheckStringComponent() {
        String test_1 = " , , , , , , ";
        assertTrue(checkStringComponent(test_1));
    }

    @org.junit.jupiter.api.Test
    void shouldCheckDate() {
        assertTrue(checkDate("08/28/2000"));
    }

    @org.junit.jupiter.api.Test
    void shouldCheckStudent() {
        String[] input = { "S000001", " ", "5/5/2020" };
        assertTrue(checkStudent(input));
    }

    @org.junit.jupiter.api.Test
    void shouldCheckCourse() {
        String[] input = {" ", " ", " ", " ", " ", "9"};
        assertTrue(checkCourse(input));
    }

    @org.junit.jupiter.api.Test
    void shouldCheckProfile() {
        String input = "S000001, ,5/5/2020, , ,9";
        assertTrue(checkProfile(input));
    }

    @org.junit.jupiter.api.Test
    void getStudentCourseList() {
    }

    @org.junit.jupiter.api.Test
    void shouldCopyStudentProfileForAdding() {
        ArrayList<StudentEnrolment> enrolments = new ArrayList<StudentEnrolment>();
        enrolments.add(createProfile("S103192,Ngan Thu Vo,3/9/1998,BUS2232,Business Law,3,2020B"));
        enrolments.add(createProfile("S101312,Alex Mike,10/13/1998,BUS2232,Business Law,3,2020C"));
//        assertEquals("2020C",copyStudentProfileForAdding("S101312","BUS2232",enrolments).getSemester());
    }

    @org.junit.jupiter.api.Test
    void shouldCopyStudentProfileForDropping() {
        ArrayList<StudentEnrolment> enrolments = new ArrayList<StudentEnrolment>();
        enrolments.add(createProfile("S103192,Ngan Thu Vo,3/9/1998,BUS2232,Business Law,3,2020B"));
        enrolments.add(createProfile("S101312,Alex Mike,10/13/1998,BUS2232,Business Law,3,2020C"));
        assertEquals("S101312",copyStudentProfileForDropping("S101312","BUS2232","2020C",enrolments).getStudent().getStudentID());
    }

    @org.junit.jupiter.api.Test
    void shouldSearchProfile() {
        ArrayList<StudentEnrolment> enrolments = new ArrayList<StudentEnrolment>();
        enrolments.add(createProfile("S103192,Ngan Thu Vo,3/9/1998,BUS2232,Business Law,3,2020B"));
        enrolments.add(createProfile("S101312,Alex Mike,10/13/1998,BUS2232,Business Law,3,2020C"));

        String input = "1";
        InputStream in_0 = new ByteArrayInputStream(input.getBytes());
        System.setIn(in_0);
        input = "S101312";
        in_0 = new ByteArrayInputStream(input.getBytes());
        System.setIn(in_0);
        assertEquals("S101312",searchProfile().get(0).getStudent().getStudentID());
    }

    @org.junit.jupiter.api.Test
    void shouldAddCourse() {
        ArrayList<StudentEnrolment> enrolments = new ArrayList<StudentEnrolment>();
        enrolments.add(createProfile("S103192,Ngan Thu Vo,3/9/1998,BUS2232,Business Law,3,2020B"));
        enrolments.add(createProfile("S101312,Alex Mike,10/13/1998,BUS2232,Business Law,3,2020C"));
        String[] id_sem = { "S101312", "2020C"};
        String course = "NEW000,new course,9";
        InputStream in_0 = new ByteArrayInputStream(course.getBytes());
        System.setIn(in_0);
        assertTrue(addCourse(id_sem,enrolments));
        assertNotEquals(2,enrolments.size());
    }

    @org.junit.jupiter.api.Test
    void shouldDelCourse() {
        ArrayList<StudentEnrolment> enrolments = new ArrayList<StudentEnrolment>();
        enrolments.add(createProfile("S103192,Ngan Thu Vo,3/9/1998,BUS2232,Business Law,3,2020B"));
        enrolments.add(createProfile("S101312,Alex Mike,10/13/1998,BUS2232,Business Law,3,2020C"));
        String[] id_sem = { "S101312", "2020C"};

        String course = "BUS2232,Business Law,3";
        InputStream in_0 = new ByteArrayInputStream(course.getBytes());
        System.setIn(in_0);

        assertTrue(delCourse(id_sem,enrolments));
        assertNotEquals(2,enrolments.size());
    }

    @org.junit.jupiter.api.Test
    void shouldSaveFileToDesktop() {
        ArrayList<StudentEnrolment> enrolments = new ArrayList<StudentEnrolment>();
        enrolments.add(createProfile("S103192,Ngan Thu Vo,3/9/1998,BUS2232,Business Law,3,2020B"));
        assertTrue(saveFileToDesktop(enrolments));
    }

    @org.junit.jupiter.api.Test
    void saveFileDefault() {
    }

    @org.junit.jupiter.api.Test
    void saveFileToLocation() {
    }
}