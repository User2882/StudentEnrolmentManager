import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class StudentEnrolmentManager {
    /**
    * Default pathing (can be edited) Ex: "C:\\Users\\Admin\\Desktop\\default.csv"
    */
    private static final String default_path = "default.csv";

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


    //utility functions
    static String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    static Course readCourseFromUser(String[] input_string) {
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
    static String askFilePath() {
        System.out.print("Welcome to the Student Enrolment Manager software (S.E.M)\nNavigate by enter the [number] correspond to the option:\n");
        System.out.print("[1]: Initialize using the default path.\n[2]: Initialize using user-inputted path.\n[0]: Exit.\nInput path: ");
        String path = readUserInput();
        if (path.equals("2")) {
            System.out.print("Enter file path: ");
            path = readUserInput();
            return path;
        }
        if (path.equals("1")) {
            return default_path;
        }
        return "";
    }
    static String[] askSTDIDandSemester() {
        String[] return_string = new String[2];
        System.out.print("Enter student ID: ");
        return_string[0] = readUserInput().toUpperCase();
        System.out.print("Enter enrolment semester: ");
        return_string[1] = readUserInput().toUpperCase();
        return return_string;
    }
    static String askSearchMethod() {
        System.out.print("[1]: Search via student ID.\n[2]: Search via course ID.\n[3]: Search via semester of enrolment.\nInput: ");
        return readUserInput();
    }


    /**
     * Parse a string with format "mm/dd/yy" and return a Dates object
     * @param day_of_birth A string with format "mm/dd/yy"
     * @return Dates object
     */
    static Dates readDate(String day_of_birth) {
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
    static Student readStudent(String[] input_string) {
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
    static Course readCourse(String[] input_string) {
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
        String option = askSearchMethod();
        switch (option) {
            case "1" -> {
                System.out.print("Enter student ID: ");
                String std_id = readUserInput().toUpperCase();
                for (StudentEnrolment enrolment : getStudent_enrolments_list()) {
                    if (std_id.equals(enrolment.getStudent().getStudentID())) {
                        return_list.add(new StudentEnrolment(enrolment));
                    }
                }
                return return_list;
            }
            case "2" -> {
                System.out.print("Enter course ID: ");
                String crs_id = readUserInput().toUpperCase();
                for (StudentEnrolment enrolment : getStudent_enrolments_list()) {
                    if (crs_id.equals(enrolment.getCourses().getCourseID())) {
                        return_list.add(new StudentEnrolment(enrolment));
                    }
                }
                return return_list;
            }
            case "3" -> {
                System.out.print("Enter semester: ");
                String sem = readUserInput().toUpperCase();
                for (StudentEnrolment enrolment : getStudent_enrolments_list()) {
                    if (sem.equals(enrolment.getSemester())) {
                        return_list.add(new StudentEnrolment(enrolment));
                    }
                }
                return return_list;
            }
            default -> {
                System.out.println("Invalid input");
                return return_list;
            }
        }
    }

    static boolean addCourse(String[] studentID_and_semester, ArrayList<StudentEnrolment> enrolment_list) {
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
    static boolean delCourse(String[] studentID_and_semester, ArrayList<StudentEnrolment> enrolment_list) {
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

    /**
     * Start to find and read file to populate the enrolment array list
     */
    public void init() {
        String read_line = "";
        while (true) {
            try {
//            Scanner reader_object = new Scanner(new File("C:\\Users\\loilo\\Desktop\\default.csv"));   //find and read the CSV file
                Scanner reader_object = new Scanner(new File(askFilePath()));
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
                System.out.println("Student enrolment list has been initialize.");
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
        String user_input = readUserInput();
        if (checkStringComponent(user_input) && checkProfile(user_input)) {
            getStudent_enrolments_list().add(createProfile(readUserInput()));
            return true;
        }
        System.out.println("Incorrect input format.");
        return false;
    }
    public boolean updateEnrolment() {
        String[] studentID_and_semester = askSTDIDandSemester();
        ArrayList<Course> course_list = getStudentCourseList(studentID_and_semester[0], studentID_and_semester[1], getStudent_enrolments_list());
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
            return addCourse(studentID_and_semester, getStudent_enrolments_list());
        }
        if (user_input.equals("2")) {
            //del a course
            return delCourse(studentID_and_semester, getStudent_enrolments_list());
        }
        System.out.println("Invalid input!");
        return false;
    }
    public boolean search() {
        //search and save file
    }

    public static void main(String[] args) {
        StudentEnrolmentManager system = new StudentEnrolmentManager();
        system.init();
        String user_input = "";
        while (!user_input.equals("0")) {
            System.out.print("[1]: Add new enrolment profile.\n[2]: Update existing profile.\n[3]: Search existing profile.\n[0]: Exit.\nInput: ");
            user_input = readUserInput();
            switch (user_input) {
                case "0":   //exit
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

                    break;

                default:    //invalid user input catch
                    System.out.println("Invalid input!\n\n\n");
                    break;
            }
        }
    }
}
