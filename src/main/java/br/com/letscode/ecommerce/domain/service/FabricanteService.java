package br.com.letscode.ecommerce.domain.service;

import br.com.letscode.ecommerce.domain.repository.FabricanteRepository;
import br.com.letscode.ecommerce.domain.model.exchange.FabricanteRequest;
import br.com.letscode.ecommerce.domain.model.entity.FabricanteEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class FabricanteService {

    private FabricanteRepository fabricanteRepository;

    public FabricanteEntity criar(FabricanteRequest request){
        return fabricanteRepository.save(request.toEntity());
    }
}
