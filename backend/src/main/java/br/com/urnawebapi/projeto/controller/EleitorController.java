package br.com.urnawebapi.projeto.controller;

import java.util.HashMap;
import java.util.List;
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

import br.com.urnawebapi.projeto.security.Token;
import br.com.urnawebapi.projeto.dto.EleitorDto;
import br.com.urnawebapi.projeto.model.Eleitor;
import br.com.urnawebapi.projeto.service.EleitorService;
import br.com.urnawebapi.projeto.service.SmsService;

@RestController
@CrossOrigin("*")
@RequestMapping("/eleitores")
public class EleitorController  {
    
    @Autowired
    private EleitorService eleitorService;
    @Autowired
    private SmsService smsService;

    public EleitorController(EleitorService eleitorService) {  
        this.eleitorService = eleitorService;
    }

    @GetMapping
    public Page<Eleitor> listaEleitores (
        @RequestParam(value="minDate", defaultValue = "")String minDate, 
        @RequestParam(value="maxDate", defaultValue = "")String maxDate, 
        Pageable pageable) {
        return eleitorService.listarEleitor(minDate, maxDate, pageable);
    }

    @GetMapping("/{id}/notificar")
    public void notificar(@PathVariable Integer id){
        smsService.sendSms(id);
    }

    @PostMapping
    public ResponseEntity<Eleitor> criarEleitor (@Valid @RequestBody Eleitor eleitor) {
        return ResponseEntity.status(201).body(eleitorService.criarEleitor(eleitor));
    }

    @PutMapping
    public ResponseEntity<Eleitor> editarEleitor (@Valid @RequestBody Eleitor eleitor) {
        return ResponseEntity.status(200).body(eleitorService.editaEleitor(eleitor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirEleitor (@PathVariable Integer id) {
        eleitorService.excluirEleitor(id);
        return ResponseEntity.status(204).build();
    }

     @PostMapping("/login")
    public ResponseEntity<Token> logar(@Valid @RequestBody EleitorDto eleitor) {
        //Boolean valido = eleitorService.validarSenha(eleitor);
        Token token = eleitorService.gerarToken(eleitor);
        if(token != null) {
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(403).build();
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
