package com.witkey.stock.atr.enums;

import com.witkey.stock.atr.R;

//@Getter
//@AllArgsConstructor
public enum Stocks {
    MAOTAI("贵州茅台", "600519.SS", R.drawable.sh600519),
//    YANGHE("洋河股份", "002304.SZ", R.drawable.sz002304),
//    GUJINGGONG("古井贡酒", "000596.SZ", R.drawable.sz000596),
    ZHAOHANG("招商银行", "600036.SS", R.drawable.sh600036),
    DONGFANG("东方财富", "300059.SZ", R.drawable.sz300059),
//    NINGDE("宁德时代", "300750.SZ", R.drawable.sz300750),
//    XIANDAO("先导智能", "300450.SZ", R.drawable.sz300450),
    CHUNQIU("春秋航空", "601021.SS", R.drawable.sh601021),
//    ZHONGJIAN("中国建筑", "601668.SS", R.drawable.sh601668),
//    FENZHONG("分众传媒", "002027.SZ", R.drawable.sz002027),
//    CHONGBAI("重庆百货", "600729.SS", R.drawable.sh600729),
    HENGRUI("恒瑞医药", "600276.SS", R.drawable.sh600276),
    XINLITAI("信立泰", "002294.SZ", R.drawable.sz002294),
    HUALAN("华兰生物", "002007.SZ", R.drawable.sz002007),
//    TIANTAN("天坛生物", "600161.SS", R.drawable.sh600161),
    JINYU("生物股份", "600201.SS", R.drawable.sh600201),
    SHANDONGGOLD("山东黄金", "600547.SS", R.drawable.sh600547),
    YINTAI("银泰黄金", "000975.SZ", R.drawable.sz000975),
//    HENGBANG("恒邦股份", "002237.SZ", R.drawable.sz002237),
//    HAIKANG("海康威视", "002415.SZ", R.drawable.sz002415),
//    SHENNAN("深南电路", "002916.SZ", R.drawable.sz002916),
//    GONGHANG("工商银行", "601398.SS", R.drawable.sh601398),
//    JIANHANG("建设银行", "601939.SS", R.drawable.sh601939),
//    JIANHANG("华能水电", "600025.SS", R.drawable.sh600025),
    ETF500("500ETF", "510500.SS", R.drawable.empty),
    GLOD("黄金ETF", "518880.SS", R.drawable.sh518880),
    ZHENGQUAN_ETF("证券ETF", "512880.SS", R.drawable.empty),
    QUANSHANG_B("券商B", "150201.SZ", R.drawable.empty),
    JUNGONG_ETF("军工ETF", "512660.SS", R.drawable.empty),
    JUNGONGG_B("军工B", "150182.SZ", R.drawable.empty);
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
