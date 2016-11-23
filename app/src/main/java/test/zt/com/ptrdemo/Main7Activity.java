package test.zt.com.ptrdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.header.StoreHouseHeader;
import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;

import java.util.ArrayList;
//http://blog.csdn.net/evan_man/article/details/51570466
public class Main7Activity extends AppCompatActivity{

    RecyclerView recyclerView;
    PtrFrameLayout ptrFrameLayout;
    RVAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        recyclerView = ((RecyclerView) findViewById(R.id.activity_main7_recyclerViewId));
        ptrFrameLayout = ((PtrFrameLayout) findViewById(R.id.activity_main7_ptrFrameLayoutId));
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        adapter = new RVAdapter(this);
        //注意：设置适配器的时候一定要套用这个布局，adapter适配器不要泛型
        recyclerView.setAdapter(new RecyclerAdapterWithHF(adapter){
            @Override
            public void onAttachedToRecyclerView(RecyclerView recyclerView) {
                super.onAttachedToRecyclerView(recyclerView);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if(layoutManager instanceof GridLayoutManager){
                    final GridLayoutManager gridLayoutManager = (GridLayoutManager)layoutManager;
                    gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                        @Override
                        public int getSpanSize(int position) {
                            if(position == adapter.getItemCount())
                                return gridLayoutManager.getSpanCount();
                            return 1;
                        }
                    });
                }
            }
        });
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(StaggeredGridLayoutManager.VERTICAL,2));
//
//        adapter = new RVAdapter(this);
//        //注意：设置适配器的时候一定要套用这个布局，adapter适配器不要泛型
//        recyclerView.setAdapter(new RecyclerAdapterWithHF(adapter){
//            /**
//             * 对StaggeredGridLayoutManager的处理
//             * @param holder
//             */
//            @Override
//            public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
//                super.onViewAttachedToWindow(holder);
//                ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
//                if(layoutParams!=null&& layoutParams instanceof StaggeredGridLayoutManager.LayoutParams ){
//                    StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) layoutParams;
//                    int position = holder.getLayoutPosition();
//                    if(position == adapter.getItemCount())
//                    {
//                        lp.setFullSpan(true);
//                    }
//                }
//                holder.itemView.setLayoutParams(layoutParams);
//            }
//        });

        final StoreHouseHeader ptrUIHandler = new StoreHouseHeader(this);

        ptrUIHandler.initWithString("wang sheng");

        ptrUIHandler.setPadding(40,40,40,40);

        //添加头部ui处理功能
        ptrFrameLayout.addPtrUIHandler(ptrUIHandler);
        //设置头布局
        ptrFrameLayout.setHeaderView(ptrUIHandler);


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
                //清空数据源
                adapter.clear();
                //加载新数据
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadData();
                        //隐藏刷新进度
                        frame.refreshComplete();
                        //激活加载更多
                        frame.setLoadMoreEnable(true);
                    }
                },2000);
            }
        });

        ptrFrameLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                ptrFrameLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadData();
                        //已经加载完成
                        ptrFrameLayout.loadMoreComplete(true);
                    }
                },2000);
            }
        });

        loadData();
    }

    private void loadData() {
        ArrayList<String> dd = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            dd.add("item:"+i);
        }
        adapter.addAll(dd);
    }
}
