package debug.base;

import com.tgcity.mode.news.utils.NewsUtils;
import com.tgcity.network.base.NetworkApplication;
import com.tgcity.network.base.NetworkConstant;

/**
 * @author TGCity
 * Home模块中的application
 */
public class NewsApplication extends NetworkApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        NewsUtils.initNetWork(this,false,NetworkConstant.ServiceFlag.SERVER_DEFAULT);
    }

}
