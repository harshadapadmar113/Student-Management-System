
package com.hp.studentmanagement.controller;
import org.springframework.stereotype.Controller;
import com.hp.studentmanagement.model.User;
import com.hp.studentmanagement.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hp.studentmanagement.model.Student;
import com.hp.studentmanagement.service.StudentService;

import jakarta.validation.Valid;

@Controller
public class StudentController {

    private final StudentService studentService;
    private final UserRepository userRepository;

    public StudentController(
            StudentService studentService,
            UserRepository userRepository) {
        this.studentService = studentService;
        this.userRepository = userRepository;
    }


@GetMapping("/")
public String listStudents(Model model) {
    model.addAttribute("students", studentService.getAllStudents());
    model.addAttribute("totalStudents", studentService.getTotalStudents());
    model.addAttribute("student", new Student());

    return "students";
}




    @GetMapping("/students/search")
    public String searchStudents(
        @org.springframework.web.bind.annotation.RequestParam String name,
        Model model) {

    model.addAttribute("students",
            studentService.searchStudentsByName(name));

    model.addAttribute("searchName", name);

    model.addAttribute("totalStudents",
            studentService.getTotalStudents());
    model.addAttribute("student", new Student());

    return "students";
    }

    @PostMapping("/students")
    public String addStudent(
        @Valid @ModelAttribute("student") Student student,
        BindingResult result,
        Model model) {

    if (!result.hasErrors()
            && studentService.emailExists(student.getEmail())) {
        result.rejectValue("email", "duplicate",
                "Email already exists!");
    }

    if (result.hasErrors()) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("totalStudents", studentService.getTotalStudents());
        return "students";
    }

    studentService.saveStudent(student);
    return "redirect:/";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudent(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id);

        if (student == null) {
            return "redirect:/";
        }

        model.addAttribute("student", student);
        return "edit-student";
    }



    @PostMapping("/students/update")
    public String updateStudent(
        @Valid @ModelAttribute("student") Student student,
        BindingResult result,
        Model model) {

    if (!result.hasErrors()
            && studentService.emailExists(student.getEmail())) {

        Student existingStudent = studentService.getStudentById(student.getId());

        if (existingStudent == null
                || !existingStudent.getEmail().equalsIgnoreCase(student.getEmail())) {
            result.rejectValue("email", "duplicate",
                    "Email already exists!");
        }
    }

    if (result.hasErrors()) {
        return "edit-student";
    }

    studentService.saveStudent(student);
    return "redirect:/";
    }


    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
    	studentService.deleteStudent(id);
    	return "redirect:/";
    }

    @GetMapping("/student/dashboard")
    public String studentDashboard(
            Authentication authentication,
            Model model) {

        User user = userRepository
                .findByUsername(authentication.getName())
                .orElse(null);

        if (user == null || user.getStudentId() == null) {
            model.addAttribute("errorMessage",
                    "Your student account is not linked to a student record.");
            return "student-dashboard";
        }

        Student student = studentService
                .getStudentById(user.getStudentId());

        if (student == null) {
            model.addAttribute("errorMessage",
                    "Student record not found.");
            return "student-dashboard";
        }

        model.addAttribute("student", student);

        return "student-dashboard";
    }

}
