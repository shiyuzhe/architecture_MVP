package com.android.mvp.presenter;

import com.android.mvp.MyApplication;
import com.android.mvp.bean.TeacherBean;
import com.android.mvp.interactor.ITeacherInteractor;
import com.android.mvp.interactor.impl.TeacherInteractorImpl;
import com.android.mvp.util.CommonUtils;
import com.android.mvp.view.ITeacherView;
import java.util.List;

/**
 * Created by shiyuzhe on 2018/3/27.
 * WeChat:18091664559
 */

public class TeacherPresenter {
    private ITeacherInteractor mInteractor;
    private ITeacherView mView;

    public TeacherPresenter(ITeacherView mView) {
        this.mView = mView;
        mInteractor = new TeacherInteractorImpl(new TeachersListener(),new TeachersListener());
    }

    public void getTeacher(String id){
        mInteractor.getTeacher(id);
    }
    public void getTeachers(int start, int end) {
        mInteractor.getTeachers(start, end);
    }


    private class TeacherListener implements BaseLoadedListener<TeacherBean>{

        @Override
        public void onSuccess(int event_tag, TeacherBean data) {

        }

        @Override
        public void onError(String msg) {

        }

        @Override
        public void onException(String msg) {

        }

        @Override
        public void onBusinessError(ErrorBean error) {

        }
    }
    private class TeachersListener implements BaseLoadedListener<List<TeacherBean>> {

        @Override
        public void onSuccess(int event_tag, List<TeacherBean> data) {
            mView.setTeachers(data);
            mView.setTeacher(data.get(0));
            CommonUtils.makeEventToast(MyApplication.getContext(),data.get(0).toString(),false);
        }

        @Override
        public void onError(String msg) {
            CommonUtils.makeEventToast(MyApplication.getContext(),msg,false);
        }

        @Override
        public void onException(String msg) {
            CommonUtils.makeEventToast(MyApplication.getContext(),msg,false);
        }

        @Override
        public void onBusinessError(ErrorBean error) {
            CommonUtils.makeEventToast(MyApplication.getContext(), error.getMsg(), false);
        }
    }
}
