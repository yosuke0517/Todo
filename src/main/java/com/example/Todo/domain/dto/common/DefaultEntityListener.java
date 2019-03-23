package com.example.Todo.domain.dto.common;

import com.example.Todo.common.utils.ReflectionUtils;
import com.example.Todo.domain.exception.DoubleSubmitErrorException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.seasar.doma.Id;
import org.seasar.doma.jdbc.entity.EntityListener;
import org.seasar.doma.jdbc.entity.PreDeleteContext;
import org.seasar.doma.jdbc.entity.PreInsertContext;
import org.seasar.doma.jdbc.entity.PreUpdateContext;


import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor
@Slf4j
public class DefaultEntityListener<ENTITY> implements EntityListener<ENTITY> {

    @Override
    public void preInsert(ENTITY entity, PreInsertContext<ENTITY> context) {
        // 二重送信防止チェック
        val expected = DoubleSubmitCheckTokenHolder.getExpectedToken();
        val actual = DoubleSubmitCheckTokenHolder.getActualToken();

        if (expected != null && actual != null && !Objects.equals(expected, actual)) {
            throw new DoubleSubmitErrorException();
        }

        if (entity instanceof DomaDto) {
            val domaDto = (DomaDto) entity;
            val createdAt = AuditInfoHolder.getAuditDateTime();
            val createdBy = AuditInfoHolder.getAuditUser();

            domaDto.setCreated_at(createdAt); // 作成日
            domaDto.setCreated_by(createdBy); // 作成者
        }
    }

    @Override
    public void preUpdate(ENTITY entity, PreUpdateContext<ENTITY> context) {

        if (entity instanceof DomaDto) {
            val domaDto = (DomaDto) entity;
            val updatedAt = AuditInfoHolder.getAuditDateTime();
            val updatedBy = AuditInfoHolder.getAuditUser();

            val methodName = context.getMethod().getName();
            if (StringUtils.startsWith("delete", methodName)) {
                domaDto.setDeleted_at(updatedAt); // 削除日
                domaDto.setDeleted_by(updatedBy); // 削除者
            } else {
                domaDto.setUpdated_at(updatedAt); // 更新日
                domaDto.setUpdated_by(updatedBy); // 更新者
            }
        }
    }

    @Override
    public void preDelete(ENTITY entity, PreDeleteContext<ENTITY> context) {

        if (entity instanceof DomaDto) {
            val domaDto = (DomaDto) entity;
            val deletedAt = AuditInfoHolder.getAuditDateTime();
            val deletedBy = AuditInfoHolder.getAuditUser();
            val name = domaDto.getClass().getName();
            val ids = getIds(domaDto);

            // 物理削除した場合はログ出力する
            log.info("データを物理削除しました。entity={}, id={}, deletedBy={}, deletedAt={}", name, ids, deletedBy, deletedAt);
        }
    }

    /**
     * Idアノテーションが付与されたフィールドの値のリストを返します。
     *
     * @param dto
     * @return
     */
    protected List<Object> getIds(Dto dto) {
        return ReflectionUtils.findWithAnnotation(dto.getClass(), Id.class)
                .map(f -> ReflectionUtils.getFieldValue(f, dto)).collect(toList());
    }

}
