package br.com.xyinc.exception.exception_custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.*;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidacaoException extends RuntimeException {

    private List<Map<String, Object[]>> messages = new ArrayList<>();

    public ValidacaoException() {
    }

    public void addMessage(String message,Object... params) {
        Map<String, Object[]> messageMap = new HashMap<>();
        messageMap.put(message, params);
        this.messages.add(messageMap);
    }

    public List<Map<String, Object[]>> getMessages() {
        return messages;
    }

    public Boolean existemErros() {
        return (!this.messages.isEmpty());
    }



}
