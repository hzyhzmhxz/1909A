package com.example.experiment_day4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @NonNull
    private Context context;
    private List<MeiBean.ResultsBean> datas=new ArrayList<>();
    private List<BannerBean.DataBean> list=new ArrayList<>();
    private  LayoutInflater inflater;
    private int VIEW_TYPE_ONT=1;
    private int VIEW_TYPE_TWO=2;
    public void setDatas(List<MeiBean.ResultsBean> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public void setList(List<BannerBean.DataBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public RvAdapter(@NonNull Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==VIEW_TYPE_ONT){
            View view = inflater.inflate(R.layout.item_layout, null);
            return new ViewHolder(view);
        }else {
            View view = inflater.inflate(R.layout.item_layout_a, null);
            return new ViewHolderone(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = holder.getItemViewType();
        if (itemViewType==VIEW_TYPE_ONT){
            ViewHolder viewHolder= (ViewHolder) holder;
            ArrayList<String> title = new ArrayList<>();
            final ArrayList<String> image = new ArrayList<>();
            for (BannerBean.DataBean ban:list){
                image.add(ban.getImagePath());
                title.add(ban.getTitle());
            }
            viewHolder.ban.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                    .setImages(image)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            Glide.with(context).load(path).into(imageView);
                        }
                    }).setBannerTitles(title).start();
        }else {
            ViewHolderone viewHolderone= (ViewHolderone) holder;
            viewHolderone.tv_title.setText(datas.get(position).getDesc());
            Glide.with(context).load(datas.get(position).getUrl()).into(viewHolderone.img);
        }
    }

    @Override
    public int getItemCount() {
        return datas.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return VIEW_TYPE_ONT;
        }else {
            return VIEW_TYPE_TWO;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final Banner ban;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ban = itemView.findViewById(R.id.ban);
        }
    }
    public class ViewHolderone extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView tv_title;

        public ViewHolderone(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tv_title = itemView.findViewById(R.id.tv_title);
        }
    }
}
