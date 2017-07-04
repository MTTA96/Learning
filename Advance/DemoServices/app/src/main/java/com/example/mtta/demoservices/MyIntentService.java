package com.example.mtta.demoservices;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_DEMLE = "com.example.mtta.demoservices.action.DEMLE";
    public static final String ACTION_DEMCHAN = "com.example.mtta.demoservices.action.DEMCHAN";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.example.mtta.demoservices.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.example.mtta.demoservices.extra.PARAM2";

    public MyIntentService() {
        super("MyIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionDemLe(Context context) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_DEMLE);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionDemChan(Context context) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_DEMCHAN);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_DEMLE.equals(action)) {
                handleActionLe();
            } else if (ACTION_DEMCHAN.equals(action)) {
                handleActionChan();
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionLe() {
        Thread threadLe = new Thread(new Runnable() {
            int i = 1;
            Intent intent = new Intent();
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000);
                        i+=2;
                        Log.d("le","Lẻ: " + String.valueOf(+i));
                        //set intent và truyền vào broadcast
                        intent.setAction(ACTION_DEMLE);
                        intent.putExtra("Le", String.valueOf(i));
                        sendBroadcast(intent);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        threadLe.start();
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionChan() {
        Thread threadChan = new Thread(new Runnable() {
            int i = 0;
            Intent intent = new Intent();
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000);
                        i+=2;
                        Log.d("chan","Chẵn: " + String.valueOf(i));
                        //set intent và truyền vào broadcast
                        intent.setAction(ACTION_DEMCHAN);
                        intent.putExtra("Chan", String.valueOf(i));
                        sendBroadcast(intent);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        threadChan.start();
    }
}
