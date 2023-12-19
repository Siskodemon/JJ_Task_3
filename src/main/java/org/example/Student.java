package org.example;
import java.io.*;

public class Student implements Serializable {
    private String name;
    private int age;
    private transient double GPA;//поскольку GPA объявлено как transient, его значение не сохраняется в файле в процессе сериализации, и при десериализации оно будет равно 0.0.

    public Student(String name, int age, double GPA) {
        this.name = name;
        this.age = age;
        this.GPA = GPA;
    }

    public static void main(String[] args) {
        // Создание и инициализация объекта типа Student
        Student student = new Student("John wick", 20, 3.5);

        // Сереализация объекта в файл
        serializeToFile(student, "student.ser");

        // Десериализация объекта из файлав
        Student deserializedStudent = deserializeFromFile("student.ser");

        // Выведение на экран полученного результата
        deserializedStudent.printDetails();
    }

    private static void serializeToFile(Student obj, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(obj);
            System.out.println("Данные сериализированны в файл:  " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Student deserializeFromFile(String fileName) {
        Student obj = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            obj = (Student) ois.readObject();
            System.out.println("Данные сериализированны из файла:" + fileName);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }
    public void printDetails() {
        System.out.println("Имя: " + name);
        System.out.println("Возраст: " + age);
        System.out.println("GPA: " + GPA);
    }

}