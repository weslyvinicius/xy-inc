package br.com.xyinc.exception.handeler;

import br.com.xyinc.exception.exception_custom.ResultNotFoundException;
import br.com.xyinc.util.message.MessageSourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

import static br.com.xyinc.util.message.IMessageDefaultKey.DEFAULT_INTERNAL_ERROR_KEY;
import static br.com.xyinc.util.message.IMessageDefaultKey.DEFAULT_RESULT_NOT_FOUND_KEY;

@ControllerAdvice
public class RestExceptionHandler {

    private final MessageSourceUtil messageSourceUtil;

    @Autowired
    public RestExceptionHandler(MessageSourceUtil messageSourceUtil) {
        this.messageSourceUtil = messageSourceUtil;
    }

    @ExceptionHandler(ResultNotFoundException.class)
    public ResponseEntity<?> handelerNotFoundException(ResultNotFoundException resultNotFoundException) {
        ResponseHandlerDetail response =
                ResponseHandlerDetail.builder()
                        .datetime(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .classeDeError(resultNotFoundException.getClass().getName())
                        .build()
                        .addMenssagemErro(messageSourceUtil.getMessageByKey(DEFAULT_RESULT_NOT_FOUND_KEY));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        ResponseHandlerDetail response =
                ResponseHandlerDetail.builder()
                        .datetime(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .classeDeError(methodArgumentNotValidException.getClass().getName())
                        .build()
                        .addCamposObrigarios(messageSourceUtil.getMessageCamposObrigarios(methodArgumentNotValidException.getBindingResult().getFieldErrors()));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handelerNotFoundException(Exception exception) {
        ResponseHandlerDetail response =
                ResponseHandlerDetail.builder()
                        .datetime(LocalDateTime.now())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .classeDeError(exception.getClass().getName())
                        .build()
                        .addMenssagemErro(messageSourceUtil.getMessageByKey(DEFAULT_INTERNAL_ERROR_KEY));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
