package br.com.kecyo.userapp.gateways.impl;

import br.com.kecyo.userapp.entities.User;
import br.com.kecyo.userapp.gateways.UserGateway;
import br.com.kecyo.userapp.gateways.repository.mongo.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class UserGatewayImplTest {

    private UserGateway userGateway;

    private UserRepository repository;

    @Before
    public void before(){
        repository = mock(UserRepository.class);
        userGateway = new UserGatewayImpl(repository);
    }

    @Test
    public void testFindAll(){
        userGateway.findAll(1);
        verify(repository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    public void testFindById(){
        Mockito.when(repository.findOne(anyString())).thenReturn(User.builder().build());

        Optional<User> result = userGateway.findById("1");

        verify(repository, times(1)).findOne(anyString());
        assertTrue(result.isPresent());
    }

    @Test
    public void testFindByIdIsEmpty(){
        Mockito.when(repository.findOne(anyString())).thenReturn(null);

        Optional<User> result = userGateway.findById("1");

        verify(repository, times(1)).findOne(anyString());
        assertFalse(result.isPresent());
    }

    @Test
    public void testSave(){
        userGateway.save(User.builder().build());
        verify(repository, times(1)).save(Matchers.any(User.class));
    }

}
