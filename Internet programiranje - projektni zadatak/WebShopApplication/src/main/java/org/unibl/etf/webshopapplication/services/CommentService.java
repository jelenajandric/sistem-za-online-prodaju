package org.unibl.etf.webshopapplication.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.unibl.etf.webshopapplication.model.AddCommentRequest;
import org.unibl.etf.webshopapplication.model.CommentResponse;
import org.unibl.etf.webshopapplication.model.entities.ClientEntity;
import org.unibl.etf.webshopapplication.model.entities.CommentEntity;
import org.unibl.etf.webshopapplication.repositories.ClientRepository;
import org.unibl.etf.webshopapplication.repositories.CommentRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ClientRepository clientRepository;
    private final AvatarService avatarService;

    public CommentService(CommentRepository commentRepository, ClientRepository clientRepository, AvatarService avatarService) {
        this.commentRepository = commentRepository;
        this.clientRepository = clientRepository;
        this.avatarService = avatarService;
    }

    public void addComment(AddCommentRequest addCommentRequest) {
        commentRepository.save(new CommentEntity(null, addCommentRequest.getContent(),
                addCommentRequest.getProductId(), clientRepository.getClientIdByUsername(addCommentRequest.getClientUsername()), Timestamp.from(Instant.now())));
    }


    public List<CommentResponse> getAllCommentsForProduct(int productId) {
        List<CommentResponse> response = new ArrayList<>();
        List<CommentEntity> commentEntities = commentRepository.findAllByProductId(productId);
        commentEntities.stream().forEach(commentEntity -> {
            ClientEntity clientEntity = clientRepository.findClientEntityById(commentEntity.getClientId());
            response.add(new CommentResponse(commentEntity.getId(), commentEntity.getContent(),
                    clientEntity.getName(), clientEntity.getSurname(), clientEntity.getUsername(),
                    avatarService.getAvatarPhoto(clientEntity.getUsername()).get("content"), commentEntity.getTime()));
        });
        return response;
    }

    public void deleteComment(int id) {
        commentRepository.deleteById(id);
    }
}
