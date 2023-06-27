package com.example.product;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText et_title,et_price,et_discription,et_category;
    Button btn_submit;
    ProgressBar progress_bar;
    DataModel dataModel;
    Dialog progressBar;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_title = findViewById(R.id.et_title);
        et_price = findViewById(R.id.et_price);
        et_discription = findViewById(R.id.et_discription);
        et_category = findViewById(R.id.et_category);
        btn_submit = findViewById(R.id.btn_submit);
        progress_bar = findViewById(R.id.progress_bar);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validation()){
                    showProgressbar();
                    addProduct();
                }
            }
        });
    }
    public void addProduct(){
        try{
            dataModel = new DataModel(et_title.getText().toString(),et_price.getText().toString(),
                    et_discription.getText().toString(),"https://i.pravatar.cc",et_category.getText().toString());

            ApiInterface apiInterface = RetrofitInstance.getClient().create(ApiInterface.class);
            Call call = apiInterface.addProduct(dataModel);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    cancelProgressbar();
                    if(response.code() == 200){

                        DataModel responseData = (DataModel) response.body();
                        Bundle bundle = new Bundle();
                        bundle.putString("title",responseData.title);
                        bundle.putString("price",responseData.price);
                        bundle.putString("description",responseData.description);
                        bundle.putString("category",responseData.category);
                        bundle.putString("image",responseData.image);

                        Intent intent = new Intent(MainActivity.this,ShowProduct.class);
                        intent.putExtras(bundle);
                        startActivity(intent);

                        Toast.makeText(MainActivity.this, "Record added Succesfully", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(MainActivity.this, "Please try again later", Toast.LENGTH_SHORT).show();
                    }

                }
                @Override
                public void onFailure(Call call, Throwable t) {
                    cancelProgressbar();
                    Toast.makeText(MainActivity.this, "Please try again later", Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e){
            cancelProgressbar();
            e.getMessage();
        }
    }
    private boolean validation(){
        try{
            if(et_title.getText().toString().isEmpty()){
                Toast.makeText(this, "Please Enter Title", Toast.LENGTH_SHORT).show();
                return false;
            }
            if(et_price.getText().toString().isEmpty()){
                Toast.makeText(this, "Please Enter Price", Toast.LENGTH_SHORT).show();
                return false;
            }
            if(et_discription.getText().toString().isEmpty()){
                Toast.makeText(this, "Please Enter Description", Toast.LENGTH_SHORT).show();
                return false;
            }
            if(et_category.getText().toString().isEmpty()){
                Toast.makeText(this, "Please Enter Category", Toast.LENGTH_SHORT).show();
                return false;
            }

        }catch (Exception e){
            e.getMessage();
        }
        return true;
    }
    private void showProgressbar(){
        progressBar = new Dialog(this);
        progressBar.setContentView(R.layout.progress_bar);
        progressBar.show();
    }

    private void cancelProgressbar(){
        if (progressBar != null){
            progressBar.dismiss();
            progressBar = null;
        }
    }
}