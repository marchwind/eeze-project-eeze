/*
 * prefs.java Unlocking Android http://manning.com/ableson Author: W. F. Ableson
 * fableson@msiservices.com
 */

package com.terascope.amano.incheon.common;
 

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class Prefs {

	private SharedPreferences _prefs = null;
	private Editor _editor = null;
	
	private String _bluetoothid = "";
	private String _ProgramVersion = "Version 1";
	private String _FlashFromTime = "";
	private String _FlashToTime = "";
	private String _waitTime = "3";
	private String _Name="";
	private String _UserID="";
	private String _PassWord="";
	
	private String _auth = "";
	private String _devNo = "";
	
	private String _userType = "";
	
	private String _Company="";
	private String _ParkName="";
	private String _Role="";
	private String _DbInit="";
	private String _GCMInit="";
	
	private String _Address="";
	private String _Presentation="";
	private String _Telephone="";
	 
	private String _UserLevel="";
	private String _UserFlag="";
	private String _FirstBT_Printer_Connect="";
	
	private int _ImageWidth=640;
	private int _ImageHeight=480;
	
	private String flashStatue = CommonSet.SettingType.FLASH_CUSTOM;
	private String flashTime = "";
	
	private String macAdress = "";

	private String _Resolution = "";
	
	private String today="";
	
	public String getResolution() {
		if (this._prefs == null) {
			return _Resolution;
		}
		
		this._Resolution = this._prefs.getString("Resolution",_Resolution);
		return _Resolution;
	}

	public void setResolution(String _Resolution) {
		if (this._editor == null) {
			return;
		}
		
		this._Resolution = _Resolution;
		this._editor.putString("Resolution", _Resolution);
	}
	
	public String getToday() {
		if (this._prefs == null) {
			return today;
		}
		this.today = this._prefs.getString("Today", today);
		return this.today;
	}
	public void setToday(String today) {
		if (this._editor == null) {
			return;
		}
		this._editor.putString("Address", today);
	}
	public Prefs(Context context) {
		this._prefs = context.getSharedPreferences("PREFS_PRIVATE",
				Context.MODE_PRIVATE);
		this._editor = this._prefs.edit();
	}

	public String getValue(String key, String defaultvalue) {
		if (this._prefs == null) {
			return "Unknown";
		}

		return this._prefs.getString(key, defaultvalue);
	}

	public void setValue(String key, String value) {
		if (this._editor == null) {
			return;
		}

		this._editor.putString(key, value);

	}

	public String getBluetoothID() {
		if (this._prefs == null) {
			//return "F2:23:45:44:23:11";
			return "";
		}
		this._bluetoothid = this._prefs.getString("bluetoothid",_bluetoothid);
		return this._bluetoothid;
	}
	public String getWaitTime() {
		if (this._prefs == null) {
			return "5";
		}
		this._waitTime = this._prefs.getString("waitTime",_waitTime);
		return this._waitTime;
	}
	
	public String getName() {
		if (this._prefs == null) {
			return "admin";
		}
		this._Name = this._prefs.getString("Name",_Name);
		return this._Name;
	}
	
	public String getUserID() {
		if (this._prefs == null) {
			return "admin";
		}
		this._UserID = this._prefs.getString("userid",_UserID);
		return this._UserID;
	}
	
	public void setEmail(String newemail) {
		if (this._editor == null) {
			return;
		}
		this._editor.putString("emailaddress", newemail);
	}

	public void setServer(String serverurl) {
		if (this._editor == null) {
			return;
		}
		this._editor.putString("serverurl", serverurl);
	}

	public void setBluetoothID(String txtBluetiithID) {
		if (this._editor == null) {
			return;
		}
		this._editor.putString("bluetoothid", txtBluetiithID);
	}	
	
	public void setWaitTime(String txtWaitTime) {
		if (this._editor == null) {
			return;
		}
		this._editor.putString("waitTime", txtWaitTime);
	}	
	
	public void setName(String txtName) {
		if (this._editor == null) {
			return;
		}
		this._editor.putString("Name", txtName);
	}
	
	public void setUserID(String txtName) {
		if (this._editor == null) {
			return;
		}
		this._editor.putString("userid", txtName);
	}
//-------------------------------------------- Image Width
	
	public int getWidth() {
		if (this._prefs == null) {
			return _ImageWidth;
		}
		this._ImageWidth = this._prefs.getInt("ImageWidth",
				_ImageWidth);
		return this._ImageWidth;
	}
	public void setWidth(int data) {
		if (this._editor == null) {
			return;
		}
		this._editor.putInt("ImageWidth", data);
	}
	
	
//--------------------------------------------------Image Height
	public int getHeight() {
		if (this._prefs == null) {
			return _ImageHeight;
		}
		this._ImageHeight = this._prefs.getInt("ImageHeight",
				_ImageHeight);
		return this._ImageHeight;
	}
	public void setHeight(int data) {
		if (this._editor == null) {
			return;
		}
		this._editor.putInt("ImageHeight", data);
	}

//-------------------------------------------------------------	
 
	public String getCompany() {
		if (this._prefs == null) {
			return _Company;
		}
		this._Company = this._prefs.getString("Company",_Company);
		return this._Company;
	}
	public void setCompany(String data) {
		if (this._editor == null) {
			return;
		}
		this._editor.putString("Company", data);
	}
	//-------------------------------------------------------------		 
	public String getParkName() {
		if (this._prefs == null) {
			return _ParkName;
		}
		this._ParkName = this._prefs.getString("ParkName",_ParkName);
		return this._ParkName;
	}
	public void setParkName(String data) {
		if (this._editor == null) {
			return;
		}
		this._editor.putString("ParkName", data);
	}
	
 
	//-------------------------------------------------------------		 
	public String getAddress() {
		if (this._prefs == null) {
			return _Address;
		}
		this._Address = this._prefs.getString("Address",_Address);
		return this._Address;
	}
	public void setAddress(String data) {
		if (this._editor == null) {
			return;
		}
		this._editor.putString("Address", data);
	}
	
 
	//-------------------------------------------------------------		 
	public String getPresentation() {
		if (this._prefs == null) {
			return _Presentation;
		}
		this._Presentation = this._prefs.getString("Presentation",_Presentation);
		return this._Presentation;
	}
	public void setPresentation(String data) {
		if (this._editor == null) {
			return;
		}
		this._editor.putString("Presentation", data);
	}
 
	//-------------------------------------------------------------		 
	public String getTelephone() {
		if (this._prefs == null) {
			return _Telephone;
		}
		this._Telephone = this._prefs.getString("Telephone",_Telephone);
		return this._Telephone;
	}
	public void setTelephone(String data) {
		if (this._editor == null) {
			return;
		}
		this._editor.putString("Telephone", data);
	}
	
 
//-------------------------------------------------------------	

	public String getRole() {
		if (this._prefs == null) {
			return _Role;
		}
		this._Role = this._prefs.getString("Role",_Role);
		return this._Role;
	}
	public void setRole(String data) {
		if (this._editor == null) {
			return;
		}
		this._editor.putString("Role", data);
	}

	public String getUserLevel() {
		if (this._prefs == null) {
			return _UserLevel;
		}
		this._UserLevel = this._prefs.getString("UserLevel",_UserLevel);
		return this._UserLevel;
	}
	public void setUserLevel(String data) {
		if (this._editor == null) {
			return;
		}
		this._editor.putString("UserLevel", data);
	}
	
	//-------------------------------------------------------------	

		public String getFirstBT_Printer_Connect() {
			if (this._prefs == null) {
				return _FirstBT_Printer_Connect;
			}
			this._FirstBT_Printer_Connect = this._prefs.getString("FirstBT_Printer_Connect",_FirstBT_Printer_Connect);
			return this._FirstBT_Printer_Connect;
		}
		public void setFirstBT_Printer_Connect(String data) {
			if (this._editor == null) {
				return;
			}
			this._editor.putString("FirstBT_Printer_Connect", data);
		}
		 
	//-------------------------------------------------------------	

		public String getPassWord() {
			if (this._prefs == null) {
				return _PassWord;
			}
			this._PassWord = this._prefs.getString("PassWord",_PassWord);
			return this._PassWord;
		}
		public void setPassWord(String data) {
			if (this._editor == null) {
				return;
			}
			this._editor.putString("PassWord", data);
		}	
//-------------------------------------------------------------	
	
	public String getUserFlag() {
		if (this._prefs == null) {
			return _UserFlag;
		}
		this._UserFlag = this._prefs.getString("UserFlag",_UserFlag);
		return this._UserFlag;
	}
	public void setUserFlag(String data) {
		if (this._editor == null) {
			return;
		}
		this._editor.putString("UserFlag", data);
	}
//-------------------------------------------------------------	

	public String getDB_Init_Flag() {
		if (this._prefs == null) {
			return _DbInit;
		}
		this._DbInit = this._prefs.getString("DbInit",_DbInit);
		return this._DbInit;
	}
	
	public void setDB_Init_Flag(String data) {
		if (this._editor == null) {
			return;
		}
		this._editor.putString("DbInit", data);
	}	
		
		//-------------------------------------------------------------	

	public String getGCM_Flag() {
		if (this._prefs == null) {
			return _GCMInit;
		}
		this._GCMInit = this._prefs.getString("GCMInit",_GCMInit);
		return this._GCMInit;
	}
	
	public void setGCM_Flag(String data) {
		if (this._editor == null) {
			return;
		}
		this._editor.putString("GCMInit", data);
	}		
	//-------------------------------------------------------------	

	public String get_Version() {
		if (this._prefs == null) {
			return _ProgramVersion;
		}
		this._ProgramVersion = this._prefs.getString("Version",_ProgramVersion);
		return this._ProgramVersion;
	}
	
	public void set_Version(String data) {
		if (this._editor == null) {
			return;
		}
		this._editor.putString("Version", data);
	}	
 
	public String getFlashStatue() {
		if (this._prefs == null) {
			return flashStatue;
		}
		
		this.flashStatue = this._prefs.getString("flashStatue",flashStatue);
		return flashStatue;
	}

	public void setFlashStatue(String flashStatue) {
		if (this._editor == null) {
			return;
		}
		
		this._editor.putString("flashStatue", flashStatue);
	}

	public String getFlashTime() {
		if (this._prefs == null) {
			return flashTime;
		}
		
		this.flashTime = this._prefs.getString("flashTime",flashTime);
		return flashTime;
	}

	public void setFlashTime(String flashTime) {
		if (this._editor == null) {
			return;
		}
		
		this._editor.putString("flashTime", flashTime);
		
	}

	

	public String getMacAdress() {
		if (this._prefs == null) {
			return macAdress;
		}
		
		this.macAdress = this._prefs.getString("macAddress",macAdress);
		return macAdress;
	}

	public void setMacAdress(String macAdress) {
		if (this._editor == null) {
			return;
		}
		
		this.macAdress = macAdress;
		this._editor.putString("macAddress", macAdress);
		
	}	

	public String getAuth() {
		if (this._prefs == null) {
			return _auth;
		}
		
		this._auth = this._prefs.getString("Auth",_auth);
		return _auth;
	}

	public void setAuth(String _auth) {
		if (this._editor == null) {
			return;
		}
		
		this._auth = _auth;
		this._editor.putString("Auth", _auth);
		
	}
	

	public String getDevNo() {
		if (this._prefs == null) {
			return _devNo;
		}
		
		this._devNo = this._prefs.getString("DevNo",_devNo);
		return _devNo;
	}

	public void setDevNo(String _devNo) {
		if (this._editor == null) {
			return;
		}
		
		this._devNo = _devNo;
		this._editor.putString("DevNo", _devNo);
	}
	
	public String getUserType() {
		if (this._prefs == null) {
			return _userType;
		}
		
		this._userType = this._prefs.getString("userType",_userType);
		return _userType;
	}

	public void setUserType(String _userType) {
		if (this._editor == null) {
			return;
		}
		
		this._userType = _userType;
		this._editor.putString("userType", _userType);
	}

	public String getFlashFromTime() {
		if (this._prefs == null) {
			return _FlashFromTime;
		}
		this._FlashFromTime = this._prefs.getString("FlashFromTime",_FlashFromTime);
		return this._FlashFromTime;
	}
	public void setFlashFromTime(String hour) {
		if (this._editor == null) {
			return;
		}
		this._editor.putString("FlashFromTime", hour);
	}
	
	public String getFlashToTime() {
		if (this._prefs == null) {
			return _FlashToTime;
		}
		this._FlashToTime = this._prefs.getString("FlashToTime",_FlashToTime);
		return this._FlashToTime;
	}
	public void setFlashToTime(String hour) {
		if (this._editor == null) {
			return;
		}
		this._editor.putString("FlashToTime", hour);
	}
	
	
	public void save() {
		if (this._editor == null) {
			return;
		}
		this._editor.commit();
	}
	
}
