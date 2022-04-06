package utilities;

import student.Student;
import course.Course;
import enrolment.StudentEnrolment;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static enrolment.RMITStudentEnrolmentManager.getEnrolmentList;

public class Utilities {
    public static final String DEFAULT_FILE_NAME = "default";
    public static final String DEFAULT_SAVE_FILE_NAME = "Report file";
    public static final String CSV_FILE_TYPE = ".csv";
    public static final String DEFAULT_DESKTOP_PATH = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();

    //utility functions
    public static String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static Course readCourseFromUser(String[] input_string) {
        Course course_temp = new Course();
        course_temp.setCourseID(input_string[0]);
        course_temp.setCourseName(input_string[1]);
        try {
            course_temp.setCredit(Integer.parseInt(input_string[2]));
        } catch (NumberFormatException e) {
            System.out.println("Error: Incorrect format!");
            return null;
        }
        return course_temp;
    }

    /**
     * Prompt user with opiton of using the default file path or choose a different one
     * @return String object
     */
    public static String askFilePath() {
        System.out.print("Welcome to the Student Enrolment Manager software (S.E.M)\nNavigate by enter the [number] correspond to the option:\n");
        System.out.print("[1]: Initialize using the default path.\n[2]: Initialize using user-inputted path.\nInput path: ");
        String path = readUserInput();
        if (path.equals("2")) {
            System.out.print("Enter file path: ");
            path = readUserInput();
            return path;
        }
        if (path.equals("1")) {
            return DEFAULT_FILE_NAME + CSV_FILE_TYPE;
        }
        return "";
    }

    public static String[] askSTDIDandSemester() {
        String[] return_string = new String[2];
        System.out.print("Enter student ID: ");
        return_string[0] = readUserInput().toUpperCase();
        System.out.print("Enter enrolment semester: ");
        return_string[1] = readUserInput().toUpperCase();
        return return_string;
    }

    public static String askSearchMethod() {
        System.out.print("[1]: Search via student ID.\n[2]: Search via course ID.\n[3]: Search via semester of enrolment.\n[4]: Get all enrolment profile.\nInput: ");
        return readUserInput();
    }

    public static boolean askToSaveFile() {
        System.out.println("Would you like to save the result to a file?");
        System.out.print("[Y]: Save.\n[N]: Do not save.\nInput: ");
        while (true) {
            String user_input = readUserInput().toUpperCase();
            if (user_input.equals("Y")) {
                return true;
            }
            if (user_input.equals("N")) {
                return false;
            }
            System.out.print("Incorrect input!\nInput: ");
        }
    }

    public static boolean isSaveDefault() {
        System.out.println("[1]: Save using default path.\n[2]: Save using user-inputted path.\nInput: ");
        while (true){
            String user_input = readUserInput();
            if (user_input.equals("1")) {
                return true;
            }
            if (user_input.equals("2")) {
                return false;
            }
            System.out.println("Incorrect input!");
        }
    }

    /**
     * Parse a string with format "mm/dd/yy" and return a Dates object
     * @param day_of_birth A string with format "mm/dd/yy"
     * @return Dates object
     */
    public static Dates readDate(String day_of_birth) {
        String[] month_day_year = day_of_birth.split("/");
        Dates day_of_birth_temp = new Dates();
        day_of_birth_temp.setDate(Integer.parseInt(month_day_year[0]), Integer.parseInt(month_day_year[1]), Integer.parseInt(month_day_year[2]));
        return day_of_birth_temp;
    }

    /**
     * Read from a String[] and sort out the variable needed for the Student obj
     * @param input_string A string array of 7 component
     * @return Student object
     */
    public static Student readStudent(String[] input_string) {
        Student student_temp = new Student();
        student_temp.setStudentID(input_string[0]);
        student_temp.setStudentName(input_string[1]);
        student_temp.setDate(readDate(input_string[2]));
        return student_temp;
    }

    /**
     * Read from a String[] and sort out the variable needed for the Course obj
     * @param input_string A string array of 7 component
     * @return Course object
     */
    public static Course readCourse(String[] input_string) {
        Course course_temp = new Course();
        course_temp.setCourseID(input_string[3]);
        course_temp.setCourseName(input_string[4]);
        course_temp.setCredit(Integer.parseInt(input_string[5]));
        return course_temp;
    }

