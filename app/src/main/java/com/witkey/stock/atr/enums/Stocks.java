package com.witkey.stock.atr.enums;

import com.witkey.stock.atr.R;

//@Getter
//@AllArgsConstructor
public enum Stocks {
    MAOTAI("贵州茅台", "600519.SS", R.drawable.sh600519),
    YANGHE("洋河股份", "002304.SZ", R.drawable.sz002304),
    GUJINGGONG("古井贡酒", "000596.SZ", R.drawable.sz000596),
    FENZHONG("分众传媒", "002027.SZ", R.drawable.sz002027),
    CHONGBAI("重庆百货", "600729.SS", R.drawable.sh600729),
    TIANTAN("天坛生物", "600161.SS", R.drawable.sh600161),
    HUALAN("华兰生物", "002007.SZ", R.drawable.sz002007),
    HAIKANG("海康威视", "002415.SZ", R.drawable.sz002415),
    SHENNAN("深南电路", "002916.SZ", R.drawable.sz002916);
    private String name;
    private String no;
    private Integer imageId;

    Stocks(String name, String no, Integer imageId) {
        this.name = name;
        this.no = no;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public String getNo() {
        return no;
    }

    public Integer getImageId() {
        return imageId;
    }
}
