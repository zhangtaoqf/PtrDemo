package test.zt.com.ptrdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.chanven.lib.cptr.PtrClassicDefaultHeader;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;

/**
 *
 * 采用PtrFrameLayout作为刷新布局
 * 1.可添加的头部有3种
 *          1.ClassicHeader
 *          2.MaterialHeader
 *          3.StoreHouseHeader
 */
public class Main2Activity extends AppCompatActivity {

    TextView textView;
    PtrFrameLayout ptrFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView = ((TextView) findViewById(R.id.activity_main2_textViewId));
        //刷新控件
        ptrFrameLayout = ((PtrFrameLayout) findViewById(R.id.activity_main2_ptrFrameLayoutId));

        PtrClassicDefaultHeader ptrUIHandler = new PtrClassicDefaultHeader(this);

        //添加头部处理功能
        ptrFrameLayout.addPtrUIHandler(ptrUIHandler);
        //设置头部
        ptrFrameLayout.setHeaderView(ptrUIHandler);
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
