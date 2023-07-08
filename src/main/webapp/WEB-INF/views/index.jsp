<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring Boot Proyect Demo 2</title>
<!-- En las vistas .jsp, se puede usar la siguiente directiva para construir las rutas a los recursos estáticos:
		 ${pageContext.request.contextPath} 
     
     En este proyecto se ha optado por indicarlas de forma estática, pero si cambia en las controladoras la ubicación lógica y relativas mapeadas, podrían producirse
     errores de acceso a los recursos (.js, .css) si no se actualizan manualmente.
-->
<link rel="stylesheet" href="./styles/index.css">

</head>
<body>
    <div class="cabecera">
        <div class="cab1"></div>
        <div class="cap2"></div>
    </div>
    <div class="cuerpo">
        <div class="panel1">
            <div class="nota"><div class="notacab1"><p>Proyecto</p></div>
                              <div class="notabody"><p style="text-align: center;"><span>#Spring Proyect Demo 2</span></p>
                            </div>
            </div>
            <div class="nota"><div class="notacab1"><p>Objetivos</p></div>
                              <div class="notabody"><p class="objetivos">- <b>F</b>amiliarizarse con el uso y gestión de las variables de sesión (memoria de usuario).</p>
                              						<p class="objetivos">- <b>C</b>onocer como se puede recorrer colecciones de objetos en las vistas (.jsp) basado en las Tags Library de JSTL.</p>   
                                                    <p class="objetivos">- <b>I</b>ntroducción al uso de las Spring Mvc Form Tag Library.</p>
                                                    <p class="objetivos">- <b>C</b>reación y gestión de Cookies con Spring.</p>
                              </div>        
                            
            </div>
            <div class="nota"><div class="notacab2"><p>Entorno de Desarrollo</p></div>
                              <div class="notabody"><p><span>Ide:</span> Eclipse, versión: 2023-03 </p>
                                                    <p><span>Servidor:</span> Apache Tomcat, versión: 9</p>
                                                    <p><span>Jdk:</span> OpenJdk, versión: 17.1</p>
                            </div>
            </div>
            <div class="nota"><div class="notacab2"><p>Estandar de Desarrollo</p></div>
                              <div class="notabody"><p><span>Gestor de proyecto:</span> Maven, versión: 4</p>
                            </div>
            </div> 
            <div class="nota"><div class="notacab2"><p>Dependencias</p></div>
                              <div class="notabody"><p><span>Spring Framework:</span> Spring Boot, versión 2.7.12. Módulos: Spring Web MVC</p>
                            </div>
            </div>    
            <div class="nota"><div class="notacab2"><p>Documentación y Sitios de Interés</p></div>
                              <div class="notabody"><p><a href="https://mvnrepository.com/" target="_blank">Ir a</a> Maven Repository</p>
                                                    <p><a href="https://docs.spring.io/spring-framework/docs/current/reference/html/" target="_blank">Leer</a> Documentación de Spring</p>
                                                    <p><a href="https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/"target="_blank">Leer</a> Documentación de Spring Boot</p>
                                                    <p><a href="https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-view" target="_blank">Leer</a> Documentación sobre Spring Views I</p>
                                                    <p><a href="https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/view.html" target="_blank">Leer</a> Documentación sobre Spring Views II</p>
                                                    <p><a href="https://www.javatpoint.com/spring-mvc-form-tag-library" target="_blank">Leer</a> Documentación Spring Mvc Tags</p>
                            </div>
            </div>         
        </div>
        <div class="panel2">
            <div class="item"><p><span>Ejemplo 1 - Variables de sesión:</span> Formulario que envía los datos de un alumno por método POST a <b>ejemplo1</b> (controladora MainController) y lo añade a una lista implementada con un ArrayList en un objeto de la clase Grupo. Si es el primer alumno que se añade durante la sesión del usuario, se crea e inicializa una variable de sesión denominada DataAlumno. Si al añadir un nuevo alumno, ya existe la variable de sesión, lo añade a la lista. Pruebe a introducir varios alumos. Para examinar la lista de alumnos, pruebe a ejecutar el Ejemplo 2.</p>
            				 <div><form name="ejemplo1" method="POST" action="./ejemplo1">
            						  <div><label>Nombre: </label><input type="text" name="Nombre" required/></div><!--
            					   --><div><label>Apellidos: </label><input type="text" name="Apellidos" required/></div><!--
            					   --><div><label>Edad: </label><input type="number" name="Edad" min="1" max="100" step="1" required/></div><!--
            					   --><div><input type="submit" value="Añadir" /></div>
            					  </form>
                            </div>
            </div>
            <div class="item"><p><span>Ejemplo 2 - Iteración en JSTL:</span> Este ejemplo invoca a <b>./alumnos/ejemplo2</b> (controladora AlumnosController) por método GET y muestra los alumnos almacenados en la variable de sesión DataAlumno por el Ejemplo 1. Se hace uso del Tag C de JSTL para iterar los objetos de tipo Alumno. <a href="./alumnos/ejemplo2">Ver Ejemplo</a></p></div>
            <div class="item"><p><span>Ejemplo 3 - Spring Mvc Form Tag Library:</span> Este ejemplo invoca a <b>./alumnos/ejemplo3</b> (controladora MainController) por método GET y hace uso de las Spring Mvc Tags tal que puede observar como se puede precargar un formulario desde un objeto del modelo y al enviar los datos se pueden recoger con @ModelAttribute. Es decir, se puede relacionar un formulario directamente con un objeto del modelo ahorrando código. <a href="./alumnos/ejemplo3">Ver Ejemplo</a></p></div>
            <div class="item"><p><span>Ejemplo 4 - Cookies - creación:</span> Este ejemplo invoca a <b>./ejemplo4</b> (controladora MainController) por método GET y crea una Cookie denominada Cookie1 cuya vigencia es de un día. Este ejemplo también es interesante para comprobar como podemos acceder a los objetos HttpServletRequest y HttpServletResponse que permiten a bajo nivel acceder y maniular la cabecera de la petición o respuesta de HTTP. <a href="./ejemplo4">Ver Ejemplo</a></p></div>
            <div class="item"><p><span>Ejemplo 5 - Cookies - lectura:</span> Este ejemplo invoca a <b>./ejemplo5</b> (controladora MainController) por método GET y obtiene (de existir) la cookie generada en el Ejemplo 4 denominada Cookie1 y muestra el valor contenido en ella. Se sigue haciendo uso del objeto HttpServletRequest (petición http). <a href="./ejemplo5">Ver Ejemplo</a></p></div>
            <div class="item"><p><span>Ejemplo 6 - Cookies - modificación:</span> Este ejemplo invoca a <b>./ejemplo6</b> (controladora MainController) por método GET y de existir la cookie generada en el Ejemplo 4, modifica su tiempo de vida pasando a ser de 1 hora y también modifica su valor. Después de creada una cookie, se puede modificar sus atributos. Se sigue haciendo uso de los objetos HttpServletRequest y HttpServletResponse (petición y respuesta http). <a href="./ejemplo6">Ver Ejemplo</a></p></div>
            <div class="item"><p><span>Ejemplo 7 - Cookies - eliminación:</span> Este ejemplo invoca a <b>./ejemplo7</b> (controladora MainController) por método GET y de existir la cookie generada en el Ejemplo 4, la elimina. Se sigue haciendo uso de los objetos HttpServletRequest y HttpServletResponse (petición y respuesta http). <a href="./ejemplo7">Ver Ejemplo</a></p></div>
            <div class="item"><p><span>Ejemplo 8 - Cookies - lectura con @CookieValue:</span> Este ejemplo invoca a <b>./ejemplo8</b> (controladora MainController) por método GET y de existir la cookie generada en el Ejemplo 4, procede a recuperar el valor usando la java annotation de spring. La necesidad de solo lectura del valor de una cookie suele ser lo más frecuente y para ello spring nos ofrece la notación @CookieValue. <a href="./ejemplo8">Ver Ejemplo</a></p></div>
        </div>
    </div>
    <div class="pie"><p><span>©</span> 2022 - Daniel Pérez Pérez</p></div>

</body>
</html>