package network;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.sadokmm.materialtest.MyApplication;

public class VolleySingleton {

    private static VolleySingleton sInstance=null;

    //to work with imgs
    private ImageLoader imageLoader;

    private RequestQueue mRequestQueue;

    private VolleySingleton(){

        mRequestQueue= Volley.newRequestQueue(MyApplication.getAppContext());

        imageLoader=new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {

            //create a cache variable
            private LruCache<String,Bitmap> cache=new LruCache<>((int)(Runtime.getRuntime().maxMemory()/1024/8)); // on divise sur 1024/8 car il faut être en KB et on le caste en int

            @Override
            public Bitmap getBitmap(String url) {

                return cache.get(url);

            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

                cache.put(url,bitmap);

            }
        });

    }

    public static VolleySingleton getsInstance(){

        if (sInstance == null)
        {
            sInstance = new VolleySingleton();
        }
        return sInstance;
    }


    public RequestQueue getRequestQueue(){
        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }
}
