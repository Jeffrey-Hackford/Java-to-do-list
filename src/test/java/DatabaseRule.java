import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  @Override
  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/to_do_test", null, null);
  }

  @Override
protected void after() {
  try(Connection con = DB.sql2o.open()) {
    String deleteTasksQuery = "DELETE FROM tasks *;";
    String deleteCategoriesQuery = "DELETE FROM categories *;";
    String deleteCategoriesTasksQuery = "DELETE FROM categories_tasks *;";
    con.createQuery(deleteTasksQuery).executeUpdate();
    con.createQuery(deleteCategoriesQuery).executeUpdate();
    con.createQuery(deleteCategoriesTasksQuery).executeUpdate();
  }
}

}
