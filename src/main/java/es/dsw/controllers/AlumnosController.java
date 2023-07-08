package es.dsw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.dsw.models.*;
/* GESTIÓN DE VARIABLES DE SESIÓN Y MAPEO POR CONTROLADORAS
 * 
 * En esta controladora, se hace uso de la variable de sesión DataGrupo, la cual se accede también desde la controladora MainController. Además
 * se crea un area de aplicación ya que todos los métodos se mapearán bajo la ruta url relativa /alumnos. 
 */

@Controller
@RequestMapping("/alumnos")
@SessionAttributes({"DataGrupo"})
public class AlumnosController {

	//En este ejemplo se delega a la vista la cual accede directamente al contenido de DataGrupo. Si no ha sido creada dicha variable de sesión, no 
	//causa excepción.
	@GetMapping(value={"/ejemplo2"})
	public String ejem2() {
		return "example2";
	}
	
	//Este ejemplo precarga un formulario para añadir datos del alumno proponiendo al usuario datos por defecto aprovechandose de las Spring Mvc Form Tag Library.
	@GetMapping(value = {"/ejemplo3"})
	public String ejem3(Model objModel) {
		Alumno objAlumno = new Alumno();
		objAlumno.setNom("Anonimo");
		objAlumno.setApell("Pérez");
		objModel.addAttribute("InfoAlumno", objAlumno);
		return "example3";
	}
	
	//En este ejemplo se recibe los datos del alumno. Observe como se recibe en un objeto los datos (objAlumno) gracias a las Spring Mvc Form Tag Library. Si la variable de sesión
	//DataGrupo no existe se crea asignandole un objeto grupo. De lo contrario añade el alumno al grupo.
	@PostMapping(value = {"/procesarEjemplo3"})
	public String procesarEjem3(@ModelAttribute("InfoAlumno") Alumno objAlumno, Model objModel) {

		if (objModel.getAttribute("DataGrupo") == null) {
			objModel.addAttribute("DataGrupo", (new Grupo(objAlumno)));
		} else {
			Grupo auxGrupo = (Grupo) objModel.getAttribute("DataGrupo");
			auxGrupo.setAlumno(objAlumno);
		}
			objAlumno.setResultado("¡¡ Alumno añadido a la lista en variable de sesión !!");
			return "example3";
	}
	
}
