package junit;

import enrolment.RMITStudentEnrolmentManager;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import org.mockito.Mockito;
import utilities.Utilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

class RMITStudentEnrolmentManagerTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;


    @BeforeEach
    public void setUpStreams() {
/*        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));*/
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void getEnrolmentList() {
    }

    @Test
    void shouldInit() {
        RMITStudentEnrolmentManager enrolmentManager = new RMITStudentEnrolmentManager();
        String user_input = "1";
        InputStream in = new ByteArrayInputStream(user_input.getBytes());
        System.setIn(in);
        enrolmentManager.init();

        assertTrue(outContent.toString().contains("Welcome to the Student Enrolment Manager software (S.E.M)"));
    }

    @Test
    void shouldAddEnrolment() {
        RMITStudentEnrolmentManager enrolmentManager = new RMITStudentEnrolmentManager();
        String user_input = "1";
        InputStream in = new ByteArrayInputStream(user_input.getBytes());
        System.setIn(in);
        enrolmentManager.addEnrolment();

        assertTrue(outContent.toString().contains("Please enter the enrolment profile according to the format shown:"));
    }

    @Test
    void shouldUpdateEnrolment() {
        RMITStudentEnrolmentManager enrolmentManager = new RMITStudentEnrolmentManager();
        /*String user_input = "1";
        InputStream in = new ByteArrayInputStream(user_input.getBytes());
        System.setIn(in);*/
        /*user_input = "2020C";
        in = new ByteArrayInputStream(user_input.getBytes());
        System.setIn(in);*/
        enrolmentManager.updateEnrolment();

        assertTrue(outContent.toString().contains("Student ["));
    }

    @Test
    void searchEnrolment() {

    }

    @Test
    void run() {
    }

}