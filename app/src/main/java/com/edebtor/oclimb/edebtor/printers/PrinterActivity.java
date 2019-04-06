package com.edebtor.oclimb.edebtor.printers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.edebtor.oclimb.edebtor.R;
import com.edebtor.oclimb.edebtor.collection;
import com.edebtor.oclimb.edebtor.loan_apply;
import com.edebtor.oclimb.edebtor.main_menu;

import java.io.UnsupportedEncodingException;



public class PrinterActivity extends Activity {
/******************************************************************************************************/
	// Debugging
	private static final String TAG = "Main_Activity";
	private static final boolean DEBUG = true;
/******************************************************************************************************/
	// Message types sent from the BluetoothService Handler
	public static final int MESSAGE_STATE_CHANGE = 1;
	public static final int MESSAGE_READ = 2;
	public static final int MESSAGE_WRITE = 3;
	public static final int MESSAGE_DEVICE_NAME = 4;
	public static final int MESSAGE_TOAST = 5;
	public static final int MESSAGE_CONNECTION_LOST = 6;
	public static final int MESSAGE_UNABLE_CONNECT = 7;
/*******************************************************************************************************/
	// Key names received from the BluetoothService Handler
	public static final String DEVICE_NAME = "device_name";
	public static final String TOAST = "toast";

	// Intent request codes
	private static final int REQUEST_CONNECT_DEVICE = 1;
	private static final int REQUEST_ENABLE_BT = 2;

/*******************************************************************************************************/
	private static final String CHINESE = "GBK";


/*********************************************************************************/


/******************************************************************************************************/
	// Name of the connected device
	private String mConnectedDeviceName = null;
	// Local Bluetooth adapter
	private BluetoothAdapter mBluetoothAdapter = null;
	// Member object for the services
	private BluetoothService mService = null;

/***************************   ****************************************************************/


	final byte[][] byteCommands = {
			{ 0x1b, 0x40, 0x0a },
			{ 0x0a },
			{ 0x1b, 0x4d, 0x00 },
			{ 0x1b, 0x4d, 0x01 },
			{ 0x1d, 0x21, 0x00 },
			{ 0x1d, 0x21, 0x11 },
			{ 0x1d, 0x21, 0x22 },
			{ 0x1d, 0x21, 0x33 },
			{ 0x1b, 0x45, 0x00 },
			{ 0x1b, 0x45, 0x01 },
			{ 0x1b, 0x7b, 0x00 },
			{ 0x1b, 0x7b, 0x01 },
			{ 0x1d, 0x42, 0x00 },
			{ 0x1d, 0x42, 0x01 },
			{ 0x1b, 0x56, 0x00 },
			{ 0x1b, 0x56, 0x01 },
			{ 0x0a, 0x1d, 0x56, 0x42, 0x01, 0x0a },
			{ 0x1b, 0x42, 0x03, 0x03 },
			{ 0x1b, 0x70, 0x00, 0x50, 0x50 },
			{ 0x10, 0x14, 0x00, 0x05, 0x05 },
			{ 0x1c, 0x2e },
			{ 0x1c, 0x26 },
			{ 0x1f, 0x11, 0x04 },
			{ 0x1b, 0x63, 0x35, 0x01 },
			{ 0x1b, 0x63, 0x35, 0x00 },
			{ 0x1b, 0x2d, 0x02, 0x1c, 0x2d, 0x02 },
			{ 0x1b, 0x2d, 0x00, 0x1c, 0x2d, 0x00 },
			{ 0x1f, 0x11, 0x03 },
	};


/******************************************************************************************************/

Button button_scan,buttonPrint,btn_stop,btn_return, btn_cancel;
String print_string="";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (DEBUG)
			Log.e(TAG, "+++ ON CREATE +++");

		// Set up the window layout
		//requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_printer);

		button_scan=findViewById(R.id.button_scan);
		buttonPrint=findViewById(R.id.btn_test);
		btn_stop=findViewById(R.id.btn_stop);
		btn_return=findViewById(R.id.btn_return);
		btn_cancel=findViewById(R.id.btn_cancel);



		// Get local Bluetooth adapter
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

		// If the adapter is null, then Bluetooth is not supported
		if (mBluetoothAdapter == null) {
			Toast.makeText(this, "Bluetooth is not available",
					Toast.LENGTH_LONG).show();
			finish();
		}


