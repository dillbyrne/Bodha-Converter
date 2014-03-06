package byrne.utilities.converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class TablesAct extends Activity {

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tables);
        
        
        //read in the help text file
        InputStream iFile = getResources().openRawResource(R.raw.table);
        try {
            TextView tableText = (TextView) findViewById(R.id.tabletext);
            String strFile = inputStreamToString(iFile);
            tableText.setText(strFile);
        } catch (Exception e) {
          Log.e("FILE_IO", "InputStreamToString failure", e);
        }
          
    }
	
    //converts an input stream to a string
    
    public String inputStreamToString(InputStream is) throws IOException {
        StringBuffer sBuffer = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String strLine = null;
        while ((strLine = br.readLine()) != null) {
            sBuffer.append(strLine + "\n");
        }
        br.close();
        is.close();
        return sBuffer.toString();
    }
	
}
