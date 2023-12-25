package com.petlife.seller.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.petlife.admin.dao.AcctStateDAO;
import com.petlife.admin.dao.impl.AcctStateDAOImpl;
import com.petlife.admin.dao.impl.AcctStateDAOImpl;
import com.petlife.admin.entity.AcctState;
import com.petlife.seller.entity.Seller;
import com.petlife.shelter.entity.Shelter;
import com.petlife.user.entity.User;
import com.petlife.util.HibernateUtil;

public class SellerDAOImpl implements SellerDAO {
	private SessionFactory factory;

	public SellerDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer add(Seller seller) {
		return (Integer) getSession().save(seller);
	}

	@Override
	public Integer delete(Integer sellerId) {
		Seller seller = getSession().get(Seller.class, sellerId);
		if (seller != null) {
			getSession().delete(seller);
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public Integer update(Seller seller) {
		getSession().flush();
		try {
			getSession().update(seller);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public Seller findByPK(Integer sellerId) {
		getSession().clear();
		return getSession().get(Seller.class, sellerId);
	}

	@Override
	public List<Seller> getAll(String... conditions) {
		if (conditions.length > 0 && conditions != null) {
			AcctStateDAO acctStateDAO = new AcctStateDAOImpl();
			AcctState acctState = acctStateDAO.findByPK(4);
			if ("verified".equals(conditions[0])) {
				return getSession().createQuery("from Seller where acctState!=:acctState", Seller.class)
						.setParameter("acctState", acctState).getResultList();
			} else if ("unverified".equals(conditions[0])) {
				return getSession().createQuery("from Seller where acctState=:acctState", Seller.class)
						.setParameter("acctState", acctState).getResultList();
			}
		}
		return getSession().createQuery("from Seller", Seller.class).getResultList();
	}

	@Override
	public Seller findSellerBySellerAccount(String sellerAccount) {
		List<Seller> sellers = getSession().createQuery("from Seller where sellerAcct=:sellerAcct", Seller.class)
				.setParameter("sellerAcct", sellerAccount).getResultList();

		if (sellers.size() > 0) {
			return sellers.get(0);
		}
		return null;
	}

	@Override
	public Seller findSellerBySellerAccountAndPassword(String sellerAcct, String sellerPwd) {
		List<Seller> sellers = getSession()
				.createQuery("from Seller where sellerAcct=:sellerAcct and sellerPwd=:sellerPwd", Seller.class)
				.setParameter("sellerAcct", sellerAcct).setParameter("sellerPwd", sellerPwd).getResultList();

		if (sellers.size() > 0) {
			return sellers.get(0);
		}
		return null;
	}
}
