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

import ir.parsianandroid.parsianandroidres.R;
import ir.parsianandroid.parsianandroidres.db.entity.TCategory;
import ir.parsianandroid.parsianandroidres.db.entity.TComments;


public class CommentRecyBinder extends  RecyclerView.Adapter<CommentRecyBinder.ItemViewHolder>{



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

    List<TComments> Items;
    List<TComments> OrginalItems;
    Context vContext;

    public CommentRecyBinder() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlistcomments, parent, false);
        CommentRecyBinder.ItemViewHolder pvh = new CommentRecyBinder.ItemViewHolder(v);
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
           holder.txt_title.setText(Items.get(position).getComment());




    }

    @Override
    public int getItemCount() {
        return Items.size();
    }

    public CommentRecyBinder(Activity act, List<TComments> map) {
        vContext=act;
        this.Items = map;
        this.OrginalItems=map;




    }







}



