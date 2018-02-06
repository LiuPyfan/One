package com.fantasy.pf.one.one.detail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fantasy.pf.one.R;
import com.fantasy.pf.one.model.bean.CommentBean;
import com.fantasy.pf.one.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * let none that wait on thee be ashamed
 * Created by pf on 2018/2/5.
 * 包名:com.fantasy.pf.one.one.detail
 */

public class CommentAdapter extends RecyclerView.Adapter <CommentAdapter.CommentViewHolder>{

    private Context mContext;
    private CommentBean mCommentBean;


    public CommentAdapter(Context context, CommentBean commentBean) {
        mContext = context;
        mCommentBean = commentBean;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        return new CommentViewHolder(layoutInflater.inflate(R.layout.item_comment,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        CommentBean.DataBean dataBean = mCommentBean.getData().get(position);
        Utils.displayImage(mContext,dataBean.getUser().getWebUrl(),holder.ivUser,
                Utils.getImageOptions(R.mipmap.ic_launcher_round,360));
        holder.tvUserName.setText(dataBean.getUser().getUserName());
        holder.tvCreatedAt.setText(dataBean.getCreatedAt());

        if (TextUtils.isEmpty(dataBean.getQuote())){
            holder.layoutQuote.setVisibility(View.GONE);
        }else {
            holder.layoutQuote.setVisibility(View.VISIBLE);
            holder.tvQuote.setText(dataBean.getTouser().getUserName() + ": " + dataBean.getQuote());
        }
        holder.tvContent.setText(dataBean.getContent());
        holder.tvPraiseNum.setText(dataBean.getPraisenum() + "");
    }

    @Override
    public int getItemCount() {
        return mCommentBean.getData() == null && mCommentBean.getData().size() > 0 ? 0 : mCommentBean.getData().size() ;
    }

    class CommentViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_user)
        ImageView ivUser;
        @BindView(R.id.tv_user_name)
        TextView tvUserName;
        @BindView(R.id.tv_created_at)
        TextView tvCreatedAt;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_praise_num)
        TextView tvPraiseNum;
        @BindView(R.id.tv_quote)
        TextView tvQuote;
        @BindView(R.id.layout_quote)
        RelativeLayout layoutQuote;

        public CommentViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

    }
}
