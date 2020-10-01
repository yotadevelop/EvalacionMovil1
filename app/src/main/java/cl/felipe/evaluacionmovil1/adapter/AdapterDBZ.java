package cl.felipe.evaluacionmovil1.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import cl.felipe.evaluacionmovil1.R;
import cl.felipe.evaluacionmovil1.model.DBZ;

public class AdapterDBZ extends RecyclerView.Adapter<AdapterDBZ.DBZHolder> {

    public Activity activity;
    public List<DBZ> list;
    public int item_dbz;

    public AdapterDBZ(Activity activity, List<DBZ> list, int item_dbz) {
        this.activity = activity;
        this.list = list;
        this.item_dbz = item_dbz;
    }

    @NonNull
    @Override
    public DBZHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(item_dbz,parent,false);

        return new DBZHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DBZHolder holder, int position) {
        DBZ dbz = list.get(position);
        holder.item_name.setText(dbz.name);
        holder.item_status.setText(dbz.status);
        holder.item_species.setText(dbz.species);
        holder.item_originPlanet.setText(dbz.originPlanet);
        Glide.with(activity).load(dbz.image).into(holder.item_img);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DBZHolder extends RecyclerView.ViewHolder{

        ImageView item_img;
        TextView item_species, item_status, item_originPlanet, item_name;

        public DBZHolder(@NonNull View itemView) {
            super(itemView);
            item_img = itemView.findViewById(R.id.item_img);
            item_species = itemView.findViewById(R.id.item_species);
            item_status = itemView.findViewById(R.id.item_status);
            item_originPlanet = itemView.findViewById(R.id.item_originPlanet);
            item_name = itemView.findViewById(R.id.item_name);
        }
    }


}
