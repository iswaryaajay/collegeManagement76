/*
 *
 * CollegeManagement
 *
 * Student Details
 *
 */

package collection;

import java.util.*;

public class CollegeManagement {

    public static void main(String[] args) throws InvalidInputException {
        final StudentView studentView = new StudentView();
        final HashMap<String, Student> studentMap = studentView.getStudentDetails();

        System.out.println("Student details");
        for (Map.Entry<String, Student> entry : studentMap.entrySet()) {
            final String studentId = entry.getKey();
            final Student student = entry.getValue();

            System.out.println(studentId);
            System.out.println(student.getName());
            System.out.println(student.getDateOfBirth());
            System.out.println(student.getPhone());
            System.out.println(student.getDepartment());
            System.out.println(student.getGender());
        }
    }
}

class StudentView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public final HashMap<String, Student> getStudentDetails() throws InvalidInputException {
        final HashMap<String, Student> studentMap = new HashMap<>();

        while (true) {
            final String studentId = getStudentId();

            if (studentId.equalsIgnoreCase("exit")) {
                break;
            }

            final String name = getName();
            final String dateOfBirth = getDateOfBirth();
            final Long mobile = getPhone();
            final Student.Department department = getDepartment();
            final Student.Gender gender = getGender();

            final Student student = new Student(name, dateOfBirth, mobile, department, gender);

            studentMap.put(studentId, student);
        }
        return studentMap;
    }

    private final String getStudentId() {
        System.out.println("Enter student ID (type 'exit' to close the database):");
        return SCANNER.nextLine();
    }

    private final String getName() {
        try {
            System.out.println("Enter student name:");
            String name = SCANNER.nextLine();

            if (!name.matches("^[a-zA-Z\\s]+$")) {
                throw new NameNotProvideException("Please enter a valid name");
            }
            return name;
        } catch (NameNotProvideException e) {
            System.out.println(e.getMessage());
            return getName();
        }
    }

    private final String getDateOfBirth() {
        try {
            System.out.println("Enter student date of birth:");
            String dob = SCANNER.nextLine();

            if (!dob.matches("^\\d{2}-\\d{1,2}-\\d{4}$")) {
                throw new InvalidInputException("Please enter date in dd-mm-yyyy format");
            }
            return dob;
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            return getDateOfBirth();
        }
    }

    private final Long getPhone() {
        try {
            System.out.println("Enter student phone number:");
            return SCANNER.nextLong();
        } catch (InputMismatchException e) {
            System.out.println("Invalid phone number. Please enter a valid number");
            SCANNER.nextLine(); // Consume the invalid input
            return getPhone();
        }
    }

    private final Student.Department getDepartment() {
        try {
            System.out.println("Enter student department:");
            String departmentName = SCANNER.nextLine();

            return Student.Department.valueOf(departmentName.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid department. Please enter a valid department");
            return getDepartment();
        }
    }

    private final Student.Gender getGender() {
        try {
            System.out.println("Enter student gender:");
            String gender = SCANNER.nextLine();

            return Student.Gender.valueOf(gender.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid gender. Please enter a valid gender");
            return getGender();
        }
    }
}

class Student {
    enum Department {
        BIOMEDICAL, BIOTECH, CIVIL, COMPUTERSCIENCE, MECHANICAL
    }

    enum Gender {
        MALE, FEMALE, OTHERS
    }

    private Department department;
    private Gender gender;
    private String name;
    private String dateOfBirth;
    private Long phone;


    Student(final String name, final String dateOfBirth, final Long phone, final Department department, final Gender gender) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.department = department;
        this.gender = gender;
    }

    public final String getName() {
        return name;
    }

    public final String getDateOfBirth() {
        return dateOfBirth;
    }

    public final Long getPhone() {
        return phone;
    }

    public final Department getDepartment() {
        return department;
    }

    public final Gender getGender() {
        return gender;
    }
}

class InvalidInputException extends Exception {

    public InvalidInputException(String message) {
        super(message);
    }
}

class NameNotProvideException extends InvalidInputException {

    public NameNotProvideException(String message) {
        super(message);
    }
}
