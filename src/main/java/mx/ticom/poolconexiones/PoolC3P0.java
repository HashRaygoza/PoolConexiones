/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ticom.poolconexiones;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class PoolC3P0 {
    // Notara que el pool es un miembro *estatico* esto es para evitar duplicidad

    private static PoolC3P0 datasource;
    // Esta es la fuente de datos que conecta con la base de datos
    private final ComboPooledDataSource cpds;

    /**
     * Crea el constructor del pool, notara que este constructor es privado esto
     * con el fin de que podamos controlar cuando se crea el pool
     *
     * @throws IOException
     * @throws SQLException
     * @throws PropertyVetoException
     */
    private PoolC3P0() throws IOException, SQLException, PropertyVetoException {
        // Configuramos la conexion a base de datos
        // Creamos la fuente de datos
        cpds = new ComboPooledDataSource();
        // Que driver de base de datos usaremos
        cpds.setDriverClass("com.mysql.jdbc.Driver");
        // La url de la base de datos a la que nos conectaremos
        cpds.setJdbcUrl("jdbc:mysql://127.0.0.1/");
        // Usuario de esa base de datos
        cpds.setUser("usuario");
        // ContraseÃ±a de la base de datos
        cpds.setPassword("contrasena");

        // Configuramos el pool
        // Numero de conexiones con las que iniciara el pool
        cpds.setInitialPoolSize(0);
        // Minimo de conexiones que tendra el pool
        cpds.setMinPoolSize(0);
        // Numero de conexiones a crear cada incremento
        cpds.setAcquireIncrement(1);
        // Maximo numero de conexiones
        cpds.setMaxPoolSize(5);
        // Maximo de consultas
        cpds.setMaxStatements(180);
        // Maximo numero de reintentos para conectar a base de datos
        cpds.setAcquireRetryAttempts(2);
        // Que se genere una excepcion si no se puede conectar
        cpds.setBreakAfterAcquireFailure(true);
    }

    /**
     * Nos regresa la instancia actual del pool, en caso que no halla una
     * instancia crea una nueva y la regresa
     *
     * @return
     * @throws IOException
     * @throws SQLException
     * @throws PropertyVetoException
     */
    public static PoolC3P0 getInstance() throws IOException, SQLException, PropertyVetoException {

        if (datasource == null) {
            datasource = new PoolC3P0();
            return datasource;
        } else {
            return datasource;
        }
    }

    /**
     * Este metodo nos regresa una conexion a base de datos, esta la podemos
     * usar como una conexion usual
     *
     * @return Conexion a base de datos
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return this.cpds.getConnection();
    }

}
