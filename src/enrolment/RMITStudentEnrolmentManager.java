package enrolment;

import course.Course;
import interfaces.StudentEnrolmentManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static utilities.Utilities.*;

public class RMITStudentEnrolmentManager implements StudentEnrolmentManager {
    private static final ArrayList<StudentEnrolment> ENROLMENT_LIST = new ArrayList<StudentEnrolment>();

    public static ArrayList<StudentEnrolment> getEnrolmentList() {
        return ENROLMENT_LIST;
    }


    public void init() {
        String read_line = "";
        while (true) {
            try {
                //find and read the CSV file
                Scanner reader_object = new Scanner(new File(askFilePath()));
                while (reader_object.hasNextLine()) {  //while reading the file line per line
                    read_line = reader_object.nextLine();
                    if (checkStringComponent(read_line)) {
                        //assembling the profile
                        StudentEnrolment student_enrolment_info = createProfile(read_line);
                        //add profile to list
                        getEnrolmentList().add(student_enrolment_info);
                    }
                }
                reader_object.close();
                System.out.println("Student enrolment list has been initialize.");
                break;
            } catch (FileNotFoundException e) { //error handling
//            e.printStackTrace();
                System.out.println("Error! File path invalid.\nResetting app....\n\n\n\nNote: Please make sure there is a file ready!");
            }
        }

    }

    public boolean addEnrolment() {
        System.out.println("Please enter the enrolment profile according to the format shown:");
        System.out.println("Student_ID(S123456),Student_Name(John Doe),Student_day_of_birth(mm/dd/yy)," +
                "Course_ID,Course_name,Course_credit," +
                "Semester_of_enrolment");
        System.out.print("Note: Do not use space after or before comma (',')!\nInput: ");
        String user_input = readUserInput();
        if (checkStringComponent(user_input) && checkProfile(user_input)) {
            getEnrolmentList().add(createProfile(user_input));
            return true;
        }
        System.out.println("Incorrect input format.");
        return false;
    }

    public boolean updateEnrolment() {
        String[] studentID_and_semester = askSTDIDandSemester();
        ArrayList<Course> course_list = getStudentCourseList(studentID_and_semester[0], studentID_and_semester[1], getEnrolmentList());
        if (course_list.isEmpty()) {
            System.out.println("Student [" + studentID_and_semester[0] + "] does not have a profile for semester [" + studentID_and_semester[1] + "]");
            return false;
        }
        System.out.println("Course for student [" + studentID_and_semester[0] + "] of semester [" + studentID_and_semester[1] + "]:");
        System.out.println(course_list.toString());
        System.out.print("[1]: Add/Enroll a new course.\n[2]: Delete/Drop an existing course.\nInput: ");
        String user_input = readUserInput();
        if (user_input.equals("1")) {
            //enrol a course
            return addCourse(studentID_and_semester, getEnrolmentList());
        }
        if (user_input.equals("2")) {
            //del a course
            return delCourse(studentID_and_semester, getEnrolmentList());
        }
        System.out.println("Invalid input!");
        return false;
    }

    public boolean searchEnrolment() {
        //search and save file
        ArrayList<StudentEnrolment> searched_answer_lists = searchProfile();
        if (searched_answer_lists.isEmpty()) {
            //no match
            return false;
        }
        for (StudentEnrolment enrolment : searched_answer_lists) {
            System.out.println(enrolment.toCSV());
        }
        //ask to save file
        if (askToSaveFile()) {
            saveFileToDesktop(searched_answer_lists);
        }
        return true;
    }

    public void run() {
        init();
        String user_input = "";
        while (!user_input.equals("0")) {
            System.out.print("[1]: Add new enrolment profile.\n[2]: Update existing profile.\n[3]: Search existing profile.\n[0]: Exit.\nInput: ");
            user_input = readUserInput();
            switch (user_input) {
                case "0":   //exit
                    if (askToSaveFile()) {
                        if (isSaveDefault()) {
                            saveFileDefault(getEnrolmentList());
                        }
                        else {
                            saveFileToLocation(getEnrolmentList(), askFilePath());
                        }
                    }
                    System.out.println("Closing application.....\nDone!");
                    break;

                case "1":   //add a new profile from user input
                    if (addEnrolment()) {
                        System.out.println("New profile added successful!");
                        break;
                    }
                    System.out.println("Error: New profile failed to save!");
                    break;

                case "2":
                    if (updateEnrolment()) {
                        System.out.println("Update successful!");
                        break;
                    }
                    System.out.println("Error: New update failed to save!");
                    break;

                case "3":
                    if (searchEnrolment()) {
                        System.out.println("Ending search procedure...");
                        break;
                    }
                    System.out.println("No match found in search!");
                    break;

                default:    //invalid user input catch
                    System.out.println("Invalid input!\n\n\n");
                    break;
            }
        }
    }
}
