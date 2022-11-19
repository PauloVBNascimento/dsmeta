package br.com.urnawebapi.projeto.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.urnawebapi.projeto.dto.EleitorDto;
import br.com.urnawebapi.projeto.model.Eleitor;
import br.com.urnawebapi.projeto.repository.EleitorInterface;
import br.com.urnawebapi.projeto.security.TokenUtil;
import br.com.urnawebapi.projeto.security.Token;

@Service
public class EleitorService {
    
    private EleitorInterface repository;
    private PasswordEncoder passwordEncoder;

    public EleitorService(EleitorInterface repository) {
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Page<Eleitor> listarEleitor(String minDate, String maxDate, Pageable pageable) {
        
        LocalDate hoje = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        LocalDate min = minDate.equals("") ? hoje.minusDays(365) : LocalDate.parse(minDate);
        LocalDate max = maxDate.equals("") ? hoje : LocalDate.parse(maxDate);

        return repository.findEleitores(min,max,pageable);
    }

    public Eleitor criarEleitor(Eleitor eleitor) {
        String esconder = this.passwordEncoder.encode(eleitor.getSenha());
        eleitor.setSenha(esconder);
        Eleitor eleitorNovo = repository.save(eleitor);
        return eleitorNovo;
    }

    public Eleitor editaEleitor(Eleitor eleitor) {
        String esconder = this.passwordEncoder.encode(eleitor.getSenha());
        eleitor.setSenha(esconder);
        Eleitor eleitorNovo = repository.save(eleitor);
        return eleitorNovo;
    }

    public Boolean excluirEleitor(Integer id) {
        repository.deleteById(id);
        return true;
    }

    public Boolean validarSenha(Eleitor eleitor) {
        String senha = repository.getReferenceById(eleitor.getId()).getSenha();
        Boolean valido = passwordEncoder.matches(eleitor.getSenha(), senha);
        return valido;
    }

    public Token gerarToken(@Valid EleitorDto eleitor) {
        Eleitor user = repository.findByEmail(eleitor.getEmail());
        System.out.println(eleitor.getSenha());
        System.out.println(user.getSenha());
        if(user != null) {
            Boolean valid = passwordEncoder.matches(eleitor.getSenha(), user.getSenha());
            if(valid) {
                System.out.println(valid);
                return new Token(TokenUtil.criarToken(user));
            }   
            else {

            }     
        }
        return null;
    
    } 
}
