package tech.dna.task.category;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.dna.task.category.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}