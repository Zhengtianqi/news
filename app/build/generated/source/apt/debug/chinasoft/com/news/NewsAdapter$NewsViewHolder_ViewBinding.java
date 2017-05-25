// Generated code from Butter Knife. Do not modify!
package chinasoft.com.news;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NewsAdapter$NewsViewHolder_ViewBinding<T extends NewsAdapter.NewsViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public NewsAdapter$NewsViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.title = Utils.findRequiredViewAsType(source, R.id.tv_news_title, "field 'title'", TextView.class);
    target.author = Utils.findRequiredViewAsType(source, R.id.tv_news_author, "field 'author'", TextView.class);
    target.date = Utils.findRequiredViewAsType(source, R.id.tv_news_date, "field 'date'", TextView.class);
    target.pic1 = Utils.findRequiredViewAsType(source, R.id.iv_news_pic1, "field 'pic1'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.title = null;
    target.author = null;
    target.date = null;
    target.pic1 = null;

    this.target = null;
  }
}
