package com.example.login.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrabajadorRepository  extends JpaRepository<TrabajadorEntity,Integer> {
}
