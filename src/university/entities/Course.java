package university.entities;

public class Course {
    private int id;
    private String title;
    private int credits;
    private Teacher teacher; // Асоціація з викладачем

    public Course(int id, String title, int credits, Teacher teacher) {
        if (credits <= 0) throw new IllegalArgumentException("Кредити повинні бути більше 0");
        this.id = id;
        this.title = title;
        this.credits = credits;
        this.teacher = teacher;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }
    public Teacher getTeacher() { return teacher; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; }

    @Override
    public String toString() {
        String teacherName = (teacher != null) ? teacher.getName() : "Не призначено";
        return String.format("Курс [ID: %d] \"%s\" | Кредити: %d | Викладач: %s", id, title, credits, teacherName);
    }
}