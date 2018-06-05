package com.kmdev.flix.RestClient;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * Created by ububtu on 13/7/16.
 */
public class BaseRestClient {
    public final Context _context;
    Snackbar bar = null;
    private ProgressDialog pDialog;
    private ConnectionDetector cd;
    private String pDialogMessage = "Loading...";

    protected BaseRestClient(Context _context) {
        this._context = _context;

        init();

    }

    private void init() {
        pDialog = new ProgressDialog(_context);
        pDialog.setMessage(pDialogMessage);
        pDialog.setCancelable(false);


    }


    /**
     * progress dialog functions
     **/
    public void showProgDialog() {
        pDialog.show();
    }

    public void showProgDialog(String message) {

        pDialog.setMessage(message);
        pDialog.show();
    }

    public void hideProgDialog() {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }

    public void showToast(String msg) {
        Toast.makeText(_context, msg, Toast.LENGTH_SHORT).show();
    }



      /*  public void showErrorDialog(String msg) {
            if (errorDialog == null) {
                errorDialog = new ErrorDialog(_context, msg);
            } else {
                errorDialog.setMessage(msg);
            }
            errorDialog.show();
        }*/

    public void showErrorSnackBar(View view, String message) {
        bar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        bar.dismiss();
                    }
                });

        bar.show();
        bar.setActionTextColor(Color.BLUE);
    }

    public void netNotAvail(View view) {

        showErrorSnackBar(view, "Network connection not available.");
    }

    public void showExcption(View view, String msg, Exception ex) {

        showErrorSnackBar(view, msg + "\n" + ex.toString());
    }

    public void showFailresponse(View view, String msg, int responsecode, Object response) {

        showErrorSnackBar(view, msg + "\n" + String.valueOf(response));
    }

    public void showBlankresponse(View view, String msg, int responsecode) {

        showErrorSnackBar(view, msg + "\nBlank or null response");
    }

}
