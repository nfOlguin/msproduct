package cl.falabella.msproduct.infrastructure.controller;

import cl.falabella.msproduct.application.port.input.IProductController;
import cl.falabella.msproduct.application.port.interactor.IProductService;
import cl.falabella.msproduct.infrastructure.Setting;
import cl.falabella.msproduct.infrastructure.controller.ProductController;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(classes = ProductController.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductControllerTest {

}
