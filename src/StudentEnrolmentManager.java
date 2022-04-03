import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class StudentEnrolmentManager {
    //default pathing (can be edited) Ex: "C:\\Users\\Admin\\Desktop\\default.csv"
    private static String default_path = "default.csv";
    private ArrayList<StudentEnrolment> student_enrolments_list;

    //constructor
    public StudentEnrolmentManager() {
        student_enrolments_list = new ArrayList<StudentEnrolment>();
    }
    //get list method
    public ArrayList<StudentEnrolment> getStudent_enrolments_list() {
        return student_enrolments_list;
    }
    //utility functions
    static String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    static String askFilePath() {
        System.out.print("Welcome to the Student Enrolment Manager software (S.E.M)\nNavigate by enter the [number] correspond to the option:\n");
        System.out.print("[1]: Initialize using the default path.\n[2]: Initialize using user-inputted path.\nInput path: ");
        String path = readUserInput();
        if (path.equals("2")) {
            System.out.print("Enter file path: ");
            path = readUserInput();
            return path;
        }
        if (path.equals("1")) {
            return default_path;
        }
        System.out.println("Input error\nInitializing using default path...");
        return default_path;
    }

    static Dates readDate(String dob) {
        String[] month_day_year = dob.split("/");
        Dates day_of_birth_temp = new Dates();
        day_of_birth_temp.setDate(Integer.parseInt(month_day_year[0]), Integer.parseInt(month_day_year[1]), Integer.parseInt(month_day_year[2]));
        return day_of_birth_temp;
    }
    static Student readStudent(String[] input_string) {
        Student student_temp = new Student();
        student_temp.setStudentID(input_string[0]);
        student_temp.setStudentName(input_string[1]);
        student_temp.setDate(readDate(input_string[2]));
        return student_temp;
    }
    static Course readCourse(String[] input_string) {
        Course course_temp = new Course();
        course_temp.setCourseID(input_string[3]);
        course_temp.setCourseName(input_string[4]);
        course_temp.setCredit(Integer.parseInt(input_string[5]));
        return course_temp;
    }
    static StudentEnrolment createProfile(String[] input_string) {
        StudentEnrolment student_enrolment_info_temp = new StudentEnrolment();
        student_enrolment_info_temp.setStudent(readStudent(input_string));
        student_enrolment_info_temp.setCourses(readCourse(input_string));
        student_enrolment_info_temp.setSemester(input_string[6]);
        return student_enrolment_info_temp;
    }

    public boolean init() {
        String read_line = "";
        try {
//            Scanner scannerObj = new Scanner(new File("C:\\Users\\loilo\\Desktop\\default.csv"));   //find and read the CSV file
            Scanner scannerObj = new Scanner(new File(askFilePath()));
            while (scannerObj.hasNextLine()) {  //while reading the file line per line
                read_line = scannerObj.nextLine();
                //parse into substrings containing std_id, std_name, std_dob, crs_id, crs_name, crs_cred, semester
                String[] input_profile = read_line.split(",");
                //assembling the profile
                StudentEnrolment student_enrolment_info = createProfile(input_profile);
                //add profile to list
                student_enrolments_list.add(student_enrolment_info);
            }
            scannerObj.close();
            System.out.println("Student enrolment list has been initialize.");
            return true;
        } catch (FileNotFoundException e) { //error handling
//            e.printStackTrace();
            System.out.println("Error! File path invalid.");
            return false;
        }
    }

    public static void main(String[] args) {
        StudentEnrolmentManager system = new StudentEnrolmentManager();

        if (system.init()) {
            System.out.println(system.getStudent_enrolments_list().toString());
        }

    }
}
