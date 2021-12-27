package tech.dna.task.category;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.dna.task.common.RestResponse;

import java.util.List;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    private RestResponse<List<Category>> getAll() {
        return RestResponse.ok(categoryService.getAllCategories());
    }

}
