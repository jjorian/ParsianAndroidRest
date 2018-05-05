package ir.parsianandroid.parsianandroidres.Binder;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ir.parsianandroid.parsianandroidres.AppSettings;
import ir.parsianandroid.parsianandroidres.Global.GlobalUtils;
import ir.parsianandroid.parsianandroidres.MainActivity;
import ir.parsianandroid.parsianandroidres.Models.ProductCategory;
import ir.parsianandroid.parsianandroidres.R;
import ir.parsianandroid.parsianandroidres.db.AppDatabase;
import ir.parsianandroid.parsianandroidres.db.entity.TCategory;
import ir.parsianandroid.parsianandroidres.db.entity.TProducts;


public class ProductRecyBinder extends  RecyclerView.Adapter<ProductRecyBinder.ItemViewHolder>{




    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView txt_title;
        TextView txt_price;


        ItemViewHolder(final View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.rlsg_card);
            cv.setUseCompatPadding(true);
            txt_title =  itemView.findViewById(R.id.txt_title); // city name
            txt_price =  itemView.findViewById(R.id.txt_price); // city name

        }
    }

    List<ProductCategory> Items;
    List<ProductCategory> OrginalItems;
    Context vContext;
    AppSettings app;
    public ProductRecyBinder() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlistproduct, parent, false);
        ProductRecyBinder.ItemViewHolder pvh = new ProductRecyBinder.ItemViewHolder(v);
        return pvh;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        View vi=holder.itemView;


        /*
        if(position%2==0)
                holder.cv.setBackgroundDrawable(vi.getResources().getDrawable(R.drawable.lists_row_background1));
            else
                holder.cv.setBackgroundDrawable(vi.getResources().getDrawable(R.drawable.lists_row_background2));
        if (position % 2 == 0)
            vi.setBackgroundDrawable(vi.getResources().getDrawable(R.drawable.lists_row_background1));
        else
            vi.setBackgroundDrawable(vi.getResources().getDrawable(R.drawable.lists_row_background2));
        */
           holder.txt_title.setText(Items.get(position).getTitle());
           int index=app.GetDefaultPrice();
           switch ( index)
           {
               case 1:
                   holder.txt_price.setText(GlobalUtils.getCurrecncy(Items.get(position).getPrice_1()));
                   break;
               case 2:
                   holder.txt_price.setText(GlobalUtils.getCurrecncy(Items.get(position).getPrice_2()));
                   break;
               case 3:
                   holder.txt_price.setText(GlobalUtils.getCurrecncy(Items.get(position).getPrice_3()));
                   break;
               case 4:
                   holder.txt_price.setText(GlobalUtils.getCurrecncy(Items.get(position).getPrice_4()));
                   break;
               case 5:
                   holder.txt_price.setText(GlobalUtils.getCurrecncy(Items.get(position).getPrice_5()));
                   break;
               default:
                   holder.txt_price.setText(GlobalUtils.getCurrecncy(Items.get(position).getPrice_1()));
                   break;

           }


    }

    @Override
    public int getItemCount() {
        return Items.size();
    }

    public ProductRecyBinder(Activity act, List<ProductCategory> map) {
        vContext=act;
        this.Items = map;
        this.OrginalItems=map;
        app=new AppSettings(vContext);





    }







}



