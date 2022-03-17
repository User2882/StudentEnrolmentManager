public class Student {
    private String studentID;
    private String studentName;
    private Dates dayOfBirth;

    public Student() {
        studentID = "s000";
        studentName = "Default";
        dayOfBirth = null;
    }
    public Student(String studentID, String studentName, Dates dayOfBirth) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.dayOfBirth = dayOfBirth;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public void setDate(Dates dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getStudentID() {
        return studentID;
    }
    public String getStudentName() {
        return studentName;
    }
    public Dates getDayOfBirth() {
        return dayOfBirth;
    }

    @Override
    public String toString() {
        return "[" + studentID + "]|[" + studentName + "]|[" + dayOfBirth.toString() + "]";
    }
}
