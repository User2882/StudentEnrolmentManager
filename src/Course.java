public class Course {
    private String courseID;
    private String courseName;
    private int credit;

    public Course() {
        courseID = "C000";
        courseName = "Default";
        credit = 0;
    }
    public Course(String courseID, String courseName, int credit) {
        this.courseID = courseID.toUpperCase();
        this.courseName = courseName;
        this.credit = credit;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID.toUpperCase();
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public void setCredit(int credit) {
        if (credit > 0) {
            this.credit = credit;
        }
        else {
            System.out.println("Course credit for " + courseName + " cannot be lower than 0.");
        }
    }

    public String getCourseID() {
        return courseID;
    }
    public String getCourseName() {
        return courseName;
    }
    public int getCourseCredit() {
        return credit;
    }
}
