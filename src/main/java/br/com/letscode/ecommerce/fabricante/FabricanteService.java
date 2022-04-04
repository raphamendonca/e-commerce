package br.com.letscode.ecommerce.fabricante;

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
