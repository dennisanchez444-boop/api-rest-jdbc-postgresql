package com.krakedev.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Conexion {

    private static final Logger logger = LoggerFactory.getLogger(Conexion.class);

    private static final String URL = "jdbc:postgresql://localhost:5432/apijdbc";
    private static final String USER = "postgres";
    private static final String PASSWORD = "sxnchz76#";

    public static Connection conectar() {
        Connection con = null;

        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            logger.info("Conexión exitosa a PostgreSQL");

        } catch (Exception e) {
            logger.error("Error inesperado en el sistema", e);
        }finally {
        	try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        return con;
    }
}