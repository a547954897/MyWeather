package weather;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.GetCityModel;
import model.NowWeatherModel;
import model.SeachCityModel;
import model.ThreeDayWeatherModel;
import okhttp3.*;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HomeController {
    public Label top_date, top_city; //顶部的当前日期和城市
    public ImageView img_one, img_two, img_three; //天气图
    public Label mon_two, mon_three; //周几
    public Button bt_city, bt_ok; //搜索按钮
    public ComboBox select_city;
    public Label one_temp, two_temp, three_temp;  //温度
    public Label one_wea, two_wea, three_wea;        //天气
    public Label one_wind, two_wind, three_wind;     //风速
    public Label date_two, date_three;               // 日期

    public Label te_current, te_current_text;  // 实况温度 实况描述

    public HBox top_info;
    public Pane top_input;

    SeachCityModel cityData; // api获取的搜索城市数据
    NowWeatherModel nowWeatherList; // 实况数据
    GetCityModel getCityModel; //根据ip获取城市


    final static String KEY = "5e47b2143db54d4d8279c7c853b5f0b0";

    String location = "北海";

    // 城市信息搜索
    public String citySeachInfoUrl = "https://geoapi.qweather.com/v2/city/lookup?key=" + KEY + "&location=";
    // 实况天气url
    public String getlocalWeatherUrl = "https://devapi.qweather.com/v7/weather/now?key=5e47b2143db54d4d8279c7c853b5f0b0&location=";
    // 3天气预报
    public String getWeatherReportUrl = "https://devapi.qweather.com/v7/weather/3d?key=5e47b2143db54d4d8279c7c853b5f0b0&location=";


    /**
     * 根据当前时间更新左上角日期
     */
    public void updateCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy 年 MM 月 dd 日");
        String format = LocalDate.now().format(formatter);
        System.out.println(format);
        top_date.setText(format);
    }

    /**
     * 初始化页面
     */
    public void initPage() {
        getCityByIp(getV4IP());
        initWeekDays();
        // 下拉框点击事件监听
        select_city.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {


            }
        });
        /*OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(citySeachInfoUrl + "北海")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.code());
                System.out.println(response.body().string());
            }
        });*/

    }

    /**
     * 通过ip获取城市
     *
     * @param ip
     */
    public void getCityByIp(String ip) {
        String url = "http://apis.juhe.cn/ip/ipNew?key=f9f633b66c12a2a3fa1494570c6e4857&ip=" + ip;
        System.out.println(url);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                getCityModel = new Gson().fromJson(s, GetCityModel.class);
//                System.out.println(getCityModel.getResult().getCity());
//                System.out.println(getCityModel.getResult().getProvince());

                getCityByIpSetPage(getCityModel.getResult().getCity()+"&adm="+getCityModel.getResult().getProvince());



            }
        });


    }


    /**
     * 获取本机的外网ip地址
     *
     * @return
     */
    public String getV4IP() {
        String ip = "";
        String chinaz = "http://ip.chinaz.com";

        StringBuilder inputLine = new StringBuilder();
        String read = "";
        URL url = null;
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        try {
            url = new URL(chinaz);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            while ((read = in.readLine()) != null) {
                inputLine.append(read + "\r\n");
            }
            //System.out.println(inputLine.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Pattern p = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
        Matcher m = p.matcher(inputLine.toString());
        if (m.find()) {
            String ipstr = m.group(1);
            ip = ipstr;
            //System.out.println(ipstr);
        }
        return ip;
    }


    /**
     * 初始化周数
     */
    public void initWeekDays() {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        int two, three, four, five;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        two = w + 1;
        three = w + 2;
        four = w + 3;
        five = w + 4;
        if (two > 6) two -= 7;
        if (three > 6) three -= 7;
        if (four > 6) four -= 7;
        if (five > 6) five -= 7;

//        weekDays[w] 周几
        mon_two.setText(weekDays[two]);
        mon_three.setText(weekDays[three]);
    }

    /**
     * 点击切换城市响应事件
     */
    public void click_city(MouseEvent mouseEvent) {
        changeInput();

    }

    /**
     * 点击搜索按钮响应事件
     *
     * @param mouseEvent
     */
    public void bt_click_city(MouseEvent mouseEvent) {
        //获取下拉输入框内容
        String in_text = select_city.getEditor().getText();
        getCityDateByName(in_text);
    }

    /**
     * 根据输入获取查询城市列表
     */
    public void getCityDateByName(String in_text) {
        System.out.println(":::::"+citySeachInfoUrl + in_text);

        //如果输入框为空，弹出提示框
        if (in_text.length() < 1) {
            JOptionPane.showMessageDialog(null, "请输入城市再点击搜索");
            return;
        }

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(citySeachInfoUrl + in_text)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                cityData = new Gson().fromJson(response.body().string(), SeachCityModel.class);

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        //更新下拉框
                        updateCombobox(cityData);
                    }
                });

            }
        });
    }

    /**
     * 通过本机ip获取城市 再根据获取的城市更新ui
     * @param in_text
     */
    public void getCityByIpSetPage(String in_text) {
        System.out.println(":::::"+citySeachInfoUrl + in_text);

        //如果输入框为空，弹出提示框
        if (in_text.length() < 1) {
            JOptionPane.showMessageDialog(null, "请输入城市再点击搜索");
            return;
        }

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(citySeachInfoUrl + in_text)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                cityData = new Gson().fromJson(response.body().string(), SeachCityModel.class);
                getOtherWeather(cityData.getLocation().get(0).getId());
                getNowWeatherDate(cityData.getLocation().get(0).getId());

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        top_city.setText(cityData.getLocation().get(0).getName() + "[切换城市]"); //城市名
                    }
                });

            }
        });
    }

    /**
     * 根据搜索返回的城市数据 更新下拉框
     *
     * @param cityData
     */
    public void updateCombobox(SeachCityModel cityData) {
        ArrayList<String> cityNames = new ArrayList<>();
        if (cityData.getCode().equals("200")) {
            for (SeachCityModel.LocationBean city : cityData.getLocation()) {
                cityNames.add(city.getCountry() + "--" + city.getAdm1() + "--" + city.getAdm2() + "--" + city.getName());
            }
            select_city.setItems(FXCollections.observableArrayList(cityNames));
        } else {
            select_city.getEditor().setText("暂无搜索结果");

//            select_city.setItems(FXCollections.observableArrayList(""));
        }
    }

    /**
     * 确定按钮点击事件
     *
     * @param mouseEvent
     */
    public void bt_click_ok(MouseEvent mouseEvent) {
        // 选择中的序号
        int selectIndex = select_city.getSelectionModel().getSelectedIndex();
        // 如果文本为空
        if (select_city.getEditor().getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "请输入内容后再进行此操作");
            return;
        }

        // 如果没有选择，默认第一个
        if (selectIndex == -1) {
            getCityDateByName(select_city.getEditor().getText());
            selectIndex = 0;
        }
        // 根据下拉框选中的序号获取城市id


        getNowWeatherDate(cityData.getLocation().get(selectIndex).getId());
        getOtherWeather(cityData.getLocation().get(selectIndex).getId());

        // 根据选中的城市，获取其城市名 设置右上角名字标签
        top_city.setText(cityData.getLocation().get(selectIndex).getName() + "[切换城市]"); //城市名

        exitInput();


    }

    /**
     * 更新界面
     */
    public void updateOtherPage(ThreeDayWeatherModel threeDayWeatherModel) {
        List<ThreeDayWeatherModel.DailyBean> list = threeDayWeatherModel.getDaily();
        one_temp.setText(list.get(0).getTempMin() + "°C~" + list.get(0).getTempMax());
        two_temp.setText(list.get(1).getTempMin() + "°C~" + list.get(1).getTempMax());
        three_temp.setText(list.get(2).getTempMin() + "°C~" + list.get(2).getTempMax());
        one_wea.setText(list.get(0).getTextDay() + "转" + list.get(0).getTextNight());
        two_wea.setText(list.get(1).getTextDay() + "转" + list.get(1).getTextNight());
        three_wea.setText(list.get(2).getTextDay() + "转" + list.get(2).getTextNight());
        one_wind.setText(list.get(0).getWindDirDay() + list.get(0).getWindScaleDay() + "级");
        two_wind.setText(list.get(1).getWindDirDay() + list.get(1).getWindScaleDay() + "级");
        three_wind.setText(list.get(2).getWindDirDay() + list.get(2).getWindScaleDay() + "级");
        date_two.setText(list.get(1).getFxDate());
        date_three.setText(list.get(2).getFxDate());
        img_two.setImage(new Image("icon/" + list.get(1).getIconDay() + ".png"));
        img_three.setImage(new Image("icon/" + list.get(2).getIconDay() + ".png"));


    }

    /**
     * 根据id 获取实况天气数据 并调用实况天气更新方法
     *
     * @param locationId
     */
    public void getNowWeatherDate(String locationId) {
        System.out.println("::::"+ getlocalWeatherUrl+locationId);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(getlocalWeatherUrl + locationId)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                nowWeatherList = new Gson().fromJson(s, NowWeatherModel.class);
                System.out.println(s);

                System.out.println(nowWeatherList.getNow().getIcon());
                System.out.println("更新");

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("更新ui");
                        updateNowPage(nowWeatherList);

                    }
                });

            }
        });
    }

    /**
     * 根据获取的现在天气的数据进行label赋值
     *
     * @param nowWeather
     */
    public void updateNowPage(NowWeatherModel nowWeather) {
        System.out.println(nowWeather.toString());
        //当前实况天气
        te_current.setText(nowWeather.getNow().getTemp());
        // 实况天气文本描述
        te_current_text.setText(nowWeather.getNow().getText() + "[实况]");
        System.out.println(nowWeather.getNow().getText());

        //实况天气图片
        String imgPath = "icon/" + nowWeather.getNow().getIcon() + ".png";
        System.out.println(imgPath);

        img_one.setImage(new Image("/icon/104.png"));


    }

    /**
     * 获取三天天气预报
     *
     * @param locationId 传入locationID
     */
    public void getOtherWeather(String locationId) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(getWeatherReportUrl + locationId)

                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                ThreeDayWeatherModel threeDayWeathers = new Gson().fromJson(string, ThreeDayWeatherModel.class);
                System.out.println("-----------------");
                System.out.println(string);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        updateOtherPage(threeDayWeathers);


                    }
                });
            }
        });
    }

    /**
     * 根据返回的天气描述匹配对应本地图片
     *
     * @param text
     * @return
     */
    public String matchIcon(String text) {

        switch (text) {
            case "晴":
                return "qing";
            case "霾":
                return "mai";
            case "小雨":
                return "xiaoyu";
            case "中雨":
                return "zhongyu";
            case "大雨":
                return "dayu";
            case "多云":
                return "duoyun";

        }

        return "";
    }

    /**
     * 切换到输入模式
     */
    public void changeInput() {
//        top_city.setVisible(false);
//        bt_ok.setVisible(true);
//        bt_city.setVisible(true);
//        select_city.setVisible(true);
        top_info.setVisible(false);
        top_input.setVisible(true);
    }

    /**
     * 退出输入模式
     */
    public void exitInput() {
//        top_city.setVisible(true);
//        bt_ok.setVisible(false);
//        bt_city.setVisible(false);
//        select_city.setVisible(false);
        top_info.setVisible(true);
        top_input.setVisible(false);

    }


}
