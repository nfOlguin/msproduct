package cl.falabella.msproduct.infrastructure.repository.db;

import cl.falabella.msproduct.application.port.output.db.IProductDb;
import cl.falabella.msproduct.domain.entity.Product;
import cl.falabella.msproduct.infrastructure.repository.ProductDb;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = ProductDb.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductDbTest {

    @MockBean
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private IProductDb clienteDb;

    private final Product product = new Product();
    private final List<Product> listaProducts = new ArrayList<>();

    private static final String RUT = "156977411";

    @Before
    public void setupObject() {
        product.setId(1);
        product.setRut(RUT);
        listaProducts.add(product);
    }


    @Test
    public void deberiaObtenerClientePorId() {
        when(jdbcTemplate.query(Mockito.anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Product>>any(),
                Mockito.anyLong())).thenReturn(listaProducts);

        Assertions.assertNotNull(clienteDb.obtenerPorId(1));
    }

    @Test
    public void deberiaObtenerClientePorRut() {
        when(jdbcTemplate.query(Mockito.anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Product>>any(),
                Mockito.anyString())).thenReturn(listaProducts);

        Assertions.assertNotNull(clienteDb.obtenerPorRut(RUT));
    }

    @Test
    public void deberiaGuardarCliente() {
        when(jdbcTemplate.update(Mockito.any(PreparedStatementCreator.class), Mockito.any(GeneratedKeyHolder.class)))
                .thenAnswer(invocation -> {
                    Object[] args = invocation.getArguments();
                    Map<String, Object> keyMap = new HashMap<>();
                    keyMap.put("id", 1);
                    ((GeneratedKeyHolder)args[1]).getKeyList().add(keyMap);
                    return 1;
                });

        Assertions.assertEquals(1, clienteDb.guardar(product) );
    }
}
