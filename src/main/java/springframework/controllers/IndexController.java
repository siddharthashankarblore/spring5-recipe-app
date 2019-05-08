package springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springframework.domain.Category;
import springframework.domain.UnitOfMeasure;
import springframework.repositories.CategoryRepository;
import springframework.repositories.UnitOfMeasureRepository;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(){

        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        System.out.println("Category ID is:" + categoryOptional.get().getId());
        System.out.println("UnitOfMeasure ID is:" + unitOfMeasureOptional.get().getId());

        System.out.println("IndexController text message");
        return "index";
    }
}
