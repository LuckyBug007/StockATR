package com.witkey.stock.atr.model;

import com.alibaba.fastjson.JSONObject;

public class StockKline {

    /**
     * "data":{"candle":{"002057.SZ":{"lines":[[13.32,12.7,13.49,12.24,1559606400],[13.13,13.13,13.72,13.07,1559692800],[13.52,14.44,14.44,13.52,1559779200],[15.7,17.47,17.47,15.31,1560211200],[18.4,16.96,19.22,16.91,1560297600]],"pre_close_px":0}},"fields":["open_px","close_px","high_px","low_px","tick_at"]}
     * <p>
     * {
     * code: 20000,
     * message: "OK",
     * data: {
     * candle: {
     * 002057.SZ: {
     * lines: [
     * [
     * 13.32,
     * 12.7,
     * 13.49,
     * 12.24,
     * 1559606400
     * ],
     * [
     * 13.13,
     * 13.13,
     * 13.72,
     * 13.07,
     * 1559692800
     * ],
     * [
     * 13.52,
     * 14.44,
     * 14.44,
     * 13.52,
     * 1559779200
     * ],
     * [
     * 15.7,
     * 17.47,
     * 17.47,
     * 15.31,
     * 1560211200
     * ],
     * [
     * 18.4,
     * 16.96,
     * 19.22,
     * 16.91,
     * 1560297600
     * ]
     * ],
     * pre_close_px: 0
     * }
     * },
     * fields: [
     * "open_px",
     * "close_px",
     * "high_px",
     * "low_px",
     * "tick_at"
     * ]
     * }
     * }
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private JSONObject candle;

        public JSONObject getCandle() {
            return candle;
        }

        public void setCandle(JSONObject candle) {
            this.candle = candle;
        }
    }
}
