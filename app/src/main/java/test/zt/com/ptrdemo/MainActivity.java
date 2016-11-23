package test.zt.com.ptrdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    PtrClassicFrameLayout ptrFrameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = ((TextView) findViewById(R.id.activity_main_textViewId));
        //刷新布局
        ptrFrameLayout = ((PtrClassicFrameLayout) findViewById(R.id.activity_main_ptrClassicFrameLayoutId));

        ptrFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                //执行调用下拉刷新监听
                ptrFrameLayout.autoRefresh(true);
            }
        },200);

        //设置下拉刷新监听
        ptrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                //清空数据源
                textView.setText("");
                //加载网络数据
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadData();
                        //刷新完成隐藏刷新进度
                        frame.refreshComplete();
                    }
                },2000);
            }
        });
    }

    private void loadData() {
        textView.setText("MAIN1:"+System.currentTimeMillis());
    }
}
