package cn.digirun.component.order.service;
import static org.springframework.data.jpa.repository.query.QueryUtils.toOrders;

import java.io.Serializable;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.Assert;

/** 
 * @ClassName: DBRepositoryImpl 
 * @Description: JPA扩展实现
 * @author 管东海
 * 
 * @param <T>
 * @param <ID> 
 */
public class DBRepositoryImpl<T ,ID extends Serializable> extends SimpleJpaRepository<T, ID> implements DBRepository<T, ID>{

	private EntityManager entityManager;
	@SuppressWarnings("unused")
	private JpaEntityInformation<T, ?> entityInformation;
	
	public DBRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
	}

	public DBRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
		this.entityInformation = entityInformation;
	}

	private <S> Root<T> applySpecificationToCriteria(Specification<T> spec, CriteriaQuery<S> query) {

		Assert.notNull(query);
		Root<T> root = query.from(getDomainClass());

		if (spec == null) {
			return root;
		}

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		Predicate predicate = spec.toPredicate(root, query, builder);
		
		if (predicate != null) {
			query.where(predicate);
		}

		return root;
	}
	
	@Override
	protected TypedQuery<T> getQuery(Specification<T> spec, Sort sort) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(getDomainClass());

		Root<T> root = applySpecificationToCriteria(spec, query);
		
		
		// modify by gdh
		/**
		 * 允许在Specification内部查询指定列
		 * */
		if(query.getSelection() == null)
			query.select(root);
		// modify end
		

		if (sort != null) {
			query.orderBy(toOrders(sort, root, builder));
		}
		
		
		return applyRepositoryMethodMetadata(entityManager.createQuery(query));
	}
	
	
	private TypedQuery<T> applyRepositoryMethodMetadata(TypedQuery<T> query) {

		if (getRepositoryMethodMetadata() == null) {
			return query;
		}

		LockModeType type = getRepositoryMethodMetadata().getLockModeType();
		TypedQuery<T> toReturn = type == null ? query : query.setLockMode(type);

		applyQueryHints(toReturn);

		return toReturn;
	}

	private void applyQueryHints(Query query) {

		for (Entry<String, Object> hint : getQueryHints().entrySet()) {
			query.setHint(hint.getKey(), hint.getValue());
		}
	}
}
