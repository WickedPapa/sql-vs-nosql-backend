package it.montano.sqlvsnosql.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import it.montano.sqlvsnosql.common.mapper.UserMapper;
import it.montano.sqlvsnosql.config.ConfiguredTest;
import it.montano.sqlvsnosql.dto.UserRequest;
import it.montano.sqlvsnosql.dto.UserResponse;
import it.montano.sqlvsnosql.user.model.UserDocument;
import it.montano.sqlvsnosql.user.repository.UserMongoRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.instancio.junit.Given;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@ConfiguredTest
class UserMongoServiceTest {

  @InjectMocks UserMongoService service;

  @Mock UserMongoRepository repo;

  @Mock UserMapper mapper = Mappers.getMapper(UserMapper.class);

  @Test
  void shouldCreateUser(
      @Given UserRequest request,
      @Given UserDocument userDocument,
      @Given UserResponse userResponse) {
    when(mapper.toDocument(request)).thenReturn(userDocument);
    when(repo.save(userDocument)).thenReturn(userDocument);
    when(mapper.toResponse(userDocument)).thenReturn(userResponse);

    UserResponse result = service.createUser(request);

    assertThat(result).isNotNull().isEqualTo(userResponse);
  }

  @Test
  void shouldDeleteUser(@Given UUID userId) {
    doNothing().when(repo).deleteById(userId);

    service.deleteUser(userId);

    verify(repo).deleteById(userId);
  }

  @Test
  void shouldGetUserById(
      @Given UUID userId, @Given UserDocument document, @Given UserResponse response) {
    when(repo.findById(userId)).thenReturn(Optional.of(document));
    when(mapper.toResponse(document)).thenReturn(response);

    UserResponse result = service.getUserById(userId);

    assertThat(result).isNotNull().isEqualTo(response);
  }

  @Test
  void shouldGetUsers(@Given List<UserDocument> documents, @Given UserResponse response) {
    when(repo.findAll()).thenReturn(documents);
    when(mapper.toResponse(any(UserDocument.class))).thenReturn(response);

    List<UserResponse> result = service.getUsers();

    assertThat(result).isNotNull().contains(response);
  }
}
