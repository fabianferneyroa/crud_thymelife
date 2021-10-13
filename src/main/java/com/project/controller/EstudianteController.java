package com.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.model.Estudiante;
import com.project.repository.EstudianteRepository;

//esta es la direccion a la que apuntara la url o sea http://localhost:8080/estudiante
@Controller
@RequestMapping("/estudiante")
public class EstudianteController {
	
	//nos permite visualizar los datos y objetos que estamos manejando
	private Logger logg = LoggerFactory.getLogger(Estudiante.class);
	
	//creacion e instaciamiento de un objeto automaticamente realizado por Spring
	@Autowired
	private EstudianteRepository estudianteRepository;
	
	/*direccion para que se ejecute el metodo principal
	el cual permite listar todos los estudiantes
	que se encuentran registrados en la base de datos*/
	
	@GetMapping("")
	public String estudiante(Model modelo) {
		
		modelo.addAttribute("estudiantes",estudianteRepository.findAll());
		
		//buscara directamente el archivo html que ejecutara este metodo
		return "estudiante";
	}
	
	
	@GetMapping("/registrar")
	public String registrarEstudianteNuevo() {
		
		return "estudianteNuevo";
	}
	
	//metodo el cual permite enviar un objeto a la base de datos para registrarlo
	@PostMapping("/save")
	public String save(Estudiante est) {
		
		logg.info("Informacion del Estudiante, {}", est);
		estudianteRepository.save(est);
		
		/*hace que al registrarse un nuevo estudiante se recargue la pagina principal
		 mostrando todos los registros actualizada*/
		return "redirect:/estudiante";
	}
	
	/*metodo que recoge el id del estudante que deseamos editar, 
	 * para con el consultar los datos del estudiante y mostrarlos 
	 * en un formulario nuevo y asi editarlo*/
	@GetMapping("/editar/{id}")
	public String editarEstudiante(@PathVariable Integer id, Model modelo) {
		
		Estudiante est = estudianteRepository.getById(id);
		
		logg.info("Datos Estudiante, {}", est);
		
		modelo.addAttribute("estudiante",est);
		
		//busca directamente el archivo html donde se encuentra el formulario de edicion del estudiante
		return "editarEstudiante";
	}
	
	/*metodo que recoge el id de un estudiante que deseamos eliminar para asi obtener todo el
	 * objeto de este estudiante y borrarlo de la base de datos*/
	@GetMapping("/eliminar/{id}")
	public String borrarEstudiante(@PathVariable Integer id, Model modelo) {
		
        Estudiante est = estudianteRepository.getById(id);
		
		logg.info("Datos Estudiante, {}", est);
		
		estudianteRepository.delete(est);
		
		
		/*hace que al eliminar un estudiante se recargue la pagina principal
		 mostrando la lista de estudiantes actualizada*/
		return "redirect:/estudiante";
	}
}
