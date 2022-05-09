package com.example.assign1.service.impl;



import com.example.assign1.dto.CategoryDto;
import com.example.assign1.entity.Category;
import com.example.assign1.exception.ResourceNotFoundException;
import com.example.assign1.repository.CategoryRepository;
import com.example.assign1.service.CategoryService;
import org.springframework.stereotype.Service;
import  com.example.assign1.repository.CategoryRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service //To enable this class for component scanning
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository CategoryRepository;

    public CategoryServiceImpl(CategoryRepository CategoryRepository) {
        this.CategoryRepository = CategoryRepository;
    }

    @Override
    public CategoryDto createCategory(CategoryDto CategoryDto) {

        // convert DTO to entity
        Category Category = mapToEntity(CategoryDto);
        Category newCategory = CategoryRepository.save(Category);

        // convert entity to DTO
        CategoryDto CategoryResponse = mapToDTO(newCategory);
        return CategoryResponse;
    }

    @Override
    public List<CategoryDto> getAllCategorys() {
        List<Category> categories = CategoryRepository.findAll();
        return categories.stream().map(Category -> mapToDTO(Category)).collect(Collectors.toList());
    }



    @Override
    public CategoryDto getCategoryById(long id) {
        Category Category = CategoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        return mapToDTO(Category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto CategoryDto, long id) {
        // get Category by id from the database
        Category Category = CategoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        Category.setName(CategoryDto.getName());
        Category updatedCategory = CategoryRepository.save(Category);
        return mapToDTO(updatedCategory);
    }

    @Override
    public void deleteCategoryById(long id) {
        // get Category by id from the database
        Category Category = CategoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        CategoryRepository.delete(Category);
    }

    // convert Entity into DTO
    private CategoryDto mapToDTO(Category Category){
        CategoryDto CategoryDto = new CategoryDto();
        CategoryDto.setId(Category.getId());
        CategoryDto.setName(Category.getName());

        return CategoryDto;
    }

    // convert DTO to entity
    private Category mapToEntity(CategoryDto CategoryDto){
        Category Category = new Category();
        Category.setId(CategoryDto.getId());
        Category.setName(CategoryDto.getName());

        return Category;
    }
}
