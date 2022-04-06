package course;

public class Course {
    private String courseID;
    private String courseName;
    private int credit;

    public Course() {
        this.courseID = "C000";
        this.courseName = "Default";
        this.credit = 0;
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

    public String toCSV() {
        return courseID + "," + courseName + "," + credit;
    }
    @Override
    public String toString() {
        return courseID + " | " + courseName + " | " + credit;
    }
}
