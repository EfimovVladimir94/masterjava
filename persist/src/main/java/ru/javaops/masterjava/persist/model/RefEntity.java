package ru.javaops.masterjava.persist.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
abstract public class RefEntity {
    @Getter
    private @NonNull
    String ref;
}
