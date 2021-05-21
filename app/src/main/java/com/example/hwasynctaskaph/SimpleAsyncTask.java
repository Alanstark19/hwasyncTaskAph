package com.example.hwasynctaskaph;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {

    // campos
    private WeakReference<TextView> mtxtv_1;
    private WeakReference<ProgressBar> mpb_1;
    private static final int x = 5;

    /**
     * Constructor de la clase SimpleAsyncTask
     * @param tv TextView
     * @param bar ProgressBar
     */
    SimpleAsyncTask (TextView tv, ProgressBar bar){
        mtxtv_1 = new WeakReference<>(tv);
        mpb_1 = new WeakReference<>(bar);
    }

    /**
     *
     * Método doInBackground que se encargara de esperar
     * @return the return value is automatically passed to the onPostExecute() callback.
     */
    @Override
    protected String doInBackground(Void... voids) {
        Random random = new Random();
        int number = random.nextInt(11);
        int milisegundos = number * 400;
        int y = milisegundos / x;
        for (int i = 0; i < x; i++){
            try {
                Thread.sleep(y);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            publishProgress(((i + 1) * 100) / x);
        }

        return "Awake after sleeping for " + milisegundos + " milliseconds";
    }

    /**
     *actualiza el estado del progressbar
     * @param values xd
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        mpb_1.get().setProgress(values[0]);
    }

    /**
     * update the TextView in the Activity once it has completed sleeping
     * @param result
     */
    @Override
    protected void onPostExecute(String result) {
        mtxtv_1.get().setText(result);
    }
}
