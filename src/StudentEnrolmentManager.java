public class StudentEnrolmentManager {
    public static void main(String[] args) {
        System.out.println("hello");
        Dates d1 = new Dates(1,1,2000);
        Student s1 = new Student("s001", "Max", d1);
        Course c1 = new Course("EEET1000", "Math", 12);
        Course c2 = new Course("EEET1001", "Art", 12);

        StudentEnrolment manager = new StudentEnrolment(s1, "2000C");
        manager.enroll(c1);
        manager.enroll(c2);
        System.out.println(manager.getCoursesList().get(1).getCourseID());
        System.out.println("Done!");
    }
}
