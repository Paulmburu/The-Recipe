package com.app.sample.recipe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.sample.recipe.adapter.RecipeGridAdapter;
//import com.app.sample.recipe.data.Constant;
import com.app.sample.recipe.data.Tools;
import com.app.sample.recipe.model.Category;
import com.app.sample.recipe.model.Recipe;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

public class ActivityCategoryDetails extends AppCompatActivity {

    public static final String EXTRA_OBJCT = "com.app.sample.recipe.OBJ";

    // give preparation animation activity transition
    public static void navigate(AppCompatActivity activity, View transitionImage, Category obj) {
        Intent intent = new Intent(activity, ActivityCategoryDetails.class);
        intent.putExtra(EXTRA_OBJCT, obj);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, EXTRA_OBJCT);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    private RecyclerView recyclerView;
    private RecipeGridAdapter mAdapter;
    private Category category;
    private SearchView searchView;
    private View parent_view;

    private List<Category> list_items;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_details);
        parent_view = findViewById(android.R.id.content);

        // animation transition
        ViewCompat.setTransitionName(findViewById(R.id.app_bar_layout), EXTRA_OBJCT);

        category = (Category) getIntent().getSerializableExtra(EXTRA_OBJCT);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new GridLayoutManager(this, Tools.getGridSpanCount(this));
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        Category c = new Category();
        String photo = c.getPhoto();
        String icon = c.getIcon();
        Picasso.with(this).load(photo).into((ImageView) findViewById(R.id.image_toolbar));
        Picasso.with(this).load(icon).into((ImageView) findViewById(R.id.icon));

//        Bitmap bitmap = BitmapFactory.decodeFile(category.getPhoto());
//        ((ImageView) findViewById(R.id.image_toolbar)).setImageBitmap(bitmap);
//
//        Bitmap bitmap1 = BitmapFactory.decodeFile(category.getIcon());
//        ((ImageView) findViewById(R.id.icon)).setImageBitmap(bitmap1);

        ((TextView) findViewById(R.id.name)).setText(category.getName());
        ((TextView) findViewById(R.id.description)).setText(category.getDesc());
//
//        List<Recipe> recipes = new ArrayList<>();
//        String arr_category[] = getResources().getStringArray(R.array.category_names);
//        String name_category = category.getName();
//        if (name_category.equals(arr_category[0])) {
//            recipes = Constant.getItemAppertizeRandom(this);
//        } else if (name_category.equals(arr_category[1])) {
//            recipes = Constant.getItemMainDishRandom(this);
//        } else if (name_category.equals(arr_category[2])) {
//            recipes = Constant.getItemSaladsRandom(this);
//        } else if (name_category.equals(arr_category[3])) {
//            recipes = Constant.getItemDrinksRandom(this);
//        } else if (name_category.equals(arr_category[4])) {
//            recipes = Constant.getItemSideDishRandom(this);
//        } else if (name_category.equals(arr_category[5])) {
//            recipes = Constant.getItemDessertRandom(this);
//        }
//        mAdapter = new RecipeGridAdapter(this, recipes);
//        recyclerView.setAdapter(mAdapter);
//        mAdapter.setOnItemClickListener(new RecipeGridAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View v, Recipe obj, int position) {
//                ActivityRecipeDetails.navigate(ActivityCategoryDetails.this, v.findViewById(R.id.image), obj);
//            }
//        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }else{
            Snackbar.make(parent_view, item.getTitle() + " clicked", Snackbar.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_category_details, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                try {
                    mAdapter.getFilter().filter(s);
                } catch (Exception e) {
                }
                return true;
            }
        });
        // Detect SearchView icon clicks
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setItemsVisibility(menu, searchItem, false);
            }
        });

        // Detect SearchView close
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                setItemsVisibility(menu, searchItem, true);
                return false;
            }
        });
        searchView.onActionViewCollapsed();
        return true;
    }
    private void setItemsVisibility(Menu menu, MenuItem exception, boolean visible) {
        for (int i=0; i<menu.size(); ++i) {
            MenuItem item = menu.getItem(i);
            if (item != exception) item.setVisible(visible);
        }
    }
}