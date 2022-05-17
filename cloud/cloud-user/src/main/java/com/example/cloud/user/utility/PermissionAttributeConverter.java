package com.example.cloud.user.utility;

import javax.persistence.AttributeConverter;

import com.example.cloud.infrastructure.consts.ResourceOperatePermission;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author jintaoZou
 * @date 2022/5/12-14:58
 */
public class PermissionAttributeConverter implements AttributeConverter<Set<ResourceOperatePermission>, String> {

    @Override
    public String convertToDatabaseColumn(Set<ResourceOperatePermission> ropSet) {
        return ropSet.stream().flatMap((Function<ResourceOperatePermission, Stream<String>>)
                rop -> Stream.of(rop.name())).collect(Collectors.joining(""));
    }

    @Override
    public Set<ResourceOperatePermission> convertToEntityAttribute(String operates) {
        return Arrays.stream(operates.split(""))
                .flatMap((Function<String, Stream<ResourceOperatePermission>>) sop
                        -> Stream.of(ResourceOperatePermission.valueOf(sop))).collect(Collectors.toSet());
    }

}
