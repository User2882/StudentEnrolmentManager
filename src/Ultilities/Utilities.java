package Ultilities;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;


public class Utilities {
    /**
     * Default pathing (can be edited) Ex: "C:\Users\Admin\Desktop\default.csv"
     */
    private static final String default_file_name = "default";
    private static final String default_desktop_path = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();

    private static final String default_save_file_name = "Report file";
    private static final String default_save_file_type_csv = ".csv";


    //utility functions
    static String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    static Course readCourseFromUser(String[] input_string) {
        Course course_temp = new Course();
        course_temp.setCourseID(input_string[0]);
        course_temp.setCourseName(input_string[1]);
        try {
            course_temp.setCredit(Integer.parseInt(input_string[2]));
        } catch (NumberFormatException e) {
            System.out.println("Error: Incorrect format!");
            return null;
        }
        return course_temp;
    }

    /**
     * Prompt user with opiton of using the default file path or choose a different one
     *
     * @return String object
     */
    static String askFilePath() {
        System.out.print("Welcome to the Model.Student Enrolment Manager software (S.E.M)\nNavigate by enter the [number] correspond to the option:\n");
        System.out.print("[1]: Initialize using the default path.\n[2]: Initialize using user-inputted path.\n[0]: Exit.\nInput path: ");
        String path = readUserInput();
        if (path.equals("2")) {
            System.out.print("Enter file path: ");
            path = readUserInput();
            return path;
        }
        if (path.equals("1")) {
            return StudentEnrolmentManager.default_file_name + StudentEnrolmentManager.default_save_file_type_csv;
        }
        return "";
    }

    static String askSaveFilePath() {
        System.out.print("Enter file path: ");
        return readUserInput();
    }

    static String[] askSTDIDandSemester() {
        String[] return_string = new String[2];
        System.out.print("Enter student ID: ");
        return_string[0] = readUserInput().toUpperCase();
        System.out.print("Enter enrolment semester: ");
        return_string[1] = readUserInput().toUpperCase();
        return return_string;
    }

    static String askSearchMethod() {
        System.out.print("[1]: Search via student ID.\n[2]: Search via course ID.\n[3]: Search via semester of enrolment.\n[4]: Get all enrolment profile.\nInput: ");
        return readUserInput();
    }

    static boolean askToSaveFile() {
        System.out.println("Would you like to save the result to a file?");
        System.out.print("[Y]: Save.\n[N]: Do not save.\nInput: ");
        while (true) {
            String user_input = readUserInput().toUpperCase();
            if (user_input.equals("Y")) {
                return true;
            }
            if (user_input.equals("N")) {
                return false;
            }
            System.out.print("Incorrect input!\nInput: ");
        }
    }

    static boolean isSaveDefault() {
        System.out.println("[1]: Save using default path.\n[2]: Save using user-inputted path.\nInput: ");
        while (true) {
            String user_input = readUserInput();
            if (user_input.equals("1")) {
                return true;
            }
            if (user_input.equals("2")) {
                return false;
            }
            System.out.println("Incorrect input!");
        }
    }

    static boolean addCourse(String[] studentID_and_semester, ArrayList<StudentEnrolment> enrolment_list) {
        System.out.print("Enter the course information as follow:\nCourse_ID,Course_name,Course_credit\nNote: Do not use space after or before comma (',')!\nInput: ");
        String user_input = Utilities.readUserInput();
        String[] course_info = user_input.split(",");
        Course course_temp = Utilities.readCourseFromUser(course_info);
        if (course_temp == null) {
            return false;
        }
        StudentEnrolment enrolment_temp = copyStudentProfileForAdding(studentID_and_semester[0], studentID_and_semester[1], enrolment_list);
        enrolment_temp.setCourses(course_temp);
        enrolment_list.add(enrolment_temp);
        return true;
    }

    static boolean delCourse(String[] studentID_and_semester, ArrayList<StudentEnrolment> enrolment_list) {
        System.out.print("Enter the course information as follow:\nCourse_ID,Course_name,Course_credit\nNote: Do not use space after or before comma (',')!\nInput: ");
        String user_input = Utilities.readUserInput();
        String[] course_info = user_input.split(",");
        Course course_temp = Utilities.readCourseFromUser(course_info);
        if (course_temp == null) {
            return false;
        }
        StudentEnrolment enrolment_temp = copyStudentProfileForDropping(studentID_and_semester[0], course_temp.getCourseID(), studentID_and_semester[1], enrolment_list);
        enrolment_list.remove(enrolment_temp);
        return true;
    }

    static boolean saveFileToDesktop(ArrayList<StudentEnrolment> enrolments) {
        String file_to_create = default_desktop_path + "\\" + default_save_file_name;
        try {
            int i = 1;
            while (true) {
                File file = new File(file_to_create + default_save_file_type_csv);
                if (file.createNewFile()) {
                    //write to file
                    Writer writer = new FileWriter(file_to_create + default_save_file_type_csv);
                    for (StudentEnrolment enrolment : enrolments) {
                        writer.write(enrolment.toCSV() + "\n");
                    }
                    writer.flush();
                    writer.close();
                    System.out.println("File saved at: " + file_to_create + default_save_file_type_csv);
                    return true;
                } else {
                    file_to_create = file_to_create + "_" + String.valueOf(i);
                    i++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error while trying to create [" + default_save_file_name + "] at Path: " + default_desktop_path);
            return false;
        }
    }

    static boolean saveFileDefault(ArrayList<StudentEnrolment> enrolments) {
        String file_to_create = default_file_name;
        try {
            int i = 1;
            File file = new File(file_to_create + default_save_file_type_csv);
            //write to file
            Writer writer = new FileWriter(file_to_create + default_save_file_type_csv);
            for (StudentEnrolment enrolment : enrolments) {
                writer.write(enrolment.toCSV() + "\n");
            }
            writer.flush();
            writer.close();
            System.out.println("File saved at: " + file_to_create + default_save_file_type_csv);
            return true;
        } catch (IOException e) {
            System.out.println("Error while trying to create [" + default_save_file_name + "] at Path: " + default_desktop_path);
            return false;
        }
    }

    static boolean saveFileToLocation(ArrayList<StudentEnrolment> enrolments, String file_path) {
        try {
            int i = 1;
            File file = new File(file_path);
            //write to file
            Writer writer = new FileWriter(file_path);
//                    String write_line;
            for (StudentEnrolment enrolment : enrolments) {
//                        write_line = enrolment.toCSV();
                writer.write(enrolment.toCSV() + "\n");
            }
            writer.flush();
            writer.close();
            System.out.println("File saved at: " + file_path);
            return true;
        } catch (IOException e) {
            System.out.println("Error while trying to create [" + default_save_file_name + "] at Path: " + default_desktop_path);
            return false;
        }
    }
}
