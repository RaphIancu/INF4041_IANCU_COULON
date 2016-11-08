package org.esiea.iancu_coulon.myapplication;

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
public class GetBiersServices extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_BIERS = "org.esiea.iancu_coulon.myapplication.action.BIERS";


    public GetBiersServices() {
        super("GetBiersServices");
    }

    /**
     * Starts this service to perform action Biers with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBiers(Context context) {
        Intent intent = new Intent(context, GetBiersServices.class);
        intent.setAction(ACTION_BIERS);
        context.startService(intent);
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        handleActionBiers();
    }

    /**
     * Handle action Biers in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBiers() {
        Log.i("erreur", "buggggggggggg");
    }


}
