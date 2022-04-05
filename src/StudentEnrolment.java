import java.util.ArrayList;
import java.util.Objects;

public class StudentEnrolment {
    private Student student;
    private Course courses;
    private String semester;

    //normal constructor
    public StudentEnrolment() {
        student = new Student();
        courses = new Course();
        semester = "Default";
    }
    //copy constructor
    public StudentEnrolment(StudentEnrolment enrolment) {
        this.student = enrolment.getStudent();
        this.courses = enrolment.getCourses();
        this.semester = enrolment.getSemester();
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    public void setCourses(Course courses) {
        this.courses = courses;
    }
    public void setSemester(String semester) {
        this.semester = semester.toUpperCase();
    }

    public Student getStudent() {
        return student;
    }
    public Course getCourses() {
        return courses;
    }
    public String getSemester() {
        return semester;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StudentEnrolment o1 = (StudentEnrolment) o;
        return student.equals(o1.getStudent()) && semester.equals(o1.getSemester()) && courses.equals(o1.courses);
    }

    public String toCSV() {
        return student.toCSV() + "," + courses.toCSV() + "," + semester;
    }
    @Override
    public String toString() {
        return student + " | " + courses + " | " + semester;
    }
}
