package com.campanula.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * package com.campanula.library.utils
 *
 * @author 000286 create 2018-11-07 desc
 **/
public final class JacksonUtils {

    private JacksonUtils() {
    }

    private static final ObjectMapper mObjectMapper;


    static {
        mObjectMapper = new ObjectMapper();

        // 忽略null转换
        mObjectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        /*
         * DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY 保证数组，list等中只含有一个值
         * JsonParser.Feature.ALLOW_SINGLE_QUOTES 设置使用单引号
         * JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES 设置字段可以不使用单引号
         * JsonParser.Feature.ALLOW_COMMENTS 设置允许解析Java/C++
         * DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES 设置实体无属性和json属性对应时不会出错
         */
        mObjectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                .enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES)
                .enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)
                .enable(JsonParser.Feature.ALLOW_COMMENTS)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        // 设置时间格式
        mObjectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()));

    }

    /**
     * 任意类型转换json字符串
     *
     * @param object 数据
     * @return 字符串
     * @throws JsonProcessingException 转换json异常
     */
    public static String objectConvertJson(Object object) throws JsonProcessingException {
        return mObjectMapper.writeValueAsString(object);
    }

    /**
     * 任意字符串转换称List
     *
     * @param json  字符串
     * @param clazz 转换的实体类
     * @return  转换的实体
     * @throws IOException  转换异常
     */
    public static List<?> jsonConvertList(String json, Class<?> clazz) throws IOException {
        JavaType mJavaType = mObjectMapper.getTypeFactory().constructParametricType(List.class, clazz);
        return mObjectMapper.readValue(json, mJavaType);
    }

    public static void main(String[] args) {


    }

}
