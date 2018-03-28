package com.android.mvp.interactor.impl;

import com.android.mvp.bean.TeacherBean;
import com.android.mvp.interactor.ITeacherInteractor;
import com.android.mvp.presenter.BaseLoadedListener;
import com.android.mvp.presenter.ErrorBean;
import com.android.mvp.util.retrofit.ApiService;
import com.android.mvp.util.retrofit.RetrofitFactory;
import com.android.mvp.util.retrofit.base.BaseBean;
import com.android.mvp.util.retrofit.base.BaseObserver;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by shiyuzhe on 2018/3/27.
 * WeChat:18091664559
 */

public class TeacherInteractorImpl implements ITeacherInteractor {
    private BaseLoadedListener Teachers;
    private BaseLoadedListener Teacher;

    public TeacherInteractorImpl(BaseLoadedListener Teacher,BaseLoadedListener Teachers) {
        this.Teacher = Teacher;
        this.Teachers = Teachers;
    }

    @Override
    public void getTeacher(String id) {
        RetrofitFactory.getInstance().getRetrofit()
                .create(ApiService.class).getTeacher(id)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<TeacherBean>() {
                    @Override
                    protected void onSuccees(BaseBean<TeacherBean> t) throws Exception {
                        TeacherBean object = t.getObject();
                        if (object != null) {
                            Teacher.onSuccess(1,object);
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        ErrorBean errorBean = new ErrorBean();
                        errorBean.setNetWorkError(isNetWorkError);
                        errorBean.setMsg(e.getMessage());
                        Teacher.onBusinessError(errorBean);
                    }
                });
    }

    @Override
    public void getTeachers(int start, int end) {
        RetrofitFactory.getInstance().getRetrofit()
                .create(ApiService.class)
                .getTeachers(1, 9)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<TeacherBean>>() {
                    @Override
                    protected void onSuccees(BaseBean<List<TeacherBean>> t) throws Exception {
                        List<TeacherBean> object = t.getObject();
                        if (!object.isEmpty()) {
                            Teachers.onSuccess(1, object);
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        ErrorBean errorBean = new ErrorBean();
                        errorBean.setNetWorkError(isNetWorkError);
                        errorBean.setMsg(e.getMessage());
                        Teachers.onBusinessError(errorBean);
                    }

                });
    }
}
