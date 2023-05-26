package com.example.manthilan_qly_vattu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText edtMamt,tenmt,loaimt,namsx,hangsx,dongia,soluong,edttimkiem;
    private Button btnThem, btnSua, btnXoa, btnTk, btnThoat;
    private ListView lvvt;

    // CSDL
    private manlan_VTdatabase database;
    private manlan_VTadapter adapter;
    private List<manlan_VatTu> listHP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        // khởi tạo
        database=new manlan_VTdatabase(this);
        listHP=database.InformationVT();
        adapter=new manlan_VTadapter(this,R.layout.item_vt,listHP);
        lvvt.setAdapter(adapter);
//        setAdapter();
        // bắt sự kiện cho các button

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtMamt.getText().toString().equals("")||tenmt.getText().toString().equals("")
                        ||loaimt.getText().toString().equals("")||namsx.getText().toString().equals("")
                        ||hangsx.getText().toString().equals("")||dongia.getText().toString().equals("")||soluong.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Thông tin không được để trống !!!", Toast.LENGTH_LONG).show();
                }else {
                    manlan_VatTu vt = CreateVT();
                    if(vt!=null){
                        database.AddVT(vt);
                        listHP.clear();
                        listHP.addAll(database.InformationVT());
                        // setAdapter();
                        adapter.notifyDataSetChanged();
                        //Xóa DL nhập vào
                        edtMamt.setText("");
                        tenmt.setText("");
                        loaimt.setText("");
                        soluong.setText("");
                        dongia.setText("");
                        hangsx.setText("");
                        namsx.setText("");
                    }
                }
            }
        });

        // bắt sự kiện cho từng Item trong ListView
        lvvt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                manlan_VatTu vt  = listHP.get(position);
                edtMamt.setText(vt.getMamt());
                tenmt.setText(vt.getTenmt());
                loaimt.setText(vt.getLoaimt());
                soluong.setText(String.valueOf(vt.getSoluong()));
                dongia.setText(String.valueOf(vt.getDongia()));
                hangsx.setText(vt.getHangsx());
                namsx.setText(String.valueOf(vt.getNamsx()));
            }
        });
        //btnSua
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // edtMaHp.setEnabled(false);
                manlan_VatTu vt = new manlan_VatTu();
                vt.setMamt(String.valueOf(edtMamt.getText()));
                vt.setTenmt(tenmt.getText()+" ");
                vt.setLoaimt(loaimt.getText()+"");
                vt.setNamsx(Integer.parseInt(namsx.getText()+""));
                vt.setHangsx(hangsx.getText()+"");
                vt.setDongia(Integer.parseInt(dongia.getText()+""));
                vt.setSoluong(Integer.parseInt(soluong.getText()+""));
                int kq=database.UpdateVT(vt);
                if(kq>0){
                    listHP.clear();
                    listHP.addAll(database.InformationVT());
                    if(adapter!=null) {
                        adapter.notifyDataSetChanged();
                    }
                }
                //Xóa DL nhập vào
                edtMamt.setText("");
                tenmt.setText("");
                loaimt.setText("");
                soluong.setText("");
                dongia.setText("");
                hangsx.setText("");
                namsx.setText("");

            }
        });

        //btnXoa
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Xác nhận xoa khong !!!");
                builder.setMessage("Bạn có muốn xoa không ???");
                builder.setPositiveButton("Có ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //hien thi
                        manlan_VatTu vt = new manlan_VatTu();
                        vt.setMamt(String.valueOf(edtMamt.getText()));
                        database.DeleteVT(vt.getMamt());
                        Toast.makeText(MainActivity.this,"Xóa MT thành công", Toast.LENGTH_LONG).show();
                        listHP.clear();
                        listHP.addAll(database.InformationVT());
                        adapter.notifyDataSetChanged();
                        edtMamt.setText("");
                        tenmt.setText("");
                        loaimt.setText("");
                        soluong.setText("");
                        dongia.setText("");
                        hangsx.setText("");
                        namsx.setText("");
                    }
                });
                builder.setNegativeButton("Không ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog d = builder.create();
                d.show();

            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Xác nhận Thoát chương trình !!!");
                builder.setMessage("Bạn có muốn Thoát chương trình không ???");
                builder.setPositiveButton("Có ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                builder.setNegativeButton("Không ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                //hien thi
                AlertDialog d = builder.create();
                d.show();
            }
        });

        btnTk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edttimkiem.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"Nhập Mã MT cần tìm kiếm!!!",Toast.LENGTH_LONG).show();
                }
                else{
                    manlan_VatTu vt = database.timkiemVT(edttimkiem.getText().toString());
                    if(vt !=null){
                        listHP.clear();
                        listHP.add(vt);
                        setAdapter();
                    }
                    else
                        Toast.makeText(MainActivity.this, "Không tìm thấy !!!", Toast.LENGTH_LONG).show();
                }
            }
        });
        // gán menu_context vào listview
        registerForContextMenu(lvvt);
    }
    private manlan_VatTu CreateVT() {
        String Mavt =edtMamt.getText().toString();
        String Tenvt =tenmt.getText().toString();
        String loai =loaimt.getText().toString();
        String Namsx =namsx.getText().toString();
        String Hangsx =hangsx.getText().toString();
        String Dongia = dongia.getText().toString();
        String Soluong = soluong.getText().toString();
        manlan_VatTu vt =new manlan_VatTu(Mavt, Tenvt, loai,Hangsx,Integer.parseInt(Namsx), Integer.parseInt(Dongia),Integer.parseInt(Soluong));
        return vt;
    }

    private void setAdapter() {
        if(adapter==null){
            // tạo mới
            adapter=new manlan_VTadapter(this,R.layout.item_vt,listHP);
            lvvt.setAdapter(adapter);
        }else {
            // cập nhật lại DL
            adapter.notifyDataSetChanged();
        }
    }


    private void anhxa() {
        edtMamt = findViewById(R.id.edtMaMT);
        tenmt = findViewById(R.id.edtTenMT);
        hangsx = findViewById(R.id.edtHangSx);
        soluong = findViewById(R.id.edtSoLg);
        loaimt = findViewById(R.id.edtLoaiMT);
        namsx = findViewById(R.id.edtNamSx);
        dongia = findViewById(R.id.edtDonGia);
        edttimkiem=findViewById(R.id.edtTk);
        btnThem = findViewById(R.id.btnThem);
        btnSua = findViewById(R.id.btnSua);
        btnXoa = findViewById(R.id.btnXoa);
        btnThoat = findViewById(R.id.btnThoat);
        btnTk = findViewById(R.id.btnTk);
        lvvt = findViewById(R.id.lvVT);
    }
}