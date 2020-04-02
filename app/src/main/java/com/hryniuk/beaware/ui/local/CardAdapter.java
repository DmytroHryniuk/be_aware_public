package com.hryniuk.beaware.ui.local;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hryniuk.beaware.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.WorldHolder> implements Filterable {

    private Context context;
    private List<World> worlds;
    private List<World> planetsFull;
    public CardAdapter(Context context, List<World> mWorlds) {
        this.context = context;
        this.worlds = mWorlds;
        planetsFull = new ArrayList<>(mWorlds);

    }

    @NonNull
    @Override
    public WorldHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false);
        return new WorldHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorldHolder holder, int position) {
        World world = worlds.get(position);
        holder.setDetails(world);
    }

    @Override
    public int getItemCount() {return worlds.size();}

    @Override
    public Filter getFilter() {return exampleFilter;}

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<World> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                if (planetsFull.isEmpty()){
                    Toast.makeText(context, "EMPTY", Toast.LENGTH_SHORT).show();
                }
                filteredList.addAll(planetsFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (World item : planetsFull) {
                    if (item.getCountryother().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            worlds.clear();
            worlds.addAll((List) results.values);
            notifyDataSetChanged();
        }

    };



    class WorldHolder extends RecyclerView.ViewHolder {

        private TextView txtCountry, txtTotalCases, txtNewCases, txtTotalDeaths,
                txtNewDeaths, txtTotalRecovered, txtActiveCases;

        WorldHolder(View itemView) {
            super(itemView);
            txtCountry = itemView.findViewById(R.id.txtCoutry);
            txtTotalCases = itemView.findViewById(R.id.txtTotalCases);
            //txtNewCases = itemView.findViewById(R.id.txtNewCases);
            txtTotalDeaths = itemView.findViewById(R.id.txtTotalDeaths);
            //txtNewDeaths = itemView.findViewById(R.id.txtNewDeaths);
            txtTotalRecovered = itemView.findViewById(R.id.txtTotalRecovered);
            //txtActiveCases = itemView.findViewById(R.id.txtActiveCases);
        }

        void setDetails(World world) {
            txtCountry.setText(world.getCountryother());
            txtTotalCases.setText(String.format(Locale.US, "Total Cases: %s", world.getTotalcases()));
            //txtNewCases.setText(String.format(Locale.US, "New Cases : %s", world.getNewCases()));
            txtTotalDeaths.setText(String.format(Locale.US, "Total Fatal: %s", world.getTotaldeaths()));
            //txtNewDeaths.setText(String.format(Locale.US, "New Deaths : %s", world.getNewDeaths()));
            txtTotalRecovered.setText(String.format(Locale.US, "Total Recovered: %s", world.getTotalrecovered()));
            //txtActiveCases.setText(String.format(Locale.US, "Active Cases : %s", world.getActiverCases()));

        }
    }
}
