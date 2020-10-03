/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ticom.poolconexiones;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class C3P0Ejemplo {

    public static void main(String[] args) {
        try {
            // Obtenemos el pool de conexiones
            PoolC3P0 pool = PoolC3P0.getInstance();

            for (int i = 0; i < 10; i++) {
                // Solicitamos una conexion al pool
                Connection cx = pool.getConnection();
                // Creamos nuestro objecto de consulta
                Statement consulta = cx.createStatement();
                // La consulta que ejecutaremos
                String sql = "SELECT * FROM pruebas.prueba";
                // Obtenemos los datos
                ResultSet data = consulta.executeQuery(sql);
                data.next();

                Integer puerto = data.getInt("puerto_serial");
                Integer impresora = data.getInt("puerto_impresora");
                Integer red = data.getInt("puerto_red");
                Integer scaner = data.getInt("puerto_escaner");

                // Los presentamos en pantalla
                System.out.println("Vuelta numero " + i);
                System.out.println("Puerto " + puerto);
                System.out.println("Impresora " + impresora);
                System.out.println("Red " + red);
                System.out.println("Escaner " + scaner);
                System.out.println("");
                System.out.println("");

                // Cerramos la conexion, esto es vital, de no hacerlo el pool creara una nueva conexion
                // pero al cerrar liberamos esa conexion para que el pool la reuse
                cx.close();

                // Un retrazo de 10 segundos para poder ver las conexiones en MySQL Workbench
                Thread.sleep(5000);
            }

        } catch (IOException ex) {
            System.out.println("Error de entrada salida");
        } catch (SQLException ex) {
            System.out.println("Error de conexion a base de datos");
            ex.printStackTrace();
        } catch (PropertyVetoException ex) {
            System.out.println("Error de propiedades");
        } catch (InterruptedException ex) {
            System.out.println("Error de interrupcion de proceso");
        }
    }
}
