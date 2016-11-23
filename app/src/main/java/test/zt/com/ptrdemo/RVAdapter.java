package test.zt.com.ptrdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/2.
 */

public class RVAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private List<String> datas;

    public RVAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        datas = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_rv,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).textView.setText(datas.get(position));
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void addAll(List<String> dd) {
        datas.addAll(dd);
        notifyDataSetChanged();
    }

    public void clear() {
        datas.clear();
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = ((TextView) itemView.findViewById(R.id.item_rv_textViewId));
        }
    }
}
