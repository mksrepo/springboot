import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.groovy.sql.runtime.entity.Student;
def query="select * from student where id=?";
def JdbcTemplate groovyJdbcTemplate=binding.getVariable("jdbcTemplate");
def Long id=binding.getVariable("id");
return groovyJdbcTemplate.queryForObject(query,[id] as Object[],new BeanPropertyRowMapper<Student>(Student.class));
