package de.sp.trashNothing_backend.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;


@Service
public class ImageUploadService {
    @Value("${imgbb.key}")
    private String imgbbApiKey;

    @Value("${imgbb.url}")
    private String imgbbApiUrl;

    public Map<String, String> uploadImage(MultipartFile file) {
        Map<String, String> resultMap = new HashMap<>();
        RestTemplate restTemplate = new RestTemplate();

        try {
            // 将文件转换为 Base64 编码的字符串
            String encodedImage = Base64.getEncoder().encodeToString(file.getBytes());

            // 设置请求头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            // 创建请求主体并设置参数，使用 MultiValueMap
            MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
            requestBody.add("key", imgbbApiKey);
            requestBody.add("image", encodedImage);

            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

            // 发送请求到 imgBB API
            ResponseEntity<Map> response = restTemplate.postForEntity(imgbbApiUrl, requestEntity, Map.class);

            // 检查响应状态并解析 URL
            if (response.getStatusCode().is2xxSuccessful()) {
                Map<String, Object> data = (Map<String, Object>) response.getBody().get("data");
                resultMap.put("imgUrl", (String) data.get("url"));
                resultMap.put("deleteUrl", (String) data.get("delete_url"));
            } else {
                throw new RuntimeException("Image upload failed with status: " + response.getStatusCode());
            }

        } catch (IOException e) {
            throw new RuntimeException("Error encoding image file", e);
        }

        return resultMap;
    }
}