    /**
     * Create a StudentEnrolment object from a String
     * @param input_string A string that can be parsed into 7 components
     * @return StudentEnrolment object
     */
    public static StudentEnrolment createProfile(String input_string) {
        String[] parsed_string = input_string.split(",");   //parse into substrings containing std_id, std_name, std_dob, crs_id, crs_name, crs_cred, semester
        StudentEnrolment student_enrolment_info_temp = new StudentEnrolment();
        student_enrolment_info_temp.setStudent(readStudent(parsed_string));
        student_enrolment_info_temp.setCourses(readCourse(parsed_string));
        student_enrolment_info_temp.setSemester(parsed_string[6]);
        return student_enrolment_info_temp;
    }

    /**
     * Check the input string if it is valid for parsing into 7 substring
     * @param input String with 6 ','
     * @return String[] with 7 component
     */
    public static boolean checkStringComponent(String input) {
        String[] input_string = input.split(",");
        return Arrays.stream(input_string).count() == 7;
    }

    public static boolean checkDate(String day_of_birth) {
        String[] month_day_year = day_of_birth.split("/");
        Dates day_of_birth_temp = new Dates();
        try {
            return day_of_birth_temp.setDate(Integer.parseInt(month_day_year[0]), Integer.parseInt(month_day_year[1]), Integer.parseInt(month_day_year[2]));
        } catch (NumberFormatException e) {
//            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkStudent(String[] input_string) {
        Student student_temp = new Student();
        return checkDate(input_string[2]) && student_temp.setStudentID(input_string[0]);
    }

    public static boolean checkCourse(String[] input_string) {
        try {
            Course course_temp = new Course();
            course_temp.setCredit(Integer.parseInt(input_string[5]));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean checkProfile(String input_string) {
        String[] parsed_string = input_string.split(",");
        return checkStudent(parsed_string) && checkCourse(parsed_string);
    }

    public static ArrayList<Course> getStudentCourseList(String student_id, String semester, ArrayList<StudentEnrolment> enrolment_list) {
        if (enrolment_list.isEmpty()) {
            //terminate
            System.out.println("Error: Enrolment list is empty!");
            return new ArrayList<Course>();
        }
        ArrayList<Course> return_courses_list = new ArrayList<Course>();
        for (StudentEnrolment enrolment : enrolment_list) {
            if (student_id.equals(enrolment.getStudent().getStudentID()) && semester.equals(enrolment.getSemester())) {
                return_courses_list.add(enrolment.getCourses());
            }
        }
        return return_courses_list;
    }

    public static StudentEnrolment copyStudentProfileForAdding(String student_id, String semester, ArrayList<StudentEnrolment> enrolment_list) {
        if (enrolment_list.isEmpty()) {
            //terminate
            System.out.println("Error: Enrolment list is empty!");
            return new StudentEnrolment();
        }
        for (StudentEnrolment enrolment : enrolment_list) {
            if (student_id.equals(enrolment.getStudent().getStudentID()) && semester.equals(enrolment.getSemester())) {
                return new StudentEnrolment(enrolment);
            }
        }
        return new StudentEnrolment();
    }

    public static StudentEnrolment copyStudentProfileForDropping(String student_id, String course_id, String semester, ArrayList<StudentEnrolment> enrolment_list) {
        if (enrolment_list.isEmpty()) {
            //terminate
            System.out.println("Error: Enrolment list is empty!");
            return null;
        }
        for (StudentEnrolment enrolment : enrolment_list) {
            if (student_id.equals(enrolment.getStudent().getStudentID())
                    && course_id.equals(enrolment.getCourses().getCourseID())
                    && semester.equals(enrolment.getSemester())) {
                return enrolment;
            }
        }
        return null;
    }

    public static ArrayList<StudentEnrolment> searchProfile() {
        ArrayList<StudentEnrolment> return_list = new ArrayList<StudentEnrolment>();
        String option = askSearchMethod();
        switch (option) {
            case "1" -> {
                System.out.print("Enter student ID: ");
                String std_id = readUserInput().toUpperCase();
                for (StudentEnrolment enrolment : getEnrolmentList()) {
                    if (std_id.equals(enrolment.getStudent().getStudentID())) {
                        return_list.add(new StudentEnrolment(enrolment));
                    }
                }
                return return_list;
            }
            case "2" -> {
                System.out.print("Enter course ID: ");
                String crs_id = readUserInput().toUpperCase();
                for (StudentEnrolment enrolment : getEnrolmentList()) {
                    if (crs_id.equals(enrolment.getCourses().getCourseID())) {
                        return_list.add(new StudentEnrolment(enrolment));
                    }
                }
                return return_list;
            }
            case "3" -> {
                System.out.print("Enter semester: ");
                String sem = readUserInput().toUpperCase();
                for (StudentEnrolment enrolment : getEnrolmentList()) {
                    if (sem.equals(enrolment.getSemester())) {
                        return_list.add(new StudentEnrolment(enrolment));
                    }
                }
                return return_list;
            }
            case "4" -> {
                for (StudentEnrolment enrolment : getEnrolmentList()) {
                    return_list.add(new StudentEnrolment(enrolment));
                }
                return return_list;
            }
            default -> {
                System.out.println("Invalid input");
                return return_list;
            }
        }
    }

    public static boolean addCourse(String[] studentID_and_semester, ArrayList<StudentEnrolment> enrolment_list) {
        System.out.print("Enter the course information as follow:\nCourse_ID,Course_name,Course_credit\nNote: Do not use space after or before comma (',')!\nInput: ");
        String user_input = readUserInput();
        String[] course_info = user_input.split(",");
        Course course_temp = readCourseFromUser(course_info);
        if (course_temp == null) {
            return false;
        }
        StudentEnrolment enrolment_temp = copyStudentProfileForAdding(studentID_and_semester[0], studentID_and_semester[1], enrolment_list);
        enrolment_temp.setCourses(course_temp);
        enrolment_list.add(enrolment_temp);
        return true;
    }

    public static boolean delCourse(String[] studentID_and_semester, ArrayList<StudentEnrolment> enrolment_list) {
        System.out.print("Enter the course information as follow:\nCourse_ID,Course_name,Course_credit\nNote: Do not use space after or before comma (',')!\nInput: ");
        String user_input = readUserInput();
        String[] course_info = user_input.split(",");
        Course course_temp = readCourseFromUser(course_info);
        if (course_temp == null) {
            return false;
        }
        StudentEnrolment enrolment_temp = copyStudentProfileForDropping(studentID_and_semester[0], course_temp.getCourseID(), studentID_and_semester[1], enrolment_list);
        enrolment_list.remove(enrolment_temp);
        return true;
    }

    public static boolean saveFileToDesktop(ArrayList<StudentEnrolment> enrolments) {
        String file_to_create = DEFAULT_DESKTOP_PATH + "\\" + DEFAULT_SAVE_FILE_NAME;
        try {
            int i = 1;
            while (true) {
                File file = new File(file_to_create + CSV_FILE_TYPE);
                if (file.createNewFile()) {
                    //write to file
                    Writer writer = new FileWriter(file_to_create + CSV_FILE_TYPE);
                    for (StudentEnrolment enrolment : enrolments) {
                        writer.write(enrolment.toCSV() + "\n");
                    }
                    writer.flush();
                    writer.close();
                    System.out.println("File saved at: " + file_to_create + CSV_FILE_TYPE);
                    return true;
                }
                else {
                    file_to_create = file_to_create + "_" + String.valueOf(i);
                    i++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error while trying to create [" + DEFAULT_SAVE_FILE_NAME + "] at Path: " + DEFAULT_DESKTOP_PATH);
            return false;
        }
    }

    public static boolean saveFileDefault(ArrayList<StudentEnrolment> enrolments) {
        String file_to_create = DEFAULT_FILE_NAME;
        try {
            int i = 1;
            File file = new File(file_to_create + CSV_FILE_TYPE);
            //write to file
            Writer writer = new FileWriter(file_to_create + CSV_FILE_TYPE);
            for (StudentEnrolment enrolment : enrolments) {
                writer.write(enrolment.toCSV() + "\n");
            }
            writer.flush();
            writer.close();
            System.out.println("File saved at: " + file_to_create + CSV_FILE_TYPE);
            return true;
        } catch (IOException e) {
            System.out.println("Error while trying to create [" + DEFAULT_SAVE_FILE_NAME + "] at Path: " + DEFAULT_DESKTOP_PATH);
            return false;
        }
    }

    public static boolean saveFileToLocation(ArrayList<StudentEnrolment> enrolments, String file_path) {
        try {
            int i = 1;
            File file = new File(file_path);
            //write to file
            Writer writer = new FileWriter(file_path);
//                    String write_line;
            for (StudentEnrolment enrolment : enrolments) {
//                        write_line = enrolment.toCSV();
                writer.write(enrolment.toCSV() + "\n");
            }
            writer.flush();
            writer.close();
            System.out.println("File saved at: " + file_path);
            return true;
        } catch (IOException e) {
            System.out.println("Error while trying to create [" + DEFAULT_SAVE_FILE_NAME + "] at Path: " + DEFAULT_DESKTOP_PATH);
            return false;
        }
    }
}
