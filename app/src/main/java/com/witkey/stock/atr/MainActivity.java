package com.witkey.stock.atr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.collect.Lists;
import com.witkey.stock.atr.enums.Stocks;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView stockInfoView;// stock_item.xml 里的 TextView：stock_info

    private ImageView imageView;// stock_item.xml 里的 ImageView：stock_image

    private ListView listView;// activity_main.xml 里的 ListView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<Stocks> stocks = Lists.newArrayList(Stocks.values());

        listView = findViewById(R.id.listView);

        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return stocks.size();//数目
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                View view;

                if (convertView == null) {
                    //因为 getView()返回的对象，adapter会自动赋给ListView
                    view = inflater.inflate(R.layout.stock_item, null);
                } else {
                    view = convertView;
                    Log.i("info", "有缓存，不需要重新生成" + position);
                }
                stockInfoView = view.findViewById(R.id.stock_info);//找到 stock_info
                stockInfoView.setText(stocks.get(position).getName() + "\t" + stocks.get(position).getNo());//为文本视图设置文本内容

                imageView = view.findViewById(R.id.stock_image);//找到 stock_image
                imageView.setImageResource(stocks.get(position).getImageId());//为图片视图设置图片资源
                return view;
            }

            @Override
            public long getItemId(int position) {// 取在列表中与指定索引对应的行id
                return 0;
            }

            @Override
            public Object getItem(int position) {// 获取数据集中与指定索引对应的数据项
                return null;
            }
        });

        // 获取当前 ListView 点击的行数，并且得到该数据
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                stockInfoView = view.findViewById(R.id.stock_info); // 找到 stock_info
                String str = stockInfoView.getText().toString(); // 得到数据
//                Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();//显示数据

                Intent atr = new Intent(MainActivity.this, ATRActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("stockInfo", str);
                atr.putExtras(bundle);
                startActivity(atr);
            }
        });

    }

}
