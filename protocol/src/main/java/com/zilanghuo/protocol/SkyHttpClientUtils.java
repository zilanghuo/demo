package com.zilanghuo.protocol;

import okhttp3.*;
import okio.BufferedSink;

import java.io.IOException;

/**
 * @author laiwufa
 * @date 2019/3/4 0004 下午 2:15
 */
public class SkyHttpClientUtils {

    public final static String ENDPOINT = "http://10.100.60.84:8080/api/dashboard";

    public static void main(String[] args) throws Exception {

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.addHeader("Accept", "application/json");
        builder.addHeader("Origin", "http://10.100.60.84:8080");
        builder.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36");
        builder.addHeader("DNT", "1");
        builder.addHeader("Content-Type", "application/json");

        RequestBody reqbody = new RequestBody() {
            @Override
            public MediaType contentType() {
                return null;
            }
            @Override
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                bufferedSink.writeUtf8("{\"variables\":{\"duration\":{\"start\":\"2019-02-20\",\"end\":\"2019-03-03\",\"step\":\"DAY\"}},\"query\":\"\\n    query Dashboard($duration: Duration!) {\\n        getTopNSlowService(duration: $duration, topN: 50) {\\n        service {\\n          key: id\\n          label: name\\n          applicationId\\n          applicationName\\n        }\\n        value: avgResponseTime\\n      }\\n       }\\n  \"}");
            }
        };
        builder.post(reqbody);

        Request request = builder
                .url(ENDPOINT)
                .build();
        // Execute the request and retrieve the response.
        try (Response response = client.newCall(request).execute()) {
            // Deserialize HTTP response to concrete type.
            ResponseBody body = response.body();
            System.out.println(body.string());
        }

    }
}
