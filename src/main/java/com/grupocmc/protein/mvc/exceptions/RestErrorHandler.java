package com.grupocmc.protein.mvc.exceptions;


import com.grupocmc.protein.config.Constantes;
import com.grupocmc.protein.pojos.webservices.response.ResponseBaseWebService;
import com.grupocmc.protein.utils.i18n.ResourceTranslator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Locale;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class RestErrorHandler {


    private static final Logger LOGGER = LoggerFactory.getLogger(RestErrorHandler.class);

    private MessageSource messageSource;
    private ResourceTranslator translator;


    @Autowired
    public RestErrorHandler(MessageSource messageSource, ResourceTranslator translator) {
        this.messageSource = messageSource;
        this.translator = translator;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseBaseWebService processGeneralExceptions(Exception ex) {

        ResponseBaseWebService dto = new ResponseBaseWebService();
        dto.setCodigoRespuesta(Constantes.RETORNO_ERRROR_PROCESAMIENTO_GENERICO_INTERNO);
        dto.setError(true);
        dto.setMensajeRespuesta(ex.getMessage());
        LOGGER.error("error", ex);
        return dto;
    }

    @ExceptionHandler(InternalError.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseBaseWebService processInternalError(InternalError ex) {

        ResponseBaseWebService dto = new ResponseBaseWebService();
        dto.setCodigoRespuesta(ex.getMessage());
        dto.setError(true);
        dto.setMensajeRespuesta(ex.getMessage());
        LOGGER.error("error", ex);
        return dto;
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ResponseBaseWebService processValidationError(MethodArgumentNotValidException ex) {

        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        LOGGER.error("Peticion incorrecta", ex);
        return processFieldErrors(fieldErrors);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ResponseBaseWebService proccessCustomValidationErrors(ValidationException ex) {

        ResponseBaseWebService respuesta = new ResponseBaseWebService();
        respuesta.setCodigoRespuesta(Constantes.RETORNO_KO_400);
        respuesta.setError(true);
        respuesta.setMensajeRespuesta(translator.resolve(ex.getMessage(), ex.getReplacers()));
        LOGGER.error("Peticion incorrecta", respuesta.getMensajeRespuesta());
        return respuesta;

    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    public ResponseBaseWebService proccessNotFound(NotFoundException ex) {

        ResponseBaseWebService respuesta = new ResponseBaseWebService();
        respuesta.setCodigoRespuesta(String.valueOf(NOT_FOUND.value()));
        respuesta.setError(true);
        respuesta.setMensajeRespuesta(translator.resolve(ex.getMessage()));
        LOGGER.error("Datos no encontrados", respuesta.getMensajeRespuesta());
        return respuesta;

    }

    private ResponseBaseWebService processFieldErrors(List<FieldError> fieldErrors) {

        ResponseBaseWebService dto = new ResponseBaseWebService();
        dto.setCodigoRespuesta(Constantes.RETORNO_KO_400);
        dto.setError(true);

        int aux = 0;
        for (FieldError fieldError : fieldErrors) {
            String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
            // first error message
            if (aux == 0) {
                dto.setMensajeRespuesta(localizedErrorMessage);
            }
            aux++;
        }

        return dto;
    }

    private String resolveLocalizedErrorMessage(FieldError fieldError) {

        String localizedErrorMessage = "";

        try {

            Locale currentLocale = LocaleContextHolder.getLocale();
            localizedErrorMessage = messageSource.getMessage(
                    fieldError.getDefaultMessage(),
                    new Object[]{fieldError.getField()},
                    currentLocale);

        } catch (NoSuchMessageException e) {
            localizedErrorMessage = "VALIDATION ERROR ON: " + fieldError.getField();
        }


        return localizedErrorMessage;
    }


}
