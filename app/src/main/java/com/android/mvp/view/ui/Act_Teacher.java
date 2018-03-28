package com.android.mvp.view.ui;

import android.widget.TextView;

import com.android.mvp.R;
import com.android.mvp.bean.TeacherBean;
import com.android.mvp.presenter.TeacherPresenter;
import com.android.mvp.view.ITeacherView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by shiyuzhe on 2018/3/27.
 * WeChat:18091664559
 * Description:老师界面
 */

public class Act_Teacher extends BaseActivity implements ITeacherView {

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.brief)
    TextView brief;
    @BindView(R.id.content)
    TextView content;

    //    private TeacherPrecess precess;
    private TeacherPresenter presenter;
    private TeacherBean teacherBean = null;
    private List<TeacherBean> teacherBeans = null;

    @Override
    protected void initEvents() {
//        precess = new TeacherPrecess(this);
//        precess.getAllTeacherInfo();
        presenter = new TeacherPresenter(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_teacher;
    }

    @Override
    public void setTeacher(TeacherBean teacherBean) {
        if (teacherBean != null) {
            this.teacherBean = teacherBean;
            setDate();
        }
    }

    private void setDate() {
        name.setText(teacherBean.getName() + "");
        brief.setText(teacherBean.getBrief() + "");
        content.setText(teacherBean.getContenturl() + "");
    }

    @Override
    public void setTeachers(List<TeacherBean> teacherBeans) {
        this.teacherBeans = teacherBeans;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (teacherBean == null) {
            presenter.getTeacher(715182173+"");
            presenter.getTeachers(1, 9);
        } else {
            setDate();
        }
    }
}
