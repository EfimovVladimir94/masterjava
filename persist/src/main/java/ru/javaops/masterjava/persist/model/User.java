package ru.javaops.masterjava.persist.model;

import com.bertoncelj.jdbi.entitymapper.Column;
import lombok.*;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ToString(callSuper = true)
public class User extends BaseEntity {
    @Column("full_name")
    private @NonNull String fullName;
    private @NonNull String email;
    private @NonNull UserFlag flag;
    private @NonNull @Column("city_ref") String ref;

    public User(Integer id, String fullName, String email, UserFlag flag, String ref) {
        this(fullName, email, flag, ref);
        this.id = id;
    }
}