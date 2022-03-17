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
    public StudentEnrolment(Student student, String semester) {
        this.student = student;
        this.coursesList = new ArrayList<Course>();
        this.semester = semester;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Student getStudent() {
        return student;
    }
    public ArrayList<Course> getCoursesList() {
        return coursesList;
    }
    public String getSemester() {
        return semester;
    }

    public boolean enrollCourse(Course course) {
        if (this.coursesList.contains(course)) {
//            System.out.println("Student with ID [" + this.student.getStudentID() + "] has already enrolled course [" + course.getCourseID() + "] in semester [" + this.semester + "].");
            return false;
        }
        else {
            coursesList.add(course);
            //System.out.println("Successfully enroll into [" + course.getCourseID() + "] for semester [" + semester + "].");
            return true;
        }
    }
    public boolean dropCourse(Course course) {
        if (coursesList.contains(course)) {
            coursesList.remove(course);
            System.out.println("Course [" + course.getCourseID() + "] has been dropped.");
            return true;
        }
        return false;
    }
    public void dropAllCourse() {
        if (coursesList.isEmpty()) {
            System.out.println("No course left to drop in semester [" + semester + "].");
        }
        else {
            coursesList.clear();
            System.out.println("All course has been drop for semester [" + semester + "].");
        }
    }

}
