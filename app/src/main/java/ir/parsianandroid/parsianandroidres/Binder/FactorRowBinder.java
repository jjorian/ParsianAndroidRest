package ir.parsianandroid.parsianandroidres.Binder;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ir.parsianandroid.parsianandroidres.Global.GlobalUtils;
import ir.parsianandroid.parsianandroidres.Interface.DeletageRecyBinderButtonClick;
import ir.parsianandroid.parsianandroidres.Models.FactorRow;
import ir.parsianandroid.parsianandroidres.R;

public class FactorRowBinder extends BaseAdapter{
	private DeletageRecyBinderButtonClick ButtonClick;
	LayoutInflater inflater;
	ImageView thumb_image;
	List<FactorRow> Items;
	List<FactorRow> OrginalItems;
	ViewHolder holder;
	public FactorRowBinder() {
		// TODO Auto-generated constructor stub
	}
	public FactorRowBinder(Activity act, List<FactorRow> map,DeletageRecyBinderButtonClick delegate) {
		this.Items = map;
		this.OrginalItems=map;
		ButtonClick=delegate;
		inflater = (LayoutInflater) act
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	public int getCount() {
		// TODO Auto-generated method stub
		//		return idlist.size();
		return Items.size();
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
			vi = inflater.inflate(R.layout.rowlistfactorrow, null);
			holder = new ViewHolder();
			holder.cv = vi.findViewById(R.id.rlsg_card);
			holder.cv.setUseCompatPadding(true);
			holder.txt_title =  vi.findViewById(R.id.txt_title);
			holder.txt_count =  vi.findViewById(R.id.txt_count);
			holder.txt_fi =  vi.findViewById(R.id.txt_fi);
			holder.txt_price=vi.findViewById(R.id.txt_price);
			holder.btn_inc=vi.findViewById(R.id.btn_inc);
			holder.btn_dec=vi.findViewById(R.id.btn_dec);

			holder.btn_dec.setTag(vi);
			holder.btn_inc.setTag(vi);
			holder.cv.setTag(vi);
			vi.setTag(holder);
		}
		else
		{
			holder = (ViewHolder)vi.getTag();
		}	     
		/*if(position%2==0)
			vi.setBackgroundDrawable(vi.getResources().getDrawable(R.drawable.lists_row_background1));
		else
			vi.setBackgroundDrawable(vi.getResources().getDrawable(R.drawable.lists_row_background2));*/
		holder.txt_title.setText(Items.get(position).getLabel()+(!Items.get(position).getComment().equals("")?"-"+Items.get(position).getComment():""));
        holder.txt_count.setText(GlobalUtils.getCurrecncy(Items.get(position).getCount()));
		holder.txt_price.setText(GlobalUtils.getCurrecncy(Items.get(position).getPrice()));
		holder.btn_inc.setOnClickListener(v -> ButtonClick.onRecyButtonClick(1,1,position));
		holder.btn_dec.setOnClickListener(v -> ButtonClick.onRecyButtonClick(2,-1,position));
		holder.cv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ButtonClick.onRecyButtonClick(4,-1,position);
			}
		});

		holder.txt_fi.setText(GlobalUtils.getCurrecncy(Items.get(position).getCount()*Items.get(position).getPrice()));

		return vi;
	}

	/*
	 * 
	 * */
	static class ViewHolder{

		CardView cv;
		TextView txt_title;
		TextView txt_count;
		TextView txt_price;
		TextView txt_fi;
		Button btn_inc;
		Button btn_dec;

	}
	public void resetData() {
		Items = OrginalItems;
	}


}



