package br.com.tech4me.avaliacaobkend.service;
import br.com.tech4me.avaliacaobkend.shared.MusicaDto;
import br.com.tech4me.avaliacaobkend.model.Musica;
import java.util.List;
import java.util.Optional;

public interface MusicaService {
    MusicaDto criarMusica(MusicaDto musicaDto);
    List<MusicaDto> obterTodos();
    Optional<MusicaDto> obterPorId(String id);
    void removerMusica(String id);
    MusicaDto atualizarMusica(String id, MusicaDto musicaDto);

    Optional<Musica> obterPorIdComOptional(String id);
}
