package com.krakedev.jdbc.videojuegos;

import com.krakedev.jdbc.Conexion;
import com.krakedev.videojuegos.entidades.Videojuego;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VideojuegoJdbc {

	// INSERTAR
	public Videojuego insertar(Videojuego videojuego) {
		String sql = """
				INSERT INTO videojuegos
				(codigo,nombre,plataforma,precio,disponible,genero)
				VALUES (?,?,?,?,?,?)
				""";

		try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, videojuego.getCodigo());
			ps.setString(2, videojuego.getNombre());
			ps.setString(3, videojuego.getPlataforma());
			ps.setDouble(4, videojuego.getPrecio());
			ps.setBoolean(5, videojuego.isDisponible());
			ps.setString(6, videojuego.getGenero());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return videojuego;
	}

	// LISTAR
	public List<Videojuego> listar() {

	    System.out.println("ENTRO AL LISTAR");

	    List<Videojuego> lista = new ArrayList<>();

	    String sql = "SELECT * FROM videojuegos";

	    try (
	        Connection con = Conexion.conectar();
	        PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery()
	    ) {

	        System.out.println("CONEXION OK");

	        while (rs.next()) {

	            System.out.println("LEYENDO FILA");

	            Videojuego v = new Videojuego();

	            v.setCodigo(rs.getString("codigo"));
	            v.setNombre(rs.getString("nombre"));
	            v.setPlataforma(rs.getString("plataforma"));
	            v.setPrecio(rs.getDouble("precio"));
	            v.setDisponible(rs.getBoolean("disponible"));
	            v.setGenero(rs.getString("genero"));

	            lista.add(v);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    System.out.println(lista);

	    return lista;
	}

	// BUSCAR
	public Videojuego buscar(String codigo) {
		Videojuego v = null;
		String sql = "SELECT * FROM videojuegos WHERE codigo = ?";
		try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, codigo);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				v = new Videojuego();
				v.setCodigo(rs.getString("codigo"));
				v.setNombre(rs.getString("nombre"));
				v.setPlataforma(rs.getString("plataforma"));
				v.setPrecio(rs.getDouble("precio"));
				v.setDisponible(rs.getBoolean("disponible"));
				v.setGenero(rs.getString("genero"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return v;
	}

	// ACTUALIZAR
	public Videojuego actualizar(String codigo, Videojuego videojuego) {

		String sql = """
				UPDATE videojuegos
				SET nombre=?,
				    plataforma=?,
				    precio=?,
				    disponible=?,
				    genero=?
				WHERE codigo=?
				""";
		try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, videojuego.getNombre());
			ps.setString(2, videojuego.getPlataforma());
			ps.setDouble(3, videojuego.getPrecio());
			ps.setBoolean(4, videojuego.isDisponible());
			ps.setString(5, videojuego.getGenero());
			ps.setString(6, codigo);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return videojuego;
	}

	// ELIMINAR
	public boolean eliminar(String codigo) {
		String sql = "DELETE FROM videojuegos WHERE codigo=?";
		try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, codigo);
			int filas = ps.executeUpdate();
			return filas > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}