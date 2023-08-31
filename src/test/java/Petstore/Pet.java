package Petstore;

public class Pet {
    private int id;
    private String name;
    private String[] photoUrls;
    private String status;
    private Category category;
    private Tag[] tags;


    // Agrega un objeto Tag a la lista de tags
    public void addTag(Tag tag) {
        if (tags == null) {
            tags = new Tag[1];
            tags[0] = tag;
        } else {
            Tag[] newTags = new Tag[tags.length + 1];
            System.arraycopy(tags, 0, newTags, 0, tags.length);
            newTags[tags.length] = tag;
            tags = newTags;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(String[] photoUrls) {
        this.photoUrls = photoUrls;
    }

    public Tag[] getTags() {
        return tags;
    }

    public void setTags(Tag[] tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
