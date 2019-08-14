package com.codemobiles.cmfeeddatabase;


import android.content.res.Resources;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class FeedProductFragment extends Fragment {

    private RecyclerView mRecyclerView;

    private ArrayList<ProductBean> mData;

    public FeedProductFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View _view = inflater.inflate(R.layout.fragment_feed_product, container, false);

        mRecyclerView = (RecyclerView) _view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));

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

            View _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);

            return new ViewHolder(_view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            holder.title.setText(mData.get(position).getName());
            holder.detail.setText( mData.get(position).getDetail());
            holder.price.setText(mData.get(position).getPrice() + " ฿");

            Glide.with(getContext())
                    .load(mData.get(position).getImg_product())
                    .into(holder.imgProduct);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView detail;
        TextView price;
        ImageView imgProduct;

        public ViewHolder(View view) {
            super(view);

            title = view.findViewById(R.id.nameTV);
            detail = view.findViewById(R.id.detailTV);
            price = view.findViewById(R.id.priceTV);
            imgProduct = view.findViewById(R.id.imageProductIMV);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ProductBean _data = mData.get(getAdapterPosition());
                    Toast.makeText(getContext(), _data.getName(), Toast.LENGTH_LONG).show();
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

                Type type = new TypeToken<List<ProductBean>>() {}.getType();

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

            if(result != null){
                mRecyclerView.setAdapter(new CustomRecyclerView());
            }else{
                Toast.makeText(getActivity(), "feed data failure", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
