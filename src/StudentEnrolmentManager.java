public class StudentEnrolmentManager {
    public static void main(String[] args) {
        System.out.println("hello");

        Dates d1 = new Dates(1,1,2000);
        Dates d2 = new Dates(2,2,3000);

        Student s1 = new Student("s001", "Max", d1);
        Student s2 = new Student("s002", "Mike", d2);

        Course c1 = new Course("EEET1000", "Math", 12);
        Course c2 = new Course("EEET1001", "Art", 12);

        StudentEnrolment enroll1 = new StudentEnrolment(s1, "2020A");
        StudentEnrolment enroll2 = new StudentEnrolment(s2, "2020B");

        enroll1.enroll(c1);
        enroll1.enroll(c2);
        enroll2.enroll(c1);

        System.out.println(enroll2.getCoursesList().get(0).getCourseID());
        System.out.println("Done!");
    }
}
