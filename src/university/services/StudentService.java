package university.services;

import university.entities.Student;
import university.enums.StudentStatus;
import university.util.GPAUtils;

public class StudentService {
    private Student[] students = new Student[0];
    private int nextId = 1;

    public void addStudent(String name, String email, int year, StudentStatus status) {
        Student student = new Student(nextId++, name, email, year, status);
        Student[] temp = new Student[students.length + 1];
        System.arraycopy(students, 0, temp, 0, students.length);
        temp[students.length] = student;
        students = temp;
    }

    public Student[] getAllStudents() { return students; }

    public Student getStudentById(int id) {
        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    public void updateStudent(int id, String name, String email, int year) {
        Student s = getStudentById(id);
        if (s == null) { System.out.println("Студента не знайдено!"); return; }
        s.setName(name);
        s.setEmail(email);
        s.setYear(year);
    }

    public void deleteStudent(int id) {
        int index = -1;
        for (int i = 0; i < students.length; i++) {
            if (students[i].getId() == id) { index = i; break; }
        }
        if (index == -1) { System.out.println("Студента не знайдено!"); return; }

        Student[] temp = new Student[students.length - 1];
        System.arraycopy(students, 0, temp, 0, index);
        System.arraycopy(students, index + 1, temp, index, students.length - index - 1);
        students = temp;
    }

    public void sortStudentsByName() {
        GPAUtils.bubbleSortByName(students);
    }
}