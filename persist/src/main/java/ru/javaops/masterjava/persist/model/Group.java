package ru.javaops.masterjava.persist.model;

import com.bertoncelj.jdbi.entitymapper.Column;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group extends BaseEntity {

    private @NonNull String name;
    private @NonNull GroupType type;
    private @NonNull @Column("project_id") int projectId;

}
