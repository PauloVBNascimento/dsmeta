package br.com.urnawebapi.projeto.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.urnawebapi.projeto.model.Candidato;

public interface CandidatoInterface extends JpaRepository<Candidato, Integer>{

    //Candidato findByEmail(String email);

    @Query("SELECT obj FROM Candidato obj ORDER BY obj.id ASC")
    Page<Candidato> findCandidatos(Pageable pageable);

    Optional<Candidato> findByNumero(String numero);  

    @Query("SELECT obj FROM Candidato obj WHERE obj.numero LIKE :number ORDER BY obj.id ASC")
    Page<Candidato> findCandidatoEspecifico(@Param("number") String number, Pageable pageable);

    @Modifying
    @Query(value = "UPDATE Candidato c SET c.votos = c.votos + 1 WHERE c.numero = ?", nativeQuery = true 
    )
    void voteCandidatoEspecifico(String number);  
    
    
}
