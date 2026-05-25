package com.krakedev.jdbc.videojuegos.services;
import com.krakedev.jdbc.videojuegos.VideojuegoJdbc;
import com.krakedev.videojuegos.entidades.Videojuego;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServicioVideojuegoJdbc {
    private VideojuegoJdbc jdbc = new VideojuegoJdbc();

    // CREAR
    public Videojuego crear(Videojuego videojuego) {
        return jdbc.insertar(videojuego);
    }

    // LISTAR
    public List<Videojuego> listar() {
        return jdbc.listar();
    }

    // BUSCAR
    public Videojuego buscarPorCodigo(String codigo) {
        return jdbc.buscar(codigo);
    }

    // ACTUALIZAR
    public Videojuego actualizar(String codigo, Videojuego videojuego) {
        return jdbc.actualizar(codigo, videojuego);
    }

    // ELIMINAR
    public boolean eliminar(String codigo) {
        return jdbc.eliminar(codigo);
    }
}