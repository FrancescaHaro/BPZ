package com.bpz.app.service;

import java.util.List;

import com.bpz.app.models.entity.Banco;

public interface IBancoService {

	public List<Banco> findAll();
	
	public void save(Banco banco);
}
