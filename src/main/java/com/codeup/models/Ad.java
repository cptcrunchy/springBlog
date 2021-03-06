package com.codeup.models;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "ads")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "Text")
    private String description;

    @OneToOne
    private User owner;

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "ad")
    private List<AdImage> images;

    @Column(nullable = true)
    private String imageURL;

    @ManyToMany(cascade = ALL)
    @JoinTable(
      name="ads_categories",
      joinColumns = {@JoinColumn(name = "ad_id")},
      inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private List<AdCategory> categories;

    public Ad(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Ad() {

    }

    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public User getOwner() {return owner;}
    public void setOwner(User owner) {this.owner = owner;}

    public List<AdImage> getImages() {return images;}
    public void setImages(List<AdImage> images) {this.images = images;}

    public String getImageURL() {return imageURL;}
    public void setImageURL(String imageURL) {this.imageURL = imageURL;}
}
