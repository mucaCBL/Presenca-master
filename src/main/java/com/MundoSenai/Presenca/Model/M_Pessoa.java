package com.MundoSenai.Presenca.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="pessoa")
public class M_Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private Long cpf;
    private Long telefone;
    private LocalDate data_nasc;
    private String senha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    public LocalDate getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(LocalDate data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
