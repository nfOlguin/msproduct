package cl.falabella.msproduct.infrastructure.controller;


import cl.falabella.msproduct.application.ApplicationException;
import cl.falabella.msproduct.infrastructure.Setting;
import cl.falabella.msproduct.infrastructure.controller.ErrorHandlerController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = ErrorHandlerController.class)
@RunWith(SpringJUnit4ClassRunner.class)
class ErrorResponseHandlerControllerTests {
	
	@MockBean
	private BindingResult bindingResult;

	@MockBean
	private HttpServletRequest request;

	@MockBean
	private Setting setting;
	
	@Autowired
	private ErrorHandlerController controller;
	
	@Test
	void shouldValidateMethodArgumentNotValidExceptionHandler() {

		when(setting.getProjectName()).thenReturn("PROJECT_TEST");

		List<FieldError> listaErrores = new ArrayList<>();
		FieldError error = new FieldError("test", "test", "test");
		listaErrores.add(error);

		when(bindingResult.getFieldErrors()).thenReturn(listaErrores);
		MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResult);

		Assertions.assertEquals(400, controller.methodArgumentNotValidException(request, exception).getStatusCodeValue());
	}
	
	@Test
	void shouldValidateMissingParameterExceptionHandler() {
		
		Assertions.assertEquals(400, controller.missingParameterExceptionHandler(request, new MissingServletRequestParameterException("test", "test")).getStatusCodeValue());
	}

	
	@Test
	void shouldValidateExceptionHandler() {
		Assertions.assertEquals("test", controller.exceptionHandler(request, new ApplicationException("test",HttpStatus.INTERNAL_SERVER_ERROR)));
	}

}
