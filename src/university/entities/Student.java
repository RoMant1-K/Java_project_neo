package university.entities;

import university.enums.StudentStatus;

public class Student extends Person {
    private int year;
    private StudentStatus status;

    public Student(int id, String name, String email, int year, StudentStatus status) {
        super(id, name, email);
        if (year < 1 || year > 6) {
            throw new IllegalArgumentException("Помилка: курс навчання має бути від 1 до 6");
        }
        this.year = year;
        this.status = status;
    }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public StudentStatus getStatus() { return status; }
    public void setStatus(StudentStatus status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("Студент [ID: %d] ПІБ: %s | Email: %s | Курс: %d | Статус: %s",
                getId(), getName(), getEmail(), year, status);
    }
}