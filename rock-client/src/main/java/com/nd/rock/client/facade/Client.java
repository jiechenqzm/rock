package com.nd.rock.client.facade;

import java.util.List;
import java.util.Map;

/**
 * 
 * 配置中心的核心客户端
 * 
 * @author QiuZongming
 *
 */
public interface Client {
	
	/**
	 * 获取指定${分组}下面指定${属性}的数据
	 * @param group		${分组}
	 * @param dataId	${属性}
	 * @return
	 */
	public String getContent(String group, String dataId);

	/**
	 * 获取指定${分组}下面指定${属性列表}的数据
	 * @param group		${分组}
	 * @param dataIds	${属性列表}
	 * @return
	 */
	public Map<String, String> getContentBatch(String group, List<String> dataIds);
	
	/**
	 * 对指定${分组}下面指定${属性}的数据的指定${内容}设置${观察者}
	 * 当${内容}发生变化的时候,观察者的update方法被调用,并且传递最新的内容给观察者
	 * @param group		${分组}
	 * @param dataId	${属性}
	 * @param content	${内容}
	 * @param observer	${观察者}
	 */
	public void addObserver(String group, String dataId, String content,
			Observer observer);

}
