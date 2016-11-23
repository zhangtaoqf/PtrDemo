package test.zt.com.ptrdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.header.StoreHouseHeader;
//        storeHouseHeader.initWithStringArray(R.array.storehouse);
public class Main6Activity extends AppCompatActivity {

    TextView textView;
    PtrFrameLayout ptrFrameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        textView = ((TextView) findViewById(R.id.activity_main6_textViewId));
        //刷新控件
        ptrFrameLayout = ((PtrFrameLayout) findViewById(R.id.activity_main6_ptrFrameLayoutId));

        StoreHouseHeader storeHouseHeader = new StoreHouseHeader(this);
        //初始化头部数据
        storeHouseHeader.initWithStringArray(R.array.storehouse);

        storeHouseHeader.setPadding(40,40,40,40);

        //添加头部处理功能
        ptrFrameLayout.addPtrUIHandler(storeHouseHeader);
        //设置头部
        ptrFrameLayout.setHeaderView(storeHouseHeader);
        //设置自动刷新
        ptrFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                ptrFrameLayout.autoRefresh(true);
            }
        },200);
        //设置下拉刷新监听
        ptrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                textView.setText("");
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadData();
                        //隐藏刷新进度
                        frame.refreshComplete();
                    }
                },2000);
            }
        });


    }

    private void loadData() {
        textView.setText("MAIN2"+System.currentTimeMillis());
    }
}
