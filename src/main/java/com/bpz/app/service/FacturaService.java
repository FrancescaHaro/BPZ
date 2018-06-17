package com.bpz.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bpz.app.models.dao.IFacturaDao;
import com.bpz.app.models.entity.Factura;

@Service
public class FacturaService implements IFacturaService{

	@Autowired
	private IFacturaDao facturaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Factura> findAll() {
		
		return facturaDao.findAll();
	}

	@Override
	@Transactional
	public void save(Factura factura) {
		facturaDao.save(factura);
		
	}

	@Override
	@Transactional
	public Factura findbyId(Long id) {
		// TODO Auto-generated method stub
		return facturaDao.findById(id).orElse(null);
	}

	

}
