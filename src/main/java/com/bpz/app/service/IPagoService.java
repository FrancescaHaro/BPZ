package com.bpz.app.service;

import java.util.List;

import com.bpz.app.models.entity.Pago;

public interface IPagoService {

	public List<Pago> findAll();
	
	public void save(Pago pago);
}
