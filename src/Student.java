public class Student {
    private String studentID;
    private String studentName;
    private Dates dayOfBirth;

    public Student() {
        studentID = "S000";
        studentName = "Default";
        dayOfBirth = null;
    }
    public Student(String studentID, String studentName, Dates dayOfBirth) {
        this.studentID = studentID.toUpperCase();
        this.studentName = studentName;
        this.dayOfBirth = dayOfBirth;
    }

    public boolean setStudentID(String studentID) {
        int number;
        boolean valid = true;
        String temp = studentID.substring(1);

        try {
            number = Integer.parseInt(temp);
        } catch (NumberFormatException e) {
//            e.printStackTrace();
            valid = false;
        }

        if (studentID.toUpperCase().startsWith("S") && studentID.length() == 7 && valid) {
            this.studentID = studentID.toUpperCase();
            return true;
        }
        return false;
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
