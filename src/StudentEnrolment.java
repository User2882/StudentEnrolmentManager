import java.util.ArrayList;

public class StudentEnrolment {
    private Student student;
    private ArrayList<Course> coursesList;
    private String semester;

    public StudentEnrolment() {
        student = new Student();
        coursesList = new ArrayList<Course>();
        semester = "Default";
    }
    public StudentEnrolment(Student student, Course course, String semester) {
        if (coursesList.contains(course)) {
            System.out.println("StudentID [" + student.getStudentID() + "] has already enrolled in course [" + course.getCourseID() + "].");
        }
        else {
            this.student = student;
            this.coursesList.add(course);
            this.semester = semester;
        }
    }
    
}
