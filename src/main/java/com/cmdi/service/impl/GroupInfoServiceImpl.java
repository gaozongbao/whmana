package com.cmdi.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cmdi.dao.GroupInfoDao;
import com.cmdi.model.GroupInfo;
import com.cmdi.service.GroupInfoService;
import com.cmdi.util.CustomerContextHolder;

/**
 * 测试Service
 * 
 * @author: 高宗宝
 * @Description:事务管理需要注意数据引擎，异常类型为RuntimeException
 */
@Service

public class GroupInfoServiceImpl implements GroupInfoService {
	@Autowired
	private GroupInfoDao groupInfoDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	//尽量不要使用一个启用事务的方法，调用同一个service中另外一个启动事务的方法，可能会产生自调用问题，引起事务失效
	//在事务方法中如果使用try catch，需要手动抛出RuntimeException让spring捕捉这个异常才可以事务有效
	public GroupInfo get(String id) throws Exception {
		// TODO Auto-generated method stub
		return groupInfoDao.selectByPrimaryKey(id);
	}

}
