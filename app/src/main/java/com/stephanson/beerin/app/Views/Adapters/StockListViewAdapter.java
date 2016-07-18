package com.stephanson.beerin.app.Views.Adapters;

import android.widget.ImageView;
import android.widget.RatingBar;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.stephanson.beerin.app.Infrastructure.Animations.FlipAnimation;
import com.stephanson.beerin.app.Infrastructure.ImageLoaderController;
import com.stephanson.beerin.app.Model.Stock;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.inject.Inject;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.stephanson.beerin.app.R;

import java.util.List;

/**
 * Created by Stephan on 14.04.2016.
 */
public class StockListViewAdapter extends RecyclerView.Adapter<StockListViewAdapter.ViewHolder>{

    private List<Stock> _stock;
    private Context _context;
    private DisplayImageOptions _displayImageOptions;

    public StockListViewAdapter(Context context, List<Stock> stock) {
        _stock = stock;
        _context = context;
        _displayImageOptions = ImageLoaderController.GetOptionsForStockList();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(_context);
        View v = inflater.inflate(R.layout.stock_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Stock item = _stock.get(position);
        holder.setStockItemName(item.getName());
        holder.setStockItemShortDescription(item.getShortDescription());
        holder.setStockItemDescription(item.getDescription());
        holder.setStockItemPrice(item.getPrice());
        holder.setStockItemRate(item.getRate());
        holder.displayImageFromUrlWithOptions(item.getPhotoUrl(),_displayImageOptions);
    }

    @Override
    public int getItemCount() {
        return _stock.size();
    }

    public void updateData(List<Stock> newStock)
    {
        _stock = newStock;
        notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView _itemName;
        private TextView _shortDescription;
        private TextView _description;
        private TextView _price;
        private ImageView _stockImage;
        private RatingBar _rate;

        @Inject
        public ViewHolder(View itemView) {
            super(itemView);
            _itemName = (TextView)itemView.findViewById(R.id.stock_item_name);
            _price = (TextView)itemView.findViewById(R.id.stock_item_price);
            _shortDescription = (TextView)itemView.findViewById(R.id.stock_item_short_description);
            _description = (TextView)itemView.findViewById(R.id.stock_item_description);
            _stockImage = (ImageView) itemView.findViewById(R.id.stock_image);
            _rate = (RatingBar) itemView.findViewById(R.id.stock_item_rate);
            itemView.setOnClickListener(this);
        }

        public void setStockItemName(String pubName)
        {
            _itemName.setText(pubName);
        }
        public void setStockItemDescription(String description)
        {
            _description.setText(description);
        }
        public void setStockItemShortDescription(String description)
        {
            _shortDescription.setText(description);
        }
        public void setStockItemPrice(String price)
        {
            _price.setText(price);
        }
        public void setStockItemRate(Integer rate)
        {
            _rate.setRating(rate);
        }
        public void displayImageFromUrlWithOptions(String url, DisplayImageOptions options) {
            ImageLoader.getInstance().displayImage(url, _stockImage, options);
        }

        @Override
        public void onClick(View v) {
            View _faceSide = v.findViewById(R.id.stock_light_side);
            View _backSide = v.findViewById(R.id.stock_dark_side);
            FlipAnimation anim = new FlipAnimation(_faceSide, _backSide);

            if (_faceSide.getVisibility() == View.GONE)
            {
                anim.reverse();
            }
            v.startAnimation(anim);
        }
    }
}