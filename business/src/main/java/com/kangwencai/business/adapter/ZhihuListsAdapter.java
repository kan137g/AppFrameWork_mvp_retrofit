package com.kangwencai.business.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kangwencai.business.R;
import com.kangwencai.business.model.bean.zhihu.ZhihuTopStory;
import com.kangwencai.common.utils.img_utils.GlideUtils;

import java.util.ArrayList;

import butterknife.BindView;


/**
 * Description:
 * Author: kangwencai
 * DATA: Date on 2018/6/26.
 * PS: Not easy to write code, please indicate.
 */
public class ZhihuListsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private ArrayList<ZhihuTopStory> zhihuTopStories;
    private Context context;

    private OnItemClickListener onItemClickListener;

    public ZhihuListsAdapter(Context context, ArrayList<ZhihuTopStory> zhihuTopStories) {
        this.context = context;
        if (zhihuTopStories == null) {
            this.zhihuTopStories = new ArrayList<>();

        } else {
            this.zhihuTopStories = zhihuTopStories;

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_zhihu_list, viewGroup, false);

        return new ZhihuListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        ZhihuListViewHolder zhihuListViewHolder = (ZhihuListViewHolder) viewHolder;
        zhihuListViewHolder.itemTitle.setText(zhihuTopStories.get(i).getTitle());
        zhihuListViewHolder.itemDes.setText(zhihuTopStories.get(i).getGa_prefix());
        GlideUtils.showImageView(context, zhihuTopStories.get(i).getImage(), zhihuListViewHolder.imageView);

        ((ZhihuListViewHolder) viewHolder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.OnItemClick(view, i);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return zhihuTopStories == null ? 0 : zhihuTopStories.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


    public void setZhihuTopStories(ArrayList<ZhihuTopStory> zhihuTopStories) {
        this.zhihuTopStories = zhihuTopStories;
//        this.zhihuTopStories.addAll(zhihuTopStories);
        notifyDataSetChanged();
    }

    class ZhihuListViewHolder extends RecyclerView.ViewHolder {

        TextView itemTitle;
        TextView itemDes;
        ImageView imageView;

        public ZhihuListViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.item_title);
            itemDes = itemView.findViewById(R.id.item_des);
            imageView = itemView.findViewById(R.id.news_image);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void OnItemClick(View view, int position);
    }
}
