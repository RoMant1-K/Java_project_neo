package university.services;

import university.entities.Teacher;
import university.enums.TeacherPosition;

public class TeacherService {
    private Teacher[] teachers = new Teacher[0];
    private int nextId = 1;

    public void addTeacher(String name, String email, TeacherPosition position) {
        Teacher teacher = new Teacher(nextId++, name, email, position);
        Teacher[] temp = new Teacher[teachers.length + 1];
        System.arraycopy(teachers, 0, temp, 0, teachers.length);
        temp[teachers.length] = teacher;
        teachers = temp;
    }

    public Teacher[] getAllTeachers() { return teachers; }

    public Teacher getTeacherById(int id) {
        for (Teacher t : teachers) {
            if (t.getId() == id) return t;
        }
        return null;
    }

    public void updateTeacher(int id, String name, String email, TeacherPosition pos) {
        Teacher t = getTeacherById(id);
        if (t == null) return;
        t.setName(name);
        t.setEmail(email);
        t.setPosition(pos);
    }

    public void deleteTeacher(int id) {
        int index = -1;
        for (int i = 0; i < teachers.length; i++) {
            if (teachers[i].getId() == id) { index = i; break; }
        }
        if (index == -1) return;
        Teacher[] temp = new Teacher[teachers.length - 1];
        System.arraycopy(teachers, 0, temp, 0, index);
        System.arraycopy(teachers, index + 1, temp, index, teachers.length - index - 1);
        teachers = temp;
    }
}