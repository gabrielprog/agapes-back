package br.com.agapesistemas.demo.controllers;

import br.com.agapesistemas.demo.database.entities.ClientEntity;
import br.com.agapesistemas.demo.gateways.ClientGateway;
import br.com.agapesistemas.demo.patterns.ClientStrategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientGateway clientGateway;
    private final ClientStrategy clientStrategy;

    public ClientController(ClientGateway clientGateway, ClientStrategy clientStrategy) {
        this.clientGateway = clientGateway;
        this.clientStrategy = clientStrategy;
    }

    @PostMapping
    public ResponseEntity<ClientEntity> create(@RequestBody ClientEntity client) {
        ClientEntity clientEntity = this.clientGateway.create(client);

        return ResponseEntity.ok(clientEntity);
    }

    @PutMapping
    public ResponseEntity<ClientEntity> update(@RequestBody ClientEntity client) {
        boolean updateClient = this.clientGateway.update(client);

        if(updateClient)
            return ResponseEntity.ok(client);

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ClientEntity read(@PathVariable Long id) {
        return clientGateway.readById(id).get();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientEntity> delete(@PathVariable Long id) {
        Optional<ClientEntity> clientEntity = clientGateway.readById(id);

        if(clientEntity.isPresent()) {
            this.clientGateway.delete(clientEntity.get());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public Page<ClientEntity> getAllClients(
            Pageable pageable,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String qcode,
            @RequestParam(required = false) String qname,
            @RequestParam(required = false) String qcnpj
    ) {
        if (sort != null && !sort.isEmpty() && !sort.equals("")
                && type != null && !type.isEmpty() && !type.equals(""))
            return clientStrategy.sort(sort, type, pageable);

        if (qname != null && !qname.isEmpty() && !qname.equals(""))
            return clientGateway.readByName(qname, pageable);

        if (qcode != null && !qcode.isEmpty() && !qcode.equals(""))
            return clientGateway.readByCode(Long.valueOf(qcode), pageable);

        return clientGateway.readByCnpj(qcnpj, pageable);
    }

    @GetMapping("/pdf")
    public List<ClientEntity> getListClients() {
        return this.clientGateway.findAll();
    }

}
