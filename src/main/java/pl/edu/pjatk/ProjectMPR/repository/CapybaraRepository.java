package pl.edu.pjatk.ProjectMPR.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjatk.ProjectMPR.model.Capybara;

import java.util.List;

@Repository
//@Component
public interface CapybaraRepository extends CrudRepository<Capybara, Long> {
    public List<Capybara> findByName(String name);
    public List<Capybara> findByColor(String color);
    public List<Capybara> findBySerialNumber(Integer sn);
}
