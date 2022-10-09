package cinema.dao.impl;

import cinema.dao.AbstractDao;
import cinema.dao.RoleDao;
import cinema.exception.DataProcessingException;
import cinema.model.Role;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
    public RoleDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Role.class);
    }

    @Override
    public Optional<Role> getByName(String roleName) {
        try (Session session = factory.openSession()) {
            Query<Role> query = session.createQuery(
                    "from Role r where r.roleName = :roleName", Role.class);
            query.setParameter("roleName", Role.RoleName.valueOf(roleName));
            return query.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't retrieve role with name:" + roleName,e);
        }
    }
}