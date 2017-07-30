package br.com.kecyo.userapp.usescases.exception;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserNotFoundExceptionTest {

    @Test
    public void testException(){
        UserNotFoundException userNotFoundException = new UserNotFoundException();
        assertThat(userNotFoundException.getMessage(), is(equalTo("User Not Found!")));

    }
}
