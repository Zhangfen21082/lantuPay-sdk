package top.zxdemo.ltzf.test;

import top.zxdemo.ltzf.payments.nativepay.INativePayApi;
import top.zxdemo.ltzf.payments.nativepay.model.PrepayResponse;
import top.zxdemo.ltzf.utils.SignUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangXing zxdemo.top
 * @description api test
 */
@Slf4j
public class ApiTest {

    public static void main(String[] args) {

        long timestamp = System.currentTimeMillis() / 1000;
        System.out.println(timestamp);

        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("mch_id", "1673424392");
        dataMap.put("out_trade_no", "xfg240413001");
        dataMap.put("total_fee", "0.01");
        dataMap.put("body", "QQ公仔");
        dataMap.put("timestamp", String.valueOf(timestamp));
        dataMap.put("notify_url", "https://gaga.plus");

        System.out.println(SignUtils.createSign(dataMap, "6d3e889f359fcb83d150e9553a9217b9"));

    }

    @Test
    public void test_retrofit2() throws IOException {
        OkHttpClient httpClient = new OkHttpClient();

        INativePayApi nativePayApi = new Retrofit.Builder()
                .baseUrl("https://api.ltzf.cn/")
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(INativePayApi.class);

        long timestamp = System.currentTimeMillis() / 1000;
        System.out.println(timestamp);

        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("mch_id", "1673424392");
        dataMap.put("out_trade_no", "xfg240413002");
        dataMap.put("total_fee", "0.01");
        dataMap.put("body", "QQ公仔");
        dataMap.put("timestamp", String.valueOf(timestamp));
        dataMap.put("notify_url", "https://gaga.plus");


        Call<PrepayResponse> call = nativePayApi.prepay(
                dataMap.get("mch_id"),
                dataMap.get("out_trade_no"),
                dataMap.get("total_fee"),
                dataMap.get("body"),
                dataMap.get("timestamp"),
                dataMap.get("notify_url"),
                SignUtils.createSign(dataMap, "6d3e889f359fcb83d150e9553a9217b9"));

        Response<PrepayResponse> response = call.execute();
        PrepayResponse prepayResponse = response.body();

        log.info("测试结果:{}", JSON.toJSONString(prepayResponse));

    }

}
