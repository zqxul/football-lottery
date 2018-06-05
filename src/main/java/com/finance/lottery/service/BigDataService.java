package com.finance.lottery.service;

import com.finance.lottery.entity.BigDataPara;
import com.finance.lottery.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xuzhiqing
 * @Description:
 * @Date: 2018/6/4 14:16
 */
@Service
public class BigDataService {

    @Value("${lottery.bigdata.url}")
    private String bigDataUrl;

    @Value("${lottery.bigdata.dqdWebsite}")
    private String dqdWebsite;

    @Value("${server.ip}")
    private String serverIp;

    @Value("${lottery.bigdata.statListCssPath}")
    private String statListCssPath;

    @Value("${lottery.bigdata.statDetailCssPath}")
    private String statDetailCssPath;

//    public List<String> getMatchData(BigDataPara bigDataPara) {
//        List<String> elements = new ArrayList<>();
//        String url = bigDataUrl + "?competition=" + bigDataPara.getCompetition();
//        String responseHtml = HttpUtil.get(url);
//        Document document = Jsoup.parse(responseHtml);
//        Element statListElement = document.selectFirst(statListCssPath);
//        Element statDetailElement = document.selectFirst(statDetailCssPath);
//        if (statListElement != null) {
//            elements.add(statListElement.toString());
//        }
//        if (statDetailElement != null) {
//            elements.add(statDetailElement.toString());
//        }
//        return elements;
//    }

    /**
     * @Author: xuzhiqing
     * @Date: 2018/6/5 12:07
     * @Description: 获取球员数据
     * @param playerId
     */
    public String getPlayerData(String playerId) {
        String url = dqdWebsite + "player/" + playerId + ".html";
        String responseHtml = HttpUtil.get(url);
        Document document = Jsoup.parse(responseHtml);
        Element newMainElement = document.selectFirst(".newmain");
        Element newListElement = newMainElement.selectFirst(".news_list");
        newListElement.remove();
        Element moreInfoElement = newMainElement.selectFirst(".more_info_button");
        moreInfoElement.remove();
        String elementStr = newMainElement.toString().replaceAll("<a href=\"https://www.dongqiudi.com", "<a href=\"");
        return elementStr;
    }

    /**
     * @Author: xuzhiqing
     * @Date: 2018/6/5 12:06
     * @Description: 获取球队数据
     * @param teamId
     */
    public String getTeamData(String teamId) {
        String url = dqdWebsite + "team/" + teamId + ".html";
        String responseHtml = HttpUtil.get(url);
        Document document = Jsoup.parse(responseHtml);
        Element newMainElement = document.selectFirst(".newmain");
        Element newListElement = newMainElement.selectFirst(".news_list");
        newListElement.remove();
        String elementStr = newMainElement.toString().replaceAll("<a href=\"https://www.dongqiudi.com", "<a href=\"");
        return elementStr;
    }


    /**
     * @param bigDataPara
     * @Author: xuzhiqing
     * @Date: 2018/6/4 23:39
     * @Description: 获取大数据
     */
    public List<String> getMatchData(BigDataPara bigDataPara) {
        List<String> elements = new ArrayList<>();
        String url = bigDataUrl;
        if (StringUtils.isNotBlank(bigDataPara.getCompetition())) {
            url = url + "?competition=" + bigDataPara.getCompetition();
        }
        if (StringUtils.isNotBlank(bigDataPara.getType())) {
            url = url + "&type=" + bigDataPara.getType();
        }
        if (StringUtils.isNotBlank(bigDataPara.getSeason_id())) {
            url = url + "&season_id=" + bigDataPara.getSeason_id();
        }
        if (StringUtils.isNotBlank(bigDataPara.getRound_id())) {
            url = url + "&round_id=" + bigDataPara.getRound_id();
        }
        if (StringUtils.isNotBlank(bigDataPara.getGameweek())) {
            url = url + "&gameweek=" + bigDataPara.getGameweek();
        }
        if (StringUtils.isNotBlank(bigDataPara.getApp())) {
            url = url + "&app=" + bigDataPara.getApp();
        }
        if (StringUtils.isNotBlank(bigDataPara.getPlatform())) {
            url = url + "&platform=" + bigDataPara.getPlatform();
        }
        if (StringUtils.isNotBlank(bigDataPara.getVersion())) {
            url = url + "&version=" + bigDataPara.getVersion();
        }
        String responseHtml = HttpUtil.get(url);
        Document document = Jsoup.parse(responseHtml);
        Element statListElement = document.selectFirst(statListCssPath);
        Element statDetailElement = document.selectFirst(statDetailCssPath);
        if (statListElement != null) {
            elements.add(statListElement.toString());
        }
        if (statDetailElement != null) {
            String elementStr = statDetailElement.toString().replaceAll("<a href=\"https://www.dongqiudi.com", "<a href=\"");
            elements.add(elementStr);
        }
        return elements;
    }

}
