package br.com.tech4me.avaliacaobkend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import br.com.tech4me.avaliacaobkend.model.Musica;

public interface MusicaRepository extends MongoRepository<Musica, String> {
    
}
