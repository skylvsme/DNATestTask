package tech.dna.task.common.util;

import lombok.val;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class UtilComponent {

    // I use a model mapper in update methods where the field updates (without nulls) are required
    @Bean
    public ModelMapper modelMapper(
            @Value("${spring.jackson.date-format}") String datetimeFormat
    ) {
        val modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Provider<LocalDate> dateProvider = request -> LocalDate.now();

        Converter<String, LocalDate> dateStringConverter = c -> c.getSource() == null ? null
                : LocalDate.parse(c.getSource(), DateTimeFormatter.ofPattern(datetimeFormat));
        modelMapper.createTypeMap(String.class, LocalDate.class);
        modelMapper.addConverter(dateStringConverter);
        modelMapper.getTypeMap(String.class, LocalDate.class).setProvider(dateProvider);
        return modelMapper;
    }

}
