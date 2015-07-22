import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToBeanResultTransformer;

import com.sfm.model.CompoundFees;
import com.sfm.model.Fees;
import com.sfm.model.User;
import com.sfm.util.HibernateUtil;


public class HibernateCriteriaExamples {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// Prep work
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = getSession(sessionFactory);
		Transaction tx = session.beginTransaction();

		//Get All Employees
		//        Criteria criteria = session.createCriteria(Fees.class);
		//        List<Fees> empList = criteria.list();
		//        for(Fees emp : empList){
		//            System.out.println("ID="+emp.getId()+", name:="+emp.getUser().getFirstName()+"-> "+emp.getTotalFees()+"," + emp.getPaidFees());
		//        }
		//
		String SQL=" Select u.id id,totalFees, count(noOfInstallment),sum(paidFees) totalPaidFees,sum(additionCharges),totalFees+ totalExpenses-(sum(paidFees) + sum(additionCharges)) totalPendingFees,nextPaymentDueDate nextDueDate, totalExpenses  from fees f inner join User u on u.id=f.userId inner join (select userId, sum(amount) totalExpenses from charges) ch on f.userId = ch.userId group by f.userId order by u.id";

		/*private User user;
   	private BigDecimal totalFees;
   	private BigDecimal totalExpenses;
   	private BigDecimal totalPaidFees;
   	private BigDecimal totalPendingFees;
   	private Date nextDueDate;*/

		List<CompoundFees> results =  session.createSQLQuery(SQL).addEntity("f", CompoundFees.class).list();

		for (CompoundFees cf : results) {
			
			
			System.out.println(cf);
		}
	}


	public static Session getSession(SessionFactory sessionFactory) throws HibernateException {         
		Session sess = null;       
		try {         
			sess = sessionFactory.getCurrentSession();  
		} catch (org.hibernate.HibernateException he) {  
			sess = sessionFactory.openSession();     
		}             
		return sess;
	}

}