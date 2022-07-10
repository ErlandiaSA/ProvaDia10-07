package br.com.tech4me.avaliacaobkend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.tech4me.avaliacaobkend.shared.MusicaDto;
import br.com.tech4me.avaliacaobkend.model.Musica;
import br.com.tech4me.avaliacaobkend.repository.MusicaRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MusicaServiceImpl implements MusicaService {

    @Autowired
    private MusicaRepository repositorio;

    ModelMapper mapper = new ModelMapper();

    @Override
    public MusicaDto criarMusica(MusicaDto musicaDto) {
        Musica musica = mapper.map(musicaDto, Musica.class);
        musica = repositorio.save(musica);
        MusicaDto dto = mapper.map(musica, MusicaDto.class);

        return dto;
    }

    @Override
    public List<MusicaDto> obterTodos() {
        List<Musica> musicas = repositorio.findAll();
        List<MusicaDto> musicaDto = 
        musicas.
        stream().
        map(p -> mapper.map(p, MusicaDto.class)).
        collect(Collectors.toList());

        return musicaDto;
    }

    @Override
    public Optional<MusicaDto> obterPorId(String id) {
        Optional<Musica> musica = repositorio.findById(id);

        if(musica.isPresent()) {
            MusicaDto musicaDto = mapper.map(musica.get(), MusicaDto.class);
            return Optional.of(musicaDto);
        }

        return Optional.empty();
    }

    @Override
    public void removerMusica(String id) {
        repositorio.deleteById(id);
    }

    @Override
    public MusicaDto atualizarMusica(String id, MusicaDto musicaDto) {
        Musica musica = mapper.map(musicaDto, Musica.class);
        
        musica.setId(id);
        musica = repositorio.save(musica);

        MusicaDto dto = mapper.map(musica, MusicaDto.class);

        return dto;
    }

    @Override
    public Optional<Musica> obterPorIdComOptional(String id) {
        return repositorio.findById(id);
    }
}
