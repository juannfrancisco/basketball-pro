package cl.grupo.maldonado.app.basket.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;

public class MatchStatCustomImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public void findUserByEmails() {
/**
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        cb.createQuery();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> user = query.from(User.class);

        Path<String> emailPath = user.get("email");

        List<Predicate> predicates = new ArrayList<>();
        for (String email : emails) {
            predicates.add(cb.like(emailPath, email));
        }
        query.select(user)
                .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query)
                .getResultList();
 **/
    }
}
