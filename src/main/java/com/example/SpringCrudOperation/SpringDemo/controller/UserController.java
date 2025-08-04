package com.example.SpringCrudOperation.SpringDemo.controller;

import com.example.SpringCrudOperation.SpringDemo.entity.Student;
import com.example.SpringCrudOperation.SpringDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class UserController {
    @Autowired
    public UserRepository userRepository;

    @GetMapping("/findall")
    public List<Student> findAll()
    {
        return userRepository.findAll();
    }

    @GetMapping("/findbyId/{id}")
    public Student findByID(@PathVariable int id)
    {
        return userRepository.findbyId(id);
    }

    @PostMapping("/savedetails")
    public String savedetails(@RequestBody Student student)
    {
        userRepository.savedetails(student);
        return "Student details saved";
    }

    @PostMapping("/updatedetails")
    public String updateDetails(@RequestBody Student student)
    {
        userRepository.updateDetails(student);
        return "Student details updated";
    }

    @DeleteMapping("/delete/{id}")
    public String deletebyId(@PathVariable int id)
    {
        userRepository.deleteById(id);
        return "The Student details id deleted";
    }
}
