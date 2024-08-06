package br.com.urnawebapi.projeto.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.urnawebapi.projeto.model.Candidato;
import br.com.urnawebapi.projeto.service.CandidatoService;
import br.com.urnawebapi.projeto.service.SmsService;

@RestController
@CrossOrigin("*")
@RequestMapping("/candidatos")
public class CandidatoController  {
    
    @Autowired
    private CandidatoService candidatoService;
    @Autowired
    private SmsService smsService;

    public CandidatoController(CandidatoService candidatoService) {  
        this.candidatoService = candidatoService;
    }

    @GetMapping
    public Page<Candidato> listaCandidatos ( Pageable pageable) {
        return candidatoService.listarCandidato(pageable);
    }

    @GetMapping("/{id}/notificar")
    public void notificar(@PathVariable Integer id){
        smsService.sendSms(id);
    }
    
    @GetMapping("/{id}")
    public Candidato acharCandidatoPeloId(@PathVariable Integer id){
        return candidatoService.procurarCandidato(id);
    }
/*
     @GetMapping
    public Page<Eleitor> listaEleitores (
        @RequestParam(value="minDate", defaultValue = "")String minDate, 
        @RequestParam(value="maxDate", defaultValue = "")String maxDate, 
        Pageable pageable) {
        return eleitorService.listarEleitor(minDate, maxDate, pageable);
    }
 */
    @GetMapping("/votar")
    public Page<Candidato> acharCandidatoPeloNumero(
        @RequestParam(value = "number", defaultValue = "") String number, Pageable pageable){
        return candidatoService.listarCandidatoEspecifico(number, pageable);
    }

    @GetMapping("/votar/{number}")
    public void votarCandidatoPeloNumero(
        @RequestParam(value = "number", defaultValue = "") String number){
        candidatoService.votarCandidatoEspecifico(number);
    }

 /*    @PutMapping("/{id}/editar")
    public ResponseEntity<Candidato> editarCandidato (@Valid @RequestBody Candidato candidato) {
        return ResponseEntity.status(200).body(candidatoService.editaCandidato(candidato));
    } */ 

    @PostMapping("/criar")
    public ResponseEntity<Candidato> criarCandidato (@Valid @RequestBody Candidato candidato) {
        return ResponseEntity.status(201).body(candidatoService.criarCandidato(candidato));
        
    }

    @PutMapping("/{id}/editar")
    public ResponseEntity<Candidato> editarCandidato (@Valid @RequestBody Candidato candidato) {
        return ResponseEntity.status(200).body(candidatoService.editaCandidato(candidato));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirCandidato (@PathVariable Integer id) {
        candidatoService.excluirCandidato(id);
        return ResponseEntity.status(204).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String nomeCampo = ((FieldError) error).getField();
            String mensagemErro = error.getDefaultMessage();
            erros.put(nomeCampo, mensagemErro);
            
        });

        return erros;
    }
}