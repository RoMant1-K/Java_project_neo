package university.services;

import university.entities.Course;
import university.entities.Teacher;

public class CourseService {
    private Course[] courses = new Course[0];
    private int nextId = 1;

    public void addCourse(String title, int credits, Teacher teacher) {
        Course course = new Course(nextId++, title, credits, teacher);
        Course[] temp = new Course[courses.length + 1];
        System.arraycopy(courses, 0, temp, 0, courses.length);
        temp[courses.length] = course;
        courses = temp;
    }

    public Course[] getAllCourses() { return courses; }

    public Course getCourseById(int id) {
        for (Course c : courses) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    public void updateCourse(int id, String title, int credits, Teacher teacher) {
        Course c = getCourseById(id);
        if (c == null) return;
        c.setTitle(title);
        c.setCredits(credits);
        c.setTeacher(teacher);
    }

    public void deleteCourse(int id) {
        int index = -1;
        for (int i = 0; i < courses.length; i++) {
            if (courses[i].getId() == id) { index = i; break; }
        }
        if (index == -1) return;
        Course[] temp = new Course[courses.length - 1];
        System.arraycopy(courses, 0, temp, 0, index);
        System.arraycopy(courses, index + 1, temp, index, courses.length - index - 1);
        courses = temp;
    }
}