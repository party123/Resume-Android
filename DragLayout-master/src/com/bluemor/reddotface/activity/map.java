package com.bluemor.reddotface.activity;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.MKEvent;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.map.m;
import com.bluemor.reddotface.R;


/**

* 基本的地图图层，包括若干个缩放级别，显示基本的地图信息，包括道路、街道、学校、公园等内容。

* @author android_ls

*

*/

public class map extends Activity {

    /**地图引擎管理类*/
    private BMapManager mBMapManager = null;

    /**显示地图的View*/

    private MapView mMapView = null;
    /**

     * 经研究发现在申请KEY时：应用名称一定要写成my_app_应用名（也就是说"my_app_"是必须要有的）。

     * 百度地图SDK提供的服务是免费的，接口无使用次数限制。您需先申请密钥（key)，才可使用该套SDK。

     * */

    public static final String BAIDU_MAP_KEY = "07418AEC69BAAB7104C6230A6120C580DFFAEEBB";

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // 注意：请在调用setContentView前初始化BMapManager对象，否则会报错

        mBMapManager = new BMapManager(this.getApplicationContext());

        mBMapManager.init(BAIDU_MAP_KEY, new MKGeneralListener() {



            @Override

            public void onGetNetworkState(int iError) {

                if (iError == MKEvent.ERROR_NETWORK_CONNECT) {

                    Toast.makeText(map.this.getApplicationContext(),

                            "请检查您的网络连接^_^", 

                            Toast.LENGTH_LONG).show();

                }

            }



            @Override

            public void onGetPermissionState(int iError) {

                if (iError == MKEvent.ERROR_PERMISSION_DENIED) {

                    // 授权Key错误：

                    Toast.makeText(map.this.getApplicationContext(), 

                            "请在 DemoApplication.java文件输入正确的授权Key！", 

                            Toast.LENGTH_LONG).show();

                }

            }

        });



        setContentView(R.layout.map);

        mMapView = (MapView) this.findViewById(R.id.bmapView);

        // 设置启用内置的缩放控件

        mMapView.setBuiltInZoomControls(true);

        // 获取地图控制器，可以用它控制平移和缩放

        MapController mMapController = mMapView.getController();

        // 用给定的经纬度构造一个GeoPoint，单位是微度 (度 * 1E6)

        // 北京天安门的经纬度：39.915 * 1E6，116.404 * 1E6

       /* GeoPoint mGeoPoint = new GeoPoint(

                (int) (39.915 * 1E6), 

                (int) (116.404 * 1E6));*/



        // 上海市浦东新区的GPS纬度经度值:31.224078,121.540419

        GeoPoint mGeoPoint = new GeoPoint(

                (int) (32.02895 * 1E6), 

                (int) (118.85052999999994 * 1E6));

        // 设置地图的中心点

        mMapController.setCenter(mGeoPoint);

        // 设置地图的缩放级别。 这个值的取值范围是[3,18]。 

        mMapController.setZoom(13);

        // 当前，全国范围内已支持多个城市实时路况查询，且会陆续开通其他城市。

        // 在地图中显示实时交通信息示

        // mMapView.setTraffic(true);

        // 卫星地图是卫星拍摄的真实的地理面貌，所以卫星地图可用来检测地面的信息，你可以了解到地理位置，地形等。

        mMapView.setTraffic(true);

        //mMapView.setSatellite(true);
    }

    // 重写以下方法，管理API

    @Override

    protected void onResume() {

        mMapView.onResume();

        if (mBMapManager != null) {

            mBMapManager.start();

        }

        super.onResume();

    }

    @Override

    protected void onPause() {

        mMapView.onPause();

        if (mBMapManager != null) {

            mBMapManager.stop();

        }

        super.onPause();

    }

    @Override

    protected void onDestroy() {

        mMapView.destroy();

        if (mBMapManager != null) {

            mBMapManager.destroy();

            mBMapManager = null;

        }

        super.onDestroy();

    }

}
