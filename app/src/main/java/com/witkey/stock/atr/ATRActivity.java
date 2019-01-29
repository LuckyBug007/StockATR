package com.witkey.stock.atr;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.google.common.collect.ImmutableMap;
import com.witkey.stock.atr.model.StockKline;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class ATRActivity extends AppCompatActivity {
    private final String DAYS = "15";

    private EditText editText;
    private Button button;

    private TextView atrInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atr_layout);

        atrInfo = findViewById(R.id.atr_info);

        Intent intent = getIntent();
        String stockInfo = intent.getStringExtra("stockInfo");
        // 华兰生物\t002007.SZ
        String[] strs = stockInfo.split("\t");

        NetRequest netRequest = new NetRequest(strs[0], strs[1], DAYS);
        new Thread(netRequest).start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle data = msg.getData();
            //从 data 中拿出存的数据
            String val = data.getString("res");
            //将数据进行显示到界面等操作
            atrInfo.setText(val);
        }
    };

    class NetRequest implements Runnable {
        String stockName;
        String stockNo;
        String days;

        public NetRequest(String stockName, String stockNo, String days) {
            this.stockName = stockName;
            this.stockNo = stockNo;
            this.days = days;
        }

        @Override
        public void run() {
            String res = calATR(stockName, stockNo, days);
            //发送消息，通知UI组件显示图片
            Message msg = Message.obtain();
            Bundle data = new Bundle();
            data.putString("res", res);
            msg.setData(data);
            handler.sendMessage(msg);
        }
    }

    private String calATR(String stockName, String stockNo, String days) {
        Map<String, String> params = ImmutableMap.of("data_count", days, "prod_code", stockNo);
        String response = requestGet(params);

        StringBuilder builder = new StringBuilder();
        try {
            StockKline stockKline = JSON.parseObject(response, StockKline.class);
            JSONArray lists = stockKline.getData().getCandle().getJSONArray(stockNo);

            double sum = 0;
            double lastClose = Double.MAX_VALUE;
            for (int i = 1; i < lists.size(); i++) {
                JSONArray preDay = lists.getJSONArray(i - 1);
                double preDayClose = preDay.getDoubleValue(2);

                JSONArray today = lists.getJSONArray(i);
                double todayHigh = today.getDoubleValue(3);
                double todayLow = today.getDoubleValue(4);
                lastClose = today.getDoubleValue(2);

                double tr = Math.max(Math.abs(todayHigh - todayLow),
                        Math.abs(todayHigh - preDayClose));
                tr = Math.max(tr, Math.abs(preDayClose - todayLow));

                builder.append(String.format("%s ============> %s元\n", today.getInteger(0), String.format("%.2f", tr)));

                sum += tr;
            }

            double atr = sum / Math.abs(Integer.parseInt(days) - 1);
            double percent = atr / lastClose * 100;
            builder.append(String.format("%s %s ======> %s天 ATR: %s元，百分比%s%%",
                    stockNo, stockName, Integer.parseInt(days) - 1, String.format("%.2f", atr), String.format("%.2f", percent)));

            return builder.toString();
        } catch (JSONException e) {
            Log.e("error", "字符串转 JSON 失败");
            return null;
        }
    }

    /**
     * HTTP GET 请求
     *
     * @param paramsMap
     * @return String
     * https://mdc.wallstreetcn.com/kline?candle_period=6&data_count=15&fields=min_time,open_px,close_px,high_px,low_px&prod_code=002007.SZ&adjust_price_type=forward
     */
    private String requestGet(Map<String, String> paramsMap) {
        String result = null;
        try {
            // &data_count={days}   &prod_code={stockNo}
            String baseUrl = "https://mdc.wallstreetcn.com/kline?candle_period=6&fields=min_time,open_px,close_px,high_px,low_px&adjust_price_type=forward";
            StringBuilder tempParams = new StringBuilder();
            for (String key : paramsMap.keySet()) {
                tempParams.append("&");
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
            }
            String requestUrl = baseUrl + tempParams.toString();
            // 新建一个URL对象
            URL url = new URL(requestUrl);
            // 打开一个 HttpURLConnection 连接
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            // 设置连接主机超时时间
            urlConn.setConnectTimeout(5 * 1000);
            // 设置从主机读取数据超时
            urlConn.setReadTimeout(5 * 1000);
            // 设置是否使用缓存  默认是true
            urlConn.setUseCaches(true);
            // 设置为 GET 请求
            urlConn.setRequestMethod("GET");
            // urlConn设置请求头信息
            // 设置请求中的媒体类型信息。
            urlConn.setRequestProperty("Content-Type", "application/json");
            // 设置客户端与服务连接类型
            urlConn.addRequestProperty("Connection", "Keep-Alive");
            // 开始连接
            urlConn.connect();
            // 判断请求是否成功
            if (urlConn.getResponseCode() == 200) {
                // 获取返回的数据
                result = streamToString(urlConn.getInputStream());
                Log.e("error", "Get 请求成功，result ===>" + result);
            } else {
                Log.e("error", "Get 请求失败");
            }
            // 关闭连接
            urlConn.disconnect();
        } catch (Exception e) {
            Log.e("error", e.toString());
        }
        return result;
    }

    /**
     * 将输入流转换成字符串
     *
     * @param is 从网络获取的输入流
     * @return String
     */
    public String streamToString(InputStream is) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.close();
            is.close();
            byte[] byteArray = baos.toByteArray();
            return new String(byteArray);
        } catch (Exception e) {
            Log.e("error", e.toString());
            return null;
        }
    }
}