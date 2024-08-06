package br.com.urnawebapi.projeto.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.urnawebapi.projeto.model.Candidato;
import br.com.urnawebapi.projeto.repository.CandidatoInterface;


@Service
public class CandidatoService {
    
    private CandidatoInterface repository;

    public CandidatoService(CandidatoInterface repository) {
        this.repository = repository;
    }

    public Page<Candidato> listarCandidato(Pageable pageable) {
        return repository.findCandidatos(pageable);
    }

/*     public Page<Eleitor> listarEleitor(String minDate, String maxDate, Pageable pageable) {
        
        LocalDate hoje = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        LocalDate min = minDate.equals("") ? hoje.minusDays(365) : LocalDate.parse(minDate);
        LocalDate max = maxDate.equals("") ? hoje : LocalDate.parse(maxDate);

        return repository.findEleitores(min,max,pageable);
    } */

    public Page<Candidato> listarCandidatoEspecifico(String number, Pageable pageable) {
        return repository.findCandidatoEspecifico(number, pageable);
    }

    @Transactional
    public void votarCandidatoEspecifico(String number) {
        repository.voteCandidatoEspecifico(number);
    }

    public Candidato criarCandidato(Candidato candidato) {
        Candidato candidatonovo = repository.save(candidato);
        return candidatonovo;
    }

    public Candidato procurarCandidato(Integer id) {
        Optional<Candidato> candidatoencontrado = repository.findById(id);
        if (candidatoencontrado.isPresent()) {
            return candidatoencontrado.get();
        }
        throw new RuntimeException("Não encontrei");
    }

    public Candidato procurarCandidatoNumero(String numero) {
        Optional<Candidato> candidatoencontrado = repository.findByNumero(numero);
        if (candidatoencontrado.isPresent()) {
            return candidatoencontrado.get();
        }
        throw new RuntimeException("numero inexistente");
    }

    public Candidato editaCandidato(Candidato candidato) {
        Candidato candidatonovo = repository.save(candidato);
        return candidatonovo;
    }

    public Boolean excluirCandidato(Integer id) {
        repository.deleteById(id);
        return true;
    }

    /*public Boolean validarSenha(Candidato Candidato) {
        String senha = repository.getReferenceById(Candidato.getId()).getSenha();
        Boolean valido = true;
        return valido;
    }

    /*public Candidato gerarToken(@Valid CandidatoDto Candidato, String token) {
        Candidato user = repository.findByEmail(Candidato.getEmail());
        if(user != null) {
            Boolean valid = passwordEncoder.matches(Candidato.getSenha(), user.getSenha());
            if(valid && !token.isEmpty() && validarToken(token)) {
                System.out.println(valid);
                Candidato.setToken(TokenUtil.criarToken(user));
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
    }   */
}
