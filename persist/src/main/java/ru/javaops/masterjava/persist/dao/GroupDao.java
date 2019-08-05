package ru.javaops.masterjava.persist.dao;

import com.bertoncelj.jdbi.entitymapper.EntityMapperFactory;
import one.util.streamex.StreamEx;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import ru.javaops.masterjava.persist.model.Group;

import java.util.List;
import java.util.Map;

@RegisterMapperFactory(EntityMapperFactory.class)
public abstract class GroupDao implements AbstractDao {

    @SqlUpdate("INSERT INTO groups (name, type, project_id) VALUES (:name, CAST(:type, AS group_type), :project_id)")
    @GetGeneratedKeys
    public abstract int insertWithId(@BindBean Group group);

    public void insert(Group group) {
        int id = insertWithId(group);
        group.setId(id);
    }

    @SqlQuery("SELECT * FROM groups ORDER BY name")
    public abstract List<Group> getAll();

    public Map<String, Group> getAsMap() {
        return StreamEx.of(getAll()).toMap(Group::getName, g -> g);
    }

    @SqlUpdate("TRUNCATE groups CASCADE")
    @Override
    public abstract void clean();
}
