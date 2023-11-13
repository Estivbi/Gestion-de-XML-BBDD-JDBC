package org.example;

import java.io.IOException;
import java.sql.*;

import org.postgresql.gss.GSSOutputStream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class Main {

    public static void main(String[] args) {
        try {
// Crear la conexión a la base de datos
            Connection connection = DBConnection.getConnection();
            // Crear la tabla "empleados" si no existe
            DBConnection.createTable(connection);

            // Parsear el archivo XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("C:\\Users\\Carolina\\IdeaProjects\\ActividadUd2_Carolina_Rodriguez\\src\\main\\java\\empleados.xml");

            // Obtener la lista de nodos "empleado"
            NodeList nodeList = document.getElementsByTagName("empleado");

            // Iterar sobre cada nodo "empleado" y agregarlo a la base de datos
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element empleadoElement = (Element) node;

                    // Obtener los datos del empleado del XML
                    String nombre = empleadoElement.getElementsByTagName("nombre").item(0).getTextContent();
                    String apellido = empleadoElement.getElementsByTagName("apellido").item(0).getTextContent();
                    String dni = empleadoElement.getElementsByTagName("dni").item(0).getTextContent();
                    String depto = empleadoElement.getElementsByTagName("depto").item(0).getTextContent();

                    // Verificar si el empleado ya existe en la base de datos
                    if (!employeeExists(connection, dni)) {
                        // Insertar el empleado en la base de datos
                        insertEmployee(connection, nombre, apellido, dni, depto);
                        System.out.println("Empleado insertado: " + nombre + " " + apellido);
                    } else {
                        System.out.println("Empleado duplicado: " + nombre + " " + apellido);
                    }
                }
            }

            // Cerrar la conexión a la base de datos
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean employeeExists(Connection connection, String dni) throws SQLException {
        String query = "SELECT COUNT(*) FROM empleados WHERE dni = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, dni);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() && resultSet.getInt(1) > 0;
            }
        }
    }


    private static void insertEmployee(Connection connection, String nombre, String apellido, String dni, String depto) throws SQLException {
        String insertSQL = "INSERT INTO empleados (nombre, apellido, dni, depto) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellido);
            preparedStatement.setString(3, dni);
            preparedStatement.setString(4, depto);
            preparedStatement.executeUpdate();
        }
    }
}