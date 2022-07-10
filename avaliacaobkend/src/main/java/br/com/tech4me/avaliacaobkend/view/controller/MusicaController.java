package br.com.tech4me.avaliacaobkend.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4me.avaliacaobkend.shared.MusicaDto;
import br.com.tech4me.avaliacaobkend.view.model.MusicaRequest;
import br.com.tech4me.avaliacaobkend.view.model.MusicaResponse;
import br.com.tech4me.avaliacaobkend.model.Musica;
import br.com.tech4me.avaliacaobkend.service.MusicaService;

@RestController
@RequestMapping("/api/musicas")
public class MusicaController {
    
    @Autowired
    private MusicaService servico;

    ModelMapper mapper = new ModelMapper();
    
    @GetMapping(value ="/status")
    public String statusServico(@Value("${local.server.port}") String porta) {
        return String.format("Servico funcionando na porta %s", porta);
    }
    
    @GetMapping
    public ResponseEntity<List<MusicaResponse>> obterTodos() {
        List<MusicaDto> musicaDto = servico.obterTodos();
        List<MusicaResponse> response = 
        musicaDto.
        stream().
        map(p -> mapper.map(p, MusicaResponse.class)).
        collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MusicaResponse> criarMusica(@RequestBody @Valid MusicaRequest request) {
        MusicaDto musicaDto = mapper.map(request, MusicaDto.class);
        musicaDto = servico.criarMusica(musicaDto);
        MusicaResponse response = mapper.map(musicaDto, MusicaResponse.class);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping(value="/{id}")
    public ResponseEntity<MusicaResponse> obterPorId(@PathVariable String id) {
        Optional<MusicaDto> musicaDto = servico.obterPorId(id);

        if(musicaDto.isPresent()) {
            MusicaResponse response = mapper.map(musicaDto.get(), MusicaResponse.class);
            return new ResponseEntity<>(response, HttpStatus.FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    
    @PutMapping(value="/{id}")
    public ResponseEntity<MusicaResponse> atualizarMusica(@PathVariable String id, @RequestBody MusicaRequest request) {
        MusicaDto musicaDto = mapper.map(request, MusicaDto.class);
        musicaDto = servico.atualizarMusica(id, musicaDto);
        MusicaResponse response = mapper.map(musicaDto, MusicaResponse.class);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }   
    
    
    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> removerMusica(@PathVariable String id) {
        servico.removerMusica(id);
        return new ResponseEntity<String>("Removido com sucesso!", HttpStatus.OK);
    } 

    //-------------------

    @GetMapping(value="/optional/{id}")
    public ResponseEntity<Musica> obterPorIdComOptional(@PathVariable String id) {
        Optional<Musica> musica = servico.obterPorIdComOptional(id);

        if(musica.isPresent()) {
            return new ResponseEntity<>(musica.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);        
    }

}