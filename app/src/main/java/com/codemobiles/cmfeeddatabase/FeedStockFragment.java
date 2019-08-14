package com.codemobiles.cmfeeddatabase;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codemobiles.cmfeeddatabase.Bean.ProductBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.codemobiles.cmfeeddatabase.Bean.ProductBean.BASE_URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedStockFragment extends Fragment {

    private RecyclerView mRecyclerView;

    private ArrayList<ProductBean> mData;

    public FeedStockFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View _view = inflater.inflate(R.layout.fragment_feed_stock, container, false);

        mRecyclerView = (RecyclerView) _view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        return _view;
    }


    @Override
    public void onResume() {
        super.onResume();

        feedDatabase();
    }

    public void feedDatabase() {
        new FeedAsyn().execute(BASE_URL + "query.php");
    }

    private class CustomRecyclerView extends RecyclerView.Adapter<ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stock, parent, false);

            return new ViewHolder(_view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            holder.title.setText(mData.get(position).getName());

            Glide.with(getContext())
                    .load(mData.get(position).getImg_product())
                    .into(holder.imgYoutube);

            holder.edit.setTag(R.id.editBTN, mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView imgYoutube;
        Button edit;

        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.nameTV);
            imgYoutube = itemView.findViewById(R.id.imageProductIMV);
            edit = itemView.findViewById(R.id.editBTN);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), EditActivity.class);
                    intent.putExtra("product_bean", (ProductBean) edit.getTag(R.id.editBTN));
                    startActivity(intent);
                }
            });
        }
    }

    public class FeedAsyn extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {

            try {
                OkHttpClient _OkHttpClient = new OkHttpClient();

                Request _request = new Request.Builder().url(strings[0]).get().build();

                Response _response = _OkHttpClient.newCall(_request).execute();

                String _result = _response.body().string();

                Gson _gson = new Gson();

                Type type = new TypeToken<List<ProductBean>>() {
                }.getType();

                mData = _gson.fromJson(_result, type);

                return "successfully";

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null) {
                mRecyclerView.setAdapter(new CustomRecyclerView());
            } else {
                Toast.makeText(getActivity(), "feed data failure", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
