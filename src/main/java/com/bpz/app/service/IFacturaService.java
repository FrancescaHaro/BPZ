package com.bpz.app.service;

import java.util.List;

import com.bpz.app.models.entity.Factura;

public interface IFacturaService {

	public List<Factura> findAll();
	
	public void save(Factura factura);
	
    public Factura findbyId(Long id);
    
    public void eliminarPorId(Long id);
}
