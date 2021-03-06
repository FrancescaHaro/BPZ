package com.bpz.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bpz.app.models.dao.IProveedorDao;
import com.bpz.app.models.entity.Proveedor;

@Service
public class ProveedorService implements IProveedorService {

	@Autowired
	private IProveedorDao proveedorDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Proveedor> findAll() {
		
		return proveedorDao.findAll();
	}

	@Override
	@Transactional
	public void save(Proveedor proovedor) {
		proveedorDao.save(proovedor);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Proveedor findbyId(Long id) {
		// TODO Auto-generated method stub
		return proveedorDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void eliminarPorId(Long id) {
		// TODO Auto-generated method stub
		proveedorDao.deleteById(id);
	}

	
}
