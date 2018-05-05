package ir.parsianandroid.parsianandroidres.Binder;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ir.parsianandroid.parsianandroidres.Models.DrawerItems;
import ir.parsianandroid.parsianandroidres.R;

/**
 * Created by Behzad on 28/10/2017.
 */

public class DrawerAdapter extends BaseAdapter {


    LayoutInflater inflater;
    List<DrawerItems> items;
    List<DrawerItems> orginalitems;
    ViewHolder holder;


    public DrawerAdapter() {
        // TODO Auto-generated constructor stub
    }

    public DrawerAdapter(Activity act, List<DrawerItems> list) {

        this.items = list;
        this.orginalitems=list;

        inflater = (LayoutInflater) act
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public int getCount() {
        // TODO Auto-generated method stub
        //		return idlist.size();
        return items.size();
    }


    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @SuppressWarnings("deprecation")
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null){
            vi = inflater.inflate(R.layout.rowlistdrawer, null);
            holder = new ViewHolder();
            holder.txt_title = (TextView)vi.findViewById(R.id.rldm_txt_title); // city name
            holder.img_icon = (ImageView)vi.findViewById(R.id.rldm_imageview_icon); // city name

            vi.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)vi.getTag();
        }

        /*if(position%2==0)
            vi.setBackgroundColor(vi.getResources().getColor(R.color.transparent));
        else
            vi.setBackgroundDrawable(vi.getResources().getDrawable(R.drawable.lists_row_background2));*/

        if(items.get(position).getIconID()!=0)
        {
            holder.img_icon.setImageResource(items.get(position).getIconID());
        }
        holder.txt_title.setText(items.get(position).getTitle());
        holder.img_icon.setColorFilter(vi.getResources().getColor(R.color.black));
        return vi;
    }

    /*
     *
     * */
    static class ViewHolder{
        ImageView img_icon;
        TextView txt_title;

    }
    public void resetData() {
        items = orginalitems;
    }





}
