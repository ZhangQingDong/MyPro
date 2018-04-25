package com.example.zqd.myproject.utils;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;


public final class RxBus {

    private ConcurrentHashMap<String, List<Subject>> mSubjectsMapper;

    private RxBus() {
        mSubjectsMapper = new ConcurrentHashMap<>();
    }

    private static class SingletonHolder {
        private static final RxBus INSTANCE = new RxBus();
    }

    public static RxBus getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 注册RxBus
     *
     * @param tag
     * @param <T>
     * @return
     */
    public <T> Observable<T> register(@NonNull String tag) {
        List<Subject> subjectList = mSubjectsMapper.get(tag);
        if (subjectList == null) {
            subjectList = new ArrayList<>();
            mSubjectsMapper.put(tag, subjectList);
        }
        Subject<T> subject = PublishSubject.<T>create().toSerialized();
        subjectList.add(subject);
        return subject;
    }

    /**
     * 反注册RxBus
     *
     * @param tag
     * @param observable
     */
    public void unregister(@NonNull String tag, @NonNull Observable observable) {
        List<Subject> subjects = mSubjectsMapper.get(tag);
        if (subjects != null) {
            subjects.remove(observable);
            if (subjects.isEmpty()) {
                mSubjectsMapper.remove(tag);
            }
        }
    }

    /**
     * 发送
     *
     * @param tag
     * @param content
     */
    public void post(@NonNull String tag, @NonNull Object content) {
        List<Subject> subjects = mSubjectsMapper.get(tag);
        if (subjects != null && !subjects.isEmpty()) {
            for (Subject subject : subjects) {
                subject.onNext(content);
            }
        }
    }
}
