package com.example.mtta.demoasynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.TextView;

/**
 * Created by 508-2 on 5/30/2017.
 */

public class DemGiayAsyncTask extends AsyncTask<Integer, String, Void> {
    Activity myActivity;

    public DemGiayAsyncTask(Activity myActivity) {
        this.myActivity = myActivity;
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        for (int i = 0; i < integers[0]; i++) {
            SystemClock.sleep(1000);
            publishProgress(String.valueOf(i));
        }
        return null; //tra ve gia tri cua onPostExecute
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);

        TextView tvDemGiay = (TextView) myActivity.findViewById(R.id.tvDemGiay);
        tvDemGiay.setText(values[0]);
    }
}
