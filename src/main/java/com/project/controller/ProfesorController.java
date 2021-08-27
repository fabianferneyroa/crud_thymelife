package com.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.model.Estudiante;
import com.project.model.Profesor;
import com.project.repository.ProfesorRepository;

@Controller
@RequestMapping("/profesor")
public class ProfesorController {
	
private Logger logg = LoggerFactory.getLogger(Profesor.class);
	
	@Autowired
	private ProfesorRepository profesorRepository;
	
	@GetMapping("")
	public String profesor(Model modelo) {
		
		modelo.addAttribute("profesores",profesorRepository.findAll());
		
		return "profesor";
	}
	
	@GetMapping("/registrar")
	public String registrarProfesorNuevo() {
		
		return "profesorNuevo";
	}
	
	@PostMapping("/save")
	public String save(Profesor prof) {
		
		logg.info("Informacion del Profesor, {}", prof);
		profesorRepository.save(prof);
		
		return "redirect:/profesor";
	}
	
	@GetMapping("/editar/{id}")
	public String editarProfesor(@PathVariable Integer id, Model modelo) {
		
		Profesor prof = profesorRepository.getById(id);
		
		logg.info("Datos Profesor, {}", prof);
		
		modelo.addAttribute("profesor",prof);
		
		return "editarProfesor";
	}
	
	@GetMapping("/eliminar/{id}")
	public String borrarProfesor(@PathVariable Integer id, Model modelo) {
		
        Profesor prof = profesorRepository.getById(id);
		
		logg.info("Datos Profesor, {}", prof);
		
		profesorRepository.delete(prof);
		
		return "redirect:/profesor";
	}


}
