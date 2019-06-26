package com.tk.crmapp.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Converter;


public class CRMoDataV7ResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Type type;

    public CRMoDataV7ResponseBodyConverter(Type type) {
        this.type = type;
    }

    /*
     * 转换方法
     */
    @Override
    public T convert(ResponseBody value) throws IOException {

        BufferedSource bufferedSource = Okio.buffer(value.source());
        String tempStr = bufferedSource.readUtf8();
        bufferedSource.close();
        T t;

        JSONObject jsonObject = (JSONObject) JSONObject.parseObject(tempStr)
                .getJSONObject("d");

        String tempStr1 = jsonObject.getString("results");

        if(tempStr1!=null)
            t = JSON.parseObject(tempStr1, type);
        else
        {
            t = JSON.parseObject(jsonObject.toJSONString(),type);
        }

        return t;


    }
}