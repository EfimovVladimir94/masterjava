package ru.javaops.masterjava.persist.model;

import com.bertoncelj.jdbi.entitymapper.Column;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGroup extends BaseEntity {

    private @NonNull @Column("user_id") int userId;
    private @NonNull @Column("group_id") int groupId;

}
