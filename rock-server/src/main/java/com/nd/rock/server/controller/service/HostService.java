package com.nd.rock.server.controller.service;

import java.net.InetAddress;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nd.rock.common.net.bean.response.FinalHeartBeatResponse;
import com.nd.rock.common.net.bean.response.FinalGetHostResponse;
import com.nd.rock.common.net.bean.response.base.HeartBeatResponse;
import com.nd.rock.common.net.bean.response.base.HostResponse;

@Controller
@RequestMapping("/host")
public class HostService extends AbstractService {

	/**
	 * 获取服务器列表，目前mock为本机ip
	 */
	@RequestMapping(value = "/getHostList.do", method = RequestMethod.POST)
	public void doGetHostList(HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap) {
		try {
//			GetHostParam getHostParam = GetHostParam.fromJsonString(param);
			
			String host = InetAddress.getLocalHost().getHostAddress().toString();
			FinalGetHostResponse responseBody = new FinalGetHostResponse(
					new HostResponse(Arrays.asList(host)));
			doResponse(response, responseBody);
		} catch (Exception e) {
			super.doErrorResponse(response, "DoGetHost Error.", e);
		}
	}
	
	/**
	 * 心跳测试，测试服务器存活
	 */
	@RequestMapping(value = "/heartBeat.do", method = RequestMethod.POST)
	public void doHeartBeat(HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap) {
		try {
			FinalHeartBeatResponse responseBody = new FinalHeartBeatResponse(
					new HeartBeatResponse(true));
			doResponse(response, responseBody);
		} catch (Exception e) {
			super.doErrorResponse(response, "DoCheckHost Error.", e);
		}
	}

}
