package com.poyrazaktas.bitirme.usr.service;

import com.poyrazaktas.bitirme.usr.service.entityservice.UsrUserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsrUserService {
    private final UsrUserEntityService userEntityService;
}
