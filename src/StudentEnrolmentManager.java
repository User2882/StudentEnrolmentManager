import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class StudentEnrolmentManager {



    public static void main(String[] args) throws IOException {
        String read_line = "";
        String separator = ",";
        ArrayList<StudentEnrolment> studentProfileList = new ArrayList<StudentEnrolment>();

        //block main function: read from CSV and store data to ArrayList
        try {
            //find and read the CSV file
            BufferedReader buffered_reader = new BufferedReader(new FileReader("C:\\Users\\loilo\\Desktop\\default.csv"));
            //while reading the file line per line
            while ((read_line = buffered_reader.readLine()) != null) {
                //parse the line read into section separated by ','
                String[] input_profile = read_line.split(separator);

                String[] dob = input_profile[2].split("/");
                Dates day_of_birth = new Dates(Integer.parseInt(dob[0]), Integer.parseInt(dob[1]), Integer.parseInt(dob[2]));
                Student student = new Student(input_profile[0], input_profile[1], day_of_birth);
                Course course = new Course(input_profile[3], input_profile[4], Integer.parseInt(input_profile[5]));

                if (studentProfileList.isEmpty()) {
                    //create first/new profile
                    StudentEnrolment student_enrolment_info = new StudentEnrolment(student, input_profile[6]);
                    student_enrolment_info.enrollCourse(course);
                    studentProfileList.add(student_enrolment_info);
                }
                //after first profile was initiate
                else {
                    //scan for existing profile with matching student and semester to update course list
                    for (int index = 0; index < studentProfileList.size(); index++) {
                        if (Objects.equals(studentProfileList.get(index).getStudent(), student) && Objects.equals(studentProfileList.get(index).getSemester(), input_profile[6])) {
                            //student info already setup and is the same semester
                            //do update/add/drop course
                            studentProfileList.get(index).enrollCourse(course);
                        }
                        else {
                            //add new profile if same student but enroll for different semester
                            StudentEnrolment student_enrolment_info = new StudentEnrolment(student, input_profile[6]);
                            student_enrolment_info.enrollCourse(course);
                            studentProfileList.add(student_enrolment_info);
                        }
                    }
                }
            }
            buffered_reader.close();
        }
        catch (IOException errorIO) {
            errorIO.printStackTrace();
        }

        System.out.println("File read and info stored!");
        //user interface start here
        String string_input = "";

        System.out.println("Welcome to the Student Enrolment Manager software (S.E.M)");
        System.out.println("Navigate by enter the [number] correspond to the option:");

        while (!Objects.equals(string_input, "0")) {
            System.out.print("[1]: Add a new enrolment profile.\n[2]: Update existing enrolment profile.\n[3]: Delete an existing enrolment profile.\n[0]: Exit.\nInput: ");
            BufferedReader read_from_console = new BufferedReader(new InputStreamReader(System.in));
            string_input = read_from_console.readLine();

            switch (string_input) {
                case "0":   //exit block
                    //need to save file
                    System.out.println("File saved!");
                    break;
                case "1":   //add new profile block
                    Student student = new Student();

                    System.out.print("Student information:\n -Student ID: ");
                    String studentID = read_from_console.readLine();
                    if (!student.setStudentID(studentID)) {
                        System.out.println("[" + studentID + "] is not a valid ID!");
                        student = null;
                        break;
                    }

                    System.out.print(" -Student Name: ");
                    String studentName = read_from_console.readLine();
                    student.setStudentName(studentName);

                    System.out.print(" -Student DOB (mm/dd/yy): ");
                    String studentDOB[] = read_from_console.readLine().split("/");
                    int month = 0, day = 0, year = 0;
                    //check for error when Integer.parseInt(String input) input contain char
                    try {
                        month = Integer.parseInt(studentDOB[0]);
                        day = Integer.parseInt(studentDOB[1]);
                        year = Integer.parseInt(studentDOB[2]);
                    } catch (NumberFormatException e) {
                        //uncomment to print out error message
                        //e.printStackTrace();
                        System.out.println("Character(s) input detected!");
                        //clear the invalid obj
                        student = null;
                        break;
                    }
                    Dates day_of_birth = new Dates();
                    if (!day_of_birth.setDate(month, day, year)) {
                        System.out.println("New student info invalid!");
                        student = null;
                        break;
                    }
                    student.setDate(day_of_birth);

                    System.out.println(" -Semester of enrolment: ");
                    String semester = read_from_console.readLine();
                    if (Integer.parseInt(semester.substring(0,3)) >= 1800 && Integer.parseInt(semester.substring(0,3)) <= 9999 &&
                            (semester.toUpperCase().endsWith("A") || semester.toUpperCase().endsWith("B") || semester.toUpperCase().endsWith("C"))) {
                        studentProfileList.add(new StudentEnrolment(student, semester));
                        System.out.println("Successfully enroll [" + studentID + "] into semester [" + semester + "]");
                        break;
                    }
                    else {
                        //clear obj
                        student = null;
                    }
                    break;
                case "2":
                    System.out.println("2");
                    break;
                case "3":
                    System.out.println("3");
                    break;
                default:
                    System.out.println("Invalid input: [" + string_input + "] is not a valid listed input option.");
                    break;
            }
        }

    }
}
