package com.fzq.retrofitmanager.http;

import com.fzq.retrofitmanager.bean.ErrorMsgBean;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 服务器返回数据的解析工具；
 * 支持XML,json对象,json数组
 * </p>
 *
 * @author fzq 2018/09/05 15:22
 * @version V1.2.0
 * @name DataParseUtil
 */


public class DataParseUtil {

    private DataParseUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 解析json对象
     *
     * @param string 要解析的json
     * @param clazz  解析类
     */
    public static <T> T parseObject(String string, Class<T> clazz) {
        try {
            return new Gson().fromJson(string, clazz);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static <T> ErrorMsgBean parseObject(String string) {
        try {
            return new Gson().fromJson(string, ErrorMsgBean.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 解析json数组为ArrayList
     *
     * @param json  要解析的json
     * @param clazz 解析类
     * @return ArrayList
     */
    public static <T> ArrayList<T> parseToArrayList(String json, Class<T> clazz) {
        Type type = new TypeToken<ArrayList<JsonObject>>() {}.getType();
        ArrayList<JsonObject> jsonObjects = new Gson().fromJson(json, type);
        ArrayList<T> arrayList = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects) {
            try {
                arrayList.add(new Gson().fromJson(jsonObject, clazz));
            }catch (JsonSyntaxException e){
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    /**
     * 解析json数组为List
     *
     * @param json  要解析的json
     * @param clazz 解析类
     * @return List
     */
    public static <T> List<T> parseToList(String json, Class<T[]> clazz) {
        Gson gson = new Gson();
        try {
            T[] array = gson.fromJson(json, clazz);
            return Arrays.asList(array);
        }catch (JsonSyntaxException e){
            e.printStackTrace();
            return null;
        }
    }


//    /**
//     * 解析Xml格式数据
//     * @param json  要解析的json
//     * @param clazz 解析类
//     */
//    public static Object parseXml(String json, Class<?> clazz) {
//        try {
//            if (!TextUtils.isEmpty(json) && clazz != null) {
//                Serializer serializer = new Persister();
//                InputStreamReader is = new InputStreamReader(new ByteArrayInputStream(json.getBytes("UTF-8")), "utf-8");
//                return serializer.read(clazz, is);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

}