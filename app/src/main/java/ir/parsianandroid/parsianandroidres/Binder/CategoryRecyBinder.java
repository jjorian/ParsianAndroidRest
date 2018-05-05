package ir.parsianandroid.parsianandroidres.Binder;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ir.parsianandroid.parsianandroidres.R;
import ir.parsianandroid.parsianandroidres.db.entity.TCategory;


public class CategoryRecyBinder extends  RecyclerView.Adapter<CategoryRecyBinder.ItemViewHolder>{



    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView txt_title;


        ItemViewHolder(final View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.rlsg_card);
            cv.setUseCompatPadding(true);
            txt_title = (TextView) itemView.findViewById(R.id.txt_title); // city name


        }
    }

    List<TCategory> Items;
    List<TCategory> OrginalItems;
    Context vContext;

    public CategoryRecyBinder() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlistcategory, parent, false);
        CategoryRecyBinder.ItemViewHolder pvh = new CategoryRecyBinder.ItemViewHolder(v);
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




    }

    @Override
    public int getItemCount() {
        return Items.size();
    }

    public CategoryRecyBinder(Activity act, List<TCategory> map) {
        vContext=act;
        this.Items = map;
        this.OrginalItems=map;




    }







}



