package com.hswu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hswu.bean.BaseBean;
import com.hswu.bean.CreditCard;
import com.hswu.bean.Note;
import com.hswu.messages.R;

import java.util.List;

/**
 * Created by HandsomeWu on 2016/7/22.
 */

public class BaseBeanListItemAdapter extends BaseAdapter {

    private List<? extends BaseBean> baseBean;
    private Context context;

    class ViewHolder {
        ImageView iv;
        TextView tv;
        TextView tv_num;
    }

    public BaseBeanListItemAdapter(Context context, List<? extends BaseBean> baseBean) {

        this.context = context;
        this.baseBean = baseBean;

    }

    @Override
    public int getCount() {
        return baseBean != null ? baseBean.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return baseBean != null ? baseBean.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.basebean_listview_item, null);
            viewHolder = new ViewHolder();
            viewHolder.iv = (ImageView) convertView.findViewById(R.id.iv);
            viewHolder.tv = (TextView) convertView.findViewById(R.id.tv);
            viewHolder.tv_num = (TextView) convertView.findViewById(R.id.tv_num);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (baseBean != null) {


            if (baseBean.get(position) instanceof  CreditCard){
                CreditCard card = (CreditCard) baseBean.get(position);
                viewHolder.iv.setImageResource(R.mipmap.type_credit_card);
                if (card.getBankName() != null) {
                    viewHolder.tv.setText(card.getBankName());
                }
                if (card.getCardNumber() != null) {
                    viewHolder.tv_num.setText(card.getCardNumber());
                }
            }

            if (baseBean.get(position) instanceof Note){
                Note note = (Note) baseBean.get(position);
                viewHolder.iv.setImageResource(R.mipmap.type_note);
                if (note.getNoteName() != null) {
                    viewHolder.tv.setText(note.getNoteName());
                }
                if (note.getNoteContent() != null) {
                    viewHolder.tv_num.setText(note.getNoteContent());
                }
            }


        }

        return convertView;
    }
}

