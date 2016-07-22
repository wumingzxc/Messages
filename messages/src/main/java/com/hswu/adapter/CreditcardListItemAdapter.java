package com.hswu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hswu.bean.CreditCard;
import com.hswu.messages.R;

import java.util.List;

/**
 * Created by HandsomeWu on 2016/7/22.
 */

public class CreditcardListItemAdapter extends BaseAdapter {

    private List<CreditCard> cards;
    private Context context;

    class ViewHolder {
        TextView tv;
    }

    public CreditcardListItemAdapter(Context context, List<CreditCard> cards) {

        this.context = context;
        this.cards = cards;

    }

    @Override
    public int getCount() {
        return cards != null ? cards.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return cards != null ? cards.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.creditcard_listview_item, null);
            viewHolder = new ViewHolder();
            viewHolder.tv = (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (cards != null) {

            CreditCard card = cards.get(position);
            if (card.getBankName() != null) {
                viewHolder.tv.setText(card.getBankName());
            }
        }

        return convertView;
    }
}

