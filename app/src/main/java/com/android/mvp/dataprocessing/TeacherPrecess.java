package com.android.mvp.dataprocessing;

import com.android.mvp.bean.TeacherBean;
import com.android.mvp.util.retrofit.ApiService;
import com.android.mvp.util.retrofit.RetrofitFactory;
import com.android.mvp.util.retrofit.base.BaseBean;
import com.android.mvp.util.retrofit.base.BaseObserver;
import com.android.mvp.view.ITeacherView;

import java.util.List;

/**
 * Created by Administrator on 2018/3/26.
 */

public class TeacherPrecess extends BasePrecess {
    private ITeacherView mView;

    public TeacherPrecess(ITeacherView mView) {
        this.mView = mView;

    }

    public void getTeacherInfo(String id) {
        RetrofitFactory.getInstance().getRetrofit().create(ApiService.class).getTeacher(id).compose(this.<BaseBean<TeacherBean>>setThread()).subscribe(new BaseObserver<TeacherBean>() {
            @Override
            protected void onSuccees(BaseBean<TeacherBean> t) throws Exception {
                TeacherBean object = t.getObject();
                if (object != null)
                    mView.setTeacher(object);
            }

            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {

            }
        });
    }

    public void getAllTeacherInfo() {

        RetrofitFactory.getInstance().getRetrofit().create(ApiService.class).getTeachers(1, 9).compose(this.<BaseBean<List<TeacherBean>>>setThread()).subscribe(new BaseObserver<List<TeacherBean>>() {
            @Override
            protected void onSuccees(BaseBean<List<TeacherBean>> t) throws Exception {
                List<TeacherBean> object = t.getObject();
                if (!object.isEmpty())
                    mView.setTeacher(object.get(0));
                mView.setTeachers(object);
            }

            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {

            }
        });
    }
}

