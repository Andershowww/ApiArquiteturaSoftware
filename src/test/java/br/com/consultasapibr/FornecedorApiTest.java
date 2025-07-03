package br.com.consultasapibr;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = ApiArquiteturaSoftwareApplication.class)
@AutoConfigureMockMvc
class FornecedorApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveBuscarTodosFornecedores() throws Exception {
        mockMvc.perform(get("/fornecedores/lista-fornecedores"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void deveCadastrarFornecedorComSucesso() throws Exception {
        String novoFornecedor = """
                                {
                  "cnpj": "19131243000197",
                  "razaoSocial": "OPEN KNOWLEDGE BRASIL",
                  "nomeFantasia": "REDE PELO CONHECIMENTO LIVRE",
                  "cnae":"",
                  "endereco": {
                    "logradouro": "PAULISTA 37",
                    "numero": "100",
                    "bairro": "Bela Vista",
                    "municipio": "SAO PAULO",
                    "complemento": "Sala 3",
                    "uf": "SP"
                  }
                }
                                """;

        mockMvc.perform(post("/fornecedores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(novoFornecedor))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.cnpj").value("19131243000197"));
    }
}
