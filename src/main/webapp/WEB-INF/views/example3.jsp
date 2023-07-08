<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring Proyect Demo 2 - Ejemplo 3</title>
<link rel="stylesheet" href="../styles/index.css">
</head>
<body>
    <div class="cabecera">
        <div class="cab1"></div>
        <div class="cap2"></div>
    </div>
    <div class="cuerpo">
    	<!-- Documentación recomendada para entender las form mvc tags:
    	     https://www.javatpoint.com/spring-mvc-form-tag-library
    	     https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/view.html
    	     
    	     Busca en google "Spring Mvc Tags"
    	 -->
        <div class="panel2">
        <div class="item"><p><span>Ejemplo 3:</span> </p></div>
		<div class="item"> 
		<form:form class="frmAlumno" action="./procesarEjemplo3" method="POST" modelAttribute="InfoAlumno">
		    <!-- En path se debe indicar el nombre del set/get de la clase pero sin get/set y la primera letra en minusculas. -->
			<label>Nombre: </label><form:input path="nom" /><br/>
			<label>Apellidos: </label><form:input path="apell" /><br/>
			<label>Sexo: </label><form:select path="genero">
									<form:option value="H" label="Hombre"></form:option>
									<form:option value="M" label="Mujer"></form:option>
								 </form:select><br/>
            <label>Módulos: </label><br/><form:select path="modulos" multiple="true">
									<form:option value="613" label="Desarrollo de Aplicaciones en Entorno de Servidor"></form:option>
									<form:option value="611" label="Despliegue de Aplicaciones Web"></form:option>
								 </form:select>
								 <br/><br/>
			<label>Selecciona el turno:</label><br/>
			Mañana <form:radiobutton path="turno" value="M"/>
			Tarde <form:radiobutton path="turno" value="T"/>
			Semipresencial <form:radiobutton path="turno" value="S"/>
								 <br/><br/>
			<label>Seleccione si lo desea alguna de las siguientes situaciones:</label><br/>
			Vivo en municipio diferente al del centro de estudios.<form:checkbox path="situaciones" value="Vivo en municipio diferente al del centro de estudios."/><br/>
			Familia numerosa.<form:checkbox path="situaciones" value="Familia numerosa."/><br/>	 
			Actualmente trabajando.<form:checkbox path="situaciones" value="Actualmente trabajando."/><br/>			 
								 <br/><br/> 
			<input type="submit" value="Enviar" />
		</form:form>
		<br/><br/>
		<p>${InfoAlumno.resultado}</p>
		</div>
		<div class="item"><p style="text-align:center;"><a href="../">Volver</a></p></div> 
		</div>
    </div>
    <div class="pie"></div>
</body>
</html>