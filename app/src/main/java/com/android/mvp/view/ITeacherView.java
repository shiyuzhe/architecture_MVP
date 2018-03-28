package com.android.mvp.view;

import com.android.mvp.bean.TeacherBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/26.
 */

public interface ITeacherView {

    void setTeacher(TeacherBean teacherBean);

    void setTeachers(List<TeacherBean> teacherBeans);
}
