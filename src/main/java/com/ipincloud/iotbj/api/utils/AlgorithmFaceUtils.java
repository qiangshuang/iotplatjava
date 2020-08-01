package com.ipincloud.iotbj.api.utils;

import com.ipincloud.iotbj.oa.OAApi;
import com.ipincloud.iotbj.utils.FileUtils;
import com.ipincloud.iotbj.utils.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class AlgorithmFaceUtils {
    private static Logger logger = LoggerFactory.getLogger(AlgorithmFaceUtils.class.getName());

    public static void registerFace(String algorithmFaceRegisterUrl, String personId, String filePath) {
        logger.debug("registerFace:personId={} filePath={}", personId, filePath);
        if (StringUtils.isNotEmpty(algorithmFaceRegisterUrl) && StringUtils.isNotEmpty(personId) && StringUtils.isNotEmpty(filePath)) {
            String fullPath = FileUtils.getFullFilePath(filePath);
            logger.debug("registerFace:fullPath={}", fullPath);
            if (StringUtils.isNotEmpty(fullPath)) {
                File file = new File(fullPath);
                if (file.exists() && file.isFile()) {
                    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
                    builder.addTextBody("userId", personId);
                    builder.addBinaryBody("img", file);
                    HttpEntity multipart = builder.build();
                    HttpPost httpPost = new HttpPost(algorithmFaceRegisterUrl);
                    try {
                        httpPost.setEntity(multipart);
                        System.out.println(OAApi.execute(httpPost));
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            EntityUtils.consume(multipart);
                        } catch (IOException e) {
                        }
                    }
                }
            }
        }
    }
}
