package university.entities;

import university.enums.Grade;
import university.interfaces.Payable;

public class Enrollment implements Payable {
    private Student student;
    private Course course;
    private String semester;
    private Grade grade;
    private boolean paid;

    public Enrollment(Student student, Course course, String semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
        this.grade = Grade.NA;
        this.paid = false;
    }

    // Реалізація інтерфейсу Payable
    @Override public boolean isPaid() { return paid; }
    @Override public void setPaid(boolean paid) { this.paid = paid; }

    // Getters and Setters
    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public String getSemester() { return semester; }
    public Grade getGrade() { return grade; }
    public void setGrade(Grade grade) { this.grade = grade; }

    @Override
    public String toString() {
        return String.format("Зарахування -> Студент: %s | Курс: %s | Семестр: %s | Оцінка: %s | Оплачено: %b",
                student.getName(), course.getTitle(), semester, grade, paid);
    }
}