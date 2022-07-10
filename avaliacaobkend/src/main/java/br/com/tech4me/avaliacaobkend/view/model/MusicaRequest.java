package br.com.tech4me.avaliacaobkend.view.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class MusicaRequest {
    
    @NotBlank(message = "O titulo nao pode ser em branco")
    @NotEmpty(message = "O titulo tem que ser preenchido")
    @Size(min = 1, message = "O titulo tem que ter no minimo 1 caracteres")
    private String titulo;

    @NotBlank(message = "O artista nao pode ser em branco")
    @NotEmpty(message = "O artista tem que ser preenchido")
    private String artista;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

}
