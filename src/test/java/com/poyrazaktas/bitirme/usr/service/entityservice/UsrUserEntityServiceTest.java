package com.poyrazaktas.bitirme.usr.service.entityservice;

import com.poyrazaktas.bitirme.usr.dao.UsrUserDao;
import com.poyrazaktas.bitirme.usr.entity.UsrUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsrUserEntityServiceTest {

    @Mock
    private UsrUserDao userDao;

    @InjectMocks
    private UsrUserEntityService userEntityService;

    @Test
    void whenFindUserByUserName_calledWithValidUserName_thenItShouldReturnValidUser() {

        Long id = 2L;
        String userName = "userName";

        UsrUser user = mock(UsrUser.class);
        when(user.getId()).thenReturn(id);
        when(user.getUserName()).thenReturn(userName);

        when(userDao.findUserByUserName(anyString())).thenReturn(Optional.of(user));

        Optional<UsrUser> result = userEntityService.findUserByUserName(anyString());

        assertEquals(userName, result.get().getUserName());
        assertEquals(id, result.get().getId());
    }

    @Test
    void whenFindUserByUserName_calledWithNotValidUserName_thenItShouldThrowNullPointerException() {

        when(userDao.findUserByUserName(null)).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> userEntityService.findUserByUserName(null));

    }
}