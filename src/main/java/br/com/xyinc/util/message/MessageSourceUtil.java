package br.com.xyinc.util.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;

import java.util.*;

@Component
public class MessageSourceUtil {

    @Autowired
    private MessageSource messageSource;

    public String getMessageByKey(final String key) {
        return messageSource.getMessage(key, null, null);
    }

    public String getMessageByKey(final String key, final Object... params) {
        return messageSource.getMessage(key, params, null);
    }

    public Map<String, String> getMessageCamposObrigarios(final Collection<FieldError> fieldErrors) {
        Map<String, String> camposErros = new HashMap<>();
        fieldErrors.forEach(fieldError ->
                camposErros.put(StringUtils.capitalize(fieldError.getField()), getMessageByKey(fieldError.getDefaultMessage(), StringUtils.capitalize(fieldError.getField())))
        );
        return camposErros;
    }




}
