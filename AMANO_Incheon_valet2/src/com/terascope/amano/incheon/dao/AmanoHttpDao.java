package com.terascope.amano.incheon.dao;

import android.util.Log;

import com.terascope.amano.incheon.dao.helper.HttpHelper;
import com.terascope.amano.incheon.dto.AncmDto;
import com.terascope.amano.incheon.dto.ApiResponse;
import com.terascope.amano.incheon.dto.AuthDto;
import com.terascope.amano.incheon.dto.CarInfoDto;
import com.terascope.amano.incheon.dto.LoginDto;
import com.terascope.amano.incheon.dto.RectDto;
import com.terascope.amano.incheon.dto.ResvDto;
import com.terascope.amano.incheon.dto.UpdDto;

public class AmanoHttpDao {
 
	private HttpHelper http = new HttpHelper();
	//private static final String serverBaseUrl = "http://amano.com/IVPService.svc/";
	private static final String serverBaseUrl = "http://61.41.4.6/ivpwsj/ivpservice.svc/";
	public void useAuth(AuthDto auth) {
		auth.valueOf(
				new ApiResponse(
						http.httpGet(serverBaseUrl+"MIF_MOBILE_USE_AUTH/"+auth.toRequest())));
	}
	
	public void login(LoginDto login) {
		login.valueOf(new ApiResponse(http.httpGet(serverBaseUrl+"MIF_LOGIN/"+login.toRequest())));
	}
	public void selAncm(AncmDto ancm) {
		ancm.valueOf(new ApiResponse(http.httpGet(serverBaseUrl+"MIF_SEL_ANCM/"+ancm.toRequest())));
	}
	public void selResv(ResvDto resv) {
		resv.valueOf(new ApiResponse(http.httpGet(serverBaseUrl+"MIF_SEL_RESV/"+resv.toRequest())));
	}
	public void reqCarinfo(CarInfoDto carinfo) {
		carinfo.valueOf(new ApiResponse(http.httpGet(serverBaseUrl+"MIF_REQ_CARINFO/"+carinfo.toRequest().request())));
	}
	
	public void insRect(RectDto rect) {
		rect.valueOf(new ApiResponse(http.httpGet(serverBaseUrl+"MIF_INS_RECT/"+rect.toRequest())));
	}
	public void upd(UpdDto upd) {
		upd.valueOf(new ApiResponse(http.httpGet(serverBaseUrl+"MIF_UPD/"+upd.toRequest())));
	}
	 
}
