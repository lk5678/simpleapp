package com.tk.crmapp.tools;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by 耿 on 2016/9/6.
 */
public class StringConverterFactory extends Converter.Factory
{
    public static StringConverterFactory create()
    {
        return new StringConverterFactory();
    }
    public  StringConverterFactory() { }
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit)
    {
        return new StringResponseBodyConverter();
    }
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit)
    {
        return new StringRequestBodyConverter();
    }
}
