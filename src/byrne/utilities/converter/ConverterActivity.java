package byrne.utilities.converter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class ConverterActivity extends Activity implements OnClickListener , OnItemSelectedListener{
    /** Called when the activity is first created. */
	
	//ui components 
	private EditText inputboxdec;
	private EditText inputboxbin;
	private EditText inputboxoct;
	private EditText inputboxhex;
	private EditText inputboxasc;
	private EditText outputbox;
	private Spinner inputtype;
	private Spinner outputtype;
	private Button convert;
	private Button lookup;
	private String input;
	private String inSpin;
	private String outSpin;
	private String [] nondec , nonhex, nonoct , nonbin , nonasc;
    private ArrayAdapter<String> choice;
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        //get references
        
        inputboxdec = (EditText) findViewById(R.id.inboxdec);
        inputboxbin = (EditText) findViewById(R.id.inboxbin);
        inputboxhex = (EditText) findViewById(R.id.inboxhex);
        inputboxoct = (EditText) findViewById(R.id.inboxoct);
        inputboxasc = (EditText) findViewById(R.id.inboxasc);

        outputbox = (EditText) findViewById(R.id.outbox);
        inputtype = (Spinner) findViewById(R.id.input);
        outputtype = (Spinner) findViewById(R.id.output);
        convert = (Button) findViewById(R.id.convert);
        lookup = (Button) findViewById(R.id.lookup);
        
        //set output string arrays
        nondec = getResources().getStringArray(R.array.nondec);
        nonbin = getResources().getStringArray(R.array.nonbin);
        nonhex = getResources().getStringArray(R.array.nonhex);
        nonoct = getResources().getStringArray(R.array.nonoct);
        nonasc = getResources().getStringArray(R.array.nonasc);

 
        //set listeners
        convert.setOnClickListener(this);
        lookup.setOnClickListener(this);
        inputtype.setOnItemSelectedListener(this);
        
        
    }

    


	@Override
	public void onClick(View v) {
		
       //get the users input 
       inSpin = inputtype.getSelectedItem().toString();
       outSpin = outputtype.getSelectedItem().toString();
    
       //get the input from the visible textbox
       if(inputboxdec.isShown() == true){
    	   
    	   input = inputboxdec.getText().toString();
    	   
       }
       else if(inputboxbin.isShown() == true){
    	   
    	   input = inputboxbin.getText().toString();
    	   
       }
       else if(inputboxhex.isShown() == true){
    	   
    	   input = inputboxhex.getText().toString();
    	   
       }
       else if(inputboxoct.isShown() == true){
    	   
    	   input = inputboxoct.getText().toString();
    	   
       }
       else {
    	   
    	   input = inputboxasc.getText().toString();
    	   
       }
       
       
       try{
		
       // if decimal input
		if(v == convert && inSpin.equals("Decimal")){
			
			try{
			
				
			//don't allow empty strings
			if(input.length() == 0){
				Toast.makeText(this, "Decimal field must not be empty", Toast.LENGTH_SHORT).show();
			}
			else{
			
			
				//convert to hex
				if(outSpin.equals("Hex")){
					
					
				    // dec to hex
				    //http://www.java-tips.org/java-se-tips/java.lang/conversion-from-decimal-to-hexadecimal.html
					String hexstr = Long.toHexString(Long.parseLong(input));
					
					outputbox.setText(hexstr);
					
					
				}
				//convert to binary
				if(outSpin.equals("Binary")){
					
				    //dec to binary
				    //http://www.java-tips.org/java-se-tips/java.lang/conversion-from-decimal-to-binary.html
					String binstr = Long.toBinaryString(Long.parseLong(input));
					
					outputbox.setText(binstr);
				}
				//convert to ascii
				if(outSpin.equals("Ascii")){
				    //dec to ascii
						
					int d = Integer.parseInt(input);
					String ascstr = decToAsc(d);
					
					//if a number from 0 to 127 has been entered
					if(!(ascstr.equals("NONE"))) 
						outputbox.setText(ascstr);
					else
						Toast.makeText(this,"Decimal number must be between 0 & 127 to convert to Ascii", Toast.LENGTH_SHORT).show();
					   
					   
				}
				//convert to octal
				if(outSpin.equals("Octal")){
				    //dec to octal
				    //http://www.java2s.com/Tutorial/Java/0040__Data-Type/ConvertDecimaltoOctal.htm
					String octstr = Long.toOctalString(Long.parseLong(input));
					
					outputbox.setText(octstr);
				}
			
			}
			//catch number conversions greater than the max capacity of a long 
		       }catch(NumberFormatException decnfe){
		    	   
		    	   //decnfe.printStackTrace();
		    	   Toast.makeText(this, "Input cannot be greater than max value 9223372036854775807.\n(2^63 -1) ", Toast.LENGTH_SHORT).show();
		    	   
		       }
			
		}
		// if hex input
		if(v == convert && inSpin.equals("Hex")){
			
			try{
						
			
			//don't allow empty strings
			if(input.length() == 0){
				Toast.makeText(this, "Hex field must not be empty", Toast.LENGTH_SHORT).show();
			}
			else{
			
			
				//convert to decimal
				if(outSpin.equals("Decimal")){
				    //hex to dec
				    //http://www.roseindia.net/java/java-conversion/HexadecimalToDecima.shtml
				
					String decstr = Long.toString(Long.parseLong(input,16));
					
					outputbox.setText(decstr);
					
				}
				//convert to binary
				if(outSpin.equals("Binary")){
				    //hex to binary
				    //http://www.roseindia.net/java/java-conversion/HexadecimalToBinaryAndLong.shtml
					//http://tech.chitgoks.com/2009/05/18/convert-hexadecimal-to-binary-using-java-2/
					
					String binstr = Long.toBinaryString(Long.parseLong(input,16));
					
					outputbox.setText(binstr);
					
				}
				//convert to ascii
				if(outSpin.equals("Ascii")){
					
					

					String decstr = Long.toString(Long.parseLong(input,16));
					
					int d = Integer.parseInt(decstr);
					String ascstr = decToAsc(d);
					
					//if a number from 0 to 127 has been entered
					if(!(ascstr.equals("NONE"))) 
						outputbox.setText(ascstr);
					else
						Toast.makeText(this,"Hex number must be between 0 & 7f to convert to Ascii", Toast.LENGTH_SHORT).show();
					   
					
				}
				//convert to octal
				if(outSpin.equals("Octal")){
					
					String octstr = Long.toOctalString(Long.parseLong(input,16));
					
					outputbox.setText(octstr);
					
				}
			}
			
		       }catch(NumberFormatException hexnfe){
		    	   
		    	   //hexnfe.printStackTrace();
		    	   Toast.makeText(this, "Input cannot be greater than max value 7FFFFFFFFFFFFFFFF", Toast.LENGTH_SHORT).show();
		    	   
		       }
		}
		
		// if binary input
		if(v == convert && inSpin.equals("Binary")){
			
			try{
			
			
			//don't allow empty strings
			if(input.length() == 0){
				Toast.makeText(this, "Binary field must not be empty", Toast.LENGTH_SHORT).show();
			}
			else{
			
				//convert to hex
				if(outSpin.equals("Hex")){
					
					String hexstr = Long.toHexString(Long.parseLong(input,2));
					
					outputbox.setText(hexstr);
				}
				//convert to decimal
				if(outSpin.equals("Decimal")){
					
					String decstr = Long.toString(Long.parseLong(input,2));
					
					outputbox.setText(decstr);
					
				}
				//convert to ascii
				if(outSpin.equals("Ascii")){
					
					String decstr = Long.toString(Long.parseLong(input,2));

					int d = Integer.parseInt(decstr);
					String ascstr = decToAsc(d);
					
					//if a number from 0 to 127 has been entered
					if(!(ascstr.equals("NONE"))) 
						outputbox.setText(ascstr);
					else
						Toast.makeText(this,"Binary number must be between 0 & 1111111 to convert to Ascii", Toast.LENGTH_SHORT).show();
					   
					
				}
				//convert to octal
				if(outSpin.equals("Octal")){
					
					String octstr = Long.toOctalString(Long.parseLong(input,2));
					
					outputbox.setText(octstr);
				}
			}
			
		       }catch(NumberFormatException binnfe){
		    	   
		    	   //binnfe.printStackTrace();
		    	   Toast.makeText(this, "Input cannot be greater than max value \n111111111111111111111111111111111111111111111111111111111111111", Toast.LENGTH_SHORT).show();
		    	   
		       }
		}
		
		// if octal input
		if(v == convert && inSpin.equals("Octal")){
			
			
			try{
			
			//don't allow empty strings
			if(input.length() == 0){
				Toast.makeText(this, "Octal field must not be empty", Toast.LENGTH_SHORT).show();
			}
			else{
			
				//convert to hex
				if(outSpin.equals("Hex")){
					
					String hexstr = Long.toHexString(Long.parseLong(input,8));
					
					outputbox.setText(hexstr);
					
				}
				//convert to decimal
				if(outSpin.equals("Decimal")){
					
					String decstr = Long.toString(Long.parseLong(input,8));
					
					outputbox.setText(decstr);
				}
				//convert to ascii
				if(outSpin.equals("Ascii")){
					
					String decstr = Long.toString(Long.parseLong(input,8));
					
					int d = Integer.parseInt(decstr);
					String ascstr = decToAsc(d);
					
					//if a number from 0 to 127 has been entered
					if(!(ascstr.equals("NONE"))) 
						outputbox.setText(ascstr);
					else
						Toast.makeText(this,"Octal number must be between 0 & 177 to convert to Ascii", Toast.LENGTH_SHORT).show();
					   
				}
				//convert to binary
				if(outSpin.equals("Binary")){
					
					String binstr = Long.toBinaryString(Long.parseLong(input,8));
					
					outputbox.setText(binstr);
					
				}
			}
			
		       }catch(NumberFormatException octnfe){
		    	   
		    	   //octnfe.printStackTrace();
		    	   Toast.makeText(this, "Input cannot be greater than max value 777777777777777777777", Toast.LENGTH_SHORT).show();
		    	   
		       }
		}	
		
		// if ascii input
		if(v == convert && inSpin.equals("Ascii")){
			
			
			//don't allow empty strings
			if(input.length() == 0){
				Toast.makeText(this, "Ascii field must not be empty", Toast.LENGTH_SHORT).show();
			}
			else{
			
				//convert to hex
				if(outSpin.equals("Hex")){
	
					
					int d = ascToDec(input);
					if(d != -1){
						String hexstr = Integer.toHexString(d);
						outputbox.setText(hexstr);
					}
					else
						Toast.makeText(this, "Invalid Ascii Character Entered\nResulting Hex not in range of 0 - 7f ", Toast.LENGTH_SHORT).show();
					
				}
				//convert to decimal
				if(outSpin.equals("Decimal")){
					
					
					int d = ascToDec(input);
					if(d != -1){
						String decstr = Integer.toString(d);
						outputbox.setText(decstr);
					}
					else
						Toast.makeText(this, "Invalid Ascii Character Entered\nResulting Decimal not in range of 0 - 127 ", Toast.LENGTH_SHORT).show();
				}
				//convert to binary
				if(outSpin.equals("Binary")){
					
					int d = ascToDec(input);
					if(d != -1){
						String binstr = Integer.toBinaryString(d);
						outputbox.setText(binstr);
					}
					else
						Toast.makeText(this, "Invalid Ascii Character Entered\nResulting Binary not in range of 0 - 1111111 ", Toast.LENGTH_SHORT).show();
					
				}
				//convert to octal
				if(outSpin.equals("Octal")){
					
					int d = ascToDec(input);
					if(d != -1){
						String octstr = Integer.toOctalString(d);
						outputbox.setText(octstr);
					}
					else
						Toast.makeText(this, "Invalid Ascii Character Entered\nResulting Octal not in range of 0 - 177 ", Toast.LENGTH_SHORT).show();
				}
		
			}
		
		}
		
		//catch number conversions greater than the max capacity of a long 
       }catch(NumberFormatException nfe){
    	   
    	   nfe.printStackTrace();
    	   Toast.makeText(this, "Output greater than max value 2^63 -1", Toast.LENGTH_SHORT).show();
    	   
       }
		
       if(v == lookup){
    	   //start tables activity    	   
			Intent intent = new Intent(ConverterActivity.this, TablesAct.class);
	        startActivity(intent);
    	   
       }
       
       
	}




	@Override
	public void onItemSelected(AdapterView<?> arg0, View v, int position,
			long id) {
		
		
		
		if(position == 0){
			
			// set non decimal array in output
			
	        choice = new ArrayAdapter<String>(this, 
	        		android.R.layout.simple_spinner_item, nondec);
	        choice.setDropDownViewResource(
	                android.R.layout.simple_spinner_dropdown_item);
	        
	        //set the currently selected spinner's related input box
	        inputboxdec.setVisibility(View.VISIBLE);
	        inputboxbin.setVisibility(View.GONE);
	        inputboxhex.setVisibility(View.GONE);
	        inputboxasc.setVisibility(View.GONE);
	        inputboxoct.setVisibility(View.GONE);
	        
	        //clear the output box
	        outputbox.setText("");
	        //set the  output options as list - the selected input type
	        outputtype.setAdapter(choice);
		}

		if(position == 1){
			
			// set non Octal array in output
	        choice = new ArrayAdapter<String>(this, 
	        		android.R.layout.simple_spinner_item, nonoct);
	        choice.setDropDownViewResource(
	                android.R.layout.simple_spinner_dropdown_item);
	        
	        //set the currently selected spinner's related input box
	        inputboxdec.setVisibility(View.GONE);
	        inputboxbin.setVisibility(View.GONE);
	        inputboxhex.setVisibility(View.GONE);
	        inputboxasc.setVisibility(View.GONE);
	        inputboxoct.setVisibility(View.VISIBLE);
	        
	        //clear the input & output boxes
	        outputbox.setText("");
	        inputboxdec.setText("");
	        inputboxbin.setText("");
	        inputboxhex.setText("");
	        inputboxasc.setText("");
	        inputboxoct.setText("");
	        //set the  output options as list - the selected input type
	        outputtype.setAdapter(choice);
			
		}
		if(position == 2){
			
			// set non Hex array in output
	        choice = new ArrayAdapter<String>(this, 
	        		android.R.layout.simple_spinner_item, nonhex);
	        choice.setDropDownViewResource(
	                android.R.layout.simple_spinner_dropdown_item);
	        
	      //set the currently selected spinner's related input box

	        inputboxdec.setVisibility(View.GONE);
	        inputboxbin.setVisibility(View.GONE);
	        inputboxhex.setVisibility(View.VISIBLE);
	        inputboxasc.setVisibility(View.GONE);
	        inputboxoct.setVisibility(View.GONE);
	        
	        //clear the input & output boxes
	        outputbox.setText("");
	        inputboxdec.setText("");
	        inputboxbin.setText("");
	        inputboxhex.setText("");
	        inputboxasc.setText("");
	        inputboxoct.setText("");
	        //set the  output options as list - the selected input type
	        outputtype.setAdapter(choice);
		}
		
		if(position == 3){
			
			// set non binary array in output
	        choice = new ArrayAdapter<String>(this, 
	        		android.R.layout.simple_spinner_item, nonbin);
	        choice.setDropDownViewResource(
	                android.R.layout.simple_spinner_dropdown_item);
	        
	      //set the currently selected spinner's related input box
	        
	        inputboxdec.setVisibility(View.GONE);
	        inputboxbin.setVisibility(View.VISIBLE);
	        inputboxhex.setVisibility(View.GONE);
	        inputboxasc.setVisibility(View.GONE);
	        inputboxoct.setVisibility(View.GONE);
	        
	        //clear the input & output boxes
	        outputbox.setText("");
	        inputboxdec.setText("");
	        inputboxbin.setText("");
	        inputboxhex.setText("");
	        inputboxasc.setText("");
	        inputboxoct.setText("");
	        //set the  output options as list - the selected input type
	        outputtype.setAdapter(choice);
		}
		
		if(position == 4){
			
			// set non Ascii array in output
	        choice = new ArrayAdapter<String>(this, 
	        		android.R.layout.simple_spinner_item, nonasc);
	        choice.setDropDownViewResource(
	                android.R.layout.simple_spinner_dropdown_item);
	        
	      //set the currently selected spinner's related input box
	        
	        inputboxdec.setVisibility(View.GONE);
	        inputboxbin.setVisibility(View.GONE);
	        inputboxhex.setVisibility(View.GONE);
	        inputboxasc.setVisibility(View.VISIBLE);
	        inputboxoct.setVisibility(View.GONE);
	        
	        //clear the input & output boxes
	        outputbox.setText("");
	        inputboxdec.setText("");
	        inputboxbin.setText("");
	        inputboxhex.setText("");
	        inputboxasc.setText("");
	        inputboxoct.setText("");
	        //set the  output options as list - the selected input type
	        outputtype.setAdapter(choice);
		}
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// We can ignore this
		
	}
	
	  
	  //decimal to ascii
	  private String decToAsc(int dec){
		  
		  String asc; 
		  
		  switch(dec){
		  
		  case  0 :
			  asc = "NUL";
			  break;
		  case  1 :
			  asc = "SOH";
			  break;
		  case  2 :
			  asc = "STX";
			  break;
		  case  3 :
			  asc = "ETX";
			  break;
		  case  4 :
			  asc = "EOT";
			  break;
		  case  5 :
			  asc = "ENQ";
			  break;
		  case  6 :
			  asc = "ACK";
			  break;
		  case  7 :
			  asc = "BEL";
			  break;
		  case  8 :
			  asc = "BS";
			  break;
		  case  9 :
			  asc = "HT";
			  break;
		  case  10 :
			  asc = "LF";
			  break;
		  case  11 :
			  asc = "VT";
			  break;
		  case  12 :
			  asc = "FF";
			  break;
		  case  13 :
			  asc = "CR";
			  break;
		  case  14 :
			  asc = "SO";
			  break;
		  case  15 :
			  asc = "SI";
			  break;
		  case  16 :
			  asc = "DLE";
			  break;
		  case  17 :
			  asc = "DC1";
			  break;
		  case  18 :
			  asc = "DC2";
			  break;
		  case  19 :
			  asc = "DC3";
			  break;
		  case  20 :
			  asc = "DC4";
			  break;
		  case  21 :
			  asc = "NAK";
			  break;
		  case  22 :
			  asc = "SYN";
			  break;
		  case  23 :
			  asc = "ETB";
			  break;
		  case  24 :
			  asc = "CAN";
			  break;
		  case  25 :
			  asc = "EM";
			  break;
		  case  26 :
			  asc = "SUB";
			  break;
		  case  27 :
			  asc = "ESC";
			  break;
		  case  28 :
			  asc = "FS";
			  break;
		  case  29 :
			  asc = "GS";
			  break;
		  case  30 :
			  asc = "RS";
			  break;
		  case  31 :
			  asc = "US";
			  break;
		  case  32 :
			  asc = "SP";
			  break;
		  case  33 :
			  asc = "!";
			  break;
		  case  34 :
			  asc = "\"";
			  break;
		  case  35 :
			  asc = "#";
			  break;
		  case  36 :
			  asc = "$";
			  break;
		  case  37 :
			  asc = "%";
			  break;
		  case  38 :
			  asc = "&";
			  break;
		  case  39 :
			  asc = "'";
			  break;
		  case  40 :
			  asc = "(";
			  break;
		  case  41 :
			  asc = ")";
			  break;
		  case  42 :
			  asc = "*";
			  break;
		  case  43 :
			  asc = "+";
			  break;
		  case  44 :
			  asc = ",";
			  break;
		  case  45 :
			  asc = "-";
			  break;
		  case  46 :
			  asc = ".";
			  break;
		  case  47 :
			  asc = "/";
			  break;
		  case  48 :
			  asc = "0";
			  break;
		  case  49 :
			  asc = "1";
			  break;
		  case  50 :
			  asc = "2";
			  break;
		  case  51 :
			  asc = "3";
			  break;
		  case  52 :
			  asc = "4";
			  break;
		  case  53 :
			  asc = "5";
			  break;
		  case  54 :
			  asc = "6";
			  break;
		  case  55 :
			  asc = "7";
			  break;
		  case  56 :
			  asc = "8";
			  break;
		  case  57 :
			  asc = "9";
			  break;
		  case  58 :
			  asc = ":";
			  break;
		  case  59 :
			  asc = ";";
			  break;
		  case  60 :
			  asc = "<";
			  break;
		  case  61 :
			  asc = "=";
			  break;
		  case  62 :
			  asc = ">";
			  break;
		  case  63 :
			  asc = "?";
			  break;
		  case  64 :
			  asc = "@";
			  break;
		  case  65 :
			  asc = "A";
			  break;
		  case  66 :
			  asc = "B";
			  break;
		  case  67 :
			  asc = "C";
			  break;
		  case  68 :
			  asc = "D";
			  break;
		  case  69 :
			  asc = "E";
			  break;
		  case  70 :
			  asc = "F";
			  break;
		  case  71 :
			  asc = "G";
			  break;
		  case  72 :
			  asc = "H";
			  break;
		  case  73 :
			  asc = "I";
			  break;
		  case  74 :
			  asc = "J";
			  break;
		  case  75 :
			  asc = "K";
			  break;
		  case  76 :
			  asc = "L";
			  break;
		  case  77 :
			  asc = "M";
			  break;
		  case  78 :
			  asc = "N";
			  break;
		  case  79 :
			  asc = "O";
			  break;
		  case  80 :
			  asc = "P";
			  break;
		  case  81 :
			  asc = "Q";
			  break;
		  case  82 :
			  asc = "R";
			  break;
		  case  83 :
			  asc = "S";
			  break;
		  case  84 :
			  asc = "T";
			  break;
		  case  85 :
			  asc = "U";
			  break;
		  case  86 :
			  asc = "V";
			  break;
		  case  87 :
			  asc = "W";
			  break;
		  case  88 :
			  asc = "X";
			  break;
		  case  89 :
			  asc = "Y";
			  break;
		  case  90 :
			  asc = "Z";
			  break;
		  case  91 :
			  asc = "[";
			  break;
		  case  92 :
			  asc = "\\";
			  break;
		  case  93 :
			  asc = "]";
			  break;
		  case  94 :
			  asc = "^";
			  break;
		  case  95 :
			  asc = "_";
			  break;
		  case  96 :
			  asc = "`";
			  break;
		  case  97 :
			  asc = "a";
			  break;
		  case  98 :
			  asc = "b";
			  break;
		  case  99 :
			  asc = "c";
			  break;
		  case  100 :
			  asc = "d";
			  break;
		  case  101 :
			  asc = "e";
			  break;
		  case  102 :
			  asc = "f";
			  break;
		  case  103 :
			  asc = "g";
			  break;
		  case  104 :
			  asc = "h";
			  break;
		  case  105 :
			  asc = "i";
			  break;
		  case  106 :
			  asc = "j";
			  break;
		  case  107 :
			  asc = "k";
			  break;
		  case  108 :
			  asc = "l";
			  break;
		  case  109 :
			  asc = "m";
			  break;
		  case  110 :
			  asc = "n";
			  break;
		  case  111 :
			  asc = "o";
			  break;
		  case  112 :
			  asc = "p";
			  break;
		  case  113 :
			  asc = "q";
			  break;
		  case  114 :
			  asc = "r";
			  break;
		  case  115 :
			  asc = "s";
			  break;
		  case  116 :
			  asc = "t";
			  break;
		  case  117 :
			  asc = "u";
			  break;
		  case  118 :
			  asc = "v";
			  break;
		  case  119 :
			  asc = "w";
			  break;
		  case  120 :
			  asc = "x";
			  break;
		  case  121 :
			  asc = "y";
			  break;
		  case  122 :
			  asc = "z";
			  break;
		  case  123 :
			  asc = "{";
			  break;
		  case  124 :
			  asc = "|";
			  break;
		  case  125 :
			  asc = "}";
			  break;
		  case  126 :
			  asc = "~";
			  break;
		  case  127 :
			  asc = "DEL";
			  break;
		  default:
			  asc = "NONE";
			  break;
		  }
		  
		  return asc;
	  }
	 
	  //Ascii to decimal
	  
	  private int ascToDec(String str){
		   
		  
		if(str.equals("NUL") || str.equals("nul"))
			return 0;
		else if(str.equals("SOH") || str.equals("soh"))
			return 1;
		else if(str.equals("STX") || str.equals("stx"))
			return 2;
		else if(str.equals("ETX") || str.equals("etx"))
			return 3;
		else if(str.equals("EOT") || str.equals("eot"))
			return 4;
		else if(str.equals("ENQ") || str.equals("enq"))
			return 5;
		else if(str.equals("ACK") || str.equals("ack"))
			return 6;
		else if(str.equals("BEL") || str.equals("bel"))
			return 7;
		else if(str.equals("BS") || str.equals("bs"))
			return 8;
		else if(str.equals("HT") || str.equals("ht"))
			return 9;
		else if(str.equals("LF") || str.equals("lf"))
			return 10;
		else if(str.equals("VT") || str.equals("vt"))
			return 11;
		else if(str.equals("FF") || str.equals("ff"))
			return 12;
		else if(str.equals("CR") || str.equals("cr"))
			return 13;
		else if(str.equals("SO") || str.equals("so"))
			return 14;
		else if(str.equals("SI") || str.equals("si"))
			return 15;		
		else if(str.equals("DLE") || str.equals("dle"))
			return 16;
		else if(str.equals("DC1") || str.equals("dc1"))
			return 17;
		else if(str.equals("DC2") || str.equals("dc2"))
			return 18;
		else if(str.equals("DC3") || str.equals("dc3"))
			return 19;
		else if(str.equals("DC4") || str.equals("dc4"))
			return 20;
		else if(str.equals("NAK") || str.equals("nak"))
			return 21;
		else if(str.equals("SYN") || str.equals("syn"))
			return 22;
		else if(str.equals("ETB") || str.equals("etb"))
			return 23;
		else if(str.equals("CAN") || str.equals("can"))
			return 24;
		else if(str.equals("EM") || str.equals("em"))
			return 25;
		else if(str.equals("SUB") || str.equals("sub"))
			return 26;
		else if(str.equals("ESC") || str.equals("esc"))
			return 27;
		else if(str.equals("FS") || str.equals("fs"))
			return 28;
		else if(str.equals("GS") || str.equals("gs"))
			return 29;
		else if(str.equals("RS") || str.equals("rs"))
			return 30;
		else if(str.equals("US") || str.equals("us"))
			return 31;
		else if(str.equals("SP") || str.equals("sp")|| str.equals(" ") || str.equals("spa") || str.equals("SPA"))
			return 32;
		else if(str.equals("!"))
			return 33;
		else if(str.equals("\""))
			return 34;
		else if(str.equals("#"))
			return 35;
		else if(str.equals("$"))
			return 36;
		else if(str.equals("%"))
			return 37;
		else if(str.equals("&"))
			return 38;
		else if(str.equals("'"))
			return 39;
		else if(str.equals("("))
			return 40;
		else if(str.equals(")"))
			return 41;
		else if(str.equals("*"))
			return 42;
		else if(str.equals("+"))
			return 43;
		else if(str.equals(","))
			return 44;
		else if(str.equals("-"))
			return 45;
		else if(str.equals("."))
			return 46;
		else if(str.equals("/"))
			return 47;
		else if(str.equals("0"))
			return 48;
		else if(str.equals("1"))
			return 49;
		else if(str.equals("2"))
			return 50;
		else if(str.equals("3"))
			return 51;
		else if(str.equals("4"))
			return 52;
		else if(str.equals("5"))
			return 53;
		else if(str.equals("6"))
			return 54;
		else if(str.equals("7"))
			return 55;
		else if(str.equals("8"))
			return 56;
		else if(str.equals("9"))
			return 57;
		else if(str.equals(":"))
			return 58;
		else if(str.equals(";"))
			return 59;
		else if(str.equals("<"))
			return 60;
		else if(str.equals("="))
			return 61;
		else if(str.equals(">"))
			return 62;
		else if(str.equals("?"))
			return 63;
		else if(str.equals("@"))
			return 64;
		else if(str.equals("A"))
			return 65;
		else if(str.equals("B"))
			return 66;
		else if(str.equals("C"))
			return 67;
		else if(str.equals("D"))
			return 68;
		else if(str.equals("E"))
			return 69;
		else if(str.equals("F"))
			return 70;
		else if(str.equals("G"))
			return 71;
		else if(str.equals("H"))
			return 72;
		else if(str.equals("I"))
			return 73;
		else if(str.equals("J"))
			return 74;
		else if(str.equals("K"))
			return 75;
		else if(str.equals("L"))
			return 76;
		else if(str.equals("M"))
			return 77;
		else if(str.equals("N"))
			return 78;
		else if(str.equals("O"))
			return 79;
		else if(str.equals("P"))
			return 80;
		else if(str.equals("Q"))
			return 81;
		else if(str.equals("R"))
			return 82;
		else if(str.equals("S"))
			return 83;
		else if(str.equals("T"))
			return 84;
		else if(str.equals("U"))
			return 85;
		else if(str.equals("V"))
			return 86;
		else if(str.equals("W"))
			return 87;
		else if(str.equals("X"))
			return 88;
		else if(str.equals("Y"))
			return 89;
		else if(str.equals("Z"))
			return 90;
		else if(str.equals("["))
			return 91;
		else if(str.equals("\\"))
			return 92;
		else if(str.equals("]"))
			return 93;
		else if(str.equals("^"))
			return 94;
		else if(str.equals("_"))
			return 95;
		else if(str.equals("`"))
			return 96;
		else if(str.equals("a"))
			return 97;
		else if(str.equals("b"))
			return 98;
		else if(str.equals("c"))
			return 99;
		else if(str.equals("d"))
			return 100;
		else if(str.equals("e"))
			return 101;
		else if(str.equals("f"))
			return 102;
		else if(str.equals("g"))
			return 103;
		else if(str.equals("h"))
			return 104;
		else if(str.equals("i"))
			return 105;
		else if(str.equals("j"))
			return 106;
		else if(str.equals("k"))
			return 107;
		else if(str.equals("l"))
			return 108;
		else if(str.equals("m"))
			return 109;
		else if(str.equals("n"))
			return 110;
		else if(str.equals("o"))
			return 111;
		else if(str.equals("p"))
			return 112;
		else if(str.equals("q"))
			return 113;
		else if(str.equals("r"))
			return 114;
		else if(str.equals("s"))
			return 115;
		else if(str.equals("t"))
			return 116;
		else if(str.equals("u"))
			return 117;
		else if(str.equals("v"))
			return 118;
		else if(str.equals("w"))
			return 119;
		else if(str.equals("x"))
			return 120;
		else if(str.equals("y"))
			return 121;
		else if(str.equals("z"))
			return 122;
		else if(str.equals("{"))
			return 123;
		else if(str.equals("|"))
			return 124;
		else if(str.equals("}"))
			return 125;
		else if(str.equals("~"))
			return 126;	
		else if(str.equals("DEL") || str.equals("del"))
			return 127;
		else
		   return -1;
	  }
	  
}