package com.daynet.crudclientes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.daynet.crudclientes.model.Cliente;
import com.daynet.crudclientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Configurar los origenes permitidos
@CrossOrigin(origins = "http://localhost:4200")
/*
Esta anotación se usa para definir un controller y para establecer que el valor de retorno de los metodos
se ligara al cuerpo de la respuesta
*/
@RestController
//Declara que todas las apis del controlles comenzaran con "/api"
@RequestMapping("/api")
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;


    @GetMapping("/clientes")
    public ResponseEntity listarClientes() {
        try {

            List<Cliente> clientes = new ArrayList<>(clienteRepository.findAll());

            if(clientes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(clientes, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity getClientebyId(@PathVariable("id") long id) {
        Optional<Cliente> clienteData = clienteRepository.findById(id);

        if (clienteData.isPresent()) {
            return new ResponseEntity<>(clienteData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        }
    }

    @PostMapping("/clientes")
    public ResponseEntity crearCliente(@RequestBody Cliente cliente) {
        try{
            Cliente clienteSaved = clienteRepository.save(new Cliente(cliente.getNombre(), cliente.getApellido(), cliente.getCuenta()));
            return new ResponseEntity<>(clienteSaved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity actualizarCliente(@PathVariable("id") long id, @RequestBody Cliente cliente) {
        Optional<Cliente> clienteData = clienteRepository.findById(id);

        if(clienteData.isPresent()) {
            Cliente clienteUpdated = clienteData.get();
            clienteUpdated.setNombre(cliente.getNombre());
            clienteUpdated.setApellido(cliente.getApellido());
            clienteUpdated.setCuenta(cliente.getCuenta());
            clienteRepository.save(clienteUpdated);
            return new ResponseEntity<>(clienteUpdated, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity borrarCliente(@PathVariable("id") long id) {
        try {
            clienteRepository.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
            // return ResponseEntity.status(HttpStatus.OK).body("Cliente eliminado");
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
            // return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("No se pudo eliminar. El Cliente con id " + id + " no existe");
        }
    }

    @DeleteMapping("/clientes")
    public ResponseEntity borrarTodos() {
        try {
            clienteRepository.deleteAll();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
        }

    }

}
