package com.nd.rock.client.facade;

public interface ContentObserver {
	
	/**
	 * 数据的观察者接口
	 * 用户实现该接口，将其添加到配置中心的客户端的观察者列表，从而实现数据变更的通知
	 * 具体参见@com.nd.rock.client.facade.Client
	 * @param content
	 */
	public void update(String content);

}
