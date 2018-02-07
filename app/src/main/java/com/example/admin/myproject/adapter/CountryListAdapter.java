package com.example.admin.myproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.admin.myproject.R;
import com.example.admin.myproject.api.models.Country;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2/6/2018.
 */

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryHolder> {

    private Context context;
    private ArrayList<Country> countries;
    private LayoutInflater layoutInflater;
    private ArrayList<Country> selectedCountries;

    public CountryListAdapter(Context context, ArrayList<Country> countries) {
        this.context = context;
        this.countries = countries;
        layoutInflater = LayoutInflater.from(context);
        selectedCountries = new ArrayList<>();
    }

    @Override
    public CountryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CountryHolder(layoutInflater.inflate(R.layout.rv_item_country, parent, false));
    }

    @Override
    public void onBindViewHolder(CountryHolder holder, int position) {
        holder.setData(countries.get(position));
    }

    public ArrayList<Country> getCheckedCities() {
        return selectedCountries;
    }

    @Override
    public int getItemCount() {
        return countries == null ? 0 : countries.size();
    }

    public class CountryHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chbCountryName)
        CheckBox chbCountryName;

        public CountryHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(final Country country) {
            chbCountryName.setText(country.getName());
            chbCountryName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    chbCountryName = (CheckBox) v;
                    if (chbCountryName.isChecked()) {
                        if (selectedCountries.size() >= 5) {
                            chbCountryName.setChecked(false);
                        } else {
                            selectedCountries.add(country);
                        }
                    } else {
                        selectedCountries.remove(country);
                    }
                }
            });
        }
    }
}
