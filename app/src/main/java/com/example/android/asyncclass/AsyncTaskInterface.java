package com.example.android.asyncclass;

/**
 * Created by abc on 16-Apr-17.
 */

public interface AsyncTaskInterface {
    void onPreExecute();
    void doInBackground();
    void onPostExecute();
}
