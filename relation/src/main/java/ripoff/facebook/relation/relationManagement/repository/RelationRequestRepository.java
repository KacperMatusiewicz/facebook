package ripoff.facebook.relation.relationManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface RelationRequestRepository extends JpaRepository<RelationRequest, Long> {

    boolean existsByRequesterIdAndRecipientId(Long requesterId, Long recipientId);

    Optional<RelationRequest> findByRequesterIdAndRecipientId(Long requesterId, Long recipientId);

    List<RelationRequest> findAllByRequesterIdAndRequestStatus(Long requesterId, RelationRequestStatus requestStatus);

    List<RelationRequest> findAllByRecipientIdAndRequestStatus(Long id, RelationRequestStatus pending);

    void deleteByRequesterIdAndRecipientId(Long friend1, Long friend2);
}
