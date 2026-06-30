package university;

import university.entities.*;
import university.enums.*;
import university.services.*;
import university.util.GPAUtils;
import java.util.Scanner;

public class Main {
    private static final StudentService studentService = new StudentService();
    private static final TeacherService teacherService = new TeacherService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] teacherArgs) {
        // Додамо кілька стартових даних для зручності тестування
        initMockData();

        while (true) {
            System.out.println("\n=== СИСТЕМА УПРАВЛІННЯ УНІВЕРСИТЕТОМ ===");
            System.out.println("1. Студенти");
            System.out.println("2. Викладачі");
            System.out.println("3. Курси");
            System.out.println("4. Зарахування та Оцінки");
            System.out.println("5. Звіти / Пошук");
            System.out.println("0. Вихід");
            System.out.print("Оберіть пункт меню: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> studentMenu();
                    case 2 -> teacherMenu();
                    case 3 -> courseMenu();
                    case 4 -> enrollmentMenu();
                    case 5 -> reportMenu();
                    case 0 -> {
                        System.out.println("Завершення програми. Успіхів!");
                        return;
                    }
                    default -> System.out.println("Невірний вибір. Спробуйте ще раз.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Помилка: Введіть числове значення!");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void initMockData() {
        teacherService.addTeacher("Олександр Петренко", "petrenko@univ.edu", TeacherPosition.PROFESSOR);
        studentService.addStudent("Іван Іваненко", "ivan@gmail.com", 1, StudentStatus.ACTIVE);
        studentService.addStudent("Анна Бойко", "anna@gmail.com", 2, StudentStatus.ACTIVE);
        courseService.addCourse("Програмування Java", 5, teacherService.getTeacherById(1));
    }

    private static void studentMenu() {
        System.out.println("\n--- МЕНЮ: СТУДЕНТИ ---");
        System.out.println("1. Додати студента\n2. Показати всіх\n3. Оновити дані\n4. Видалити\n5. Сортувати за ПІБ");
        int subChoice = Integer.parseInt(scanner.nextLine());
        switch (subChoice) {
            case 1 -> {
                System.out.print("ПІБ: "); String name = scanner.nextLine();
                System.out.print("Email: "); String email = scanner.nextLine();
                System.out.print("Курс (1-6): "); int year = Integer.parseInt(scanner.nextLine());
                studentService.addStudent(name, email, year, StudentStatus.ACTIVE);
                System.out.println("Студент успішно доданий!");
            }
            case 2 -> {
                for (Student s : studentService.getAllStudents()) System.out.println(s);
            }
            case 3 -> {
                System.out.print("Введіть ID студента: "); int id = Integer.parseInt(scanner.nextLine());
                System.out.print("Нове ПІБ: "); String name = scanner.nextLine();
                System.out.print("Новий Email: "); String email = scanner.nextLine();
                System.out.print("Новий Курс: "); int year = Integer.parseInt(scanner.nextLine());
                studentService.updateStudent(id, name, email, year);
            }
            case 4 -> {
                System.out.print("Введіть ID для видалення: "); int id = Integer.parseInt(scanner.nextLine());
                studentService.deleteStudent(id);
                System.out.println("Дію виконано.");
            }
            case 5 -> {
                studentService.sortStudentsByName();
                System.out.println("Студенти відсортовані за алфавітом:");
                for (Student s : studentService.getAllStudents()) System.out.println(s);
            }
        }
    }

    private static void teacherMenu() {
        System.out.println("\n--- МЕНЮ: ВИКЛАДАЧІ ---");
        System.out.println("1. Додати викладача\n2. Показати всіх");
        int subChoice = Integer.parseInt(scanner.nextLine());
        if (subChoice == 1) {
            System.out.print("ПІБ: "); String name = scanner.nextLine();
            System.out.print("Email: "); String email = scanner.nextLine();
            System.out.println("Посада (1 - ASSISTANT, 2 - LECTURER, 3 - PROFESSOR): ");
            int posIdx = Integer.parseInt(scanner.nextLine());
            TeacherPosition pos = switch (posIdx) {
                case 2 -> TeacherPosition.LECTURER;
                case 3 -> TeacherPosition.PROFESSOR;
                default -> TeacherPosition.ASSISTANT;
            };
            teacherService.addTeacher(name, email, pos);
            System.out.println("Викладач доданий!");
        } else if (subChoice == 2) {
            for (Teacher t : teacherService.getAllTeachers()) System.out.println(t);
        }
    }

    private static void courseMenu() {
        System.out.println("\n--- МЕНЮ: КУРСИ ---");
        System.out.println("1. Створити курс\n2. Показати всі курси");
        int subChoice = Integer.parseInt(scanner.nextLine());
        if (subChoice == 1) {
            System.out.print("Назва курсу: "); String title = scanner.nextLine();
            System.out.print("Кількість кредитів: "); int credits = Integer.parseInt(scanner.nextLine());
            System.out.print("ID викладача: "); int teacherId = Integer.parseInt(scanner.nextLine());
            Teacher teacher = teacherService.getTeacherById(teacherId);
            courseService.addCourse(title, credits, teacher);
            System.out.println("Курс створено.");
        } else if (subChoice == 2) {
            for (Course c : courseService.getAllCourses()) System.out.println(c);
        }
    }

    private static void enrollmentMenu() {
        System.out.println("\n--- МЕНЮ: ЗАРАХУВАННЯ ТА ОЦІНКИ ---");
        System.out.println("1. Зарахувати студента на курс\n2. Виставити оцінку\n3. Позначити оплату\n4. Переглянути академічну виписку (Транскрипт)");
        int subChoice = Integer.parseInt(scanner.nextLine());
        switch (subChoice) {
            case 1 -> {
                System.out.print("ID Студента: "); int sId = Integer.parseInt(scanner.nextLine());
                System.out.print("ID Курсу: "); int cId = Integer.parseInt(scanner.nextLine());
                System.out.print("Семестр (напр. Осінь-2026): "); String sem = scanner.nextLine();
                enrollmentService.enrollStudent(studentService.getStudentById(sId), courseService.getCourseById(cId), sem);
            }
            case 2 -> {
                System.out.print("ID Студента: "); int sId = Integer.parseInt(scanner.nextLine());
                System.out.print("ID Курсу: "); int cId = Integer.parseInt(scanner.nextLine());
                System.out.print("Оцінка (A, B, C, D, F): "); String gStr = scanner.nextLine().toUpperCase();
                try {
                    Grade grade = Grade.valueOf(gStr);
                    enrollmentService.setGrade(sId, cId, grade);
                } catch (IllegalArgumentException ex) {
                    System.out.println("Невірна оцінка!");
                }
            }
            case 3 -> {
                System.out.print("ID Студента: "); int sId = Integer.parseInt(scanner.nextLine());
                System.out.print("ID Курсу: "); int cId = Integer.parseInt(scanner.nextLine());
                System.out.print("Оплачено? (true/false): "); boolean paid = Boolean.parseBoolean(scanner.nextLine());
                enrollmentService.setPaymentStatus(sId, cId, paid);
            }
            case 4 -> {
                System.out.print("ID Студента: "); int sId = Integer.parseInt(scanner.nextLine());
                Student s = studentService.getStudentById(sId);
                if (s == null) { System.out.println("Студента не знайдено!"); return; }

                Enrollment[] studentEnrs = enrollmentService.getEnrollmentsByStudent(sId);
                System.out.println("\n=== Академічний транскрипт для " + s.getName() + " ===");
                for (Enrollment e : studentEnrs) {
                    System.out.printf("- %s | Оцінка: %s | Статус оплати: %s\n",
                            e.getCourse().getTitle(), e.getGrade(), e.isPaid() ? "Оплачено" : "БОРГ");
                }
                System.out.printf("Поточний рейтинг GPA: %.2f\n", GPAUtils.calculateGPA(studentEnrs));
            }
        }
    }

    private static void reportMenu() {
        System.out.println("\n--- МЕНЮ: ЗВІТИ ТА ПОШУК ---");
        System.out.println("1. Пошук студента за ім'ям/email\n2. Боржники (неоплачені курси)");
        int subChoice = Integer.parseInt(scanner.nextLine());
        if (subChoice == 1) {
            System.out.print("Введіть текст для пошуку: ");
            String query = scanner.nextLine().toLowerCase();
            for (Student s : studentService.getAllStudents()) {
                if (s.getName().toLowerCase().contains(query) || s.getEmail().toLowerCase().contains(query)) {
                    System.out.println(s);
                }
            }
        } else if (subChoice == 2) {
            System.out.println("Список неоплачених зарахувань:");
            for (Enrollment e : enrollmentService.getAllEnrollments()) {
                if (!e.isPaid()) {
                    System.out.printf("Студент: %s (ID: %d) боргує за курс \"%s\"\n",
                            e.getStudent().getName(), e.getStudent().getId(), e.getCourse().getTitle());
                }
            }
        }
    }
}