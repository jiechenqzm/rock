package com.nd.rock.client.facade.decorator;

import java.util.List;
import java.util.Map;

import com.nd.rock.client.facade.Client;
import com.nd.rock.client.facade.ContentObserver;
import com.nd.rock.common.util.QListUtil;
import com.nd.rock.common.util.QStringUtil;

/**
 * Client的参数校验装饰者
 * @author QiuZongming
 *
 */
public class ParamCheckClientDecorator extends AbstractClientDecorator {
		
	public ParamCheckClientDecorator(Client client) {
		super(client);
	}

	@Override
	public String getContent(String group, String dataId) {
		if(QStringUtil.notEmpty(group, dataId))
			return super.client.getContent(group, dataId);
		
		throw new IllegalArgumentException("Param 'group','dataId' Can't Be Null Or Empty!");
	}

	@Override
	public Map<String, String> getContentBatch(String group, List<String> dataIds) {
		if(QStringUtil.notEmpty(group) && QListUtil.notEmptyContent(dataIds))
			return super.client.getContentBatch(group, dataIds);

		throw new IllegalArgumentException("Param 'group','dataIds' Can't Be Null, Empty Or EmptyContent!");
	}

	@Override
	public boolean registerObserver(String group, String dataId, String initContent,
			ContentObserver contentObserver) {
		if(QStringUtil.notEmpty(group, dataId) && contentObserver != null)
			return super.client.registerObserver(group, dataId, initContent, contentObserver);
		
		throw new IllegalArgumentException("Param 'group','dataIds','contentObserver' Can't Be Null Or Empty!");

	}
	
	@Override
	public boolean removeObserver(String group, String dataId,
			ContentObserver contentObserver) {
		if(QStringUtil.notEmpty(group, dataId) && contentObserver != null)
			return super.client.removeObserver(group, dataId, contentObserver);
		
		throw new IllegalArgumentException("Param 'group','dataIds','contentObserver' Can't Be Null Or Empty!");
	}

}
