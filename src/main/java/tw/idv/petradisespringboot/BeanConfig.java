package tw.idv.petradisespringboot;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // indicates this is a configuration class.
public class BeanConfig {

    @Bean
    public ModelMapper metodoQueCriaUmModelMapper() {
        return new ModelMapper();
    }
}