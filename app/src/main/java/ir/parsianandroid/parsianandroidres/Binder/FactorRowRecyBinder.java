package ir.parsianandroid.parsianandroidres.Binder;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.List;

import ir.parsianandroid.parsianandroidres.Global.GlobalUtils;
import ir.parsianandroid.parsianandroidres.Interface.DeletageRecyBinderButtonClick;
import ir.parsianandroid.parsianandroidres.Models.FactorRow;
import ir.parsianandroid.parsianandroidres.R;
public class FactorRowRecyBinder extends  RecyclerView.Adapter<FactorRowRecyBinder.ItemViewHolder>{
    public static class ItemViewHolder extends RecyclerView.ViewHolder
    {
        CardView cv;
        TextView txt_title;
        TextView txt_count;
        TextView txt_price;
        TextView txt_fi;
        Button btn_inc;
        Button btn_dec;
        public RelativeLayout viewBackground, viewForeground;
        ItemViewHolder(final View itemView)
        {
            super(itemView);
            cv = itemView.findViewById(R.id.rlsg_card);
            cv.setUseCompatPadding(true);
            txt_title =  itemView.findViewById(R.id.txt_title);
            txt_count =  itemView.findViewById(R.id.txt_count);
            txt_fi =  itemView.findViewById(R.id.txt_fi);
            txt_price=itemView.findViewById(R.id.txt_price);
            btn_inc=itemView.findViewById(R.id.btn_inc);
            btn_dec=itemView.findViewById(R.id.btn_dec);
            viewBackground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.view_foreground);
            btn_dec.setTag(itemView);
            btn_inc.setTag(itemView);
        }
    }
    // NOTE: Make accessible with short name

    public List<FactorRow> Items;
    public     List<FactorRow> OrginalItems;
    Context vContext;
    private DeletageRecyBinderButtonClick ButtonClick;

    int CategoryCode;
    public FactorRowRecyBinder() {
        // TODO Auto-generated constructor stub
    }
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlistfactorrow, parent, false);
        FactorRowRecyBinder.ItemViewHolder pvh = new FactorRowRecyBinder.ItemViewHolder(v);
        return pvh;
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        View vi=holder.itemView;
        /* if(position%2==0)
                holder.cv.setBackgroundDrawable(vi.getResources().getDrawable(R.drawable.lists_row_background1));
            else
                holder.cv.setBackgroundDrawable(vi.getResources().getDrawable(R.drawable.lists_row_background2));
        if (position % 2 == 0)
            vi.setBackgroundDrawable(vi.getResources().getDrawable(R.drawable.lists_row_background1));
        else
            vi.setBackgroundDrawable(vi.getResources().getDrawable(R.drawable.lists_row_background2));
        */
        holder.txt_title.setText(Items.get(position).getLabel()+(!Items.get(position).getComment().equals("")?"-"+Items.get(position).getComment():""));
        holder.txt_count.setText(GlobalUtils.getCurrecncy(Items.get(position).getCount()));
        holder.txt_price.setText(GlobalUtils.getCurrecncy(Items.get(position).getPrice()));
        holder.btn_inc.setOnClickListener(v -> ButtonClick.onRecyButtonClick(1,1,position));
        holder.btn_dec.setOnClickListener(v -> ButtonClick.onRecyButtonClick(2,-1,position));
        holder.txt_fi.setText(GlobalUtils.getCurrecncy(Items.get(position).getCount()*Items.get(position).getPrice()));
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonClick.onRecyButtonClick(4,-1,position);
            }
        });
        //holder.swipeLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT) );

       /* frontLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String displayText = "" + data + " clicked";
                MyToast.makeText(mContext, displayText, Toast.LENGTH_SHORT).show();
            }
        });*/

    }
    @Override
    public int getItemCount() {
        return Items.size();
    }
    public FactorRowRecyBinder(Activity act, List<FactorRow> map,DeletageRecyBinderButtonClick delegate)
    {
        vContext=act;
        this.Items = map;
        this.OrginalItems=map;
        ButtonClick=delegate;
    }
    public void removeItem(int position) {
        Items.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(FactorRow item, int position) {
        Items.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }
}



