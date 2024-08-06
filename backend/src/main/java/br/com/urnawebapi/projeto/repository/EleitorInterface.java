package br.com.urnawebapi.projeto.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.urnawebapi.projeto.model.Eleitor;

public interface EleitorInterface extends JpaRepository<Eleitor, Integer>{

    Eleitor findByEmail(String email);

    @Query("SELECT obj FROM Eleitor obj WHERE obj.dataentrada BETWEEN :min AND :max ORDER BY obj.id ASC")
    Page<Eleitor> findEleitores(@Param("min") LocalDate min,@Param("max") LocalDate max, Pageable pageable);  
    
    
}
