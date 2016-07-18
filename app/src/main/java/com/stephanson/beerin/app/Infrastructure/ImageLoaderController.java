package com.stephanson.beerin.app.Infrastructure;

import android.content.Context;
import android.graphics.Bitmap;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

/**
 * Created by Electric Brains on 10.04.2016.
 */
public class ImageLoaderController {

    public static void InitializeImageLoader(Context context)
    {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context)
                                                    .threadPriority(Thread.NORM_PRIORITY - 2)
                                                    .denyCacheImageMultipleSizesInMemory()
                                                    .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                                                    .diskCacheSize(50 * 1024 * 1024) // 50 MiB
                                                    .tasksProcessingOrder(QueueProcessingType.FIFO)
                                                    .writeDebugLogs(); // Remove for release app
        ImageLoader.getInstance().init(config.build());
    }


    public static DisplayImageOptions GetOptionsForPubList() {
        return new DisplayImageOptions.Builder()
                    .resetViewBeforeLoading(true)
                    .cacheOnDisk(true)
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .considerExifParams(true)
                    .displayer(new FadeInBitmapDisplayer(300))
                    .build();
    }
    public static DisplayImageOptions GetOptionsForStockList() {
        return new DisplayImageOptions.Builder()
                    .resetViewBeforeLoading(true)
                    .cacheOnDisk(true)
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .considerExifParams(true)
                    .displayer(new FadeInBitmapDisplayer(300))
                    .build();
    }
}
