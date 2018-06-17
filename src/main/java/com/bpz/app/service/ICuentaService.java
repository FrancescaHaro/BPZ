package com.bpz.app.service;

import java.util.List;

import com.bpz.app.models.entity.Cuenta;

public interface ICuentaService {

	public List<Cuenta> findAll();
	
	public void save(Cuenta cuenta);
}
