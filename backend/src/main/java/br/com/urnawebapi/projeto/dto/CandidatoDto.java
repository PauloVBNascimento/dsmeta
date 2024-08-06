package br.com.urnawebapi.projeto.dto;


public class CandidatoDto {
    private String nome;
    private String descricao;
    private String telefone;
    private String numero;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public CandidatoDto (){
        
    }

    public CandidatoDto(String nome, String descricao, String telefone, String numero) {
        this.nome = nome;
        this.descricao = descricao;
        this.telefone = telefone;
        this.numero = numero;
    }




    public String getNome() {
        return nome;
    }



    public void setNome(String nome) {
        this.nome = nome;
    }

}
