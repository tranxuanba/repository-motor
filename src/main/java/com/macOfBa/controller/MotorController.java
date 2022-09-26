package com.macOfBa.controller;

import com.macOfBa.model.Motor;
import com.macOfBa.model.MotorForm;
import com.macOfBa.service.ipmlSer.IMotorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/motors")
public class MotorController {
    @Autowired
    private IMotorService motorService;
    @GetMapping("")
    public ModelAndView list() {
        Iterable<Motor> motors = motorService.findAll();
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("motors", motors);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("motorForm", new MotorForm());
        return modelAndView;
    }
    @Value("/Users/ishopjapan/Documents/file-upload/")
    private String fileUpload;
    @PostMapping("/save")
    public ModelAndView saveProduct(@ModelAttribute MotorForm motorForm) {
        MultipartFile multipartFile = motorForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(motorForm.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Motor motor = new Motor(motorForm.getId(), motorForm.getName(), motorForm.getPrice(), fileName);
        motorService.save(motor);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("motorForm", motorForm);
        modelAndView.addObject("message", "Created new motor successfully !");
        return modelAndView;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
//        Motor motor = motorService.findById(id);
        Optional<Motor> motor = motorService.findById(id);
        if (motor != null) {
            ModelAndView modelAndView = new ModelAndView("/edit");
            modelAndView.addObject("motor", motor);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit")
    public ModelAndView updateMotor(@ModelAttribute("motor") MotorForm motorForm) {
        MultipartFile multipartFile = motorForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(motorForm.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Motor motor = new Motor(motorForm.getId(), motorForm.getName(), motorForm.getPrice(), fileName);
        motorService.save(motor);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("motor", motor);
        modelAndView.addObject("message", "Customer updated successfully");
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Motor> motor = motorService.findById(id);
        if (motor != null) {
            ModelAndView modelAndView = new ModelAndView("/delete");
            modelAndView.addObject("motor", motor);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete")
    public String deleteCustomer(@ModelAttribute("motor") Motor motor) {
        motorService.remove(motor.getId());
        return "redirect:/motors";
    }
}
