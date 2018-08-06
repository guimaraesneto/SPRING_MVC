package br.com.guimaraes.curso.domain;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.time.LocalDate;

public class Usuario {
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String nome;

    @NotBlank
    @Size(min = 3, max = 50, message = "Campo requerido entre {min} e {max} caracteres.")
    private String sobrenome;

    @NotNull(message = "O campo 'data de nascimento' é requerido.")
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate dtNascimento;

    private TipoSexo sexo;

    public Usuario() {
        super();
    }

    public Usuario(Long id, String nome, String sobrenome) {
        super();
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public Usuario(Long id, String nome, String sobrenome, LocalDate dtNascimento) {
        super();
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dtNascimento = dtNascimento;
    }

    public Usuario(Long id, String nome, String sobrenome, LocalDate dtNascimento, TipoSexo sexo) {
        super();
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dtNascimento = dtNascimento;
        this.sexo = sexo;
    }


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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public TipoSexo getSexo() {
        return sexo;
    }

    public void setSexo(TipoSexo sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome='" + nome + '\'' + ", sobrenome='" + sobrenome + '\'' + '}';
    }
}
