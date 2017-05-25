package chinasoft.com.news;

public class BannerBean {
    private String[] img;
    private String[] title;
    private String[] toPageUrl;

    public String[] getToPageUrl() {
        return toPageUrl;
    }

    public void setToPageUrl(String[] toPageUrl) {
        this.toPageUrl = toPageUrl;
    }

    public String[] getImg() {
        return img;
    }

    public void setImg(String[] img) {
        this.img = img;
    }

    public String[] getTitle() {
        return title;
    }

    public void setTitle(String[] title) {
        this.title = title;
    }

    public BannerBean() {
    }
}