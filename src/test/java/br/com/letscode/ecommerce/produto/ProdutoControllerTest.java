package br.com.letscode.ecommerce.produto;


import br.com.letscode.ecommerce.fabricante.FabricanteEntity;
import br.com.letscode.ecommerce.fabricante.FabricanteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@EnableJpaRepositories("br.com.letscode.ecommerce.fabricante")
//@ExtendWith(SpringExtension.class)
//@AutoConfigureMockMvc
//@WebAppConfiguration
//@AutoConfigureTestDatabase
public class ProdutoControllerTest {

//    @Autowired
//    private FabricanteRepository fabricanteRepository;
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    private MockMvc mockMvc;
//
//    private FabricanteEntity fabricante;
//
//
//    @BeforeEach
//    public void setup() throws Exception {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
//    }
//
//    @Test
////    @WithMockUser
//    public void criarProdutoSucesso() throws Exception {
//        this.fabricante = fabricanteRepository.save( FabricanteEntity.builder().nome("Fabrica de Teste").build());
//
//        String reqBody = "{\n" +
//                "\t\"nome\":\"Bottom\",\n" +
//                "\t\"descricao\":\"Bottom Vermelha\",\n" +
//                "\t\"valor\":4.0,\n" +
//                "\t\"codigo_barra\":\"c0513a5b6dec\",\n" +
//                "\t\"id_fabricante\":"+fabricante.getId()+",\n" +
//                "\t\"peso\":100,\n" +
//                "\t\"peso_unidade_medida\": \"g\"\n" +
//                "}";
//
//        mockMvc.perform( post("/produtos")
//                        .content(reqBody)
//                        .with(httpBasic("user", "password"))
//                )
//                .andExpect(status().isCreated());
//    }


}
