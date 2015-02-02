package com.nd.rock.server.model.container.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.FatalBeanException;

import com.nd.rock.common.exception.CallableExecuteException;
import com.nd.rock.common.net.bean.impl.DataKeyOb;
import com.nd.rock.server.controller.multithread.NamedThreadFactory;
import com.nd.rock.server.model.container.Container;
import com.nd.rock.server.model.container.CoreDataObserver;
import com.nd.rock.server.model.dao.CoreDataDAO;
import com.nd.rock.server.model.instance.CoreDataIn;

public class DefaultContainer implements CoreDataObserver {
	
    private static Logger logger = LoggerFactory.getLogger(DefaultContainer.class);

	private Container fileContainer = null;
	
	private Container summaryContainer = null;
	
	private CoreDataDAO coreDataDAO = null;
	
	private ExecutorService executorService = null;
	
	private LinkedBlockingQueue<DataKeyOb> changeBuffer = new LinkedBlockingQueue<>(256);
	
	@Override
	public void update(DataKeyOb dataKeyOb) {
		/*将通知逻辑转成异步操作*/
		try {
			if(!this.changeBuffer.offer(dataKeyOb, 100, TimeUnit.MILLISECONDS)) {
				logger.error("ChangeBuffer Is Full. Drop Data : Group >> " + dataKeyOb.getGroup() + ", dataId >> " + dataKeyOb.getDataId());
			}
		} catch (InterruptedException e) {
			logger.error("Param Is Empty. Group >> " + dataKeyOb.getGroup() + ", dataId >> " + dataKeyOb.getDataId());
		}
	}
	
	public void init() {
		try {
			/* 启动清理已有的旧数据，暂时先注释掉。
			fileContainer.clear();
			summaryContainer.clear();*/
			this.coreDataDAO.logicQueryAll(new DefaultContainerCallable(fileContainer, summaryContainer));
			this.executorService = Executors.newFixedThreadPool(1, new NamedThreadFactory("DefaultContainer-Observer"));
			this.executorService.execute(new UpdateTask());
			logger.info("DefaultContainer Init Success.");
		} catch (SQLException|CallableExecuteException e) {
			String messgage = "DefaultContainer Init Failed.";
			logger.error(messgage, e);
			throw new FatalBeanException(messgage, e);
		}
	}

	public class UpdateTask implements Runnable {
		@Override
		public void run() {
			while(true) {
				try {
					handleEvent(changeBuffer.take());
				} catch (InterruptedException | IOException e) {
					logger.error("Run UpdateTask Error.", e);
				}
			}
		}
		
		private void handleEvent(DataKeyOb dataKeyOb) throws IOException{
			CoreDataIn coreDataIn = coreDataDAO.logicQuery(dataKeyOb.getGroup(), dataKeyOb.getDataId());
			if(coreDataIn == null) {
				fileContainer.delete(dataKeyOb.getGroup(), dataKeyOb.getDataId());
				summaryContainer.delete(dataKeyOb.getGroup(), dataKeyOb.getDataId());
			}
			else {
				fileContainer.update(coreDataIn.getGroup(), coreDataIn.getDataId(), coreDataIn.getContent());
				summaryContainer.update(coreDataIn.getGroup(), coreDataIn.getDataId(), coreDataIn.getSummary());
			}
		}
	}

	public void setFileContainer(Container fileContainer) {
		this.fileContainer = fileContainer;
	}

	public void setSummaryContainer(Container summaryContainer) {
		this.summaryContainer = summaryContainer;
	}

	public void setCoreDataDAO(CoreDataDAO coreDataDAO) {
		this.coreDataDAO = coreDataDAO;
	}
}
