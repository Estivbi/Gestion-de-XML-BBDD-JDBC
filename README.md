# Gestion-de-XML-BBDD-JDBC
DESCRIPCIÓN DE LA ACTIVIDAD PRÁCTICA:
Se tiene un archivo XML llamado "empleados.xml" con información sobre los empleados de una empresa. Se necesita leer este archivo, parsear la información y guardarla en una base de datosrelacional.
1. El archivo XML tiene la siguiente estructura, tienes que crearlo y debe tener al menos 6empleados.
2. Se debe crear una base de datos con una tabla "empleados" si no existe que tenga columnasid, nombre, apellido, DNI y depto. Utiliza JDBC para esto.
3. Desarrollar un programa Java que:
	a. Lee y parsea el XML usando cualquier técnica (DOM, SAX, etc)
	b. Por cada elemento "empleado", genera un insert SQL para agregarlo a la tablaempleados
	c. Utilice JDBC para conectarse a la base de datos y ejecutar los inserts generados.
4. Imprimir en consola la cantidad de empleados insertados en la tabla.
5. Si se ejecuta varias veces el código, no debe repetirse en la base de datos el mismo usuariocon el mismo DNI.

A continuación se muestra un ejemplo de como quedaría el proyecto:

[![Demostración del Proyecto](https://clipchamp.com/watch/zD7lA3c421i)](https://clipchamp.com/watch/zD7lA3c421i)


