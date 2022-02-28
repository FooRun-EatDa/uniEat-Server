package com.foorun.unieat.config.converter;

import com.foorun.unieat.domain.common.PostType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class PostTypeConverter implements Converter<String, PostType> {
    @Override
    public PostType convert(String source) {
        return PostType.valueOf(source.toUpperCase(Locale.ROOT));
    }
}
