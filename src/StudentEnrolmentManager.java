import Model.Course;
import Model.Dates;
import Model.Student;
import Model.StudentEnrolment;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class StudentEnrolmentManager {
    /**
     * A list for keeping enrolment profile
     */
    private ArrayList<StudentEnrolment> student_enrolments_list;

    /**
     * Constructor to initialize the list
     */
    public StudentEnrolmentManager() {
        student_enrolments_list = new ArrayList<StudentEnrolment>();
    }

    /**
     * Getter for the enrolment array list
     * @return This object enrolment list
     */
    public ArrayList<StudentEnrolment> getStudent_enrolments_list() {
        return student_enrolments_list;
    }


    /**
     * Parse a string with format "mm/dd/yy" and return a Model.Dates object
     * @param day_of_birth A string with format "mm/dd/yy"
     * @return Model.Dates object
     */
    static Dates readDate(String day_of_birth) {
        String[] month_day_year = day_of_birth.split("/");
        Dates day_of_birth_temp = new Dates();
        day_of_birth_temp.setDate(Integer.parseInt(month_day_year[0]), Integer.parseInt(month_day_year[1]), Integer.parseInt(month_day_year[2]));
        return day_of_birth_temp;
    }
    /**
     * Read from a String[] and sort out the variable needed for the Model.Student obj
     * @param input_string A string array of 7 component
     * @return Model.Student object
     */
    static Student readStudent(String[] input_string) {
        Student student_temp = new Student();
        student_temp.setStudentID(input_string[0]);
        student_temp.setStudentName(input_string[1]);
        student_temp.setDate(readDate(input_string[2]));
        return student_temp;
    }
    /**
     * Read from a String[] and sort out the variable needed for the Model.Course obj
     * @param input_string A string array of 7 component
     * @return Model.Course object
     */
    static Course readCourse(String[] input_string) {
        Course course_temp = new Course();
        course_temp.setCourseID(input_string[3]);
        course_temp.setCourseName(input_string[4]);
        course_temp.setCredit(Integer.parseInt(input_string[5]));
        return course_temp;
    }
    /**
     * Create a Model.StudentEnrolment object from a String
     * @param input_string A string that can be parsed into 7 components
     * @return Model.StudentEnrolment object
     */
    static StudentEnrolment createProfile(String input_string) {
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
    static boolean checkStringComponent(String input) {
        String[] input_string = input.split(",");
        return Arrays.stream(input_string).count() == 7;
    }
    static boolean checkDate(String day_of_birth) {
        String[] month_day_year = day_of_birth.split("/");
        Dates day_of_birth_temp = new Dates();
        try {
            return day_of_birth_temp.setDate(Integer.parseInt(month_day_year[0]), Integer.parseInt(month_day_year[1]), Integer.parseInt(month_day_year[2]));
        } catch (NumberFormatException e) {
//            e.printStackTrace();
            return false;
        }
    }
    static boolean checkStudent(String[] input_string) {
        Student student_temp = new Student();
        return checkDate(input_string[2]) && student_temp.setStudentID(input_string[0]);
    }
    static boolean checkCourse(String[] input_string) {
        try {
            Course course_temp = new Course();
            course_temp.setCredit(Integer.parseInt(input_string[5]));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    static boolean checkProfile(String input_string) {
        String[] parsed_string = input_string.split(",");
        return checkStudent(parsed_string) && checkCourse(parsed_string);
    }


    static ArrayList<Course> getStudentCourseList(String student_id, String semester, ArrayList<StudentEnrolment> enrolment_list) {
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
    static StudentEnrolment copyStudentProfileForAdding(String student_id, String semester, ArrayList<StudentEnrolment> enrolment_list) {
        if (enrolment_list.isEmpty()) {
            //terminate
            System.out.println("Error: Enrolment list is empty!");
            return null;
        }
        for (StudentEnrolment enrolment : enrolment_list) {
            if (student_id.equals(enrolment.getStudent().getStudentID()) && semester.equals(enrolment.getSemester())) {
                return new StudentEnrolment(enrolment);
            }
        }
        return null;
    }
    static StudentEnrolment copyStudentProfileForDropping(String student_id, String course_id, String semester, ArrayList<StudentEnrolment> enrolment_list) {
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
    public ArrayList<StudentEnrolment> searchProfile() {
        ArrayList<StudentEnrolment> return_list = new ArrayList<StudentEnrolment>();
        String option = Utilities.askSearchMethod();
        switch (option) {
            case "1" -> {
                System.out.print("Enter student ID: ");
                String std_id = Utilities.readUserInput().toUpperCase();
                for (StudentEnrolment enrolment : getStudent_enrolments_list()) {
                    if (std_id.equals(enrolment.getStudent().getStudentID())) {
                        return_list.add(new StudentEnrolment(enrolment));
                    }
                }
                return return_list;
            }
            case "2" -> {
                System.out.print("Enter course ID: ");
                String crs_id = Utilities.readUserInput().toUpperCase();
                for (StudentEnrolment enrolment : getStudent_enrolments_list()) {
                    if (crs_id.equals(enrolment.getCourses().getCourseID())) {
                        return_list.add(new StudentEnrolment(enrolment));
                    }
                }
                return return_list;
            }
            case "3" -> {
                System.out.print("Enter semester: ");
                String sem = Utilities.readUserInput().toUpperCase();
                for (StudentEnrolment enrolment : getStudent_enrolments_list()) {
                    if (sem.equals(enrolment.getSemester())) {
                        return_list.add(new StudentEnrolment(enrolment));
                    }
                }
                return return_list;
            }
            case "4" -> {
                for (StudentEnrolment enrolment : getStudent_enrolments_list()) {
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
            System.out.println("Error while trying to create [" + default_save_file_name + "] at Path: " + default_desktop_path);
            return false;
        }
    }

    /**
     * Start to find and read file to populate the enrolment array list
     */
    public void init() {
        String read_line = "";
        while (true) {
            try {
//            Scanner reader_object = new Scanner(new File("C:\\Users\\loilo\\Desktop\\default.csv"));   //find and read the CSV file
                Scanner reader_object = new Scanner(new File(Utilities.askFilePath()));
                while (reader_object.hasNextLine()) {  //while reading the file line per line
                    read_line = reader_object.nextLine();
                    if (checkStringComponent(read_line)) {
                        //assembling the profile
                        StudentEnrolment student_enrolment_info = createProfile(read_line);
                        //add profile to list
                        student_enrolments_list.add(student_enrolment_info);
                    }
                }
                reader_object.close();
                System.out.println("Model.Student enrolment list has been initialize.");
                break;
            } catch (FileNotFoundException e) { //error handling
//            e.printStackTrace();
                System.out.println("Error! File path invalid.\nResetting app....\n\n\n\n");
            }
        }

    }
    public boolean addEnrolment() {
        System.out.println("Please enter the enrolment profile according to the format shown:");
        System.out.println("Student_ID(S123456),Student_Name(John Doe),Student_day_of_birth(mm/dd/yy)," +
                            "Course_ID,Course_name,Course_credit," +
                            "Semester_of_enrolment");
        System.out.print("Note: Do not use space after or before comma (',')!\nInput: ");
        String user_input = Utilities.readUserInput();
        if (checkStringComponent(user_input) && checkProfile(user_input)) {
            getStudent_enrolments_list().add(createProfile(user_input));
            return true;
        }
        System.out.println("Incorrect input format.");
        return false;
    }
    public boolean updateEnrolment() {
        String[] studentID_and_semester = Utilities.askSTDIDandSemester();
        ArrayList<Course> course_list = getStudentCourseList(studentID_and_semester[0], studentID_and_semester[1], getStudent_enrolments_list());
        if (course_list.isEmpty()) {
            System.out.println("Model.Student [" + studentID_and_semester[0] + "] does not have a profile for semester [" + studentID_and_semester[1] + "]");
            return false;
        }
        System.out.println("Model.Course for student [" + studentID_and_semester[0] + "] of semester [" + studentID_and_semester[1] + "]:");
        System.out.println(course_list.toString());
        System.out.print("[1]: Add/Enroll a new course.\n[2]: Delete/Drop an existing course.\nInput: ");
        String user_input = Utilities.readUserInput();
        if (user_input.equals("1")) {
            //enrol a course
            return addCourse(studentID_and_semester, getStudent_enrolments_list());
        }
        if (user_input.equals("2")) {
            //del a course
            return delCourse(studentID_and_semester, getStudent_enrolments_list());
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
        if (Utilities.askToSaveFile()) {
            saveFileToDesktop(searched_answer_lists);
        }
        return true;
    }

    public void run() {
        StudentEnrolmentManager system = new StudentEnrolmentManager();
        system.init();
        String user_input = "";
        while (!user_input.equals("0")) {
            System.out.print("[1]: Add new enrolment profile.\n[2]: Update existing profile.\n[3]: Search existing profile.\n[0]: Exit.\nInput: ");
            user_input = Utilities.readUserInput();
            switch (user_input) {
                case "0":   //exit
                    if (Utilities.askToSaveFile()) {
                        if (Utilities.isSaveDefault()) {
                            saveFileDefault(system.getStudent_enrolments_list());
                        }
                        else {
                            saveFileToLocation(system.getStudent_enrolments_list(), Utilities.askFilePath());
                        }
                    }
                    System.out.println("Closing application.....\nDone!");
                    break;

                case "1":   //add a new profile from user input
                    if (system.addEnrolment()) {
                        System.out.println("New profile added successful!");
                        break;
                    }
                    System.out.println("Error: New profile failed to save!");
                    break;

                case "2":
                    if (system.updateEnrolment()) {
                        System.out.println("Update successful!");
                        break;
                    }
                    System.out.println("Error: New update failed to save!");
                    break;

                case "3":
                    if (system.searchEnrolment()) {
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
