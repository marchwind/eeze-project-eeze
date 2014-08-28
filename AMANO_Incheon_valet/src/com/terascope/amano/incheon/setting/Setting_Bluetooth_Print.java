package com.terascope.amano.incheon.setting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sewoo.port.android.BluetoothPort;
import com.sewoo.request.android.RequestHandler;
import com.terascope.amano.R;
import com.terascope.amano.incheon.MainActivity;
import com.terascope.amano.incheon.Setting;
import com.terascope.amano.incheon.common.CommonSet;
import com.terascope.amano.incheon.common.Prefs;

public class Setting_Bluetooth_Print extends Activity {

	private Intent i;
	private Button set_bhome_btn, set_bback_btn;
	/** Called when the activity is first created. */
	
	private static final String TAG = "BluetoothConnectMenu";
    private static final int REQUEST_ENABLE_BT = 2;

	ArrayAdapter<bluetoothDto> adapter;
	private BluetoothAdapter mBluetoothAdapter;
	private Vector<BluetoothDevice> remoteDevices;
	private BroadcastReceiver searchFinish;
	private BroadcastReceiver searchStart;
	private BroadcastReceiver discoveryResult;
	private Thread hThread;
	private Context context;
	// UI
	private String btAddrBox;
	private Button searchButton;
	private ListView list;
	// BT
	private BluetoothPort bluetoothPort;
	Prefs myprefs = null;
	
	public String preBlutoothID = "";
	
