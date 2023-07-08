<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring Proyect Demo 2 - Ejemplo 1</title>
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
        <div class="panel2">
            <!--Ejemplo de notación en java de Expression language (EL) haciendo uso de $. Como se comprueba en este ejemplo, técnicamente no es obligatorio que la controladora
                deba tramitar los parámetros recibidos.
            -->
            <div class="item"><p><span>Resultado Ejemplo 1:</span> Alumno añadido correctamente. El número de alumnos actualmente introducidos en su sesión es de: ${DataGrupo.numAlumnos} alumno/s.</p>
                              <p style="text-align: center;">Para comprobar la lista de alumnos, que se visualiza con el Ejemplo 2, haga clic en el siguiente enlace.<br/><br/> <a href="./alumnos/ejemplo2">Ver Alumnos</a></p>
            </div>
            <div class="item"><p style="text-align:center;"><a href="./">Volver</a></p></div>
        </div>
    </div> 
    <div class="pie"><p><span>©</span> 2022 - Daniel Pérez Pérez</p></div>

</body>
</html>