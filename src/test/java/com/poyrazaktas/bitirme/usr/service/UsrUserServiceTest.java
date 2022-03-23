package com.poyrazaktas.bitirme.usr.service;

import com.poyrazaktas.bitirme.gen.exception.ItemNotFoundException;
import com.poyrazaktas.bitirme.usr.dto.UsrUserDto;
import com.poyrazaktas.bitirme.usr.dto.UsrUserSaveReqDto;
import com.poyrazaktas.bitirme.usr.dto.UsrUserUpdateReqDto;
import com.poyrazaktas.bitirme.usr.entity.UsrUser;
import com.poyrazaktas.bitirme.usr.service.entityservice.UsrUserEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsrUserServiceTest {
    @Mock
    private UsrUserEntityService userEntityService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsrUserService userService;

    @Test
    void whenFindAll_calledWithUserList_thenItShouldReturnUserList() {
        UsrUser user = mock(UsrUser.class);

        when(userEntityService.findAll()).thenReturn(Collections.singletonList(user));

        List<UsrUserDto> result = userService.findAll();

        assertEquals(1, result.size());

    }

    @Test
    void whenFindAll_calledWithEmptyUserList_thenItShouldReturnEmptyUserList() {

        when(userEntityService.findAll()).thenReturn(Collections.emptyList());

        List<UsrUserDto> result = userService.findAll();

        assertEquals(0, result.size());

    }

    @Test
    void whenGet_calledWithValidParams_thenItShouldReturnUser() {

        String userName = "userName";
        UsrUser user = mock(UsrUser.class);
        when(user.getUserName()).thenReturn(userName);

        when(userEntityService.findById(anyLong())).thenReturn(Optional.of(user));

        UsrUserDto result = userService.get(anyLong());

        assertEquals(userName, result.getUserName());
    }

    @Test
    void whenGet_calledWithNotMatching_thenItShouldThrowItemNotFoundException() {

        when(userEntityService.findById(anyLong())).thenThrow(ItemNotFoundException.class);

        assertThrows(ItemNotFoundException.class, () -> userService.get(anyLong()));

    }

    @Test
    void whenSave_calledWithValidRequestBody_thenItShouldReturnValidResponse() {

        String userName = "userName";
        String password = "password";

        UsrUser user = mock(UsrUser.class);
        when(user.getUserName()).thenReturn(userName);


        UsrUserSaveReqDto saveReqDto = mock(UsrUserSaveReqDto.class);
        when(saveReqDto.getPassword()).thenReturn(password);
        when(passwordEncoder.encode(anyString())).thenReturn(password);

        when(userEntityService.save(any())).thenReturn(user);

        UsrUserDto result = userService.save(saveReqDto);

        assertEquals(userName, result.getUserName());

    }

    @Test
    void whenSave_calledWithEmptyRequestBody_thenItShouldThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> userService.save(null));
    }

    @Test
    void whenUpdate_calledWithValidRequestBody_thenItShouldReturnValidResponse() {
        String userName = "username";
        String password = "password";

        UsrUser user = mock(UsrUser.class);
        when(user.getUserName()).thenReturn(userName);

        UsrUserUpdateReqDto updateReqDto = mock(UsrUserUpdateReqDto.class);
        when(updateReqDto.getPassword()).thenReturn(password);

        when(passwordEncoder.encode(anyString())).thenReturn(password);

        when(userEntityService.findById(anyLong())).thenReturn(Optional.of(user));
        when(userEntityService.save(any())).thenReturn(user);

        UsrUserDto result = userService.update(updateReqDto);

        assertEquals(userName, result.getUserName());

    }

    @Test
    void whenUpdate_calledWithNotMatchingId_thenItShouldThrowItemNotFoundException() {

        UsrUserUpdateReqDto userUpdateReqDto = mock(UsrUserUpdateReqDto.class);
        when(userEntityService.findById(anyLong())).thenThrow(ItemNotFoundException.class);
        assertThrows(ItemNotFoundException.class, () -> userService.update(userUpdateReqDto));
    }


    @Test
    void whenDelete_calledWithValidId_thenItShouldDelete() {
        UsrUser user = mock(UsrUser.class);
        when(userEntityService.findById(anyLong())).thenReturn(Optional.of(user));
        userService.delete(anyLong());

        verify(userEntityService).findById(anyLong());
        verify(userEntityService).delete(any());
    }

    @Test
    void whenDelete_calledWithNull_thenItShouldThrowItemNotFoundException() {

        when(userEntityService.findById(anyLong())).thenThrow(ItemNotFoundException.class);

        assertThrows(ItemNotFoundException.class, () -> userService.delete(anyLong()));

        verify(userEntityService).findById(anyLong());
    }
}