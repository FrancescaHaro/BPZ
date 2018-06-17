package com.bpz.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bpz.app.models.entity.Cuenta;

@Repository
public interface ICuentaDao extends JpaRepository<Cuenta, Long> {

}
