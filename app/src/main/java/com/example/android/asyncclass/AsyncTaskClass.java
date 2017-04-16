package com.example.android.asyncclass;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by abc on 16-Apr-17.
 */

public abstract class AsyncTaskClass<X,Y,Z> {
   protected abstract X onPreExecute();
   protected abstract Y doInBackground(X x);
   protected abstract void onPostExecute(Y y);
    Y ans;
    X x;
    Handler handler = new Handler(Looper.getMainLooper());
    class MyThread implements Runnable {
        @Override
        public void run() {
            ans = doInBackground(x);
            handler.post(back);
        }
    }

    Runnable back = new Runnable() {
        @Override
        public void run() {
            onPostExecute(ans);
        }
    };

    public void execute(Z z) {
        x = onPreExecute();
        Thread t = new Thread( new MyThread());
        t.start();
    }

}
