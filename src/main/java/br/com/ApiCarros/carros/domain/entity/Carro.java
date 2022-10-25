package br.com.ApiCarros.carros.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "url_foto")
    private String urlFoto;
    @Column(name = "url_video")
    private String urlVideo;
    @Column(name = "latitude")
    private String latitude;
    @Column(name = "longitude")
    private String longitude;




   /* public Carro(){
    }
    public Carro(Long id, String nome, String tipo) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }*/
}
