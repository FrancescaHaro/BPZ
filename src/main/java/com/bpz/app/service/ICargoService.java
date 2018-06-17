package com.bpz.app.service;

import java.util.List;

import com.bpz.app.models.entity.Cargo;

public interface ICargoService {

	public List<Cargo> findAll();
	
	public void save(Cargo cargo);
}
