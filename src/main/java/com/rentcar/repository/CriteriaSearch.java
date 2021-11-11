package com.rentcar.repository;

import com.rentcar.controller.requests.SearchRequest;
import com.rentcar.domain.User;
import com.rentcar.domain.User_;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;


@Repository
public class CriteriaSearch implements SearchRepository{


    @Autowired
    @Qualifier("entityManagerFactory")
    private EntityManager entityManager;


    @Override
    public List<User> criteriaApiSearch(SearchRequest request) {


        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> root = query.from(User.class);

        ParameterExpression<String> param = cb.parameter(String.class);
        ParameterExpression<Long> userSearchParam = cb.parameter(Long.class);
        ParameterExpression<Boolean> userIs = cb.parameter(Boolean.class);

        Expression<Long> id = root.get(User_.id);
        Expression<String> name = root.get(User_.name);
        Expression<String> surname = root.get(User_.surname);
        Expression<Boolean> isDeleted = root.get(User_.isDeleted);

        query
                .select(root)
                .distinct(true)
                .where(
                        cb.or(
                                cb.like(name, param),
                                cb.like(surname, param)
                        ),
                        cb.and(
                                cb.gt(id, userSearchParam)

                        ),
                        cb.equal(isDeleted,userIs)
//                        ,
//                        cb.between(
//                                root.get(TestUser_.birthDate),
//                                new Timestamp(new Date().getTime()),
//                                new Timestamp(new Date().getTime())
//                        )
                )
                .orderBy(cb.desc(id));

        TypedQuery<User> resultQuery = entityManager.createQuery(query);
        resultQuery.setParameter(param, StringUtils.join("%", request.getQuery(), "%"));
        resultQuery.setParameter(userSearchParam, request.getId());
        resultQuery.setParameter(userIs, request.getIsDeleted());
        return resultQuery.getResultList();


    }
}
