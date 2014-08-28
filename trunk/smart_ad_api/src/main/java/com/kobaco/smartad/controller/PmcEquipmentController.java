package com.kobaco.smartad.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kobaco.smartad.model.service.CommonListResult;
import com.kobaco.smartad.model.service.CommonPage;
import com.kobaco.smartad.model.service.CommonResult;
import com.kobaco.smartad.model.service.CommonSingleResult;
import com.kobaco.smartad.model.service.EquipCheckInfo;
import com.kobaco.smartad.model.service.EquipCheckRefData;
import com.kobaco.smartad.model.service.EquipInfo;
import com.kobaco.smartad.model.service.EquipUsedRefData;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.model.service.PmcSessionInfo;
import com.kobaco.smartad.model.service.UserInfo;
import com.kobaco.smartad.service.PmcEquipmentService;
import com.kobaco.smartad.utils.CommonCode;
import com.kobaco.smartad.utils.CommonMsg;


@Controller
@SessionAttributes("sessionManagerInfo")
@RequestMapping(value="/pmc/equip")
public class PmcEquipmentController {

	private static final Logger logger = LoggerFactory.getLogger(PmcEquipmentController.class);
	
	@Autowired
	public PmcEquipmentService equipService;
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(@RequestParam (value="id",defaultValue="" )String id){	
      
        return "pmcequip/"+id+"Form";
   }
	@ModelAttribute("sessionManagerInfo")
	public PmcSessionInfo setSessionManagerinfo() {
		return new PmcSessionInfo(){{
			setLogin(false);
		}};
	}	
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<EquipInfo> add(@ModelAttribute PmcMnagerInfo info,
														@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser,
														@ModelAttribute EquipInfo equipInfo){				
		CommonSingleResult<EquipInfo> is = equipService.equipAdd(info, new PmcMnagerInfo(sessUser), equipInfo);			
		return is;
	}
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<EquipInfo> delete(@ModelAttribute PmcMnagerInfo info,
														@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser,
														@ModelAttribute EquipInfo equipInfo){				
		CommonSingleResult<EquipInfo> is = equipService.equipDelete(info, new PmcMnagerInfo(sessUser), equipInfo);			
		return is;
	}
	@RequestMapping(value = "/rent", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<EquipInfo> rent(@ModelAttribute PmcMnagerInfo info,
														@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser,
														@ModelAttribute EquipInfo equipInfo,
														@ModelAttribute UserInfo userInfo){				
		CommonSingleResult<EquipInfo> is = equipService.equipRent(info, new PmcMnagerInfo(sessUser), equipInfo, userInfo);			
		return is;
	}
	@RequestMapping(value = "/rentInfo", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<EquipInfo> rentInfo(@ModelAttribute PmcMnagerInfo info,
														@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser,
														@ModelAttribute EquipInfo equipInfo){				
		CommonSingleResult<EquipInfo> is = equipService.equipRentInfo(info, new PmcMnagerInfo(sessUser), equipInfo);			
		return is;
	}
	@RequestMapping(value = "/return", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<EquipInfo> returnRent(@ModelAttribute PmcMnagerInfo info,
														@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser,
														@ModelAttribute EquipInfo equipInfo){				
		CommonSingleResult<EquipInfo> is = equipService.equipRentReturn(info, new PmcMnagerInfo(sessUser), equipInfo);			
		return is;
	}
	@RequestMapping(value = "/rentList", method = RequestMethod.POST)
	public @ResponseBody CommonListResult<EquipInfo>  rentList(@ModelAttribute CommonPage cp,
														 @ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser,
														 @ModelAttribute EquipInfo equipInfo,
														 @ModelAttribute PmcMnagerInfo info){	
		CommonListResult<EquipInfo> is = equipService.getRentList(equipInfo,cp,new PmcMnagerInfo(sessUser),info);		
		return is;
	}
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody CommonListResult<EquipInfo>  list(@ModelAttribute CommonPage cp,
														 @ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser,
														 @ModelAttribute EquipInfo equipInfo,
														 @ModelAttribute PmcMnagerInfo info){	
		CommonListResult<EquipInfo> is = equipService.getList(equipInfo,cp,new PmcMnagerInfo(sessUser),info);		
		return is;
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<EquipInfo>  get(@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser,
			@RequestParam (value="managerMode",defaultValue="" ) String mode,
			@RequestParam (value="equipNo",defaultValue=""     ) String equipNo) 
			{	
		
		if( !sessUser.isLogin() ||  !CommonCode.PmcManagerCode.PMC_YES.equals(mode)) {	
			return new CommonSingleResult<EquipInfo> (new CommonResult(CommonMsg.failCodeUnAuthrized, CommonMsg.failMsgUnAuthrized), null);
		}
		
		return equipService.getInfo(equipNo);
	}
	
	@RequestMapping(value = "/getByTag", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody CommonSingleResult<EquipInfo>  getByTag(HttpSession session,
			@RequestParam (value="nfcTagId",defaultValue=""    ) String nfcTagId) {	
		return equipService.getInfoByTag(nfcTagId);
	}
	
	@RequestMapping(value = "/checkHistory", method = RequestMethod.POST)
	public @ResponseBody CommonListResult<EquipCheckRefData>  checkHistory(@ModelAttribute CommonPage cp,
			@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser,
			@RequestParam (value="managerMode",defaultValue="" ) String mode,
			@RequestParam (value="equipNo",defaultValue=""     ) String equipNo) {	
		
		if( !sessUser.isLogin() ||  !CommonCode.PmcManagerCode.PMC_YES.equals(mode)) {	
			return new CommonListResult<EquipCheckRefData> (new CommonResult(CommonMsg.failCodeUnAuthrized, CommonMsg.failMsgUnAuthrized),
					null,
					cp);
		}
		
		return equipService.getCheckHistory(equipNo, cp);
	}
	
	@RequestMapping(value = "/usedHistory", method = RequestMethod.POST)
	public @ResponseBody CommonListResult<EquipUsedRefData>  usedHistory(@ModelAttribute CommonPage cp,
			@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser,
			@RequestParam (value="managerMode",defaultValue="" ) String mode,
			@RequestParam (value="equipNo",defaultValue=""     ) String equipNo) {	
		
		if( !sessUser.isLogin() ||  !CommonCode.PmcManagerCode.PMC_YES.equals(mode)) {	
			return new CommonListResult<EquipUsedRefData> (new CommonResult(CommonMsg.failCodeUnAuthrized, CommonMsg.failMsgUnAuthrized),
					null,
					cp);
		}

		return equipService.getUsedHistory(equipNo, cp);
	}
	
	@RequestMapping(value = "/updatePowerState", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<EquipInfo> update(
			@RequestParam (value="managerMode", defaultValue="") String mode,
			@RequestParam (value="equipNo",     defaultValue="") String equipNo,
			@RequestParam (value="powerStateCd",defaultValue="") String powerStateCd, 
			@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser){	
		if( !sessUser.isLogin() ||  !CommonCode.PmcManagerCode.PMC_YES.equals(mode)) {	
			return new CommonSingleResult<EquipInfo> (new CommonResult(CommonMsg.failCodeUnAuthrized, CommonMsg.failMsgUnAuthrized),
					null);
		}
		return equipService.updatePowerState(equipNo, powerStateCd, new PmcMnagerInfo(sessUser));			
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<EquipInfo> update(@RequestParam (value="managerMode",defaultValue="" ) String mode,
														@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser,
														@ModelAttribute EquipInfo equipInfo){	
		if( !sessUser.isLogin() ||  !CommonCode.PmcManagerCode.PMC_YES.equals(mode)) {	
			return new CommonSingleResult<EquipInfo> (new CommonResult(CommonMsg.failCodeUnAuthrized, CommonMsg.failMsgUnAuthrized),
					null);
		}
		
		return equipService.update(equipInfo, new PmcMnagerInfo(sessUser));			
	}
	
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public @ResponseBody CommonSingleResult<EquipCheckInfo> check(@RequestParam (value="managerMode",defaultValue="" ) String mode,
														@ModelAttribute("sessionManagerInfo") PmcSessionInfo sessUser,
														@ModelAttribute EquipCheckInfo checkInfo){	
		if( !sessUser.isLogin() ||  !CommonCode.PmcManagerCode.PMC_YES.equals(mode)) {	
			return new CommonSingleResult<EquipCheckInfo> (new CommonResult(CommonMsg.failCodeUnAuthrized, CommonMsg.failMsgUnAuthrized),
					null);
		}
		
		return equipService.check(checkInfo, new PmcMnagerInfo(sessUser));			
	}
	
}
