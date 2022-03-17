import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class StudentEnrolmentManager {
    public static void main(String[] args) {
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
    }
}
