package interfaces;

public interface StudentEnrolmentManager {
    public void init();

    public boolean addEnrolment();
    public boolean updateEnrolment();
    public boolean searchEnrolment();

    public void run();
}
