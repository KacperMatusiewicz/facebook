package ripoff.facebook.relation.relationManagement.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.AmqpTemplate;
import ripoff.facebook.amqp.NotificationDTO;
import ripoff.facebook.relation.commons.repository.GroupRepository;
import ripoff.facebook.relation.relationManagement.controller.ResponseType;
import ripoff.facebook.relation.relationManagement.repository.RelationRequest;
import ripoff.facebook.relation.relationManagement.repository.RelationRequestRepository;
import ripoff.facebook.relation.relationManagement.repository.RelationRequestStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RelationManagementServiceTest {
    @Mock
    private GroupRepository groupRepository;
    @Mock
    private RelationRequestRepository relationRequestRepository;
    @Mock
    private AmqpTemplate amqpTemplate;

    private RelationManagementService underTest;

    @BeforeEach
    void setUp() {
        underTest = new RelationManagementService(groupRepository, relationRequestRepository, amqpTemplate);
    }

    @Test
    void throwIfRequestWasSentBefore(){
        //given
        FriendshipRequestDto request = new FriendshipRequestDto(1L, 2L);
        given(relationRequestRepository.existsByRequesterIdAndRecipientId(any(), any())).willReturn(true);
        //when
        Executable e = () -> underTest.createFriendRequest(request);
        //then
        Assertions.assertThrows(RequestExistsException.class, e);
        
    }

    @Test
    void saveAndSendToQueueIfRequestWasNotSentBefore() {
        //given
        given(relationRequestRepository.existsByRequesterIdAndRecipientId(any(), any())).willReturn(false);
        FriendshipRequestDto request = new FriendshipRequestDto(1L, 2L);
        //when
        underTest.createFriendRequest(request);
        //then
        verify(relationRequestRepository, times(1)).save(any());
        verify(amqpTemplate, times(1)).convertAndSend(anyString(), any(NotificationDTO.class));
    }

    @Test
    void throwWhenRequestWasNotFound() {
        //given
        FriendResponseDto dto = new FriendResponseDto(1L, 2L, ResponseType.ACCEPT);
        //when
        Executable e = () -> underTest.respondToFriendshipRequest(dto);
        //then
        Assertions.assertThrows(RequestNotFoundException.class, e);
    }

    @Test
    void ifResponseStatusWasAcceptedThenMakeFriendshipAndSendToNotificationQueue() {
        //given
        var rs = new RelationRequest();
        rs.setRequesterId(1L);
        rs.setRecipientId(2L);
        FriendResponseDto dto = new FriendResponseDto(2L, 1L, ResponseType.ACCEPT);
        given(relationRequestRepository.findByRequesterIdAndRecipientId(any(), any())).willReturn(Optional.of(rs));
        //when
        underTest.respondToFriendshipRequest(dto);
        //then
        verify(amqpTemplate,times(1) ).convertAndSend(anyString(), any(NotificationDTO.class));
        verify(groupRepository, times(2)).createFollowRelation(any(),any());
        verify(relationRequestRepository, times(1)).save(any());
    }

    @Test
    void ifResponseStatusWasIgnoredThenRequestStatusToIgnored() {
        //given
        var rs = new RelationRequest();
        rs.setRequesterId(1L);
        rs.setRecipientId(2L);
        FriendResponseDto dto = new FriendResponseDto(2L, 1L, ResponseType.IGNORE);
        given(relationRequestRepository.findByRequesterIdAndRecipientId(any(), any())).willReturn(Optional.of(rs));
        //when
        underTest.respondToFriendshipRequest(dto);
        //then
        ArgumentCaptor<RelationRequest> argumentCaptor = ArgumentCaptor.forClass(RelationRequest.class);
        verify(relationRequestRepository, times(1)).save(argumentCaptor.capture());
        assertEquals(RelationRequestStatus.IGNORED, argumentCaptor.getValue().getRequestStatus());

    }

    @Test
    void throwWhenResponseStatusWasInvalid(){
        var rs = new RelationRequest();
        rs.setRequesterId(24L);
        rs.setRecipientId(15L);
        FriendResponseDto dto = new FriendResponseDto(2L, 1L, ResponseType.ACCEPT);
        given(relationRequestRepository.findByRequesterIdAndRecipientId(any(), any())).willReturn(Optional.of(rs));
        //when
        Executable e = () -> underTest.respondToFriendshipRequest(dto);
        //then
        Assertions.assertThrows(UnauthorizedOperationException.class, e);
    }

}
