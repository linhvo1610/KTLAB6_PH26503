package com.example.ktlab6_ph26503.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ktlab6_ph26503.DAO.DAO_SP;
import com.example.ktlab6_ph26503.DTO.SP;
import com.example.ktlab6_ph26503.R;
import com.example.ktlab6_ph26503.ViewHolder.ViewHolder_SP;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class Adapter_SP extends RecyclerView.Adapter<ViewHolder_SP> {
    int cyearup,cmonthup,cdayup;

    ArrayList<SP> listSP;
    DAO_SP dao_sp;
    Context context;

    public Adapter_SP( ArrayList<SP> listSP, DAO_SP dao_sp) {
        this.listSP = listSP;
        this.dao_sp = dao_sp;
    }

    @NonNull
    @Override
    public ViewHolder_SP onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view_of_item= inflater.inflate(R.layout.item_sp,parent,false);
        ViewHolder_SP toDoViewHolder=new ViewHolder_SP(view_of_item);

        return toDoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_SP holder, int position) {
        final int index = position;
        SP list = listSP.get(index);
        holder.tv_sp_content.setText("Tên Dịch Vụ: "+list.getContent());
        holder.tv_sp_date.setText("Ngày: "+list.getDate());
        holder.tv_sp_price.setText("Giá: "+String.valueOf(list.getPrice()));


        holder.imgUpdateSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogUpdate(list, index);
            }
        });
        holder.imgDeleteSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDelete(list, index);
            }
        });

    }



    private TextInputLayout inputnameUPSP;
    private TextInputLayout inputpriceUPSP;
    private TextInputLayout inputdateUPSP;
    private MaterialButton btnUPSP;

    public void dialogUpdate(SP sp, int index) {
        Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_update_sp);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);

        inputnameUPSP = dialog.findViewById(R.id.input_sp_UPSP);
        inputpriceUPSP = dialog.findViewById(R.id.input_price_UPSP);
        inputdateUPSP = dialog.findViewById(R.id.input_dateUPSP);

        btnUPSP = dialog.findViewById(R.id.btn_UPSP);

        inputnameUPSP.getEditText().setText(sp.getContent());
        inputpriceUPSP.getEditText().setText(sp.getPrice() + "");
        inputdateUPSP.getEditText().setText(sp.getDate());
        btnUPSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkErrorUp()== true) {
                    sp.setContent(inputnameUPSP.getEditText().getText().toString().trim());
                    sp.setPrice(Integer.parseInt(inputpriceUPSP.getEditText().getText().toString().trim()));
                    sp.setDate(inputdateUPSP.getEditText().getText().toString().trim());

                    if (dao_sp.updateSP(sp) > 0) {
                        listSP.set(index, sp);
                        notifyItemChanged(index);
                        Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        dialog.show();
        }

    public void dialogDelete(SP sp, int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("delete");
        builder.setMessage("Bạn có muốn xóa dịch vụ: " + sp.getContent());
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int res = dao_sp.deleteSP(sp);
                if (res > 0) {
                    listSP.remove(index);
                    notifyItemRemoved(index);
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public boolean checkErrorUp() {
        if (inputnameUPSP.getEditText().getText().toString().trim().isEmpty()||
                inputpriceUPSP.getEditText().getText().toString().trim().isEmpty()
                ||inputdateUPSP.getEditText().getText().toString().trim().isEmpty()
              ) {
            if (inputnameUPSP.getEditText().getText().toString().trim().isEmpty()) {
                inputnameUPSP.setError("Tên SP không được để trống");
            } else {
                inputnameUPSP.setError("");
            }if(inputpriceUPSP.getEditText().getText().toString().trim().isEmpty()){
                inputpriceUPSP.setError("Số tiền phòng không được để trống!");
            }else{
                inputpriceUPSP.setError("");
            }if(inputdateUPSP.getEditText().getText().toString().trim().isEmpty()){
                inputdateUPSP.setError("Số nước sử dụng trong tháng không được để trống!");
            }else{
                inputdateUPSP.setError("");
            }
            if(!inputpriceUPSP.getEditText().getText().toString().matches("[0-9]+")){
                inputpriceUPSP.setError("Giá tiền phải là số");
            }else{
                inputpriceUPSP.setError("");
            }

            return false;
        } else {
            return true;
        }
    }
    private boolean checksp(){
        for (SP sp: listSP) {
            if (inputnameUPSP.getEditText().getText().toString().equals(sp.getContent()) ) {
                Toast.makeText(inputnameUPSP.getContext(), "Dịch Vụ đã tồn tại, vui lòng kiểm tra lại.", Toast.LENGTH_SHORT).show();
                return false;
            }

        }
        return true;
    }

    @Override
    public int getItemCount() {
        return listSP == null ? 0 : listSP.size();
    }
    private TextView tv_month_details;
    private TextView tv_id_room;
    private TextView tv_room_number_detailsROOM;
    private TextView tv_tenant_name_detailsROOM;
    private TextView tv_room_price_detailsROOM;
    private TextView tv_waterbill_detailsROOM;
    private TextView tv_electricbill_detailsROOM;
    private TextView tv_servicebill_detailsROOM;
    private TextView tv_waterbilltotal_ROOM;
    private TextView tv_electricbilltotal_ROOM;
    private TextView tv_moneybilltotal_ROOM;


//    public void dialogroomdetails(Room obj) {
//        Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
//        dialog.setContentView(R.layout.layout_details_room);
//        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);
//        tv_month_details=dialog.findViewById(R.id.tv_month_details);
//        tv_id_room = dialog.findViewById(R.id.tv_id_room);
//        tv_room_number_detailsROOM = dialog.findViewById(R.id.tv_room_number_detailsROOM);
//        tv_tenant_name_detailsROOM = dialog.findViewById(R.id.tv_tenant_detailsROOM);
//        tv_room_price_detailsROOM = dialog.findViewById(R.id.tv_roomprice_detailsROOM);
//        tv_waterbill_detailsROOM = dialog.findViewById(R.id.tv_waterbillROOM);
//        tv_electricbill_detailsROOM = dialog.findViewById(R.id.tv_electricbillROOM);
//        tv_waterbilltotal_ROOM=dialog.findViewById(R.id.tv_waterbilltotalROOM);
//        tv_electricbilltotal_ROOM=dialog.findViewById(R.id.tv_electricbilltotalROOM);
//        tv_servicebill_detailsROOM = dialog.findViewById(R.id.tv_servicebillROOM);
//        tv_moneybilltotal_ROOM=dialog.findViewById(R.id.tv_moneybilltotalROOM);
//        tv_month_details.setText("Tháng: "+obj.getMonth());
//        tv_id_room.setText("Mã số phòng: " + obj.getIdRoom());
//        tv_room_number_detailsROOM.setText("Số phòng: " + obj.getRoomNumber1());
//        tv_tenant_name_detailsROOM.setText("Người Thuê: " + obj.getIdTenant() + "|" + obj.getTenantName());
//        tv_room_price_detailsROOM.setText("Tiền phòng 1 tháng: " + obj.getRoomPrice1M()+" VND");
//        tv_waterbill_detailsROOM.setText("Số khối nước đã sử dụng: " + obj.getWaterBill1M()+" khối");
//        tv_electricbill_detailsROOM.setText("Số kWH điện đã sử dụng: " + obj.getElectricBill1M()+" số");
//        tv_waterbilltotal_ROOM.setText("Số tiền nước: "+(obj.getWaterBill1M()*20000)+" VND");
//        tv_electricbilltotal_ROOM.setText("Số tiền điện: "+(obj.getElectricBill1M()*3000)+" VND");
//        tv_servicebill_detailsROOM.setText("Tiền dịch vụ hàng tháng: " + obj.getServiceBill1M()+" VND");
//        double total=(obj.getRoomPrice1M()+(obj.getWaterBill1M()*20000)+(obj.getElectricBill1M()*3000)+obj.getServiceBill1M());
//        tv_moneybilltotal_ROOM.setText("Tổng tiền tháng này: "+String.valueOf(total) +" VND");
//        dialog.show();
//    }


}
