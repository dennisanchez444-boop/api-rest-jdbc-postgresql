package com.krakedev.jdbc.videojuegos.controller;

import com.krakedev.jdbc.videojuegos.services.ServicioVideojuegoJdbc;
import com.krakedev.videojuegos.entidades.Videojuego;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jdbc/videojuegos")
public class VideojuegoJdbcController {

	@Autowired
	private ServicioVideojuegoJdbc service;

	@PostMapping
	public Videojuego crear(@RequestBody Videojuego videojuego) {
		return service.crear(videojuego);
	}

	@GetMapping
	public List<Videojuego> listar() {
		return service.listar();
	}

	@GetMapping("/{codigo}")
	public Videojuego buscar(@PathVariable String codigo) {
		return service.buscarPorCodigo(codigo);
	}

	@PutMapping("/{codigo}")
	public Videojuego actualizar(@PathVariable String codigo, @RequestBody Videojuego videojuego) {
		return service.actualizar(codigo, videojuego);
	}

	@DeleteMapping("/{codigo}")
	public boolean eliminar(@PathVariable String codigo) {
		return service.eliminar(codigo);
	}
}