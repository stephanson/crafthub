package com.stephanson.beerin.app.Views.Adapters;

import android.graphics.Color;
import android.widget.ImageView;
import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.stephanson.beerin.app.Infrastructure.ImageLoaderController;
import com.stephanson.beerin.app.Model.Broadcast;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.inject.Inject;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.stephanson.beerin.app.R;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Stephan on 17.04.2016.
 */
public class BroadcastListViewAdapter extends RecyclerView.Adapter<BroadcastListViewAdapter.ViewHolder>{

    private List<Broadcast> _broadcasts;
    private Context _context;
    private DisplayImageOptions _displayImageOptions;

    public BroadcastListViewAdapter(Context context, List<Broadcast> broadcasts) {
        _broadcasts = broadcasts;
        _context = context;
        _displayImageOptions = ImageLoaderController.GetOptionsForPubList();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(_context);
        View v = inflater.inflate(R.layout.broadcast_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Broadcast item = _broadcasts.get(position);
        holder.setBroadcastTitle(item.getTitle());
        holder.displayImageFromUrlWithOptions(item.getPictureUrl(),_displayImageOptions);
        holder.setBroadcastText(item.getText());
        holder.setBroadcastTime(  item.getLastUpdate() );
    }

    @Override
    public int getItemCount()
    {
        return _broadcasts.size();
    }

    public void updateData(List<Broadcast> newBroadcasts)
    {
        _broadcasts = newBroadcasts;
        notifyDataSetChanged();
    }


    protected class ViewHolder extends RecyclerView.ViewHolder {
        private TextView _broadcastTitle;
        private TextView _broadcastText;
        private TextView _broadcastTime;
        private ImageView _broadcastImage;

        @Inject
        public ViewHolder(View itemView) {
            super(itemView);
            _broadcastTitle = (TextView)itemView.findViewById(R.id.broadcast_item_title);
            _broadcastText = (TextView)itemView.findViewById(R.id.broadcast_item_text);
            _broadcastImage = (ImageView) itemView.findViewById(R.id.broadcast_image);
            _broadcastTime = (TextView)itemView.findViewById(R.id.broadcast_item_time);
        }

        public void displayImageFromUrlWithOptions(String url, DisplayImageOptions options)
        {
                ImageLoader.getInstance().displayImage(url, _broadcastImage, options);
        }

        public void setBroadcastTitle(String broadcastTitle)
        {
            _broadcastTitle.setText(broadcastTitle);
        }

        public void setBroadcastText(String broadcastText)
        {
            _broadcastText.setText(broadcastText);
        }
        public void setBroadcastTime(long timeStamp)
        {
            Date date = new Date(timeStamp*1000);
            Locale locale = new Locale("ru");
            SimpleDateFormat format = new SimpleDateFormat(" d MMM HH:mm", locale);
            _broadcastTime.setText(format.format(date));
        }

    }
}