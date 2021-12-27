package tech.dna.task.common;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Configuration
@AutoConfigureBefore({JacksonAutoConfiguration.class})
public class DateConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperBuilderCustomizer(
            @Value("${spring.jackson.date-format}") String dateFormat
    ) {
        return builder -> builder
                .serializers(
                        new LocalDateSerializer(
                                DateTimeFormatter.ofPattern(dateFormat)))
                .deserializers(
                        new LocalDateDeserializer(
                                DateTimeFormatter.ofPattern(dateFormat)));
    }
}
