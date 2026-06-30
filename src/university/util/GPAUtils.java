package university.util;

import university.entities.Enrollment;
import university.entities.Student;

public class GPAUtils {

    public static void bubbleSortByName(Student[] students) {
        int n = students.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (students[j].getName().compareToIgnoreCase(students[j + 1].getName()) > 0) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
    }

    public static double calculateGPA(Enrollment[] studentEnrollments) {
        if (studentEnrollments.length == 0) return 0.0;
        double totalPoints = 0;
        int count = 0;
        for (Enrollment e : studentEnrollments) {
            if (e.getGrade() != university.enums.Grade.NA) {
                totalPoints += e.getGrade().getValue();
                count++;
            }
        }
        return count == 0 ? 0.0 : totalPoints / count;
    }
}