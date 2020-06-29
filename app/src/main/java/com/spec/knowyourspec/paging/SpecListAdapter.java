package com.spec.knowyourspec.paging;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.spec.knowyourspec.R;
import com.spec.knowyourspec.data.Spec;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class SpecListAdapter extends PagedListAdapter<Spec, SpecListAdapter.SpecListViewHolder> {

    public SpecListAdapter(){
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public SpecListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spec, parent, false);
        return new SpecListViewHolder(constraintLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecListViewHolder holder, int position) {
        Spec spec = getItem(position);
        if(spec != null){
            holder.bindTo(spec);
        }
    }

    public class SpecListViewHolder extends RecyclerView.ViewHolder{
        ImageView specImageView;
        TextView firstNameTextView;
        TextView lastNameTextView;

        public SpecListViewHolder(@NonNull View itemView) {
            super(itemView);
            specImageView = itemView.findViewById(R.id.spec_image_view);
            firstNameTextView = itemView.findViewById(R.id.spec_first_name);
            lastNameTextView = itemView.findViewById(R.id.spec_last_name);
        }

        public void bindTo(Spec spec){
            firstNameTextView.setText(spec.getFirstName());
            lastNameTextView.setText(spec.getLastName());
        }
    }

    private static DiffUtil.ItemCallback<Spec> DIFF_CALLBACK = new DiffUtil.ItemCallback<Spec>() {
        @Override
        public boolean areItemsTheSame(@NonNull Spec oldItem, @NonNull Spec newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Spec oldItem, @NonNull Spec newItem) {
            return oldItem.getId() == newItem.getId()
                    && oldItem.getFirstName().equals(newItem.getFirstName())
                    && oldItem.getLastName().equals(newItem.getLastName())
                    && oldItem.getAlias().equals(newItem.getAlias())
                    && oldItem.getEmail().equals(newItem.getEmail())
                    && oldItem.getBestSpec().equals(newItem.getBestSpec())
                    && oldItem.getFavoriteLecturer().equals(newItem.getFavoriteLecturer())
                    && oldItem.getAlternateCareer().equals(newItem.getAlternateCareer())
                    && oldItem.getLikelySpecialty().equals(newItem.getLikelySpecialty())
                    && oldItem.getFavoriteQuote().equals(newItem.getFavoriteQuote())
                    ;
        }
    };
}
