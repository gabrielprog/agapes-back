package br.com.agapesistemas.demo.gateways;

import br.com.agapesistemas.demo.database.entities.ClientEntity;
import br.com.agapesistemas.demo.database.repositories.ClientRepo;
import br.com.agapesistemas.demo.utils.CopyPropsUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientGateway {
    private final ClientRepo clientRepo;

    public ClientGateway(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    public ClientEntity create(ClientEntity client) {
        return this.clientRepo.save(client);
    }

    public boolean update(ClientEntity client) {

        Optional<ClientEntity> optionalClientEntity = clientRepo.findById(client.getId());

        if (optionalClientEntity.isPresent()) {
            ClientEntity entity = optionalClientEntity.get();

            CopyPropsUtil.copyNonNullProperties(client, entity);

            this.clientRepo.save(entity);

            return true;
        }

        return false;
    }
    public void delete(ClientEntity clientEntity) {
        this.clientRepo.delete(clientEntity);
    }
    public Optional<ClientEntity> readById(Long id) {
        return this.clientRepo.findById(id);
    }

    public List<ClientEntity> findAll() {
        return this.clientRepo.findAll();
    }

    public Page<ClientEntity> readByName(String name, Pageable pageable) {
        return this.clientRepo.findByNameContainingIgnoreCase(name, pageable);
    }

    public Page<ClientEntity> readByCode(Long code, Pageable pageable) {
        return this.clientRepo.findById(code, pageable);
    }

    public Page<ClientEntity> readByCnpj(String cnpj, Pageable pageable) {
        return this.clientRepo.findByCNPJContainingIgnoreCase(cnpj, pageable);
    }

    public Page<ClientEntity> readIdAsc(Pageable pageable) {
        return this.clientRepo.findAllByOrderByIdAsc(pageable);
    }

    public Page<ClientEntity> readIdDesc(Pageable pageable) {
        return this.clientRepo.findAllByOrderByIdDesc(pageable);
    }

    public Page<ClientEntity> readNameAsc(Pageable pageable) {
        return this.clientRepo.findAllByOrderByNameAsc(pageable);
    }

    public Page<ClientEntity> readNameDesc(Pageable pageable) {
        return this.clientRepo.findAllByOrderByNameDesc(pageable);
    }

    public Page<ClientEntity> readCnpjAsc(Pageable pageable) {
        return this.clientRepo.findAllByOrderByCNPJAsc(pageable);
    }

    public Page<ClientEntity> readCnpjDesc(Pageable pageable) {
        return this.clientRepo.findAllByOrderByCNPJDesc(pageable);
    }

}
