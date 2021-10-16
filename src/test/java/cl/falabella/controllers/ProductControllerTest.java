package cl.falabella.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cl.falabella.entities.request.ProductRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import cl.falabella.configurators.ProductsConfigurationTest;

@ContextConfiguration(classes = {ProductsConfigurationTest.class})
@WebMvcTest(controllers = ProductsController.class)
public class ProductControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void insertProduct() throws Exception {
        this.mvc.perform(post("/insert")
                .content(objectMapper.writeValueAsString(createProductRequest()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'state': 'OK'}"));
    }

    @Test
    public void updateProduct() throws Exception {
        this.mvc.perform(put("/update")
                .content(objectMapper.writeValueAsString(createProductRequest()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'state': 'OK'}"));
    }

    @Test
    public void getProduct() throws Exception {
        this.mvc.perform(get("/get?sku=FAL-1000000")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    'sku': '',\n" +
                        "    'name': '',\n" +
                        "    'brand': '',\n" +
                        "    'size': '',\n" +
                        "    'price': 0,\n" +
                        "    'image': ''\n" +
                        "}"));
    }

    @Test
    public void getAllProduct() throws Exception {
        this.mvc.perform(get("/getall")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    'product': [\n" +
                        "        {\n" +
                        "            'sku': '',\n" +
                        "            'name': '',\n" +
                        "            'brand': '',\n" +
                        "            'size': '',\n" +
                        "            'price': 0,\n" +
                        "            'image': ''\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}"));
    }

    @Test
    public void deleteProduct() throws Exception {
        this.mvc.perform(delete("/delete?sku=FAL-1000000")
                .content(objectMapper.writeValueAsString(createProductRequest()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'state': 'OK'}"));
    }

    private ProductRequest createProductRequest() {
        return ProductRequest.builder().sku("").brand("").name("").price(0D).size("").image("").build();
    }

}
