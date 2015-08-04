package com.sfm.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sfm.model.Attendance;
import com.sfm.model.Charges;
import com.sfm.model.CompoundExpenses;
import com.sfm.model.CompoundFees;
import com.sfm.model.Data;
import com.sfm.model.Faculty;
import com.sfm.model.Fees;
import com.sfm.model.User;
import com.sfm.util.JQueryDataTableParamModel;

@Repository
public class DaoImpl<T, PK extends Serializable> implements Dao {

	Session session ;

	@Autowired
	private SessionFactory sessionFactory;

	//private Class<T> clazz;


	@Override
	public void save(Object t) {
		try{
			session = getSession();
			session.saveOrUpdate(t);		
		}finally{
			closeSession(session);
		}
	}

	@Override
	public List list(Class clazz) {
		try{
			session = getSession();
			return session.createQuery( "from " + clazz.getName() +" order by updatedOn desc" ).list();
		}finally{
			closeSession(session);
		}
	}

	@Override
	public int listCount(Class clazz) {
		try{
			session = getSession();
			Criteria criteria = session.createCriteria(clazz.getName());
			Object result = criteria.setProjection(Projections.rowCount()).uniqueResult();	 
			return (null == result) ? 0 : ((Number) result).intValue();
		}finally{
			closeSession(session);
		}
	}

	private int listCountParam(JQueryDataTableParamModel param, Class clazz){
		String extraParam = getSearchCreteria(param)+ getOrderBy(param);
		String strQry = "select count(*) from " + clazz.getName() + " "+ extraParam ;
		try{
			session = getSession();
			Query query = session.createQuery(strQry);
			Long count = (Long) query.uniqueResult();
			return count.intValue();
		}finally{
			closeSession(session);
		}
	}

