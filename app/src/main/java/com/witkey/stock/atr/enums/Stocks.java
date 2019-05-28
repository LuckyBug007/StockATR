package com.witkey.stock.atr.enums;

import com.witkey.stock.atr.R;

//@Getter
//@AllArgsConstructor
public enum Stocks {
    MAOTAI("贵州茅台", "600519.SS", R.drawable.sh600519),
    YANGHE("洋河股份", "002304.SZ", R.drawable.sz002304),
//    GUJINGGONG("古井贡酒", "000596.SZ", R.drawable.sz000596),
    CHUNQIU("春秋航空", "601021.SS", R.drawable.sh601021),
    FENZHONG("分众传媒", "002027.SZ", R.drawable.sz002027),
    CHONGBAI("重庆百货", "600729.SS", R.drawable.sh600729),
    HUALAN("华兰生物", "002007.SZ", R.drawable.sz002007),
    TIANTAN("天坛生物", "600161.SS", R.drawable.sh600161),
    HAIKANG("海康威视", "002415.SZ", R.drawable.sz002415),
    SHENNAN("深南电路", "002916.SZ", R.drawable.sz002916),
    GONGHANG("工商银行", "601398.SS", R.drawable.sh601398),
    JIANHANG("建设银行", "601939.SS", R.drawable.sh601939),
    NONGHANG("农业银行", "601288.SS", R.drawable.sh601288),
    ZHAOHANG("招商银行", "600036.SS", R.drawable.sh600036);
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
