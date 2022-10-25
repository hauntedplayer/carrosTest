package br.com.ApiCarros.carros.domain.service;

import br.com.ApiCarros.carros.api.exception.ObjectNotFoundException;
import br.com.ApiCarros.carros.domain.DTO.CarroDTO;
import br.com.ApiCarros.carros.domain.entity.Carro;
import br.com.ApiCarros.carros.domain.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepository repository;

    public List<CarroDTO> getCarros() {
        //Metodo lambda simplificado
        return repository.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());

       /*
       **   Metodo lambda
       List<Carro> carros = repository.findAll();
        carros.stream().map(c -> new CarroDTO(c)).collect(Collectors.toList());

        **  Metodo manual
        List<Carro> carros = repository.findAll();
        List<CarroDTO> list = new ArrayList<>();
        for(Carro c: carros){
            list.add(new CarroDTO(c));
        }
        return list;*/
    }

    public CarroDTO getCarroById(Long id) {
        // O .map serve pra converter o carro para carroDTO
        Optional<Carro> carro = repository.findById(id);
        return carro.map(CarroDTO::create).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado!"));

        /*Optional<Carro> carro = repository.findById(id);
        return carro.isPresent() ? Optional.of(new CarroDTO(carro.get())) : null;

        Optional<Carro> carro = repository.findById(id);
        if(carro.isPresent()){
            return Optional.of(new CarroDTO(carro.get()));
        } else {
            return null;
        }*/

    }

    public List<CarroDTO> getCarroByTipo(String tipo) {
        // Retornando Carro DTO com metodo simplificado do lambda
        return repository.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public CarroDTO insert(Carro carro) {
        Assert.isNull(carro.getId(), "Não foi possivel inserir o registro");
        return CarroDTO.create(repository.save(carro));
    }

    public CarroDTO update(Carro carro, Long id) {
        Assert.notNull(id, "Não foi possivel atualizar o registro");
        //Busacar o carro no banco de dados atraves do ID

        Optional<Carro> optional = repository.findById(id);
        if (optional.isPresent()) {

            //Caso o id retorne um carro, a variavel bd vai representaar o carro que vem do banco
            Carro bd = optional.get();
            //Copiando as propriedades para o banco
            bd.setNome(carro.getNome());
            bd.setTipo(carro.getTipo());
            System.out.println("Carro id" + bd.getId());

            //Atualizar carro
            repository.save(bd);

            return CarroDTO.create(bd);
        } else {
            return null;
            //throw new RuntimeException("Não foi possivel atualizar o registro");
        }
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
