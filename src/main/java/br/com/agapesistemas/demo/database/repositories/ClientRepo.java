package br.com.agapesistemas.demo.database.repositories;

import br.com.agapesistemas.demo.database.entities.ClientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ClientRepo extends JpaRepository<ClientEntity, Long> {
    Optional<ClientEntity> findById(Long id);
    Page<ClientEntity> findById(Long id, Pageable pageable);
    Page<ClientEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<ClientEntity> findByCNPJContainingIgnoreCase(String cnpj, Pageable pageable);
    Page<ClientEntity> findAllByOrderByIdAsc(Pageable pageable);
    Page<ClientEntity> findAllByOrderByIdDesc(Pageable pageable);
    Page<ClientEntity> findAllByOrderByNameAsc(Pageable pageable);

    Page<ClientEntity> findAllByOrderByNameDesc(Pageable pageable);
    Page<ClientEntity> findAllByOrderByCNPJAsc(Pageable pageable);

    Page<ClientEntity> findAllByOrderByCNPJDesc(Pageable pageable);
}
