package br.com.ApiCarros.carros.domain.repository;

import br.com.ApiCarros.carros.domain.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    List<Carro> findByTipo(String tipo);


}
