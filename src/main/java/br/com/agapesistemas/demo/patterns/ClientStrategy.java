package br.com.agapesistemas.demo.patterns;

import br.com.agapesistemas.demo.database.entities.ClientEntity;
import br.com.agapesistemas.demo.gateways.ClientGateway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientStrategy {

    private final ClientGateway clientGateway;

    public ClientStrategy(ClientGateway clientGateway) {
        this.clientGateway = clientGateway;
    }

    public Page<ClientEntity> sort(String sort, String type, Pageable pageable) {

        if(sort.equals("id") && type.equals("desc"))
            return clientGateway.readIdDesc(pageable);

        if(sort.equals("CNPJ") && type.equals("desc"))
            return clientGateway.readCnpjDesc(pageable);

        if(sort.equals("name") && type.equals("desc"))
            return clientGateway.readNameDesc(pageable);

        if(sort.equals("CNPJ") && type.equals("asc"))
            return clientGateway.readCnpjAsc(pageable);

        if(sort.equals("name") && type.equals("asc"))
            return clientGateway.readNameAsc(pageable);

        return clientGateway.readIdAsc(pageable);
    }
}
