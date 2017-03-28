package net.shojigate.toshokan.notifypigeon;

import android.os.AsyncTask;

import java.net.Socket;

import static android.R.id.message;

/**
 * Created by toshokan on 3/27/17.
 */

public class PigeonFlight extends AsyncTask<String[], Void, Void> {
    // Use an AsyncTask because we cannot do network activity on the main thread
    @Override
    protected Void doInBackground(String[]... params) {
        // Get the first String[] from the generic params
        String[] message = params[0];
        try {
            // Create a socket. FIXME Use a hardcoded IP and port for now.
            Socket send = new Socket("192.168.18.88", 1973);
            // Format the String[] as a String delimited by ASCII 31 Unit Separator and convert it to a UTF-8 byte[]
            /*
             * message[0] is the command (show or unshow)
             * message[1] is the title
             * message[2] is the text
             *
             */
            String s = message[0] + (char) 31 + message[1] + (char) 31 + message[2];
            byte[] b = s.getBytes("UTF-8");
            // Write the byte[] to the socket
            send.getOutputStream().write(b);
            //Close the socket
            send.close();
        }
        catch (Exception ex) {
            // We should never get here
            ex.printStackTrace();
        }

        // Return null to satisfy Void
        return null;
    }
}
