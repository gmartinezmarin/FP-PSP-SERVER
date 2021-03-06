package py.org.fundacionparaguaya.pspserver.surveys.repositories;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import py.org.fundacionparaguaya.pspserver.surveys.entities.SnapshotEconomicEntity;

/**
 * Created by rodrigovillalba on 10/19/17.
 */
public interface SnapshotEconomicRepository extends JpaRepository<SnapshotEconomicEntity, Long>, 
        JpaSpecificationExecutor<SnapshotEconomicEntity> {
    Collection<SnapshotEconomicEntity> findBySurveyDefinitionId(Long surveyId);
    Optional<SnapshotEconomicEntity> findFirstByFamilyFamilyIdOrderByCreatedAtDesc(Long familyId);
    List<SnapshotEconomicEntity> findByFamilyFamilyId(Long familyId);
    SnapshotEconomicEntity findTopByFamilyFamilyIdOrderByIdDesc(Long familyID);
}
