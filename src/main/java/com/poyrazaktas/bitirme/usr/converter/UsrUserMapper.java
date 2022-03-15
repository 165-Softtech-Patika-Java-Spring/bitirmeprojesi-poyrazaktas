package com.poyrazaktas.bitirme.usr.converter;

import com.poyrazaktas.bitirme.usr.dto.UsrUserDto;
import com.poyrazaktas.bitirme.usr.dto.UsrUserSaveReqDto;
import com.poyrazaktas.bitirme.usr.dto.UsrUserUpdateReqDto;
import com.poyrazaktas.bitirme.usr.entity.UsrUser;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsrUserMapper {
    UsrUserMapper INSTANCE = Mappers.getMapper(UsrUserMapper.class);

    UsrUser convertToUser(UsrUserSaveReqDto saveReqDto);

    UsrUser convertToUser(UsrUserUpdateReqDto updateReqDto);

    UsrUserDto convertToUserDto(UsrUser user);

    List<UsrUserDto> convertToUserDtoList(List<UsrUser> userList);
}
