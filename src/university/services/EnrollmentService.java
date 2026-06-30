package university.services;

import university.entities.*;
import university.enums.Grade;

public class EnrollmentService {
    private Enrollment[] enrollments = new Enrollment[0];

    public void enrollStudent(Student student, Course course, String semester) {
        if (student == null || course == null) {
            System.out.println("Помилка: Студента або Курс не знайдено!");
            return;
        }
        Enrollment e = new Enrollment(student, course, semester);
        Enrollment[] temp = new Enrollment[enrollments.length + 1];
        System.arraycopy(enrollments, 0, temp, 0, enrollments.length);
        temp[enrollments.length] = e;
        enrollments = temp;
        System.out.println("Студента успішно зараховано на курс!");
    }

    public Enrollment[] getAllEnrollments() { return enrollments; }

    public Enrollment[] getEnrollmentsByStudent(int studentId) {
        int count = 0;
        for (Enrollment e : enrollments) {
            if (e.getStudent().getId() == studentId) count++;
        }
        Enrollment[] result = new Enrollment[count];
        int idx = 0;
        for (Enrollment e : enrollments) {
            if (e.getStudent().getId() == studentId) result[idx++] = e;
        }
        return result;
    }

    public void setGrade(int studentId, int courseId, Grade grade) {
        for (Enrollment e : enrollments) {
            if (e.getStudent().getId() == studentId && e.getCourse().getId() == courseId) {
                e.setGrade(grade);
                System.out.println("Оцінку успішно виставлено!");
                return;
            }
        }
        System.out.println("Запис про зарахування не знайдено.");
    }

    public void setPaymentStatus(int studentId, int courseId, boolean paid) {
        for (Enrollment e : enrollments) {
            if (e.getStudent().getId() == studentId && e.getCourse().getId() == courseId) {
                e.setPaid(paid);
                System.out.println("Статус оплати змінено!");
                return;
            }
        }
        System.out.println("Запис про зарахування не знайдено.");
    }
}