package com.bj.wechat.service;

import com.bj.wechat.entity.res.model.Article;
import com.bj.wechat.entity.res.msg.NewsMessage;
import com.bj.wechat.util.ExpressUtil;
import com.bj.wechat.util.MessageUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 图文消息处理类
 * @author 白健
 *
 */
public class NewsService {
    public static NewsMessage processRequest(Map<String, String> requestMap){
        // 创建图文消息
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setToUserName(requestMap.get("FromUserName"));
        newsMessage.setFromUserName(requestMap.get("ToUserName"));
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
        newsMessage.setFuncFlag(0);

        String content =  requestMap.get("Content");
        List<Article> articleList = new ArrayList<Article>();
        // 单图文消息
        if ("a".equals(content)) {
            Article article = new Article();
            article.setTitle("springboot 教程！");
            article.setDescription("springboot 教程！");
            //article.setPicUrl("http://3.bjweixintest.sinaapp.com/images/index.jpg");
            article.setUrl("https://blog.csdn.net/jianmingxie/column/info/34358");

            articleList.add(article);
            // 设置图文消息个数
            newsMessage.setArticleCount(articleList.size());
            // 设置图文消息包含的图文集合
            newsMessage.setArticles(articleList);
            // 将图文消息对象转换成 xml 字符串
        }
        // 单图文消息---不含图片
        else if ("b".equals(content)) {
            Article article = new Article();
            article.setTitle("微信公众帐号开发教程 Java 版");
            // 图文消息中可以使用 QQ 表情、符号表情
            article.setDescription("柳峰，80 后，" + ExpressUtil.emoji(0x1F6B9)
                    + "，微信公众帐号开发经验 4 个月。为帮助初学者入门，特 推出此系列连载教程，也希望借此机会认识更多同行！\n\n 目前已"
                    + "推出教程共 12 篇，包括接口 配置、消息封装、框架搭建、QQ 表情发送、符号表情发送等。\n\n 后期还计划推出一些实用功能 的"
                    + "开发讲解，例如：天气预报、周边搜索、聊天功能等。");
            // 将图片置为空
            article.setPicUrl("");
            article.setUrl("http://blog.csdn.net/lyq8479");
            articleList.add(article);
            newsMessage.setArticleCount(articleList.size());
            newsMessage.setArticles(articleList);
        }
        // 多图文消息
        else if ("c".equals(content)) {
            Article article1 = new Article();
            article1.setTitle("微信公众帐号开发教程\n 引言");
            article1.setDescription("");
            article1.setPicUrl("http://3.bjweixintest.sinaapp.com/images/index.jpg");
            article1.setUrl("http://blog.csdn.net/lyq8479/article/d etails/8937622");

            Article article2 = new Article();
            article2.setTitle("第 2 篇\n 微信公众帐号的类型");
            article2.setDescription("");
            article2.setPicUrl("http://3.bjweixintest.sinaapp.com/images/index.jpg");
            article2.setUrl("http://blog.csdn.net/lyq8479/article/d etails/8941577");

            Article article3 = new Article();
            article3.setTitle("第 3 篇\n 开发模式启用及接口配置");
            article3.setDescription("");
            article3.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8 479.jpg");
            article3.setUrl("http://blog.csdn.net/lyq8479/article/d etails/8944988");
            articleList.add(article1);
            articleList.add(article2);
            articleList.add(article3);
            newsMessage.setArticleCount(articleList.size());
            newsMessage.setArticles(articleList);
        }
        // 多图文消息---首条消息不含图片
        else if ("d".equals(content)) {
            Article article1 = new Article();
            article1.setTitle("微信公众帐号开发教程 Java 版");
            article1.setDescription("");
            // 将图片置为空
            article1.setPicUrl("");
            article1.setUrl("http://blog.csdn.net/lyq8479");

            Article article2 = new Article();
            article2.setTitle("第 4 篇\n 消息及消息处理工具的封装");
            article2.setDescription("");
            article2.setPicUrl("http://3.bjweixintest.sinaapp.com/images/index.jpg");
            article2.setUrl("http://blog.csdn.net/lyq8479/article/d etails/8949088");

            Article article3 = new Article();
            article3.setTitle("第 5 篇\n 各种消息的接收与响应");
            article3.setDescription("");
            article3.setPicUrl("http://3.bjweixintest.sinaapp.com/images/index.jpg");
            article3.setUrl("http://blog.csdn.net/lyq8479/article/d etails/8952173");

            Article article4 = new Article();
            article4.setTitle("第6篇\n文本消息的内容长度限制揭秘");
            article4.setDescription("");
            article4.setPicUrl("http://3.bjweixintest.sinaapp.com/images/index.jpg");
            article4.setUrl("http://blog.csdn.net/lyq8479/article/d etails/8967824");

            articleList.add(article1);
            articleList.add(article2);
            articleList.add(article3);
            articleList.add(article4);
            newsMessage.setArticleCount(articleList.size());
            newsMessage.setArticles(articleList);
        }
        // 多图文消息---最后一条消息不含图片
        else if ("e".equals(content)) {
            Article article1 = new Article();
            article1.setTitle("第 7 篇\n 文本消息中换行符的使用");
            article1.setDescription("");
            article1.setPicUrl("http://3.bjweixintest.sinaapp.com/images/index.jpg");
            article1.setUrl("http://blog.csdn.net/lyq8479/article/d etails/9141467");

            Article article2 = new Article();
            article2.setTitle("第 8 篇\n 文本消息中使用网页超链接");
            article2.setDescription("");
            article2.setPicUrl("http://3.bjweixintest.sinaapp.com/images/index.jpg");
            article2.setUrl("http://blog.csdn.net/lyq8479/article/d etails/9157455");

            Article article3 = new Article();
            article3.setTitle("如果觉得文章对你有所帮助，请通过博客留言 或关注微信公众帐号 xiaoqrobot 来支持柳峰！");
            article3.setDescription("");
            // 将图片置为空
            article3.setPicUrl("");
            article3.setUrl("http://blog.csdn.net/lyq8479");

            articleList.add(article1);
            articleList.add(article2);
            articleList.add(article3);
            newsMessage.setArticleCount(articleList.size());
            newsMessage.setArticles(articleList);
        }
        return newsMessage;
    }
    public static NewsMessage processRequest(Map<String, String> requestMap,String infolist){
      return processRequest(requestMap);
    }
}
