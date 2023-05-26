package com.example.manthilan_qly_vattu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class manlan_VTadapter extends ArrayAdapter {
    // khai báo các thành phần
    private Context context;
    private int resource;
    private List<manlan_VatTu> listVT;

    // constructor
    public manlan_VTadapter(Context context, int resource, List<manlan_VatTu> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.listVT=objects;
    }
    // hiển thị thông tin lên giao diện
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // khai báo ViewHolder
        ViewHolder viewHolder;
        // kiểm tra xem convertView xem có rỗng không?
        // nếu rỗng thì set hiển thị item_listSV lên
        // nếu không rỗng thì setTag nội dung lên
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_vt, parent,false);
            viewHolder=new ViewHolder();
            // ánh xạ
            viewHolder.tvma=(TextView)convertView.findViewById(R.id.tv_ma);
            viewHolder.tvten=(TextView)convertView.findViewById(R.id.tv_ten);
            viewHolder.tvloai=(TextView)convertView.findViewById(R.id.tv_loai);
            viewHolder.tvnamsx=(TextView)convertView.findViewById(R.id.tv_namsx);
            viewHolder.tvhangsx=(TextView)convertView.findViewById(R.id.tv_hangsx);
            viewHolder.tvdongia=(TextView)convertView.findViewById(R.id.tv_dongia);
            viewHolder.tvsoluong=(TextView)convertView.findViewById(R.id.tv_soluong);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        // thiết lập giá trị
        manlan_VatTu vt=listVT.get(position);
        viewHolder.tvma.setText(vt.getMamt());
        viewHolder.tvten.setText(vt.getTenmt());
        viewHolder.tvloai.setText(vt.getLoaimt());
        viewHolder.tvnamsx.setText(String.valueOf(vt.getNamsx()));
        viewHolder.tvhangsx.setText(vt.getHangsx());
        viewHolder.tvdongia.setText(String.valueOf(vt.getDongia()));
        viewHolder.tvsoluong.setText(String.valueOf(vt.getSoluong()));
        return convertView;
    }
    class ViewHolder{
        // khai báo các biến tương ứng với các thành phần trong giao diện item_listsv
        TextView tvma, tvten,tvloai,tvnamsx,tvhangsx,tvdongia,tvsoluong;

    }
}
