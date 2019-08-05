package ru.javaops.masterjava.persist.dao;

import com.bertoncelj.jdbi.entitymapper.EntityMapperFactory;
import one.util.streamex.StreamEx;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import ru.javaops.masterjava.persist.model.Project;

import java.util.List;
import java.util.Map;

@RegisterMapperFactory(EntityMapperFactory.class)
public abstract class ProjectDao implements AbstractDao {

    @SqlUpdate("INSERT INTO project (name, description) VALUES (:name, :description)")
    @GetGeneratedKeys
    public abstract int insertWithId(@BindBean Project project);

    public void insert(Project project) {
        int id = insertWithId(project);
        project.setId(id);
    }

    @SqlQuery("SELECT * FROM project ORDER BY name")
    public abstract List<Project> getAll();

    public Map<String, Project> getAsMap() {
        return StreamEx.of(getAll()).toMap(Project::getName, p -> p);
    }

    @SqlUpdate("TRUNCATE project CASCADE")
    public abstract void clean();
}
