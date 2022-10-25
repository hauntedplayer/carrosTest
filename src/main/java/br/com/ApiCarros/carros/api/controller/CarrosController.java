package br.com.ApiCarros.carros.api.controller;

import br.com.ApiCarros.carros.domain.entity.Carro;
import br.com.ApiCarros.carros.domain.service.CarroService;
import br.com.ApiCarros.carros.domain.DTO.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
    @Autowired
    private CarroService service;

    @GetMapping()
    public ResponseEntity get() {
        return ResponseEntity.ok(service.getCarros());
        //return new ResponseEntity<>(service.getCarros(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getCarroById(id));


        /*Optional<CarroDTO> carro = service.getCarroById(id);
        //Metodo Lambda
        return carro.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());*/

        /* return carro.isPresent() ?
                new ResponseEntity.ok(carro.get()):
                new ResponseEntity.notFound().build;

        if(carro.isPresent()){
            return ResponseEntity.ok(carro.get());
        } else {
            return ResponseEntity.notFound().build();
        }*/

    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity getCarrosByTipo(@PathVariable("tipo") String tipo) {
        List<CarroDTO> carro = service.getCarroByTipo(tipo);
        return carro.isEmpty() ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(carro);

    }

    @PostMapping()
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity post(@RequestBody Carro carro) {
        CarroDTO c = service.insert(carro);

        URI location = getUri(c.getId());
        return ResponseEntity.created(location).build();

    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Carro carro) {
        carro.setId(id);
        CarroDTO c = service.update(carro, id);
        return c != null ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

}
