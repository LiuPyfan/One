package com.fantasy.pf.one.one;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fantasy.pf.one.R;
import com.fantasy.pf.one.model.bean.OneListBean;
import com.fantasy.pf.one.utils.Constants;
import com.fantasy.pf.one.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * let none that wait on thee be ashamed
 * Created by pf on 2018/1/25.
 * 包名:com.fantasy.pf.one.one
 */

public class OneAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private OneListBean mOneListBean;
    private OneListBean.ContentListBean mContentListBean;
    private Context mContext;

    private enum ITEM_TYPE {
        CATEGORY_COMMON,
        CATEGORY_REPORTER,
        CATEGORY_MUSIC,
        CATEGORY_MOVIE,
        CATEGORY_ADVERTISE,
        CATEGORY_RADIO
    }

    public OneAdapter(OneListBean oneListBean, Context context) {
        mOneListBean = oneListBean;
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        mContentListBean = mOneListBean.getContent_list().get(position);
        int type;
        switch (Integer.parseInt(mContentListBean.getCategory())) {
            case Constants.CATEGORY_REPORTER:
                type = ITEM_TYPE.CATEGORY_REPORTER.ordinal();// 如果服务端是c++,不可能把枚举变量直接传出去吧,传枚举序号
                break;
            case Constants.CATEGORY_MUSIC:
                type = ITEM_TYPE.CATEGORY_MUSIC.ordinal();
                break;
            case Constants.CATEGORY_MOVIE:
                type = ITEM_TYPE.CATEGORY_MOVIE.ordinal();
                break;
            case Constants.CATEGORY_ADVERTISE:
                type = ITEM_TYPE.CATEGORY_ADVERTISE.ordinal();
                break;
            case Constants.CATEGORY_RADIO:
                type = ITEM_TYPE.CATEGORY_RADIO.ordinal();
                break;
            default:
                type = ITEM_TYPE.CATEGORY_COMMON.ordinal();
                break;
        }
        return type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        if (viewType == ITEM_TYPE.CATEGORY_REPORTER.ordinal()) {
            return new OneReportedViewHolder(layoutInflater.inflate(R.layout.item_one_reported, parent, false));
        } else if (viewType == ITEM_TYPE.CATEGORY_MUSIC.ordinal()) {
            return new OneMusicViewHolder(layoutInflater.inflate(R.layout.item_one_music, parent, false));
        } else if (viewType == ITEM_TYPE.CATEGORY_MOVIE.ordinal()) {
            return new OneMovieViewHolder(layoutInflater.inflate(R.layout.item_one_movie, parent, false));
        } else if (viewType == ITEM_TYPE.CATEGORY_ADVERTISE.ordinal()) {
            return new OneViewHolder(layoutInflater.inflate(R.layout.item_one_advertise, parent, false));
        } else if (viewType == ITEM_TYPE.CATEGORY_RADIO.ordinal()) {
            return new OneRadioViewHolder(layoutInflater.inflate(R.layout.item_one_radio, parent, false));
        } else {
            return new OneViewHolder(layoutInflater.inflate(R.layout.item_one_common, parent, false));
        }

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof OneAdvertiseViewHolder) {
            Utils.displayImage(mContext, mContentListBean.getImgUrl(), ((OneAdvertiseViewHolder) viewHolder).ivAdvertise);
        }else if (viewHolder instanceof OneRadioViewHolder){
            radioHolder(((OneRadioViewHolder) viewHolder));
        }
        else {
            otherHolder((OneViewHolder) viewHolder, position);
        }

    }


    private void radioHolder(OneRadioViewHolder holder) {
        if (TextUtils.isEmpty(mContentListBean.getAuthor().getUserName())) {
            holder.ivLogo.setVisibility(View.GONE);
            holder.layoutRadio.setVisibility(View.GONE);
            holder.ivCoverBg.setVisibility(View.GONE);
            holder.ivVoice.setVisibility(View.VISIBLE);
            holder.ivAuthor.setImageResource(0);
            holder.tvTitle2.setText(mContentListBean.getTitle());
            holder.tvLikeNum.setText(String.valueOf(mContentListBean.getLikeCount()));
            Utils.displayImage(mContext, mContentListBean.getImgUrl(), holder.ivCover);
        } else {
            holder.ivVoice.setVisibility(View.GONE);
            holder.ivLogo.setVisibility(View.VISIBLE);
            holder.layoutRadio.setVisibility(View.VISIBLE);
            holder.ivCoverBg.setVisibility(View.VISIBLE);
            holder.tvVolume.setText(mContentListBean.getVolume());
            holder.tvTitle.setText(mContentListBean.getTitle());
            holder.tvLikeNum.setText(String.valueOf(mContentListBean.getLikeCount()));
            holder.tvUserName.setText(mContentListBean.getAuthor().getUserName());
            Utils.displayImage(mContext, mContentListBean.getImgUrl(), holder.ivCover);
            Utils.displayImage(mContext, mContentListBean.getAuthor().getWebUrl(), holder.ivAuthor,
                    Utils.getImageOptions(R.mipmap.ic_launcher_round, 360));
        }
    }

    @SuppressLint("SetTextI18n")
    private void otherHolder(OneViewHolder viewHolder, int position) {
        OneViewHolder holder = viewHolder;

        if (getItemViewType(position) == ITEM_TYPE.CATEGORY_MUSIC.ordinal()) {
            ((OneMusicViewHolder) holder).tvMusicInfo.setText(mContentListBean.getMusicName() +
                    " · " + mContentListBean.getAudioAuthor() + " | " + mContentListBean.
                    getAudioAlbum());
            Utils.displayImage(mContext, mContentListBean.getImgUrl(), holder.mIvCover, Utils.
                    getImageOptions(R.mipmap.ic_launcher_round, 360));
        } else {
            Utils.displayImage(mContext, mContentListBean.getImgUrl(), holder.mIvCover);
        }

        if (getItemViewType(position) == ITEM_TYPE.CATEGORY_REPORTER.ordinal()) {

            if (mContentListBean.getTitle().equals(Constants.ILLUSTRATION)) {
                ((OneReportedViewHolder)holder).ivCoverIllustration.setVisibility(View.VISIBLE);
                holder.mIvCover.setVisibility(View.GONE);
                Utils.displayImage(mContext,mContentListBean.getImgUrl(),((OneReportedViewHolder)holder).ivCoverIllustration);
            }else {
                ((OneReportedViewHolder) holder).ivCoverIllustration.setVisibility(View.GONE);
                holder.mIvCover.setVisibility(View.VISIBLE);
                Utils.displayImage(mContext,mContentListBean.getImgUrl(),holder.mIvCover);
            }
            holder.mTvCategory.setText(mContentListBean.getTitle() + " | " + mContentListBean.getPic_info());
            holder.mTvUserName.setText(mContentListBean.getWords_info().trim());
        } else {
            holder.mTvCategory.setText(String.format(mContext.getString(R.string.category),
                    mContentListBean.getShareList().getWx().getTitle().split("\\|")[0].trim()));
            holder.mTvUserName.setText(mContentListBean.getAnswerer() == null ? mContentListBean.getShareList().getWx().getDesc().split(" ")[0].trim()
                    : String.format(mContext.getString(R.string.answerer), mContentListBean.getAnswerer().getUserName()));
            holder.mTvPostDate.setText(Utils.showDate(mContext, mContentListBean.getPostDate()));
        }

        if (getItemViewType(position) == ITEM_TYPE.CATEGORY_MOVIE.ordinal()) {
            ((OneMovieViewHolder) holder).tvSubtitle.setText(String.format(mContext.getString(R.
                    string.subtitle), mContentListBean.getSubtitle()));
        }
        holder.mTvTitle.setText(mContentListBean.getTitle());

        holder.mTvForward.setText(mContentListBean.getForward());
        holder.mTvLikeNum.setText(String.valueOf(mContentListBean.getLikeCount()));
    }

    @Override
    public int getItemCount() {

        return mOneListBean.getContent_list().size() > 0 && mOneListBean == null ? 0 : mOneListBean.getContent_list().size();
    }

    class OneViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_category)
        TextView mTvCategory;
        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.tv_user_name)
        TextView mTvUserName;
        @BindView(R.id.iv_cover)
        ImageView mIvCover;
        @BindView(R.id.tv_forward)
        TextView mTvForward;
        @BindView(R.id.tv_post_date)
        TextView mTvPostDate;
        @BindView(R.id.iv_share)
        ImageView mIvShare;
        @BindView(R.id.iv_like)
        ImageView mIvLike;
        @BindView(R.id.tv_like_num)
        TextView mTvLikeNum;

        public OneViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class OneMusicViewHolder extends OneViewHolder {

        @BindView(R.id.tv_music_info)
        TextView tvMusicInfo;
        @BindView(R.id.iv_play)
        ImageView ivPlay;

        public OneMusicViewHolder(View itemView) {
            super(itemView);
        }
    }

    class OneMovieViewHolder extends OneViewHolder {
        @BindView(R.id.tv_subtitle)
        TextView tvSubtitle;

        public OneMovieViewHolder(View itemView) {
            super(itemView);
        }
    }

    class OneReportedViewHolder extends OneViewHolder {

        @BindView(R.id.iv_cover_illustration)
        ImageView ivCoverIllustration;

        public OneReportedViewHolder(View itemView) {
            super(itemView);
        }
    }

    class OneAdvertiseViewHolder extends OneViewHolder {
        @BindView(R.id.iv_advertise)
        ImageView ivAdvertise;

        public OneAdvertiseViewHolder(View itemView) {
            super(itemView);
        }
    }

    class OneRadioViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_play)
        ImageView ivPlay;
        @BindView(R.id.tv_volume)
        TextView tvVolume;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.view_line)
        View viewLine;
        @BindView(R.id.iv_author)
        ImageView ivAuthor;
        @BindView(R.id.tv_user_name)
        TextView tvUserName;
        @BindView(R.id.iv_share)
        ImageView ivShare;
        @BindView(R.id.iv_like)
        ImageView ivLike;
        @BindView(R.id.tv_like_num)
        TextView tvLikeNum;
        @BindView(R.id.layout_bottom)
        RelativeLayout layoutBottom;
        @BindView(R.id.iv_cover)
        ImageView ivCover;
        @BindView(R.id.iv_logo)
        ImageView ivLogo;
        @BindView(R.id.layout_radio)
        RelativeLayout layoutRadio;
        @BindView(R.id.iv_cover_bg)
        ImageView ivCoverBg;
        @BindView(R.id.iv_voice)
        ImageView ivVoice;
        @BindView(R.id.tv_title2)
        TextView tvTitle2;

        public OneRadioViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
