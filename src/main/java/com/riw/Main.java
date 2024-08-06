package com.riw;

import com.riw.controllers.CourseController;
import com.riw.controllers.RegistrationController;
import com.riw.controllers.ScoreController;
import com.riw.controllers.StudentController;
import com.riw.entities.Course;
import com.riw.entities.Registration;
import com.riw.entities.Score;
import com.riw.entities.Student;

import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Instanciamos los controladores de las entidades para poder llamar sus métodos
        StudentController studentController = new StudentController();
        CourseController courseController = new CourseController();
        RegistrationController registrationController = new RegistrationController();
        ScoreController scoreController = new ScoreController();

        boolean exit = false;

        while (!exit) {
            int option = Integer.parseInt(JOptionPane.showInputDialog("MENU RIWI ACADEMY\n" +
                    "1. Students\n" +
                    "2. Courses\n" +
                    "3. Registrations\n" +
                    "4. Scores\n" +
                    "5. Exit\n" +
                    "Select an option:"));

            switch (option) {
                case 1:
                    // MENU ESTUDIANTES
                    boolean backmenu = false;

                    while (!backmenu) {
                        int studentOption = Integer.parseInt(JOptionPane.showInputDialog("MENU STUDENTS\n" +
                                "1. Add student\n" +
                                "2. See all active students\n" +
                                "3. Search student by email\n" +
                                "4. Edit student status\n" +
                                "5. Back menu\n" +
                                "Select an option:"));
                        switch (studentOption) {
                            case 1:
                                // AGREGAR UN ESTUDIANTE A LA BASE DE DATOS
                                String nameStudent = JOptionPane.showInputDialog("Enter student name");
                                String lastNameStudent = JOptionPane.showInputDialog("Enter student lastname");
                                String emailStudent = JOptionPane.showInputDialog("Enter student email");

                                // Creamos una nueva entidad y le pasamos los datos que pedimos
                                Student student = new Student(0, nameStudent, lastNameStudent, emailStudent, null);
                                // Acá creamos un objeto para pasarle la entidad que tiene los datos guardados
                                Student newStudent = studentController.createStudent(student);

                                // Con el objeto creado podemos validar si se agregó o no se agregó
                                if (newStudent != null) {
                                    JOptionPane.showMessageDialog(null, "Successfully registered " + newStudent.toString2());
                                }
                                break;

                            case 2:
                                // Ver todos los estudiantes registrados y activos
                                Boolean statusStudents = Boolean.valueOf(JOptionPane.showInputDialog("Enter TRUE to see all registered and active students"));

                                student = new Student(statusStudents);
                                ArrayList<Student> status = studentController.getStudents(student);

                                StringBuilder studentInfo = new StringBuilder("Active students:\n");
                                for (Student studentStatus : status) {
                                    studentInfo.append(studentStatus).append("\n");
                                }
                                JOptionPane.showMessageDialog(null, studentInfo.toString());
                                break;

                            case 3:
                                // Buscar estudiante por email
                                String searchEmail = JOptionPane.showInputDialog("Enter student email to search");
                                Student studentEmail = studentController.getStudent(searchEmail);
                                JOptionPane.showMessageDialog(null, studentEmail);
                                break;

                            case 4:
                                // Actualizar estado del estudiante desde el email
                                searchEmail = JOptionPane.showInputDialog("Enter the student email to update their status");
                                Boolean updateStatus = Boolean.valueOf(JOptionPane.showInputDialog("Enter FALSE to update the status"));

                                student = studentController.updateStudent(updateStatus, searchEmail);

                                if (student != null) {
                                    JOptionPane.showMessageDialog(null, "Student status updated");
                                }
                                break;

                            case 5:
                                backmenu = true;
                                break;

                            default:
                                JOptionPane.showMessageDialog(null, "Invalid option.");
                        }
                    }
                    break;

                case 2:
                    // MENU CURSOS
                    backmenu = false;

                    while (!backmenu) {
                        int courseOption = Integer.parseInt(JOptionPane.showInputDialog("MENU COURSES\n" +
                                "1. Add course\n" +
                                "2. Edit course by ID\n" +
                                "3. Search course by name\n" +
                                "4. Delete course by ID\n" +
                                "5. Back menu\n" +
                                "Select an option:"));
                        switch (courseOption) {
                            case 1:
                                // AGREGAR CURSOS
                                String nameCourse = JOptionPane.showInputDialog("Enter the course name");
                                String descriptionCourse = JOptionPane.showInputDialog("Enter the course description");

                                Course course = new Course(0, nameCourse, descriptionCourse);
                                Course newCourse = courseController.createCourse(course);

                                if (newCourse != null) {
                                    JOptionPane.showMessageDialog(null, "Course added successfully");
                                }
                                break;

                            case 2:
                                // EDITAR UN CURSO
                                int idSearch = Integer.parseInt(JOptionPane.showInputDialog("Enter the course ID for editing"));
                                String newName = JOptionPane.showInputDialog("Enter the new name for the course");
                                String newDescription = JOptionPane.showInputDialog("Enter the new description");

                                course = courseController.updateCourse(idSearch, newName, newDescription);
                                if (course != null) {
                                    JOptionPane.showMessageDialog(null, "Updated the course with: " + course);
                                }
                                break;

                            case 3:
                                // BUSCAR CURSO POR NOMBRE
                                String nameCourseSearch = JOptionPane.showInputDialog("Enter the name of the course to search");
                                Course courseSearch = courseController.getCourse(nameCourseSearch);

                                if (courseSearch != null) {
                                    JOptionPane.showMessageDialog(null, courseSearch);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Course not found");
                                }
                                break;

                            case 4:
                                // ELIMINAR UN CURSO POR ID
                                int idSearchDelete = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the course to delete"));
                                Boolean deleteCourse = courseController.deleteCourse(idSearchDelete);

                                if (deleteCourse) {
                                    JOptionPane.showMessageDialog(null, "Course successfully deleted");
                                } else {
                                    JOptionPane.showMessageDialog(null, "This course does not exist with that ID");
                                }
                                break;

                            case 5:
                                backmenu = true;
                                break;

                            default:
                                JOptionPane.showMessageDialog(null, "Invalid option");
                        }
                    }
                    break;

                case 3:
                    // MENU INSCRIPCIONES
                    backmenu = false;

                    while (!backmenu) {
                        int registrationOption = Integer.parseInt(JOptionPane.showInputDialog("MENU REGISTRATION\n" +
                                "1. Make registration\n" +
                                "2. See registrations made by course\n" +
                                "3. Edit registrations\n" +
                                "4. Back menu\n" +
                                "Select an option:"));
                        switch (registrationOption) {
                            case 1:
                                // HACER REGISTRO DE CURSO
                                int idStudentRegister = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the student to register"));
                                int idCourseRegister = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the course you wish to register for"));

                                Registration registration = new Registration(0, idStudentRegister, idCourseRegister, null);
                                Registration newRegistration = registrationController.createRegistration(registration);

                                if (newRegistration != null) {
                                    JOptionPane.showMessageDialog(null, "Successful registration in the course");
                                }
                                break;

                            case 2:
                                // VER TODOS LOS REGISTROS DE UN CURSO
                                int registerCourses = Integer.parseInt(JOptionPane.showInputDialog("Enter the course ID to view your records"));

                                Registration registrationSearch = new Registration(registerCourses);
                                ArrayList<Registration> registers = registrationController.getRegistrationList(registrationSearch);

                                if (registers.size() > 0) {
                                    StringBuilder registerStatus = new StringBuilder("Course records:\n");
                                    for (Registration reg : registers) {
                                        registerStatus.append(reg).append("\n");
                                    }
                                    JOptionPane.showMessageDialog(null, registerStatus.toString());
                                } else {
                                    JOptionPane.showMessageDialog(null, "No course registration");
                                }
                                break;

                            case 3:
                                // EDITAR REGISTROS
                                int idStudentUpdate = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the student who wants to change courses"));
                                int idCourseUpdate = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the new course"));

                                Registration updatedRegistration = registrationController.updateRegistration(idStudentUpdate, idCourseUpdate);

                                if (updatedRegistration != null) {
                                    JOptionPane.showMessageDialog(null, "Student successfully changed course");
                                } else {
                                    JOptionPane.showMessageDialog(null, "No course registration");
                                }
                                break;

                            case 4:
                                backmenu = true;
                                break;

                            default:
                                JOptionPane.showMessageDialog(null, "Invalid option");
                        }
                    }
                    break;

                case 4:
                    // MENU CALIFICACIONES
                    backmenu = false;

                    while (!backmenu) {
                        int scoreOption = Integer.parseInt(JOptionPane.showInputDialog("MENU SCORE\n" +
                                "1. Make score\n" +
                                "2. Edit student note\n" +
                                "3. Back menu\n" +
                                "Select an option:"));
                        switch (scoreOption) {
                            case 1:
                                // AGREGAR NOTAS
                                int idStudent = Integer.parseInt(JOptionPane.showInputDialog("Enter the student ID"));
                                int idCourse = Integer.parseInt(JOptionPane.showInputDialog("Enter the course ID"));
                                double studentScore = Double.parseDouble(JOptionPane.showInputDialog("Enter the student score"));
                                String descriptionScore = JOptionPane.showInputDialog("Enter the score description");

                                Score score = new Score(0, descriptionScore, idStudent, idCourse, studentScore);
                                Score newScore = scoreController.createScore(score);

                                if (newScore != null) {
                                    JOptionPane.showMessageDialog(null, "Score successfully added to the student " + newScore);
                                }
                                break;

                            case 2:
                                //Actualizar nota de un estudiante
                                int idStudentEdit = Integer.parseInt(JOptionPane.showInputDialog("Enter the student ID to edit the note"));
                                double scoreEdit = Double.parseDouble(JOptionPane.showInputDialog("Enter the new note"));

                                score = scoreController.updateScore(idStudentEdit,scoreEdit);

                                if (score != null){
                                    JOptionPane.showMessageDialog(null,"Note updated successfully "+score);
                                }

                                break;

                            case 3:
                                backmenu = true;
                                break;

                            default:
                                JOptionPane.showMessageDialog(null, "Invalid option.");
                        }
                    }
                    break;

                case 5:
                    // SALIDA
                    exit = true;
                    JOptionPane.showMessageDialog(null, "See you later :)");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid option");
            }
        }
    }
}
