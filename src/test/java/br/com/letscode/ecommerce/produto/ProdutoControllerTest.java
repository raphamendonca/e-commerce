package br.com.letscode.ecommerce.produto;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
