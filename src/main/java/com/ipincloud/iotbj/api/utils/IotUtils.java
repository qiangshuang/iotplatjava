package com.ipincloud.iotbj.api.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class IotUtils {
    private static final Logger log = LoggerFactory.getLogger(IotUtils.class);

    /**
     * 1.获取模型信息（POST请求application/json）
     */
    public static JSONObject getModInfo(String url, String apikey, JSONObject jsonObject) {
        url += "/modelInfo";
        //String result = doPost(url, apikey, jsonObject);
        String result = "{\"code\":0,\"data\":{\"modIndex\":\"cameraIndex\",\"modDesc\":\"\",\"modKey\":\"xjwtCZ7EBEb3\",\"modIndexType\":\"string\",\"modIndexLen\":32,\"modName\":\"摄像头\",\"userName\":\"iotadmin\",\"modId\":\"7de297a36e54435486187233ca08f6a3\"},\"message\":\"success\"}";
        JSONObject jo = JSONObject.parseObject(result);
        if (jo.getInteger("code") == 0) {
            jo = jo.getJSONObject("data");
        }
        return jo;
    }

    /**
     * 2 获取模型密钥（GET请求）
     */
    public static String getModelKey(String url, String apikey, JSONObject jsonObject) {
        url += "/modelKey";
        JSONObject jo = JSONObject.parseObject(doGet(url, apikey, jsonObject));
        String modKey = "";
        if (jo.getInteger("code") == 0) {
            modKey = jo.getJSONObject("data").getString("modKey");
        }
        return modKey;
    }


    /**
     * 3 获取摄像头实体列表（POST请求application/json）
     */
    public static JSONArray getCameraEntityList(String url, String apikey, JSONObject jsonObject) {
        url += "/entityList";
        //String result = doPost(url, apikey, jsonObject);
        String result = "{\"code\":0,\"data\":{\"total\":1,\"pageNo\":1,\"pageSize\":10,\"list\":[{\"regionPath\":\"根节点/廊坊电厂/大门\",\"transType\":\"1\",\"hikCameraIndex\":\"fa23438760f648cb8789ffdfc3ea3609\",\"capabilitySet\":\"event_body,event_audio,io,MixMixedRoot,event_face,event_veh_compare,event_veh,event_veh_recognition,event_ias,face,event_heat,drawFrameInPlayBack,record,vss,ptz,event_io,net,maintenance,event_device,status,PlayConvert\",\"recordLocation\":\"1\",\"cameraName\":\"Camera 01\",\"firmType\":\"海康威视\",\"cameraIndex\":\"bc39d07579d04df7a86214c03b6b2019\",\"status\":\"1\"}]},\"message\":\"success\"}";
        JSONObject jo = JSONObject.parseObject(result);
        if (jo.getInteger("code") == 1001) {
            String modKey = getModelKey(url, apikey, jsonObject);
            jsonObject.put("modKey", modKey);
            return getCameraEntityList(url, apikey, jsonObject);
        }
        JSONArray ja = new JSONArray();
        if (jo.getInteger("code") == 0) {
            ja = jo.getJSONObject("data").getJSONArray("list");
        }
        return ja;
    }

    /*
    public static String getPtzControl(String url, String apikey, String modId, String modKey, String cameraIndex, String command, int action, int speed) {
        url += "/service/invoke/cameras/ptzControl";
        Map<String, Object> params = new HashMap<>();
        params.put("modId", modId);
        params.put("modKey", modKey);
        params.put("cameraIndex", cameraIndex);
        params.put("command", command);
        params.put("action", action);
        if (StringUtils.isNotEmpty(speed + "")) {
            params.put("speed", speed);
        }
        String str = doPost(url, apikey, params);
        return str;
    }

    //5	摄像头获取预览流url（POST请求application/json）
    public static String getPreviewURLs(String url, String apikey, String modId, String modKey, String cameraIndex, int streamType, int transmode, String protocol) {
        url += "/service/invoke/cameras/previewURLs";
        Map<String, Object> params = new HashMap<>();
        params.put("modId", modId);
        params.put("modKey", modKey);
        params.put("cameraIndex", cameraIndex);
        if (StringUtils.isNotEmpty("streamType")) {
            params.put("streamType", streamType);
        }
        if (StringUtils.isNotEmpty("transmode")) {
            params.put("transmode", transmode);
        }
        if (StringUtils.isNotEmpty("protocol")) {
            params.put("protocol", protocol);
        }
        String str = doPost(url, apikey, params);
        return str;
    }

    //6	摄像头获取回放流url（POST请求application/json）
    public static String getPlaybackURLs(String url, String apikey, String modId, String modKey, String cameraIndex, String protocol, String expand,
                                         Date beginTime, Date endTime) {
        url += "/service/invoke/cameras/playbackURLs";
        Map<String, Object> params = new HashMap<>();
        params.put("modId", modId);
        params.put("modKey", modKey);
        params.put("cameraIndex", cameraIndex);
        params.put("protocol", protocol);
        params.put("expand", expand);
        params.put("beginTime", beginTime);
        params.put("endTime", endTime);
        String str = doPost(url, apikey, params);
        return str;
    }

    //7	获取门禁的实体列表（POST请求application/json）
    public static String getDoorEntityList(String url, String apikey, String modId, String modKey, int pageNum, int pageSize, String entityId, String attrIndex, String type, String value) {
        url += "/entityList";
        Map<String, Object> params = new HashMap<>();
        params.put("modId", modId);
        params.put("modKey", modKey);
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);
        if (StringUtils.isNotEmpty(entityId)) {
            params.put("entityId", entityId);
        }
        if (StringUtils.isNotEmpty(attrIndex)) {
            params.put("attrIndex", attrIndex);
        }
        if (StringUtils.isNotEmpty(type)) {
            params.put("type", type);
        }
        if (StringUtils.isNotEmpty(value)) {
            params.put("value", value);
        }
        String str = doPost(url, apikey, params);
        return str;
    }

    //8	门禁反控（POST请求application/json）
    public static String getDoorControl(String url, String apikey, String modId, String modKey, String doorIndex, int controlType) {
        url += "/service/invoke/door/control";
        Map<String, Object> params = new HashMap<>();
        params.put("modId", modId);
        params.put("modKey", modKey);
        params.put("doorIndex", doorIndex);
        params.put("controlType", controlType);
        String str = doPost(url, apikey, params);
        return str;
    }

    //9	获取人员实体列表（POST请求application/json）
    public static String getUserEntityList(String url, String apikey, String modId, String modKey, int pageNum, int pageSize) {
        url += "/entityList";
        Map<String, Object> params = new HashMap<>();
        params.put("modId", modId);
        params.put("modKey", modKey);
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);

        String str = doPost(url, apikey, params);
        return str;
    }

    //10 获取人员的历史位置信息（POST请求application/json）
    public static String getUserEntityList(String url, String apikey, String modId, String modKey, Date beginTime, Date endTime, int pageNum, int pageSize, String order) {
        url += "/entity/history";
        Map<String, Object> params = new HashMap<>();
        params.put("modId", modId);
        params.put("modKey", modKey);
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);
        String str = doPost(url, apikey, params);
        return str;
    }*/

    //11 打开算法接口（打开一个和多个算法都可以）
    public static String postOpenAlgorithm(String url, JSONObject jsonObject) {
        url += "/open_algorithm";
        String str = doPost(url, jsonObject);
        return str;
    }

    //12 关闭摄像头下所有算法接口
    public static String postCloseAlgorithm(String url, JSONObject jsonObject) {
        url += "/close_algorithm";
        String str = doPost(url, jsonObject);
        return str;
    }

    //13 删除摄像头下单个算法的接口
    public static String postCloseOneAlgorithm(String url, JSONObject jsonObject) {
        url += "/close_one_algorithm";
        String str = doPost(url, jsonObject);
        return str;
    }

    public static String doPost(String url, String apikey, JSONObject params) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
            httpPost.addHeader("apikey", apikey);
            // 创建参数列表
            if (params != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : params.keySet()) {
                    paramList.add(new BasicNameValuePair(key, params.getString(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "utf-8");
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
            }
            log.info("接口调用成功 -  url：{}，apikey：{}，params：{}，result：{}", url, apikey, params, result);
        } catch (Exception e) {
            log.error("接口调用失败 -  url：{}，params：{}", url, params, e);
            e.printStackTrace();
        } finally {
            try {
                response.close();
                httpClient.close();
            } catch (IOException e) {
                log.error("接口调用失败 -  url：{}，params：{}", url, params, e);
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String doGet(String url, String apikey, JSONObject params) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (params != null) {
                for (String key : params.keySet()) {
                    builder.addParameter(key, params.get(key).toString());
                }
            }
            URI uri = builder.build();
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            httpGet.addHeader("apikey", apikey);
            // 执行请求
            response = httpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
            log.info("接口调用成功 -  url：{}，apikey：{}，params：{}，result：{}", url, apikey, params, result);
        } catch (Exception e) {
            log.error("接口调用失败 -  url：{}，params：{}", url, params, e);
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                log.error("接口调用失败 -  url：{}，params：{}", url, params, e);
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String doPost(String uri, JSONObject jsonObject) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true); // 设置可输入
            connection.setDoOutput(true); // 设置该连接是可以输出的
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.setRequestProperty("connection", "keep-alive");
            connection.setConnectTimeout(3000);
            OutputStream os = connection.getOutputStream();
            os.write(jsonObject.toJSONString().getBytes());
            os.flush();
            os.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line = null;
            while ((line = br.readLine()) != null) { // 读取数据
                result.append(line + "\n");
            }
            br.close();
            connection.disconnect();
            log.info("接口调用成功 -  url：{}，params：{}，result：{}", uri, jsonObject.toJSONString(), result);
        } catch (Exception e) {
            log.error("接口调用失败 -  url：{}，params：{}", uri, jsonObject.toJSONString(), e);
            e.printStackTrace();
        }
        return result.toString();
    }
}