	public ProgressDialog progress ;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.setting_bluetooth);
	    
	    context = this;
	    
	    set_bhome_btn = (Button) findViewById(R.id.bluetooth_home_btn);
	    set_bhome_btn.setOnClickListener(btnClickListener);
	    set_bback_btn = (Button)findViewById(R.id.bluetooth_back_btn);
	    set_bback_btn.setOnClickListener(btnClickListener);
	    
	    searchButton = (Button) findViewById(R.id.bluetooth_search);
	    searchButton.setOnClickListener(btnClickListener);
	    
 		this.myprefs = new Prefs(getApplicationContext());
 		
 		bluetoothSetup();
	     
		list = (ListView) findViewById(R.id.bluetooth_list);
		
		// Bluetooth Device List
		adapter = new ListAdapter(this, R.layout.bluetooth_list_row, new ArrayList<bluetoothDto>());
		list.setAdapter(adapter);
		
		seachDevices();
	}
	
	// clear device data used list.
	private void clearBtDevData(){
		remoteDevices = new Vector<BluetoothDevice>();
	}
	
	// Set up Bluetooth.
	private void bluetoothSetup(){
		// Initialize
		bluetoothPort = BluetoothPort.getInstance();
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

		if (mBluetoothAdapter == null) 
		{
			Log.i(TAG,"Device does not support Bluetooth");
			new AlertDialog.Builder(this)
			.setTitle("프린트 경고")
			.setMessage("블루투스를 지원하지 않습니다.\n다른 기기를 사용해주세요.")
			.setPositiveButton(getString(R.string.alert_Ok_text), new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					finish();
				}
			});
			return;
		}
		if (!mBluetoothAdapter.isEnabled()) {
		    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
		    
		    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT); 
		}	
	}

	/**
	 * bluetooth bonded device search
	 */
	private void seachDevices(){
		clearBtDevData();
		preBlutoothID = myprefs.getBluetoothID();
		
		Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                
            	if(bluetoothPort.isValidAddress(device.getAddress())){
	            	Log.i(TAG,"bluetooth devices >> " + device.getAddress());
	            	bluetoothDto bltDto = new bluetoothDto();
	            	
	            	if(device.getAddress().equals(myprefs.getBluetoothID())){
	   					bltDto.setBluetoothName(device.getName());
	   					bltDto.setBluetoothConnectYn(true);
	   				} else {
	   					bltDto.setBluetoothName(device.getName());
	   					bltDto.setBluetoothConnectYn(false);
	   				}
	            	
	            	remoteDevices.add(device);
	   				adapter.add(bltDto);
	   				adapter.notifyDataSetChanged();
    			}
            }
        }
		
		broadcastSearch();
	}
	
	/**
	 * bluetooth broadcastSearch
	 */
	private void broadcastSearch(){
		discoveryResult = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) 
			{
				BluetoothDevice remoteDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				bluetoothDto bltDto = new bluetoothDto();
				if(remoteDevice != null)
				{
					if(!remoteDevices.contains(remoteDevice)){
						Log.i(TAG, "bluetooth searching >> " + remoteDevice.getAddress());
						
						if(remoteDevice.getBondState() != BluetoothDevice.BOND_BONDED) {
							bltDto.setBluetoothName(remoteDevice.getAddress());
							bltDto.setBluetoothConnectYn(false);
						} else {
							bltDto.setBluetoothName(remoteDevice.getAddress());
							bltDto.setBluetoothConnectYn(true);
						}
						if(bluetoothPort.isValidAddress(remoteDevice.getAddress())){
							remoteDevices.add(remoteDevice);
							adapter.add(bltDto);
							adapter.notifyDataSetChanged();
						}
					}
					
				}
			}
		};
		
		registerReceiver(discoveryResult, new IntentFilter(BluetoothDevice.ACTION_FOUND));
		
		searchStart = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) 
			{	
				searchButton.setEnabled(false);
				searchButton.setText(getResources().getString(R.string.bluetooth_searching_text));
			}
		};
		registerReceiver(searchStart, new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_STARTED));
		
		searchFinish = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) 
			{
				searchButton.setEnabled(true);
				searchButton.setText(getResources().getString(R.string.bluetooth_search_text));				
			}
		};
		registerReceiver(searchFinish, new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED));
	}
	
	private void searchBlutoothList() {
		if (!mBluetoothAdapter.isDiscovering()){	
			
			adapter.clear();
			adapter.notifyDataSetChanged();
			seachDevices();
			
			mBluetoothAdapter.startDiscovery();	
		} else {	
			mBluetoothAdapter.cancelDiscovery();
		}
	}
	
	Button.OnClickListener btnClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.bluetooth_home_btn:
				setResult(CommonSet.MAIN);
				finish();
				break;
			case R.id.bluetooth_back_btn:
				finish();
				break;
			case R.id.bluetooth_search:
				searchBlutoothList();
				break;
			default:
				break;
			}
			
		}
	};
	
	protected void onDestroy() {
		
		if((hThread != null) && (hThread.isAlive()))
		{
			hThread.interrupt();
			hThread = null;
		}	
		unregisterReceiver(searchFinish);
		unregisterReceiver(searchStart);
		unregisterReceiver(discoveryResult);
		
		try{
			bluetoothPort.disconnect();
		}catch (Exception e){
			Log.e(TAG, e.getMessage(), e);
		}
		
		super.onDestroy();
		
	};
		
	/*
	 * TODO ListView Adapter
	 */
	public class ListAdapter extends ArrayAdapter<bluetoothDto>  {

		int layoutResourceId;
		
		public ListAdapter (Context context, int layoutResourceId, ArrayList<bluetoothDto> data){
			super(context, layoutResourceId, data);
			this.layoutResourceId = layoutResourceId;
		}
		
		@Override
		public int getCount() {		
			return super.getCount();
		}
		
		@Override
		public bluetoothDto getItem(int position) {
			return super.getItem(position);
		}

		@Override
		public long getItemId(int position) {
			return super.getItemId(position);
		}
		
		@Override
		public void add(bluetoothDto object) {
			super.add(object);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			ViewHolder viewHolder = null;
			
			final int po = position;
			
			if(convertView == null){
				LayoutInflater inflater = ((Activity)parent.getContext()).getLayoutInflater();
				row = inflater.inflate(layoutResourceId, parent, false);
				
				viewHolder = new ViewHolder();
				
				viewHolder.bluetoothName = (TextView) row.findViewById(R.id.bluetooth_name);
				viewHolder.bluetoothConnected = (TextView) row.findViewById(R.id.connection_yn);
				viewHolder.bluetoothToggle = (Button) row.findViewById(R.id.btooth_connection_btn);
				
				row.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) row.getTag();
			}
			
			final bluetoothDto bltItem = (bluetoothDto) getItem(position);
			
			viewHolder.bluetoothName.setText(bltItem.getBluetoothName());
			if(!bltItem.isBluetoothConnectYn()){
				viewHolder.bluetoothConnected.setText(R.string.not_connect_text);
				viewHolder.bluetoothToggle.setText(R.string.connect_txt);
				
				viewHolder.bluetoothName.setTextColor(getResources().getColor(R.color.bluetooth_blue_color));
				viewHolder.bluetoothConnected.setTextColor(getResources().getColor(R.color.bluetooth_blue_color));
				viewHolder.bluetoothToggle.setTextColor(getResources().getColor(R.color.common_black_color));
				viewHolder.bluetoothToggle.setBackgroundResource(R.drawable.bluetooth_connection_btn);
				
				viewHolder.bluetoothToggle.setOnClickListener(new Button.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						BluetoothDevice btDev = remoteDevices.elementAt(po);
						Log.i(TAG,"pairing deivce >>> " + btDev.getAddress());
						try
						{
							if(mBluetoothAdapter.isDiscovering())
							{
								mBluetoothAdapter.cancelDiscovery();
							}
							btAddrBox = btDev.getAddress();
							btConn(btDev);
						}
						catch (IOException e)
						{
							return;
						}
					}
				});
				
			} else {
				viewHolder.bluetoothConnected.setText(R.string.bluetooth_connect_txt);
				viewHolder.bluetoothToggle.setText(R.string.disconnect_text);
				viewHolder.bluetoothToggle.setTextColor(getResources().getColor(R.color.bluetooth_gray_color));
				viewHolder.bluetoothToggle.setBackgroundResource(R.drawable.bluetooth_cancel_btn);
				
				viewHolder.bluetoothToggle.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Log.i(TAG,"pairing deivce disconnect >>> ");
						btDisconn();
					}
				});
				
			}
			
			return row;
		}
	}
	
	class bluetoothDto {
		public String bluetoothName;
		public boolean bluetoothConnectYn;
		
		public String getBluetoothName() {
			return bluetoothName;
		}
		public void setBluetoothName(String bluetoothName) {
			this.bluetoothName = bluetoothName;
		}
		public boolean isBluetoothConnectYn() {
			return bluetoothConnectYn;
		}
		public void setBluetoothConnectYn(boolean bluetoothConnectYn) {
			this.bluetoothConnectYn = bluetoothConnectYn;
		}
		
	}
	
	class ViewHolder {
		public TextView bluetoothName;
		public TextView bluetoothConnected;
		public Button bluetoothToggle;
	}

	// Bluetooth Connection method.
	private void btConn(final BluetoothDevice btDev) throws IOException
	{
		new connTask().execute(btDev);
	}
	
	// Bluetooth Disconnection method.
	private void btDisconn()
	{
		try
		{
			bluetoothPort.disconnect();
			myprefs.setBluetoothID("");
			myprefs.save();
		}
		catch (Exception e)
		{
			Log.e(TAG, e.getMessage(), e);
		}
		
		if((hThread != null) && (hThread.isAlive()))
			hThread.interrupt();
		
		searchBlutoothList();
	}
		
	// Bluetooth Connection Task.
	class connTask extends AsyncTask<BluetoothDevice, Void, Integer>
	{
		
		
		@Override
		protected void onPreExecute()
		{
			super.onPreExecute();
			progress = ProgressDialog.show(Setting_Bluetooth_Print.this, "프린트", "프린트 연결 중입니다.");
		}
		
		@SuppressLint("UseValueOf")
		@Override
		protected Integer doInBackground(BluetoothDevice... params)
		{
			Integer retVal = null;
			try
			{
			
				bluetoothPort.connectSecure(params[0]);
				
				btAddrBox = params[0].getAddress();
				retVal = new Integer(0);
			}
			catch (IOException e)
			{
				Log.e(TAG, e.getMessage());
				retVal = new Integer(-1);
			}
			return retVal;
		}
		
		@Override
		protected void onPostExecute(Integer result)
		{
			progress.dismiss();
			if(result.intValue() == 0) {
				RequestHandler rh = new RequestHandler();				
				hThread = new Thread(rh);
				hThread.start();
				
				Log.i(TAG,"set bluetooth id is >>> " + btAddrBox);
				myprefs.setBluetoothID(btAddrBox);	
				myprefs.save();
				
				Toast.makeText(Setting_Bluetooth_Print.this, "프린트가 연결 되었습니다.", Toast.LENGTH_LONG).show();
				
				//addPairedDevices();
				searchBlutoothList();

			} else {
				Toast.makeText(Setting_Bluetooth_Print.this, "프린터를 연결하지 못했습니다. 다시 시도해 주세요.", Toast.LENGTH_LONG).show();
			}

			super.onPostExecute(result);
		}
	}
	
	@Override
	   public void onBackPressed() {
		super.onBackPressed();
	      finish();
	     
	   }

}
