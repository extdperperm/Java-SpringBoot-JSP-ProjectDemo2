package es.dsw.controllers;
 
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.dsw.models.*;

/* GESTIÓN DE VARIABLES DE SESIÓN Y COOKIES
 * 
 * La Java Annotation @SessionAttributes indica la/s variables de sesiones que entran en juego en la controladora. Esto significa que a la hora de hacer getAttribute o 
 * addAttribute en el modelo de spring, la persistencia de dicho objeto (modelo de negocio) va mas alla de la petición y respuesta http, es decir, se almacena en memoria de usuario. El tiempo que permanecerá
 * en memoria activa dicha variable de sesión, en este proyecto, será hasta un máximo de 10 minutos después de que no se detecte actividad del usuario. Este último parámetro, está indicado en el
 * fichero web.xml y debes recordar que la configuración en el servidor prevalece a la configuración que se pueda hacer en el respectivo fichero de despliegue web.xml.
 *  
 * Por lo tanto, en toda controladora que se necesite acceder a una o varias variables de sesión de usuario, se debe indicar explícitamente el nombre de las mismas con @SessionAttributes.
 */
@Controller
@SessionAttributes({"DataGrupo"}) //Se pueden indicar más de una variable de sesión. Ejemplo: @SessionAttributes({"DataGrupo", "OtraVariable"})
public class MainController {
	
	//Controladora por defecto. Observe que los mapeos/sobrecarga de los métodos del protocolo http, se indican de una atacada con @GetMapping o @PostMapping a diferencia
	//de los ejemplos anteriores del proyecto 1 que se realizaban con @RequestMapping indicando el atributo method. Con esto, ahorramos código.
	@GetMapping(value = {"/","/index"})
	public String index() {
		return "index";
	}

	//En este ejemplo, se recoge por método post una serie de parámetros y si no existe aún o no está inicializada la variable de sesión DataGrupo, entonces
	//se crea asignandole un objeto de la clase Grupo que implementa una lista de alumnos. Si ya existe, se añaden los datos del nuevo alumno al grupo.
	@PostMapping(value = {"/ejemplo1"})
	public String ejem1(@RequestParam("Nombre") String Nom, @RequestParam("Apellidos") String Apell, @RequestParam("Edad") int Edad, Model objModel) {
		
		if (objModel.getAttribute("DataGrupo") == null) {
			//Si la variable de sesión es nula, entonces se crea y se inicializa por primera vez el ArrayList, con el primer alumno introducido.
			objModel.addAttribute("DataGrupo", (new Grupo(new Alumno(Nom, Apell, Edad))));
		} else {
			Grupo auxGrupo = (Grupo) objModel.getAttribute("DataGrupo");
			//Si la variable de sesión ya está inicializada, se añaden los datos del nuevo alumno introducido. Recordar que en JAVA en paso de parámetros es por
			//referencia. Por ello, en este caso no es necesario un AddAttribute del modelo, pues estamos modificando el contenido del objeto.
			auxGrupo.setAlumno(new Alumno(Nom, Apell, Edad));
		}
		
		return "example1";
	}
	
	
	//En este ejemplo, se crea una cookie denominada Cookie1 cuyo valor es la cadena 'valor1#valor2#valor3'. Notar que hay caracteres que suelen dar problemas, como es el caso del espacio. Un
	//uso común es guardar varios valores en una misma cookie y para ello se puede introducir separadores; en este ejemplo se ha usado el caracter # como separador. Por defecto,
	//el valor de la cookie no está cifrada y un usuario experto puede examinarla desde el navegador.
	
	//RECUERDA: En las cookies solo se debe almacenar la minima información necesaria que es útil más allá de la sesión del usuario. La escritura y lectura de los valores de una cookie
	//es muy costosa en rendimiento si se compara con las variables de sesión. El valor de una cookie no esta serializada, es decir, es una cadena de texto (no intentes guardar un objeto directamente).
	@GetMapping(value = {"/ejemplo4"}) 
	public String ejem4(HttpServletRequest request, HttpServletResponse response) {
		

		//Se crea la cookie indicando su nombre (Cookie1) y el valor (valor1#valor2#valor3).
        Cookie newCookie = new Cookie("Cookie1", "valor1#valor2#valor3");
        
        //Vigencia de la cookie: 24 horas. Como no se indica de forma explícita el dominio, es accesible a todo el dominio de la aplicación.
        newCookie.setMaxAge(24 * 60 * 60); 
        
        //Se añade a la respuesta para que se guarde en el cliente.
        response.addCookie(newCookie);
        
		return "example4";
	}
	
