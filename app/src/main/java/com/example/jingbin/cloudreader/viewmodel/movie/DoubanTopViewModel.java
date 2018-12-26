package com.example.jingbin.cloudreader.viewmodel.movie;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.example.jingbin.cloudreader.bean.HotMovieBean;
import com.example.jingbin.cloudreader.data.model.OneRepository;

/**
 * @author jingbin
 * @data 2017/12/15
 * @Description 依赖注入：依赖注入允许类在不构造它们的情况下定义它们的依赖关系。在运行时，另一个类负责提供这些依赖关系。
 * 我们推荐Google的Dagger 2库在Android应用程序中实现依赖注入。Dagger 2通过遍历依赖关系树来自动构造对象，并为依赖关系提供编译时间保证。
 */

public class DoubanTopViewModel extends AndroidViewModel {

    private int mStart = 0;
    private int mCount = 21;
    private OneRepository oneRepo;
//    private OnMovieLoadListener loadListener;

    public DoubanTopViewModel(@NonNull Application application) {
        super(application);
        this.oneRepo = new OneRepository();
    }

//    public void setOnMovieLoadListener(OnMovieLoadListener loadListener) {
//        this.loadListener = loadListener;
//    }
//
//    public void onDestroy() {
//        loadListener = null;
//    }

    public MutableLiveData<HotMovieBean> getHotMovie() {
        final MutableLiveData<HotMovieBean> data = new MutableLiveData<>();
        oneRepo.getMovieTop250(mStart, mCount, new OnMovieLoadListener() {
            @Override
            public void onSuccess(HotMovieBean hotMovieBean) {
                data.setValue(hotMovieBean);
//                if (loadListener != null) {
//                    loadListener.onSuccess(hotMovieBean);
//                }
            }

            @Override
            public void onFailure() {
                data.setValue(null);
//                if (loadListener != null) {
//                    loadListener.onFailure();
//                }
            }
        });
        return data;
    }

    public int getStart() {
        return mStart;
    }

    public void handleNextStart() {
        mStart += mCount;
    }
}
