package net.betterbing.androidframworkstudy.utils;

import com.squareup.okhttp.Request;

/**
 * Created by aibb on 15/11/9.
 */
public abstract class ResultCallback<T> {
    public void onBefore(Request request) {
    }

    public void onAfter() {
    }

    public void inProgress(float progress) {
    }

    public abstract void onError(Request request, Exception e);

    public abstract void onResponse(T response);

}
