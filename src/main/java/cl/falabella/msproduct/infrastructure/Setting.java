package cl.falabella.msproduct.infrastructure;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class Setting {

    @Value("${spring.app.version}")
    private String version;

    @Value("${spring.project.name}")
    private String projectName;
}