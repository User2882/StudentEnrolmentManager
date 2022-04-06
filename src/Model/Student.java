package Model;

public class Student {
    private String studentID;
    private String studentName;
    private Dates dayOfBirth;

    public Student() {
        studentID = "S000";
        studentName = "Default";
        dayOfBirth = new Dates();
    }
    /*public Model.Student(String studentID, String studentName, Model.Dates dayOfBirth) {
        this.studentID = studentID.toUpperCase();
        this.studentName = studentName;
        this.dayOfBirth = dayOfBirth;
    }*/

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
    public boolean setStudentName(String studentName) {
        this.studentName = studentName;
        return true;
    }
    public boolean setDate(Dates dayOfBirth) {
//        this.dayOfBirth = dayOfBirth;
        return this.dayOfBirth.setDate(dayOfBirth.getMonth(), dayOfBirth.getDay(), dayOfBirth.getYear());
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student o1 = (Student) o;
        return studentID.equals(o1.studentID) && studentName.equals(o1.studentName) && dayOfBirth.equals(o1.dayOfBirth);
    }

    public String toCSV() {
        return studentID + "," + studentName+ "," + dayOfBirth.toString();
    }
    @Override
    public String toString() {
        return studentID + " | " + studentName + " | " + dayOfBirth.toString();
    }

}
