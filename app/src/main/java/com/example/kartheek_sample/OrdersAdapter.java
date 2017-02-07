package com.example.kartheek_sample;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kartheek_sample.models.Item;
import com.example.kartheek_sample.models.Payments;
import com.example.kartheek_sample.models.SectionItem;

import java.util.List;


public class OrdersAdapter extends ArrayAdapter<Item> {

    private Context context;
    private List<Item> items;
    private LayoutInflater vi;
    private ViewHolder holder;
    List<String> PostrowItems;
    ProgressDialog pDialog;
    SectionItem si;
    private Typeface regular;


    public OrdersAdapter(Context context, List<Item> items) {
        super(context, 0, items);
        this.context = context;
        this.items = items;
        vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    private class ViewHolder {
        TextView payment_type,price;
    }

    @Override
    public int getCount() {
        return items.size();

    }

    @Override
    public Item getItem(int position) {
        return items.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final int item_position = position;
        final Item i = items.get(position);
        if (i != null) {
            regular = Typeface.createFromAsset(context.getAssets(), "Roboto-Regular.ttf");
            if (i.isHeader()) {
                si = (SectionItem) i;
                v = vi.inflate(R.layout.header_row, null);

                v.setOnClickListener(null);
                v.setOnLongClickListener(null);
                v.setLongClickable(false);

                final TextView sectionView = (TextView) v.findViewById(R.id.list_item_section_text);
                sectionView.setTypeface(regular);
                sectionView.setText(si.getTitle());

            } else {
                final Payments item = (Payments) i;
                v = vi.inflate(R.layout.order_list_item, null);
                holder = new ViewHolder();
                holder.payment_type = (TextView) v.findViewById(R.id.payment_type);
                holder.price = (TextView) v.findViewById(R.id.price);

                holder.payment_type.setTypeface(regular);
                holder.price.setTypeface(regular);

                if(!TextUtils.isEmpty(item.getTenders().getLabel()) && item.getTenders().getLabel().equalsIgnoreCase("Credit Card"))
                {
                    holder.payment_type.setText(item.getCardtranscation().getCardType());
                }else {
                    holder.payment_type.setText(item.getTenders().getLabel());
                }
                    holder.price.setText("$ "+item.getAmount());



            }
        }
        return v;
    }
}
 