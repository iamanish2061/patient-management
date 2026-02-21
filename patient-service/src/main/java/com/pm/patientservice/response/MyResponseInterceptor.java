package com.pm.patientservice.response;

import com.pm.patientservice.config.CustomMessageSource;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;

@RestControllerAdvice
@RequiredArgsConstructor
public class MyResponseInterceptor implements ResponseBodyAdvice<Object> {

    private final CustomMessageSource customMessageSource;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        Method method = returnType.getMethod();
        return method.isAnnotationPresent(MessageParameter.class);
    }

    @Nullable
    @Override
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Method method = (Method) returnType.getMember();
        if(method.isAnnotationPresent(MessageParameter.class)){
            MessageParameter annotation = method.getAnnotation(MessageParameter.class);
            String message = annotation.message();
            String[] sources = annotation.source();

            return ApiResponse.builder()
                    .data(body)
                    .message(buildMessage(message, sources))
                    .build();
        }
        return null;
    }

    private String buildMessage(String message, String... sources){
        return switch (sources.length){
            case 2 -> customMessageSource.get(message,
                    customMessageSource.get(sources[0]),
                    customMessageSource.get(sources[1]));
            case 3 -> customMessageSource.get(message,
                    customMessageSource.get(sources[0]),
                    customMessageSource.get(sources[1]),
                    customMessageSource.get(sources[2]));
            default -> customMessageSource.get(message,
                    customMessageSource.get(sources[0]));
        };
    }
}
