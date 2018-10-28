package com.hs.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hs.entity.RegistrationEntity;
import com.hs.util.HibernateUtil;
import com.hs.vo.RegistrationVO;

public class RegistrationDaoImpl implements RegistrationDAO{

	@Override
	public RegistrationVO insertData(RegistrationVO registrationVO) {
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		RegistrationEntity registrationEntity=new RegistrationEntity();
		registrationEntity.setUserName(registrationVO.getUserName());
		registrationEntity.setPassword(registrationVO.getPassword());
		registrationEntity.setProfession(registrationVO.getProfession());
		registrationEntity.setAddress(registrationVO.getAddress());
		registrationEntity.setAge(registrationVO.getAge());
		registrationEntity.setMobile(registrationVO.getMobile());
		session.save(registrationEntity);
		transaction.commit();
		session.close();
		return registrationVO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegistrationVO> getData() {
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		Query query=(Query) session.createQuery("from RegistrationEntity");		
		List<RegistrationEntity> entityList=query.list();
		List<RegistrationVO> registrationList=new ArrayList<RegistrationVO>();
		for(RegistrationEntity entity:entityList){
			RegistrationVO registrationVO=new RegistrationVO();
			registrationVO.setUserName(entity.getUserName());
			registrationVO.setPassword(entity.getPassword());
			registrationVO.setProfession(entity.getProfession());
			registrationVO.setAddress(entity.getAddress());
			registrationVO.setAge(entity.getAge());
			registrationVO.setMobile(entity.getMobile());
			registrationList.add(registrationVO);
		}
		transaction.commit();
		session.close();
		return registrationList;
	}
}