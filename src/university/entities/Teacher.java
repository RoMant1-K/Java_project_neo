package university.entities;

import university.enums.TeacherPosition;

public class Teacher extends Person {
    private TeacherPosition position;

    public Teacher(int id, String name, String email, TeacherPosition position) {
        super(id, name, email);
        this.position = position;
    }

    public TeacherPosition getPosition() { return position; }
    public void setPosition(TeacherPosition position) { this.position = position; }

    @Override
    public String toString() {
        return String.format("Викладач [ID: %d] ПІБ: %s | Email: %s | Посада: %s",
                getId(), getName(), getEmail(), position);
    }
}