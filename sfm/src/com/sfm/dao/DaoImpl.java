package com.sfm.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sfm.model.Fees;
import com.sfm.util.HibernateUtil;

@Repository
public class DaoImpl<T, PK extends Serializable> implements Dao {

	@Autowired
	private SessionFactory sessionFactory;

	//private Class<T> clazz;


	@Override
	public void add(Object t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);		
	}

	@Override
	public void update(Object t) {
		sessionFactory.getCurrentSession().update(t);		
	}

	@Override
	public List list(Class clazz) {
		return sessionFactory.getCurrentSession().createQuery( "from " + clazz.getName() ).list();
	}

	@Override
	public Object getById(Integer id, Class clazz) {
		return (T) sessionFactory.getCurrentSession().get( clazz, id );
	}


	@Override
	public void remove(Object t) {
		sessionFactory.getCurrentSession().delete(t);		
	}

	@Override
	public List list(Integer deptId, Integer year, Integer semester,
			Class clazz) {
		String sql ;
		StringBuffer buffer = new StringBuffer();
		if(deptId != 0){
			buffer.append("department = "+ deptId);
		}

		if(year != 0){
			if(StringUtils.isNotBlank(buffer.toString())){
				buffer.append("year = "+ year);
			}else {
				buffer.append("AND year = "+ year);
			}
		}

		if(semester !=0){
			if(StringUtils.isNotBlank(buffer.toString())){
				buffer.append("semester = "+ semester);
			} else {
				buffer.append("AND semester = "+ semester);
			}
		}
		if(StringUtils.isNotBlank(buffer.toString())){
			sql  = "from "+ clazz.getName() + " where " + buffer.toString();
		} else {
			sql  = "from "+ clazz.getName();
		}
		return sessionFactory.getCurrentSession().createQuery( sql ).list();
	}

	@Override
	public List listFees() {
		Criteria criteria = 
				sessionFactory.getCurrentSession()
			        .createCriteria(Fees.class)
			        .setProjection(Projections.projectionList()            
			            .add(Projections.sum("paidFees"))
			            .add(Projections.sum("pendingFees"))
			            .add(Projections.sum("additionCharges"))
			            .add(Projections.groupProperty("user")));			        
			return (List<Fees>) criteria.list();	}


public static void main(String[] args) throws Exception {
	List<Object> list = (List<Object>)HibernateUtil.getSessionFactory().openSession().createCriteria(Fees.class)
    .setProjection(Projections.projectionList()            
            .add(Projections.sum("paidFees"))
            .add(Projections.sum("pendingFees"))
            .add(Projections.sum("additionCharges"))
            .add(Projections.groupProperty("user"))).list();	
	for (Object fees : list) {
		System.out.println((Fees)fees);
	}
}
}
