package com.galore.gadget.gadgetgalore;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.galore.gadget.gadgetgalore.Model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ProductDetail extends AppCompatActivity {

    TextView product_name,product_price,product_description;
    ImageView product_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;

    String prodcutId= "";

    FirebaseDatabase database;
    DatabaseReference products;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        //firebase

        database = FirebaseDatabase.getInstance();
        products = database.getReference("Products");

        //init view
        numberButton = (ElegantNumberButton)findViewById(R.id.number_button);
        btnCart = (FloatingActionButton)findViewById(R.id.btnCart);

        product_description = (TextView)findViewById(R.id.product_description);
        product_name = (TextView)findViewById(R.id.product_name);
        product_price = (TextView)findViewById(R.id.product_price);
        product_image = (ImageView)findViewById(R.id.img_product);

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExtendedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        //get product id form intent

        if(getIntent()!= null)
            prodcutId = getIntent().getStringExtra("ProductId");
        if(!prodcutId.isEmpty()){
            getDetailProduct(prodcutId);
        }

    }

    private void getDetailProduct(final String prodcutId) {
        products.child(prodcutId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Product product= dataSnapshot.getValue(Product.class);

                //set Image

                Picasso.with(getBaseContext()).load(product.getImage()).into(product_image);
                collapsingToolbarLayout.setTitle(product.getName());
                product_price.setText(product.getPrice());
                product_name.setText(product.getName());
                product_description.setText(product.getDescription());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
