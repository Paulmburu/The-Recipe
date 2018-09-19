package com.app.sample.recipe.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.sample.recipe.R;
import com.balysv.materialripple.MaterialRippleLayout;
import com.squareup.picasso.Picasso;

/**
 * Created by user on 9/13/2018.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    View v;

    public TextView names;
    public TextView recipe;
    public ImageView image;
    public ImageView icons;
    public MaterialRippleLayout lyt_parent;



    public ViewHolder(View itemView) {
        super(itemView);
        v = itemView;
    }

    public void setDetails(Context ctx, String name, String photo, String icon, Integer recipes){
        names = (TextView) v.findViewById(R.id.name);
        recipe = (TextView) v.findViewById(R.id.recipes);
        image = (ImageView) v.findViewById(R.id.image);
        icons = (ImageView) v.findViewById(R.id.icon);
        lyt_parent = (MaterialRippleLayout) v.findViewById(R.id.lyt_parent);

        names.setText(name);
        recipe.setText(recipes);
        Picasso.with(ctx).load(photo).into(image);
        Picasso.with(ctx).load(icon).into(icons);


    }
}
