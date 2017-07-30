package br.com.kecyo.userapp.util;

import br.com.kecyo.userapp.http.UserController;
import br.com.kecyo.userapp.usescases.impl.UserSave;
import br.com.kecyo.userapp.usescases.impl.UserSearch;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(value = {
        UserController.class
}, secure = false)
public class WebMvcTestBase {

    @MockBean
    protected UserSave userSave;

    @MockBean
    protected UserSearch userSearch;

}
