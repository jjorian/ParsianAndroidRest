package ir.parsianandroid.parsianandroidres.Binder;
import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ir.parsianandroid.parsianandroidres.Global.Constants;
import ir.parsianandroid.parsianandroidres.R;
import ir.parsianandroid.parsianandroidres.db.entity.TTables;
public class TablesRecyBinder extends  RecyclerView.Adapter<TablesRecyBinder.ItemViewHolder>{
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView txt_title;
        TextView txt_code;
        ImageView img_status;
        ItemViewHolder(final View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.rlsg_card);
            cv.setUseCompatPadding(true);
            txt_title = (TextView) itemView.findViewById(R.id.txt_title); // city name
            txt_code= (TextView) itemView.findViewById(R.id.txt_code); // city name
            img_status =  itemView.findViewById(R.id.img_status); // city name
        }
    }
    List<TTables> Items;
    List<TTables> OrginalItems;
    Context vContext;
    public TablesRecyBinder() {
        // TODO Auto-generated constructor stub
    }
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlisttables, parent, false);
        TablesRecyBinder.ItemViewHolder pvh = new TablesRecyBinder.ItemViewHolder(v);
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
        holder.txt_title.setText(Items.get(position).getTitle());
        holder.txt_code.setText(Items.get(position).getCode()+"");
        if(Items.get(position).getStatus()== Constants.Status_Table_Empty)
        {
            //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //     holder.img_status.setImageDrawable(vContext.getDrawable(R.drawable.img_empty));
            //   }
            holder.img_status.setImageDrawable(vContext.getResources().getDrawable(R.drawable.img_empty));

        }
        else if(Items.get(position).getStatus()== Constants.Status_Table_Fill)
        {
            //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //  holder.img_status.setImageDrawable(vContext.getDrawable(R.drawable.img_fill));
            //}
            holder.img_status.setImageDrawable(vContext.getResources().getDrawable(R.drawable.img_full));
        }
        else if(Items.get(position).getStatus()== Constants.Status_Table_Reserve)
        {
            //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //  holder.img_status.setImageDrawable(vContext.getDrawable(R.drawable.img_reserved));
            //}
            holder.img_status.setImageDrawable(vContext.getResources().getDrawable(R.drawable.img_reserved));
        }
    }
    @Override
    public int getItemCount() {
        return Items.size();
    }

    public TablesRecyBinder(Activity act, List<TTables> map) {
        vContext=act;
        this.Items = map;
        this.OrginalItems=map;
    }
}
