
package co.udea.hero.api.repository;

import co.udea.hero.api.model.Hero;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Integer> {
    @Query("SELECT hero " +
            "FROM Hero hero " +
            "WHERE hero.name " +
            "LIKE %?1%"   // con % % busca sin importar que hay antes o después
    )public List<Hero> search(String keyword);

}
