package br.com.urnawebapi.projeto.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.urnawebapi.projeto.dto.EleitorDto;
import br.com.urnawebapi.projeto.exception.ExpiredTokenException;
import br.com.urnawebapi.projeto.exception.InvalidTokenException;
import br.com.urnawebapi.projeto.model.Eleitor;
import br.com.urnawebapi.projeto.repository.EleitorInterface;
import br.com.urnawebapi.projeto.security.TokenUtil;
import io.jsonwebtoken.Claims;


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

    public Eleitor procurarEleitor(Integer id) {
        Optional<Eleitor> eleitorEncontrado = repository.findById(id);
        if (eleitorEncontrado.isPresent()) {
            return eleitorEncontrado.get();
        }
        throw new RuntimeException("Não encontrei");
    }

    public Eleitor editaEleitor(Eleitor eleitor) {
        if (validarSenha(eleitor)) {
        String esconder = this.passwordEncoder.encode(eleitor.getSenha());
        eleitor.setSenha(esconder);
        Eleitor eleitorNovo = repository.save(eleitor);
        return eleitorNovo;
        }
        throw new RuntimeException("Senha incorreta");
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

    public Eleitor gerarToken(@Valid EleitorDto eleitor, String token) {
        Eleitor user = repository.findByEmail(eleitor.getEmail());
        if(user != null) {
            Boolean valid = passwordEncoder.matches(eleitor.getSenha(), user.getSenha());
            if(valid && !token.isEmpty() && validarToken(token)) {
                System.out.println(valid);
                eleitor.setToken(TokenUtil.criarToken(user));
                //String tokenGerado = TokenUtil.criarToken(user);
                //user.setToken(tokenGerado);
                return user;
            }   
            else {
                
            }     
        }
        return null;
    
    }

    private boolean validarToken(String token) {
        try {
            String tokenValido = token.replace("Bearer ", "");
            Claims claims = TokenUtil.decodificarToken(tokenValido);

            System.out.println(claims.getIssuer());
            System.out.println(claims.getIssuedAt());

            if(claims.getExpiration().before(new Date(System.currentTimeMillis()))) throw new ExpiredTokenException();
            System.out.println(claims.getExpiration());
            return true;
        } catch (ExpiredTokenException et) {
            et.printStackTrace();
            throw et;
        }catch (Exception e) {
            e.printStackTrace();
            throw new InvalidTokenException();
        }
    }   
}
