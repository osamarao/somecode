package io.clutchstud.nfems.models;

/**
 * Created by a653h496 on 4/14/16.
 */
public class Category{


    private Integer id;

    private String category_name;


    private String createdAt;
    private String updatedAt;


    public Category(Integer id, String category_name) {
        this.id = id;
        this.category_name = category_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return category_name;
    }

    public void setCategoryName(String categoryName) {
        this.category_name = categoryName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + category_name + '\'' +
                '}';
    }
}