	//En este ejemplo se procede a leer el valor de la cookie creada en el ejemplo 4 y mostrar un mensaje en la vista.
	@GetMapping(value = {"/ejemplo5"}) 
	public String ejem5(HttpServletRequest request, Model model) {
		
		//Se obtienen todas las cookies accesibles por el dominio.
		Cookie[] cookies = request.getCookies();
		
		//Se crea un objeto a modo de modelo de negocio para alimentar la vista
		CookieData objCookieData = new CookieData();
		
		//Se inicializa el resultado o información que se mostrará en la vista. Por defecto, se asume que la cookie no existe.
		objCookieData.setEvaluarCookie("Cookie1 no encontrada o localizada. Para generar esta cookie haga click en el Ejemplo 4.");
		
		//Si existe alguna cookie accesible al dominio de la aplicación.
		if (cookies != null) {
			//Se recorren todas las cookies accesibles hasta localizar la que nos interesa.
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("Cookie1")) {
					objCookieData.setEvaluarCookie("El valor de la Cookie1 es: " + cookies[i].getValue());
					break;
				}
			}
		}
		
		//Se carga el modelo del negocio (CookieData) como atributo en el modelo de spring.
		model.addAttribute("CookieData", objCookieData);
		
		return "example5";
	}
	
	//En este ejemplo, se muestra como podemos modificar cualquier atributo de una cookie ya creada. Si no existe, se crea la cookie.
	@GetMapping(value = {"/ejemplo6"}) 
	public String ejem6(HttpServletRequest request, HttpServletResponse response) {
		
		//Se obtienen todas las cookies accesibles por el dominio.
		Cookie[] cookies = request.getCookies();
		
		//Se crea una variable de tipo Cookie
		Cookie cookie = null;
		

		//Si existe alguna cookie accesible al dominio de la aplicación.
		if (cookies != null) {
			//Se recorren todas las cookies accesibles hasta localizar la que nos interesa.
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("Cookie1")) {
					cookie = cookies[i];
					break;
				}
			}
		} 
		
		//Si la cookie existe se modifica. Si no existe se crea. Se establece una vigencia de 1 hora.
		if (cookie != null) {
			cookie.setValue("valor4#valor5#valor6");
			cookie.setMaxAge(60*60);
		} else {
			//Se crea la cookie indicando su nombre (Cookie1) y el valor (valor1#valor2#valor3).
			cookie= new Cookie("Cookie1", "valor4#valor5#valor6");
	        
	        //Vigencia de la cookie: 24 horas. Como no se indica dominio, solo es accesible por el dominio de la aplicacion.
			cookie.setMaxAge(60*60); 
		}

        //Para que los cambios surjan efecto, es necesario añadirla a la respuesta del cliente.
        response.addCookie(cookie);
		
		return "example6";
	}
	
	//En este ejemplo, se elimina la cookie. No exíste un método para eliminar la cookie pero para conseguirlo, solo es necesario
	//asignar o al tiempo máximo de vigencia de la cookie.
	@GetMapping(value = {"/ejemplo7"}) 
	public String ejem7(HttpServletRequest request, HttpServletResponse response) {
		
		//Se obtienen todas las cookies accesibles por el dominio.
		Cookie[] cookies = request.getCookies();
		
		//Se crea una variable de tipo Cookie
		Cookie cookie = null;
		

		//Si existe alguna cookie accesible al dominio de la aplicación.
		if (cookies != null) {
			//Se recorren todas las cookies accesibles hasta localizar la que nos interesa.
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("Cookie1")) {
					cookie = cookies[i];
					break;
				}
			}
		} 
		
		//No existe un método como tal para eliminar una cookie, pero si se asigna 0 a MaxAge, el enviarla al cliente se elimina.
		if (cookie != null) {
			//Se fuerza su eliminación.
			cookie.setMaxAge(0); 
	        //Para que los cambios surjan efecto, es necesario añadirla a la respuesta del cliente.
	        response.addCookie(cookie);
		} 

		return "example7";
	}
	
	//En este ejemplo se hace lo mismo que en el Ejemplo 5 pero con ménos código. Se hace uso de la java annotation de spring que permite leer valores de cookies por el nombre. En este caso se declara
	//la cookie como no requerida y si no existe no se produce excepción, sino que en valor de la variable es nula.
	@GetMapping(value = {"/ejemplo8"}) 
	public String ejem8(@CookieValue(name="Cookie1", required=false) String auxCookie, Model model) { //También podría indicarse un defaultValue (Ejemplo: defaultValue = "0").
		
		//Se crea un objeto a modo de modelo de negocio para alimentar la vista
		CookieData objCookieData = new CookieData();
		
		if (auxCookie == null) {
			objCookieData.setEvaluarCookie("Cookie1 no encontrada o localizada. Para generar esta cookie haga click en el Ejemplo 4.");
		} else {
			objCookieData.setEvaluarCookie(auxCookie);
		}
		
		//Se carga el modelo del negocio (CookieData) como atributo en el modelo de spring.
		model.addAttribute("CookieData", objCookieData);
		
		return "example8";
	}

	

}

