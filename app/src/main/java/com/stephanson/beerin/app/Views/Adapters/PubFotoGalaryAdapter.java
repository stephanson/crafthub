package com.stephanson.beerin.app.Views.Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.stephanson.beerin.app.Infrastructure.ImageLoaderController;
import com.stephanson.beerin.app.Model.PubPicture;
import com.stephanson.beerin.app.R;

import java.util.List;

/**
 * Created by Electric Brains on 31.05.2016.
 */
public class PubFotoGalaryAdapter extends PagerAdapter{

    private LayoutInflater _inflater;
    private List<PubPicture> _urlList;
    private DisplayImageOptions _displayImageOptions;

    public PubFotoGalaryAdapter(Context context, List<PubPicture> imageUrls) {
            _inflater = LayoutInflater.from(context);
            _displayImageOptions = ImageLoaderController.GetOptionsForPubList();
            _urlList = imageUrls;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return _urlList.size();
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            View imageLayout = _inflater.inflate(R.layout.pub_image_for_galary, view, false);
            ImageView imageView = (ImageView) imageLayout.findViewById(R.id.pub_image_item);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageLoader.getInstance().displayImage(_urlList.get(position).getUrl(), imageView, _displayImageOptions);
            view.addView(imageLayout, 0);
            return imageLayout;
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }


}