	protected List listByPage(Class clazz, int firstResult, int maxResult, String orderBy, boolean direction) {
		String strQry = "from " + clazz.getName() + " order by "+orderBy+" " + getDirection(direction);
		try{
			session = getSession();
			Query query = this.sessionFactory.getCurrentSession().createQuery(strQry);
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);

			return query.list();
		}finally{
			closeSession(session);
		}
	}



	private String getDirection(boolean direction) {
		return direction?"ASC":"DESC";
	}

	@Override
	public Object getById(Integer id, Class clazz) {
		try{
			session = getSession();
			return (T) session.get( clazz, id );
		}finally{
			closeSession(session);
		}
	}


	@Override
	public void remove(Object t) {
		try{
			session = getSession();
			session.delete(t);	
		}finally{
			closeSession(session);
		}
	}

	@Override
	public Data list(JQueryDataTableParamModel param, Class clazz) {
		try{
			session = getSession();
			String extraParam = getSearchCreteria(param)+ getOrderBy(param);
			String strQry = "from " + clazz.getName() + " "+ extraParam ;

			Query query = session.createQuery(strQry);
			query.setFirstResult(param.getiDisplayStart());
			query.setMaxResults(param.getiDisplayLength());		
			List list = query.list(); 
			return new Data(list,listCountParam(param, clazz),list.size()); // list - display record - total record
		}finally{
			closeSession(session);
		}
	}

	private String getSearchCreteria(JQueryDataTableParamModel param) {
		try{
			session = getSession();
			StringBuffer searchStr = new StringBuffer();
			if(StringUtils.isNotEmpty(param.getSearchTerm())){
				searchStr.append(" WHERE ");
				String []cols = null;
				if(param.getsColumns().length() > 0 ){
					cols = param.getsColumns().split(",");	
				}
				for (int index = 0 ; index < cols.length ; index++) {
					if(cols.length -1 == index){
						searchStr .append(cols[index]+" like '%"+param.getSearchTerm()+"%'");
					} else {
						searchStr .append(cols[index]+" like '%"+param.getSearchTerm()+"%'").append(" OR ");
					}
				}
			}
			return searchStr.toString();
		}finally{
			closeSession(session);
		}
	}

	private String getOrderBy(JQueryDataTableParamModel param) {
		try{
			session = getSession();
			String []cols = null;
			if(param.getsColumns().length() > 0 ){
				cols = param.getsColumns().split(",");	
			}
			int index = param.getiSortColumnIndex();
			return (cols!=null && cols.length > 0)?" order by " + cols[index] +" "+ param.getsSortDirection(): "";
		}finally{
			closeSession(session);
		}
	}

	@Override
	public List listFees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompoundFees getCompoundFeesByUserId(Integer id) {
		try{
			session = getSession();
			String SQL="Select u.id id," +
					"concat(u.firstName,' ',u.middleName,' ',u.lastname) fullName, " +
					"u.fatherName fatherName,"+
					"totalFees, " +
					"count(noOfInstallment)," +
					"sum(paidFees) totalPaidFees," +
					"sum(additionCharges) totalAdditionCharges," +
					"totalFees+ totalExpenses-(sum(paidFees) + sum(additionCharges)) totalPendingFees," +
					"nextPaymentDueDate nextDueDate, " +
					"totalExpenses  " +
					"from " +
					"fees f " +
					"inner join User u on u.id=f.userId and u.id="+id +" "+
					"left outer join (select userId, sum(amount) totalExpenses from charges group by userId) ch on f.userId = ch.userId " +
					"group by f.userId order by u.id asc";
			return (CompoundFees) session.createSQLQuery(SQL).addEntity(CompoundFees.class).uniqueResult();
		}finally{
			closeSession(session);
		}
	}

	@Override
	public Data listCompoundFees(JQueryDataTableParamModel param) {
		try{
			session = getSession();
			String searchParam = param != null? getSearchCreteria(param):"";
			String orderByParam = param != null? getOrderBy(param):"";
			String SQL="SELECT * FROM (Select u.id id," +
					"concat(u.firstName,' ',u.middleName,' ',u.lastname) fullName, " +
					"u.fatherName fatherName,"+
					"totalFees, " +
					"count(noOfInstallment)," +
					"sum(paidFees) totalPaidFees," +
					"sum(additionCharges) totalAdditionCharges," +
					"totalFees+ totalExpenses-(sum(paidFees) + sum(additionCharges)) totalPendingFees," +
					"nextPaymentDueDate nextDueDate, " +
					"totalExpenses  " +
					"from " +
					"fees f " +
					"inner join User u on u.id=f.userId " +
					"left outer join (select userId, sum(amount) totalExpenses from charges group by userId) ch on f.userId = ch.userId " +				
					"group by f.userId ) a " +searchParam + orderByParam;
			SQLQuery query = session.createSQLQuery(SQL);
			if(param != null){
				System.out.println("search with: "+param.getiDisplayStart()+" - "+param.getiDisplayLength() );
				query.setFirstResult(param.getiDisplayStart());
				query.setMaxResults(param.getiDisplayLength());	
			}
			List list = query.addEntity("f", CompoundFees.class).list();
			return new Data(list,countParam(param),list.size());
		}finally{
			closeSession(session);
		}
	}

	private int countParam(JQueryDataTableParamModel param) {
		try{
			session = getSession();
			String searchParam = param != null? getSearchCreteria(param):"";
			String orderByParam = param != null? getOrderBy(param):"";
			String SQL="SELECT count(distinct id) FROM (Select u.id id," +
					"concat(u.firstName,' ',u.middleName,' ',u.lastname) fullName, " +
					"u.fatherName fatherName,"+
					"totalFees, " +
					"count(noOfInstallment)," +
					"sum(paidFees) totalPaidFees," +
					"sum(additionCharges) totalAdditionCharges," +
					"totalFees+ totalExpenses-(sum(paidFees) + sum(additionCharges)) totalPendingFees," +
					"nextPaymentDueDate nextDueDate, " +
					"totalExpenses  " +
					"from " +
					"fees f " +
					"inner join User u on u.id=f.userId " +
					"left outer join (select userId, sum(amount) totalExpenses from charges) ch on f.userId = ch.userId " +				
					"group by f.userId ) a " +searchParam + orderByParam;
			BigInteger count = (BigInteger)session.createSQLQuery(SQL).uniqueResult();
			return count.intValue();
		}finally{
			closeSession(session);
		}
	}

	@Override
	public List<CompoundExpenses> listCompoundExpenses() {
		try{
			session = getSession();
			String SQL= "Select " +
					"c.userId id , " +
					"concat(u.firstName,' ',u.middleName,' ',u.lastName) fullName ," +
					"u.fatherName, sum(c.Amount) totalAmount " +
					"from " +
					"charges c " +
					"left outer join user u on c.userId = u.id " +
					"group by userId order by userId asc;";
			return session.createSQLQuery(SQL).addEntity("f", CompoundExpenses.class).list();
		}finally{
			closeSession(session);
		}
	}

	private Session getSession() {
		try{
			return sessionFactory.getCurrentSession();
		}catch(Exception ex){
			ex.printStackTrace();
			return sessionFactory.openSession();
		}
	}

	private void closeSession(Session session){
		/*try{if(session != null && session.isOpen())
			session.close();
		} catch(Exception ex){
			ex.printStackTrace();
		}*/
	}
	@Override
	public List<Charges> getChargesByUserId(Integer userId) {
		try{
			session = getSession();
			Criteria criteria = session.createCriteria(Charges.class);
			criteria.add(Expression.eq("user",(User)getById(userId,User.class)));
			criteria.addOrder(Order.asc("updatedOn"));
			return criteria.list();
		}finally{
			closeSession(session);
		}
	}

	@Override
	public  List<User>  listUsersByName(String userName) {
		try{
			session = getSession();
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.ilike("firstName", userName, MatchMode.START));
			return criteria.list();
		}finally{
			closeSession(session);
		}
	}

	@Override
	public List<Fees> listFeesByUserId(Integer userId) {
		try{
			session = getSession();
			Criteria criteria = session.createCriteria(Fees.class);
			criteria.add(Expression.eq("user",(User)getById(userId,User.class)));
			criteria.addOrder(Order.asc("id"));
			return criteria.list();
		}finally{
			closeSession(session);
		}

	}

	@Override
	public List<Object[]> getUserByNextPaymentDate() {
		try{
			session = getSession();
			String SQL = "Select * from fees f inner join (select userId, MAX(updatedOn) updatedOn  from fees group by userId  order by updatedOn asc) a on a.userId = f.userId and f.updatedOn = a.updatedOn left outer join user u on f.userId = u.id where nextPaymentDueDate BETWEEN  now() and DATE_ADD( now(), INTERVAL 1 month ) AND pendingFees > 0";

			return session.createSQLQuery(SQL).addEntity("u",User.class).addEntity("f",Fees.class).list();
		}finally{
			closeSession(session);
		}
	}

	@Override
	public User validateUser(String username, String password) {
		try{
			session = getSession();
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Expression.eq("userName",username));
			criteria.add(Expression.eq("password",password));		
			return (User)criteria.uniqueResult();
		}finally{
			closeSession(session);
		}
	}

	@Override
	public List listAttendanceByFacultyId(Integer facultyId, Class clazz) {
		try{
			session = getSession();
			String SQL ="select * from sfm.attendance att left outer join sfm.faculty fac on att.facultyId=fac.id where att.facultyId="+facultyId;	
			List list = session.createSQLQuery(SQL).addEntity("att",Attendance.class).list();
			return list;
		}finally{
			closeSession(session);
		}
	}
}
