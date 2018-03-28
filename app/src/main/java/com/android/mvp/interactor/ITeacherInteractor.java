package com.android.mvp.interactor;

/**
 * Created by shiyuzhe on 2018/3/27.
 * WeChat:18091664559
 * Description:老师数据
 */

public interface ITeacherInteractor {

    /*
     * Param id 老师Id
     */
    void getTeacher(String id);

    void getTeachers(int start, int end);
}
