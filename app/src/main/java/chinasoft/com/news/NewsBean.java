package chinasoft.com.news;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class NewsBean {

    private String reason;
    private ResultBean result;
    private int error_code;

    public static NewsBean objectFromData(String str) {

        return new Gson().fromJson(str, NewsBean.class);
    }


    public static List<NewsBean> arrayNewsBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<NewsBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }



    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {


        private String stat;
        private List<DataBean> data;

        public static ResultBean objectFromData(String str) {

            return new Gson().fromJson(str, ResultBean.class);
        }
        public static List<ResultBean> arrayResultBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<ResultBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }
        public String getStat() {
            return stat;
        }
        public void setStat(String stat) {
            this.stat = stat;
        }
        public List<DataBean> getData() {
            return data;
        }
        public void setData(List<DataBean> data) {
            this.data = data;
        }
        public static class DataBean {
            private String title;
            private String date;
            private String author_name;
            private String thumbnail_pic_s;
            private String thumbnail_pic_s02;
            private String thumbnail_pic_s03;
            private String url;
            private String uniquekey;
            private String type;
            private String realtype;

            public static DataBean objectFromData(String str) {

                return new Gson().fromJson(str, DataBean.class);
            }
            public static List<DataBean> arrayDataBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<DataBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }
            public String getTitle() {
                return title;
            }
            public void setTitle(String title) {
                this.title = title;
            }
            public String getDate() {
                return date;
            }
            public void setDate(String date) {
                this.date = date;
            }
            public String getAuthor_name() {
                return author_name;
            }
            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }
            public String getThumbnail_pic_s() {
                return thumbnail_pic_s;
            }
            public void setThumbnail_pic_s(String thumbnail_pic_s) {
                this.thumbnail_pic_s = thumbnail_pic_s;
            }
            public String getThumbnail_pic_s02() {
                return thumbnail_pic_s02;
            }
            public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
                this.thumbnail_pic_s02 = thumbnail_pic_s02;
            }
            public String getThumbnail_pic_s03() {
                return thumbnail_pic_s03;
            }
            public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
                this.thumbnail_pic_s03 = thumbnail_pic_s03;
            }
            public String getUrl() {
                return url;
            }
            public void setUrl(String url) {
                this.url = url;
            }
            public String getUniquekey() {
                return uniquekey;
            }
            public void setUniquekey(String uniquekey) {
                this.uniquekey = uniquekey;
            }
            public String getType() {
                return type;
            }
            public void setType(String type) {
                this.type = type;
            }
            public String getRealtype() {
                return realtype;
            }
            public void setRealtype(String realtype) {
                this.realtype = realtype;
            }
        }
    }
}
