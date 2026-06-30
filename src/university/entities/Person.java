package university.entities;

public abstract class Person {
    private int id;
    private String name;
    private String email;

    public Person(int id, String name, String email) {
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Помилка: неправильний формат email (" + email + ")");
        }
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) {
        if (!email.contains("@")) throw new IllegalArgumentException("Неправильний формат email");
        this.email = email;
    }
}