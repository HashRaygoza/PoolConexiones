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
import java.util.logging.Level;
import java.util.logging.Logger;

public class C3P0Ejemplo {

    private final static Logger LOGGER = Logger.getLogger("mx.ticom.poolconexiones.C3P0Ejemplo");

    public static void main(String[] args) {
        try {

            PoolC3P0 pool = PoolC3P0.getInstance();

            for (int i = 0; i < 10; i++) {

                try ( Connection cx = pool.getConnection()) {
                    Statement consulta = cx.createStatement();

                    String sql = "SELECT * FROM pruebas.prueba";

                    ResultSet data = consulta.executeQuery(sql);
                    if (data.next() == true) {
                        Integer puerto = data.getInt("puerto_serial");
                        Integer impresora = data.getInt("puerto_impresora");
                        Integer red = data.getInt("puerto_red");
                        Integer scaner = data.getInt("puerto_escaner");

                        System.out.println("Vuelta numero " + i);
                        System.out.println("Puerto " + puerto);
                        System.out.println("Impresora " + impresora);
                        System.out.println("Red " + red);
                        System.out.println("Escaner " + scaner);
                        System.out.println("");
                        System.out.println("");
                    }
                }

                Thread.sleep(10000);
            }

        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error de entrada/salida");
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error de conexion a base de datos");
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            LOGGER.log(Level.SEVERE, "Error de interrupcion de proceso");
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (PropertyVetoException ex) {
            LOGGER.log(Level.SEVERE, "Error de propiedades");
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
}