		buttonPrint.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				//print_string="\nHi Kushan\n We will be we printing @12 \n \n";
				print_string= collection.msg;
				BluetoothPrintTest();;
			}
		});

		button_scan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent serverIntent = new Intent(PrinterActivity.this, DeviceListActivity.class);
				startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
			}
		});
		btn_stop.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				mService.stop();
				button_scan.setEnabled(true);
				buttonPrint.setEnabled(false);
				btn_stop.setEnabled(false);
			}
		});
		btn_return.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				mService.stop();
				button_scan.setEnabled(true);
				buttonPrint.setEnabled(false);
				btn_stop.setEnabled(false);

				//Intent i = new Intent(PrinterActivity.this, main_menu.class); //commented to modify the code to return to loan detail page
				Intent i = new Intent(PrinterActivity.this, collection.class);
				startActivity(i);
			}
		});
		btn_cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				mService.stop();
				button_scan.setEnabled(true);
				buttonPrint.setEnabled(false);
				btn_stop.setEnabled(false);

				Intent i = new Intent(PrinterActivity.this, main_menu.class);
				startActivity(i);
			}
		});


	}

	@Override
	public void onStart() {
		super.onStart();

		// If Bluetooth is not on, request that it be enabled.
		// setupChat() will then be called during onActivityResult
		if (!mBluetoothAdapter.isEnabled()) {
			Intent enableIntent = new Intent(
					BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
			// Otherwise, setup the session
		} else {
			if (mService == null) {
				KeyListenerInit();
			}
		}
	}

	@Override
	public synchronized void onResume() {
		super.onResume();

		if (mService != null) {

			if (mService.getState() == BluetoothService.STATE_NONE) {
				// Start the Bluetooth services
				mService.start();
			}
		}
	}

	@Override
	public synchronized void onPause() {
		super.onPause();
		if (DEBUG)
			Log.e(TAG, "- ON PAUSE -");
	}

	@Override
	public void onStop() {
		super.onStop();
		if (DEBUG)
			Log.e(TAG, "-- ON STOP --");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		// Stop the Bluetooth services
		if (mService != null)
			mService.stop();
		if (DEBUG)
			Log.e(TAG, "--- ON DESTROY ---");
	}

/*****************************************************************************************************/




/*****************************************************************************************************/
	/*
	 * SendDataString
	 */
	private void SendDataString(String data) {

		if (mService.getState() != BluetoothService.STATE_CONNECTED) {
			Toast.makeText(this, R.string.not_connected, Toast.LENGTH_SHORT)
					.show();
			return;
		}
		if (data.length() > 0) {
			try {
				mService.write(data.getBytes("GBK"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}



	/****************************************************************************************************/
	@SuppressLint("HandlerLeak")
	private final Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MESSAGE_STATE_CHANGE:
				if (DEBUG)
					Log.i(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
				switch (msg.arg1) {
				case BluetoothService.STATE_CONNECTED:
					button_scan.setEnabled(false);
					buttonPrint.setEnabled(true);
					btn_stop.setEnabled(true);

					//Print_Test();//

					break;
				case BluetoothService.STATE_CONNECTING:
					//mTitle.setText(R.string.title_connecting);
					break;
				case BluetoothService.STATE_LISTEN:
				case BluetoothService.STATE_NONE:
					//mTitle.setText(R.string.title_not_connected);
					break;
				}
				break;

			case MESSAGE_DEVICE_NAME:
				// save the connected device's name
				mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
				Toast.makeText(getApplicationContext(),
						"Connected to " + mConnectedDeviceName,
						Toast.LENGTH_SHORT).show();
				break;
			case MESSAGE_TOAST:
				Toast.makeText(getApplicationContext(),
						msg.getData().getString(TOAST), Toast.LENGTH_SHORT)
						.show();
				break;
			case MESSAGE_CONNECTION_LOST:
                Toast.makeText(getApplicationContext(), "Device connection was lost",
                               Toast.LENGTH_SHORT).show();
				buttonPrint.setEnabled(false);
				btn_stop.setEnabled(false);

                break;
            case MESSAGE_UNABLE_CONNECT:
            	Toast.makeText(getApplicationContext(), "Unable to connect device",
                        Toast.LENGTH_SHORT).show();
            	break;
			}
		}
	};

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (DEBUG)
			Log.d(TAG, "onActivityResult " + resultCode);
		switch (requestCode) {
		case REQUEST_CONNECT_DEVICE:{
				// When DeviceListActivity returns with a device to connect
				if (resultCode == Activity.RESULT_OK) {
					// Get the device MAC address
					String address = data.getExtras().getString(
							DeviceListActivity.EXTRA_DEVICE_ADDRESS);
					// Get the BLuetoothDevice object
					if (BluetoothAdapter.checkBluetoothAddress(address)) {
						BluetoothDevice device = mBluetoothAdapter
								.getRemoteDevice(address);
						// Attempt to connect to the device
						mService.connect(device);
					}
				}
				break;
			}
			case REQUEST_ENABLE_BT:{
				// When the request to enable Bluetooth returns
				if (resultCode == Activity.RESULT_OK) {
					// Bluetooth is now enabled, so set up a session
					KeyListenerInit();
				} else {
					// User did not enable Bluetooth or an error occured
					Log.d(TAG, "BT not enabled");
					Toast.makeText(this, R.string.bt_not_enabled_leaving,
							Toast.LENGTH_SHORT).show();
					finish();
				}
				break;
			}

		}
	}

/****************************************************************************************************/


	private void BluetoothPrintTest() {

		SendDataString(print_string);

	}

	private void KeyListenerInit() {

		mService = new BluetoothService(this, mHandler);
		buttonPrint.setEnabled(false);
		btn_stop.setEnabled(false);
	}
}