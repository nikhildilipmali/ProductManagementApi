package com.jbk.Product.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.AggregateProjection;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.Product.entity.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean saveProduct(Product product) {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		boolean isAdded = false;
		try {
			session.save(product);
			transaction.commit();
			isAdded = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return isAdded;
	}

	@Override
	public List<Product> getAllProduct() {
		List<Product> list = null;
		Session session = sessionFactory.openSession();
		try {
			Criteria criteria = session.createCriteria(Product.class);
			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	@Override
	public boolean deleteProductById(String productId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		boolean isDelete = false;
		try {

			Product deletedProduct = session.get(Product.class, productId);
			if (deletedProduct != null) {
				session.delete(deletedProduct);
				transaction.commit();
				isDelete = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return isDelete;
	}

	@Override
	public boolean updateProduct(Product product) {
		Session session = sessionFactory.openSession();
		boolean isUpdated = false;
		Transaction transaction = session.beginTransaction();
		try {
			Product products = session.get(Product.class, product.getProductId());
			if (products != null) {
				session.evict(products);
				session.update(product);
				transaction.commit();
				isUpdated = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (session != null) {
				session.close();
			}
		}
		return isUpdated;
	}

	@Override
	public Product getProductById(String productId) {
		Session session = sessionFactory.openSession();
		Product products = null;

		try {
			products = session.get(Product.class, productId);
			if (products != null) {
				return products;

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return products;
	}

	@Override
	public List<Product> getProductByName(String productName) {
		Session session = sessionFactory.openSession();
		List<Product> list = null;
		try {
			Criteria criteria = session.createCriteria(Product.class);
			criteria.add(Restrictions.eq("productName", productName));
			list = criteria.list();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	@Override
	public List<Product> sortProduct(String sortBy) {
		List<Product> list = null;
		Session session = sessionFactory.openSession();
		try {
			Criteria criteria = session.createCriteria(Product.class);

			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return list;
	}

	@Override
	public List<Product> getMaxPriceProductHib() {

		Session session = sessionFactory.openSession();

		List<Product> list=null;

	List lists = null;
	double max=0;
		
		try {
			Criteria criteria1 = session.createCriteria(Product.class);
			criteria1.setProjection(Projections.max("productPrice"));

			max= (Double) criteria1.list().get(0);

			// lists= (List) criteria1.list().get(0);
			Criteria criteria2 = session.createCriteria(Product.class);
			criteria2.add(Restrictions.eq("productPrice", max));
			list = criteria2.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (session != null) {
				session.close();
			}

		}

		return list;
	}

	@Override
	public long countOfProductsHib() {
		Session session = sessionFactory.openSession();
		long count = 0;
		List list = null;

		try {
			Criteria criteria = session.createCriteria(Product.class);
			criteria.setProjection(Projections.rowCount());
			list = criteria.list();
			count = (Long) list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();

		}
		return count;
	}

	@Override
	public Product getMinPriceProductHib() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getSumOfProductPrice() {
		Session session = sessionFactory.openSession();
		long sumPrice = 0;
		List list = null;
		try {
			Criteria criteria = session.createCriteria(Product.class);
			criteria.setProjection(Projections.sum("productPrice"));
			list = criteria.list();
			sumPrice = (long) list.get(0);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (session != null)
				session.close();

		}
		return sumPrice;
	}

	@Override
	public List<Product> sortByHib(String sort) {
		Session session = sessionFactory.openSession();
		List<Product> list = null;

		try {
			Criteria criteria = session.createCriteria(Product.class);
			if (sort.equalsIgnoreCase("productName")) {
				list = criteria.addOrder(Order.asc(sort)).list();
			} else if (sort.equalsIgnoreCase("productPrice")) {
				list = criteria.addOrder(Order.asc(sort)).list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();

		}
		return list;
	}

}
