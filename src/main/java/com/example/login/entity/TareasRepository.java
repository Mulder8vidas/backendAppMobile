package com.example.login.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TareasRepository  extends JpaRepository<TareasEntity,Integer> {


/*    @Query("select t from tareas t inner join trabajadores  tb on tb.id=t.idTrabajador")*/

}
