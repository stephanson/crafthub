package com.stephanson.beerin.app.Views.Adapters;

import android.graphics.Typeface;
import com.stephanson.beerin.app.Infrastructure.ImageLoaderController;
import com.stephanson.beerin.app.Model.Pub;
import com.stephanson.beerin.app.Presenters.Interface.IPubsPresenter;
import com.stephanson.beerin.app.Views.Adapters.ClickListeners.IPubClickListener;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.inject.Inject;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.stephanson.beerin.app.R;

import java.util.List;

/**
 * Created by Stephan on 06.04.2016.
 */
public class PubsListViewAdapter extends RecyclerView.Adapter<PubsListViewAdapter.BarViewHolder>{

    private List<Pub> _pubs;
    private Context _context;
    private DisplayImageOptions _displayImageOptions;
    private IPubClickListener _onPubClick;
    private IPubsPresenter _pubsPresenter;

    public PubsListViewAdapter(Context context,
                               List<Pub> pubs,
                               IPubClickListener onPubClick,
                               IPubsPresenter presenter
    )
    {
        _pubs = pubs;
        _context = context;
        _displayImageOptions = ImageLoaderController.GetOptionsForPubList();
        _onPubClick = onPubClick;
        _pubsPresenter = presenter;
    }

    @Override
    public BarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(_context);
        View v = inflater.inflate(R.layout.pub_list_item, parent, false);
        return new BarViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BarViewHolder holder, int position) {
        Pub pub = _pubs.get(position);
        holder.setPubName(pub.getName());
        holder.displayImageFromUrlWithOptions(pub.getBackgroundUrl(),_displayImageOptions);
        holder.setPubIndex(position);
        holder.setOnPubClickListener(_onPubClick);
        holder.setOnSubcribeClickListener(_pubsPresenter);
        holder.setSubscribeIcon(_pubsPresenter, pub);
    }

    @Override
    public int getItemCount() {
        return _pubs.size();
    }

    public void updateData(List<Pub> newPubs)
    {
        _pubs = newPubs;
        notifyDataSetChanged();
    }


    protected class BarViewHolder extends RecyclerView.ViewHolder {
        private ImageView _pubImage;
        private TextView _pubName;
        private ImageButton _subscribe;


        @Inject
        public BarViewHolder(View itemView) {
            super(itemView);
            _pubImage = (ImageView)itemView.findViewById(R.id.pub_item_image_background);
            _pubName = (TextView)itemView.findViewById(R.id.pub_item_name);
            _subscribe = (ImageButton)itemView.findViewById(R.id.subscribe_to_pub);

        }

        public void setPubName(String pubName)
        {
            _pubName.setText(pubName);
            applyCustomFont();
        }
        public void displayImageFromUrlWithOptions(String url, DisplayImageOptions options)
        {
            ImageLoader.getInstance().displayImage(url, _pubImage, options);
        }

        public void setOnPubClickListener(IPubClickListener onPubClick) {
            _pubImage.setOnClickListener(view ->
                    onPubClick.onPubClicked(_pubs.get((int)view.getTag()))
            );
        }
        public void setOnSubcribeClickListener(IPubsPresenter presenter)
        {
            _subscribe.setOnClickListener(view ->{
                Pub pub = _pubs.get((int)view.getTag());
                    presenter.subscribeToPubPush(pub);
                    setSubscribeIcon(presenter,pub);
            });
        }

        public void setPubIndex(int position) {
            _pubImage.setTag(position);
            _subscribe.setTag(position);
        }

        public void setSubscribeIcon(IPubsPresenter presenter, Pub pub) {
            _subscribe.setImageResource(presenter.isSubscribeToPubPush(pub)
                    ? R.drawable.ic_done_white_24dp
                    : R.drawable.push_icon);
        }

        private void applyCustomFont()
        {
            Typeface tf = Typeface.createFromAsset(_context.getAssets(), "fonts/ocr_font.ttf");
            _pubName.setTypeface(tf);
        }
    }
}
