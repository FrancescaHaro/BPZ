package com.bpz.app.service;

import java.util.List;

import com.bpz.app.models.entity.Empleado;

public interface IEmpleadoService {

	public List<Empleado> finAll();
	
	public void save(Empleado empleado);
}
