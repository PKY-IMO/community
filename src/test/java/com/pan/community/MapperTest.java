package com.pan.community;

import com.pan.community.dao.DiscussPostMapper;
import com.pan.community.dao.LoginTicketMapper;
import com.pan.community.dao.MessageMapper;
import com.pan.community.dao.UserMapper;
import com.pan.community.entity.DiscussPost;
import com.pan.community.entity.LoginTicket;
import com.pan.community.entity.Message;
import com.pan.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Test
    public void testSelectUser(){
        User user = userMapper.selectById(101);
        System.out.println(user);
    }

    @Test
    public void selectPosts(){
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(149,0,10);
        for (DiscussPost dis:list) {
            System.out.println(dis);
        }
        int rows = discussPostMapper.selectDiscussPostRows(149);
        System.out.println(rows);
    }

    @Test
    public void testInsertLoginTicket() {
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(102);
        loginTicket.setTicket("abc");
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + 1000*60));
        loginTicketMapper.insertLoginTicket(loginTicket);
    }

    @Test
    public void testSelectLoginTicket(){
        LoginTicket loginTicket = loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket);
        loginTicketMapper.updateStatus("abc",1);
        loginTicket = loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket);
    }

    @Test
    public void testSelectLetters() {
        List<Message> list = messageMapper.selectConversations(111,0,20);
        for (Message message : list) {
            System.out.println(message.toString());
        }

        int c = messageMapper.selectConversationCount(111);
        System.out.println(c);

        list = messageMapper.selectLetters("111_112",0,10);
        for (Message message : list) {
            System.out.println(message.toString());
        }

        int count = messageMapper.selectLetterCount("111_112");
        System.out.println(count);

        int a = messageMapper.selectLetterUnreadCount(1,null);
        System.out.println(a);

    }


}
