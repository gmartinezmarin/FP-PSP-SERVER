/**
 * 
 */
package py.org.fundacionparaguaya.pspserver.network.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import py.org.fundacionparaguaya.pspserver.network.entities.ApplicationEntity;
import py.org.fundacionparaguaya.pspserver.network.entities.OrganizationEntity;
import py.org.fundacionparaguaya.pspserver.network.entities.OrganizationEntity_;

/**
 * @author bsandoval
 *
 */
public class OrganizationSpecification {
    
    private static final String ID_ATTRIBUTE = "id";
    
    public static Specification<OrganizationEntity> byFilter(Long applicationId, Long organizationId) {
        return new Specification<OrganizationEntity>() {
            @Override
            public Predicate toPredicate(Root<OrganizationEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                
                if (applicationId != null) {
                    Join<OrganizationEntity, ApplicationEntity> joinApplication = root.join(OrganizationEntity_.application);
                    Expression<Long> byApplicationId = joinApplication.<Long> get(ID_ATTRIBUTE);
                    predicates.add(cb.equal(byApplicationId, applicationId));
                }
                
                if (organizationId != null) {
                    Expression<Long> byOrganizationId = root.get(OrganizationEntity_.id);
                    predicates.add(cb.equal(byOrganizationId, organizationId));
                }
                
                //search only active organizations
                Expression<Boolean> isActive = root.<Boolean> get(OrganizationEntity_.isActive);
                predicates.add(cb.isTrue(isActive));
                
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
    
    public static Specification<OrganizationEntity> hasApplication(Long applicationId) {
        return new Specification<OrganizationEntity>() {
            @Override
            public Predicate toPredicate(Root<OrganizationEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Join<OrganizationEntity, ApplicationEntity> join = root.join(OrganizationEntity_.application);
                Expression<Long> expression = join.<Long> get(ID_ATTRIBUTE);
                return cb.equal(expression, applicationId);
            }
        };
    }
    
    public static Specification<OrganizationEntity> hasId(Long organizationId) {
        return new Specification<OrganizationEntity>() {
            @Override
            public Predicate toPredicate(Root<OrganizationEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Expression<Long> expression = root.get(OrganizationEntity_.id);
                return cb.equal(expression, organizationId);
            }
        };
    }
    
    public static Specification<OrganizationEntity> isActive() {
        return new Specification<OrganizationEntity>() {
            @Override
            public Predicate toPredicate(Root<OrganizationEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Expression<Boolean> isActive = root.get(OrganizationEntity_.isActive);
                return cb.isTrue(isActive);
            }
        };
    }

}
