package com.witkey.stock.atr.model;

import com.alibaba.fastjson.JSONObject;

public class StockKline {

    /**
     * data : {"candle":{"002007.SZ":[[20190124,33.35,33.37,33.47,32.79,5025922,166743899],[20190125,33.3,33.69,34.05,33,6018804,202810488]],"fields":["min_time","open_px","close_px","high_px","low_px","business_amount","business_balance"]}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * candle : {"002007.SZ":[[20190124,33.35,33.37,33.47,32.79,5025922,166743899],[20190125,33.3,33.69,34.05,33,6018804,202810488]],"fields":["min_time","open_px","close_px","high_px","low_px","business_amount","business_balance"]}
         */

        private JSONObject candle;

        public JSONObject getCandle() {
            return candle;
        }

        public void setCandle(JSONObject candle) {
            this.candle = candle;
        }
    }
}
